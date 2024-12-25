# AWS Architecture: VPC with CloudWatch Monitoring and Alerts

## Table of Contents
1. [Architecture Overview](#architecture-overview)
2. [Prerequisites](#prerequisites)
3. [Steps to Implement](#steps-to-implement)
    - [Step 1: Create a VPC](#step-1-create-a-vpc)
    - [Step 2: Create a Subnet](#step-2-create-a-subnet)
    - [Step 3: Create a Security Group](#step-3-create-a-security-group)
    - [Step 4: Launch an EC2 Instance](#step-4-launch-an-ec2-instance)
    - [Step 5: Configure CloudWatch Monitoring](#step-5-configure-cloudwatch-monitoring)
    - [Step 6: Set Up SNS Topic and Alerts](#step-6-set-up-sns-topic-and-alerts)
    - [Step 7: Test Alerts](#step-7-test-alerts)
4. [Troubleshooting](#troubleshooting)
5. [Conclusion](#conclusion)

---

## Architecture Overview
This architecture demonstrates a Virtual Private Cloud (VPC) setup with:
- A subnet within the VPC.
- Security Group to manage instance access.
- EC2 instance deployed within the subnet.
- CloudWatch monitoring configured to trigger an SNS alert when CPU usage exceeds 70%.
- SNS topic integrated with an email notification system.

![image](https://github.com/user-attachments/assets/56c5d5af-4dd7-4628-97d2-7dbcc99294e2)


---

## Prerequisites
- AWS account with access to EC2, VPC, CloudWatch, and SNS services.
- AWS CLI configured on your local machine (optional for automation).

---

## Steps to Implement

### Step 1: Create a VPC
1. Navigate to the **VPC Dashboard** in the AWS Management Console.
2. Click **Create VPC**.
3. Configure:
   - **Name tag**: `MyVPC`
   - **IPv4 CIDR block**: `10.0.0.0/16`
   - Leave other options as default.
4. Click **Create VPC**.

### Step 2: Create a Subnet
1. In the **VPC Dashboard**, go to **Subnets**.
2. Click **Create Subnet**.
3. Configure:
   - **Name tag**: `MySubnet`
   - **VPC ID**: Select `MyVPC`.
   - **IPv4 CIDR block**: `10.0.0.0/24`.
4. Click **Create Subnet**.
