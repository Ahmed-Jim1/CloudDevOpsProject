# AKS Setup with Argo CD

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Steps to Implement](#steps-to-implement)
    - [Step 1: Create AKS Cluster](#step-1-create-aks-cluster)
    - [Step 2: Install Argo CD](#step-2-install-argo-cd)
    - [Step 3: Patch Service and Access Argo CD](#step-3-patch-service-and-access-argo-cd)
    - [Step 4: Add new project on Argo-CD ](#step-3-add-new-project)
4. [Troubleshooting](#troubleshooting)
5. [Conclusion](#conclusion)

---

## Overview
This guide describes the steps to set up an AKS cluster and install Argo CD for continuous deployment. It also includes the configuration of a LoadBalancer service to provide external access to the Argo CD server.

---

## Prerequisites
- Azure account with sufficient permissions to create resources.
- Azure CLI installed on your local machine.
- Helm and Kubernetes CLI (kubectl) installed.

---

## Steps to Implement

### Step 1: Create AKS Cluster
1. Log in to the Azure portal or use the Azure CLI.
2. Create a resource group for your AKS cluster:
   ```bash
   az group create --name jim-rg --location eastus
   ```
3. Create the AKS cluster:
   ```bash
   az aks create --resource-group jim-rg --name ivolveCluster --node-count 2 --enable-addons monitoring --generate-ssh-keys
   ```
4. Install the Kubernetes CLI (kubectl) and connect it to the AKS cluster:
   ```bash
   az aks get-credentials --resource-group jim-rg --name ivolveCluster
   ```
5. Verify the connection:
   ```bash
   kubectl get nodes
   ```
6. Create a Ubuntu VM and connect to it to manage your cluster.

**Validation Step:**
- Take a screenshot of the AKS cluster nodes and vm running :

![image](https://github.com/user-attachments/assets/fd173205-84c3-46c3-b945-4bae2c144f7c)


![image](https://github.com/user-attachments/assets/60dcdcf8-6b29-4ee9-87d8-cb2c33aa3f7d)


![image](https://github.com/user-attachments/assets/f2841f51-c316-444d-b386-c176508f8e82)



---

### Step 2: Install Argo CD
#### Create a Helm Umbrella Chart
1. Create a directory in your Git repository for the chart:
   ```bash
   mkdir -p charts/argo-cd
   ```
2. Create the `Chart.yaml` file:
   ```yaml
   apiVersion: v2
   name: argo-cd
   version: 1.0.0
   dependencies:
     - name: argo-cd
       version: 5.46.8
       repository: https://argoproj.github.io/argo-helm
   ```
3. Create the `values.yaml` file for the chart:
   ```yaml
   argo-cd:
     dex:
       enabled: false
     notifications:
       enabled: false
     applicationSet:
       enabled: false
     server:
       extraArgs:
         - --insecure
   ```
4. Add the Helm repository and update dependencies:
   ```bash
   helm repo add argo-cd https://argoproj.github.io/argo-helm
   helm dep update charts/argo-cd/
   ```
5. Commit and push the chart:
   ```bash
   git add charts/argo-cd
   git commit -m 'add argo-cd chart'
   git push
   ```

#### Install the Chart
1. Install the chart manually from your local machine:
   ```bash
   kubectl create namespace argo-cd
   helm install argo-cd charts/argo-cd/  -n argo-cd
   ```
2. Verify the deployment:
   ```bash
   kubectl get pods -n argo-cd
   ```
**Validation Step:**
- Take a screenshot of the Argo CD pods running in the AKS cluster:

![image](https://github.com/user-attachments/assets/818b73ad-a6a3-4a74-95cb-0140021d718d)

---

### Step 3: Patch Service and Access Argo CD
#### Patch the Argo CD Server Service
1. Change the service type to `LoadBalancer`:
   ```bash
   kubectl patch svc argo-cd-argocd-server -n argo-cd -p '{
     "spec": {
       "ports": [
         {
           "port": 8080,
           "targetPort": 443,
           "name": "https"
         },
         {
           "port": 80,
           "targetPort": 80,
           "name": "http"
         }
       ],
       "type": "LoadBalancer"
     }
   }'
   ```
2. Verify the update:
   ```bash
   kubectl get svc argo-cd-argocd-server -n argo-cd
   ```
   ![image](https://github.com/user-attachments/assets/43da64c0-af4a-4147-be21-278cd7b8effe)

   
   Ensure the `EXTERNAL-IP` field is populated once the LoadBalancer is provisioned.

#### Configure NSG Rules in Azure
1. Log in to the Azure portal.
2. Open the network security group (NSG) associated with the AKS cluster.
3. Add inbound rules to allow traffic on ports 80, 443, and 8080.

#### Access the Argo CD Server
1. Once the LoadBalancer is ready, access Argo CD using the external IP:
   - Open a browser and go to `http://<EXTERNAL-IP>` or `https://<EXTERNAL-IP>`.
2. Log in using the default credentials:
   ```bash
   kubectl get secret argocd-initial-admin-secret -n argo-cd -o jsonpath="{.data.password}" | base64 --decode
   ```

**Validation Step:**
- Take a screenshot of the Argo CD login page and dashboard.

![image](https://github.com/user-attachments/assets/87a6bdd2-6972-4165-8fe4-d7100a66899a)

---
### Step 4: Add new project on Argo-CD

#### create App in argo-cd
1. on the top of the dashboard click NEW APP
![image](https://github.com/user-attachments/assets/dd269a8a-e798-442a-96f0-4da2dade2e48)

2. configure your app
   ![image](https://github.com/user-attachments/assets/9c706c47-cfa6-4faf-9815-ba95c4be281c)
   
   ![image](https://github.com/user-attachments/assets/69724d56-1260-41eb-bdb1-3a1fa3c8b020)

   ![image](https://github.com/user-attachments/assets/77a12b96-5267-4e36-b0de-8db73ae61c4b)

**Validation Step:**
- Argo CD Dashboard with the app and app details .


  ![image](https://github.com/user-attachments/assets/b9186a40-0994-4245-8b52-4ea8b007986f)

  ![image](https://github.com/user-attachments/assets/92829adc-02ec-4957-ab49-21986fb3b912)




## Troubleshooting
- **Service Not Accessible**:
  - Verify the LoadBalancer's external IP and ensure NSG rules are configured correctly.
  - Check `kubectl describe svc argo-cd-argocd-server` for potential issues.

- **Helm Chart Fails to Install**:
  - Ensure the dependencies are correctly updated.
  - Check for connectivity issues with the Helm repository.

---

## Conclusion
This setup enables robust application deployment to AKS using Argo CD, accessible through a LoadBalancer. The configuration simplifies external access and provides a scalable, production-ready deployment pipeline.
