![ivolve-lg](https://github.com/user-attachments/assets/857233f7-0c08-4c2f-95d0-7b1e14342940)

# Final Project: Infrastructure Automation and Java App Deployment Using Terraform, Ansible, CI/CD with Jenkins and AKS Cluster

## Table of Contents
1. [Project Overview](#project-overview)
2. [Technologies Used](#technologies-used)
3. [Tasks and Deliverables](#tasks-and-deliverables)
    - [Containerization with Docker](#1-containerization-with-docker)
    - [Infrastructure Provisioning with Terraform](#2-infrastructure-provisioning-with-terraform)
    - [AWS Integration](#3-aws-integration)
    - [Configuration Management with Ansible](#4-configuration-management-with-ansible)
    - [Continuous Integration with Jenkins](#5-continuous-integration-with-jenkins)
4. [Architecture Overview](#architecture-overview)
5. [Setup Instructions](#setup-instructions)
6. [Troubleshooting Guidelines](#troubleshooting-guidelines)

---

## Project Overview
This project focuses on building a complete DevOps pipeline that incorporates containerization, infrastructure provisioning, AWS integration, configuration management, and continuous integration. The final deployment happens on an Azure Kubernetes Service (AKS) cluster using Jenkins pipelines.



![image](https://github.com/user-attachments/assets/468b0633-5281-4d83-bbe8-213d459c874a)



---

## Technologies Used
- **Docker**: Containerizing the application.
- **Terraform**: Provisioning AWS infrastructure.
- **AWS Services**: VPC, EC2, S3, CloudWatch.
- **Ansible**: Configuring EC2 instances.
- **Jenkins**: Building CI/CD pipelines.
- **SonarQube**: Code quality analysis.
- **Azure Kubernetes Service (AKS)**: Application deployment.

---

## Tasks and Deliverables

### 1. Containerization with Docker
- **Task**:
    - Deliver a Dockerfile for building the application image.
    - Source code is available in the following repository: [FinalProjectCode](https://github.com/Ibrahim-Adell/FinalProjectCode)
- **Deliverables**:
    - Dockerfile is committed to the repository.

### 2. Infrastructure Provisioning with Terraform
- **Task**:
    - Deliver Terraform scripts to provision the following AWS resources:
        - VPC, Subnets, Security Groups.
        - EC2 instances for application deployment.
    - Use **Terraform Modules** for better code reusability.
- **Deliverables**:
    - Terraform scripts are committed to the repository.

**Key Features**:
- **VPC** with public and private subnets.
- **Security Groups** to allow SSH and application access.
- **EC2 Instance** for hosting the application.
---

### 3. AWS Integration
- **Task**:
    - Integrate the following AWS services:
        - **S3** as the Terraform Backend to store the Terraform state.
        - **CloudWatch** for monitoring infrastructure metrics.
- **Deliverables**:
    - Instructions for AWS integration in the Terraform code.

---

### 4. Configuration Management with Ansible
- **Task**:
    - Deliver Ansible playbooks for configuring the EC2 instance:
        - Install required packages: Git, Docker, Java, Jenkins, and SonarQube.
        - Set up necessary environment variables.
    - Use **Ansible Roles** for modular playbooks.
- **Deliverables**:
    - Ansible playbooks committed to the repository.

---

### 5. Continuous Integration with Jenkins
- **Task**:
    - Deliver a Jenkins pipeline with the following pipeline stages:
        1. Git Checkout
        2. Unit Test
        3. Build JAR
        4. SonarQube Test
        5. Build Docker Image
        6. Push Image to Registry
        7. Deploy on AKS
    - Use Jenkins **shared libraries** and a Jenkins **slave** node.
- **Deliverables**:
    - Jenkins pipeline configuration committed to `Jenkinsfile`.
---

### 6. Documentation
- **Task**:
    - Provide comprehensive documentation for:
        - Setup instructions.
        - Architecture overview.
        - Troubleshooting guidelines.
- **Deliverables**:
    - Documentation is available in the repository.

---

## Architecture Overview
This project implements the following architecture:
- **Docker** for application containerization.
- **Terraform** provisions AWS infrastructure (VPC, EC2, Security Groups).
- **Ansible** automates EC2 instance configurations.
- **Jenkins** CI/CD pipeline automates testing, image creation, and deployment.
- **SonarQube** ensures code quality.
- **Azure Kubernetes Service (AKS)** for final application deployment.

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
