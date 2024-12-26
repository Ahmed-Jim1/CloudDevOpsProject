# Continuous Integration with Jenkins

## Table of Contents
1. [Overview](#overview)
2. [Prerequisites](#prerequisites)
3. [Steps to Implement](#steps-to-implement)
    - [Step 1: Configure the Jenkinsfile](#step-1-configure-the-jenkinsfile)
    - [Step 2: Create the Shared Jenkins Library](#step-2-create-the-shared-jenkins-library)
    - [Step 3: Set Up Jenkins Slave](#step-3-set-up-jenkins-slave)
    - [Step 4: Test and Execute the Pipeline](#step-4-test-and-execute-the-pipeline)
4. [Troubleshooting](#troubleshooting)
5. [Conclusion](#conclusion)

---

## Overview
This section details how to set up a Jenkins pipeline for continuous integration (CI) using a Jenkinsfile and a shared library. The pipeline includes the following stages:
- **Git Checkout**
- **Unit Test**
- **Build JAR**
- **SonarQube Test**
- **Build Image**
- **Push Image to Registry**
- **Deploy on AKS (Azure Kubernetes Service)**

The implementation utilizes:
- Shared Jenkins Library for reusable pipeline logic.
- Jenkins slave to execute builds and tests.

![File Structure](./image.png) 

---

## Prerequisites
- Jenkins server with master and slave nodes configured.
- Docker, Kubernetes CLI, and SonarQube installed on the Jenkins slave.
- A Git repository to store the Jenkinsfile and shared library.
- AKS cluster set up and accessible from Jenkins.
- SonarQube server configured with a project and token.
- Credentials for Docker Registry, Git, and SonarQube added to Jenkins.

---

## Steps to Implement

### Step 1: Configure the Jenkinsfile
1. Create a `Jenkinsfile` in the root of your repository.
2. Define the pipeline stages:
   - Git Checkout
   - Unit Test
   - Build JAR
   - SonarQube Test
   - Build Image
   - Push Image to Registry
   - Deploy on AKS
3. Reference the shared library functions for each stage.
4. Validate the syntax of the Jenkinsfile using Jenkins' pipeline syntax validator.
5. Commit the `Jenkinsfile` to your repository.
6. **Validation Step:** screenshot of the pipeline running in Jenkins and all stages passing.

![image](https://github.com/user-attachments/assets/2307b055-7025-47b6-a97d-90b63f968ee2)

### Step 2: Create the Shared Jenkins Library
1. Create a folder structure as shown:
   ```
   shared-library/
       vars/
           buildAndTest.groovy
           checkoutPipeline.groovy
           deployToKubernetes.groovy
           dockerTasks.groovy
           sonarQubeAnalysis.groovy
   ```
2. Add Groovy scripts for each function:
   - `checkoutPipeline.groovy`: Handles Git checkout.
   - `buildAndTest.groovy`: Executes unit tests and builds the JAR.
   - `sonarQubeAnalysis.groovy`: Performs SonarQube analysis.
   - `dockerTasks.groovy`: Builds and pushes Docker images.
   - `deployToKubernetes.groovy`: Deploys the application to AKS.
3. Commit the shared library to the repository.
4. Add the shared library to Jenkins by navigating to **Manage Jenkins > Configure System > Global Pipeline Libraries** and defining the library name and repository URL.
5. **Validation Step:**  screenshot of the shared library configuration in Jenkins.
![image](https://github.com/user-attachments/assets/b54a633a-a462-4dc7-add1-6ff84092f01b)

### Step 3: Set Up Jenkins Slave
1. Configure a Jenkins slave node with:
   - Docker and Kubernetes CLI installed.
   - SonarQube scanner.
2. Link the slave to the Jenkins master.
3. Verify the slave is active in Jenkins.
4. **Validation Step:** screenshot of the Jenkins slave node configuration and its status as "Online."
![image](https://github.com/user-attachments/assets/00f37b3b-767c-4c21-b6c9-1ff931636903)

### Step 4: Configure SonarQube in Jenkins
1. Install the **SonarQube Scanner** plugin in Jenkins.
2. Navigate to **Manage Jenkins > Configure System > SonarQube Servers** and add the SonarQube server details and authentication token.
3. Configure the SonarQube scanner executable in **Manage Jenkins > Global Tool Configuration.**
4. Ensure the `sonarQubeAnalysis.groovy` script references the correct SonarQube project key.
5. **Validation Step:** screenshot of the SonarQube project configuration and analysis result in Jenkins.
![image](https://github.com/user-attachments/assets/1ba38b7d-3b84-48af-8024-fd253b746a28)


### Step 5: Test and Execute the Pipeline
1. Open the Jenkins dashboard.
2. Create a new pipeline project and link it to your Git repository.
3. Execute the pipeline and monitor the stages.
4. Verify that each stage completes successfully:
   - Code is checked out.
   - Unit tests pass.
   - JAR is built.
   - SonarQube analysis runs without issues.
   - Docker image is built and pushed.
   - Application is deployed to AKS.
5. **Validation Step:**  screenshots of:
   - Last build from Jenkins pipeline.
     ![image](https://github.com/user-attachments/assets/ccf25956-1e3d-4e9b-9e6a-413b01c3a033)
   - Successful Docker image push to the registry.
     ![image](https://github.com/user-attachments/assets/e423e435-12b6-47ab-97c1-91fbd27053c1)
   - Kubernetes deployment running in AKS.
     ![image](https://github.com/user-attachments/assets/61c46a3c-8e3c-43f9-9de1-cee4a45d1573)
     ![image](https://github.com/user-attachments/assets/3466fdad-c3db-44de-b4d8-c005a4670254)

---

## Troubleshooting
- **Pipeline fails at Git Checkout:** Ensure the repository URL and credentials are correct.
- **SonarQube issues:** Check the configuration of the SonarQube server and the scanner on the Jenkins slave.
- **Docker build errors:** Verify the Dockerfile and permissions for the registry.
- **Kubernetes deployment issues:** Confirm the kubeconfig is correctly set up on the slave.

---

## Conclusion
This setup enables a robust CI pipeline with reusable components via a shared Jenkins library. The pipeline stages ensure code quality, automated testing, and seamless deployment to AKS. By leveraging Jenkins slaves, the workload is distributed effectively, enhancing performance and scalability.
