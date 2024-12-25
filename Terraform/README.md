# AWS Architecture: VPC with CloudWatch Monitoring and Alerts using Terraform

## Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Prerequisites](#prerequisites)
3. [Steps to Implement](#steps-to-implement)
    - [Step 1: Write Terraform Configuration Files](#step-1-write-terraform-configuration-files)
    - [Step 2: Initialize Terraform](#step-2-initialize-terraform)
    - [Step 3: Apply Terraform Configuration](#step-3-apply-terraform-configuration)
    - [Step 4: Verify the Setup](#step-4-verify-the-setup)
4. [Troubleshooting](#troubleshooting)
5. [Conclusion](#conclusion)

---

## Architecture Overview
This architecture demonstrates a Virtual Private Cloud (VPC) setup using Terraform with:
- A subnet within the VPC.
- Security Group to manage instance access.
- EC2 instance deployed within the subnet.
- CloudWatch monitoring configured to trigger an SNS alert when CPU usage exceeds 70%.
- SNS topic integrated with an email notification system.

![image](https://github.com/user-attachments/assets/d15c2631-b5c8-4629-b917-ee35dabc6b1a)

---

## Prerequisites
- AWS account with access to EC2, VPC, CloudWatch, and SNS services.
- Terraform installed on your local machine.
- IAM credentials with permissions to manage AWS resources.

---

## Steps to Implement

### Step 1: Write Terraform Configuration Files
1. **Create a Terraform file** (e.g., `main.tf`) with the following configurations:
   - **VPC**: Define a VPC resource with CIDR block `10.0.0.0/16`.
   - **Subnet**: Define a subnet resource within the VPC with CIDR block `10.0.0.0/24`.
   - **Security Group**: Create a security group allowing inbound SSH and HTTP traffic.
   - **EC2 Instance**: Provision an EC2 instance within the subnet and associate it with the security group.
   - **CloudWatch**: Configure a CloudWatch metric alarm to monitor CPU utilization and trigger alerts.
   - **SNS Topic**: Create an SNS topic and subscribe an email endpoint for notifications.

### Step 2: Initialize Terraform
1. Open a terminal and navigate to the directory containing the Terraform files.
2. Run `terraform init` to initialize the Terraform working directory and download required providers.

### Step 3: Apply Terraform Configuration
1. Run `terraform plan` to preview the changes Terraform will make.
2. Execute `terraform apply` and confirm by typing `yes` to create the infrastructure.
3. Wait for Terraform to provision the resources.

### Step 4: Verify the Setup
1. Log in to the AWS Management Console.
2. Navigate to the **VPC Dashboard** to verify the VPC and subnet creation.
3. Go to the **EC2 Dashboard** to confirm the instance is running.
4. Check **CloudWatch Alarms** for the metric alarm configuration.
5. Verify the **SNS Topic** and ensure the email subscription is active.

---

## Troubleshooting
- Ensure your AWS credentials are correctly configured.
- Check for any syntax errors in the Terraform files.
- Verify that the email subscription for the SNS topic is confirmed.
- Review logs in the AWS Management Console if resources fail to provision.

---

## Conclusion
By completing this task, you have:
- Created a VPC, subnet, and security group using Terraform.
- Deployed an EC2 instance within the VPC.
- Configured CloudWatch monitoring and SNS alerts for proactive notifications.

This approach ensures a scalable and automated deployment of AWS infrastructure using Terraform.
