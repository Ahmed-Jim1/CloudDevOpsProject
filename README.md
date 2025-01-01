![ivolve-lg](https://github.com/user-attachments/assets/857233f7-0c08-4c2f-95d0-7b1e14342940)

# Final Project:
# Complete DevOps Workflow: Automating Java App Deployment with Terraform, Ansible, Jenkins Pipelines, AKS, and Continuous Sync via Argo CD.

1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [Troubleshooting Guidelines](#troubleshooting-guidelines)

---

## Project Overview
This project focuses on building a complete DevOps pipeline that incorporates containerization, infrastructure provisioning, AWS integration, configuration management, and continuous integration. The final deployment happens on an Azure Kubernetes Service (AKS) cluster using Jenkins pipelines.



<img width="3296" alt="final-elfinal" src="https://github.com/user-attachments/assets/727a6e64-7782-48d1-bd2d-08804e24c3a3" />

**Look at each directory README.md File**

---

## Technologies Used
- **Docker**: Containerizing the application.
- **Terraform**: Provisioning AWS infrastructure.
- **AWS Services**: VPC, EC2, S3, CloudWatch.
- **Ansible**: Configuring EC2 instances.
- **Jenkins**: Building CI/CD pipelines.
- **SonarQube**: Code quality analysis.
- **Argo-CD**: Manage k8's cluster.
- **Azure Kubernetes Service (AKS)**: Application deployment 
---

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/Ibrahim-Adell/FinalProjectCode
   cd FinalProjectCode
   ```
2. Run Terraform to provision AWS infrastructure:
   ```bash
   cd terraform
   terraform init
   terraform apply
   ```
3. Configure EC2 instances with Ansible:
   On your master machine setup your inventory and run this command 
   ```bash
   cd Ansible/master
   ansible-playbook -i inventory playbook.yml
   ```
   On your slave machine setup your inventory and run this command
   ```bash
   cd Ansible/slave
   ansible-playbook -i inventory playbook.yml
   ```
5. Configure Jenkins pipeline:
   - Add the `Jenkinsfile` to your Jenkins job.
   - Use shared libraries for pipeline stages.
6. Verify AKS Deployment:
   ```bash
   kubectl get pods
   kubectl get services
   ```

---

## Troubleshooting Guidelines
- **Terraform State Issues**:
  - Ensure S3 backend is correctly configured and accessible.
- **Ansible Playbook Failures**:
  - Verify SSH access to EC2 instances.
  - Check package manager dependencies.
- **Jenkins Pipeline Failures**:
  - Check the Jenkins logs and shared library configurations.
- **AKS Deployment Issues**:
  - Verify `kubectl` CLI is configured correctly.
  - Check the deployment YAML syntax.

---

## Conclusion
This project delivers a complete DevOps pipeline integrating Docker, Terraform, Ansible, Jenkins, and AWS. The final application is deployed on Azure Kubernetes Service (AKS), ensuring scalability and maintainability.
