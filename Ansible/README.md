# Ansible Roles for EC2 Instance Configuration

## Table of Contents
1. [Task Overview](#Task-overview)
2. [Directory Structure](#directory-structure)
3. [Roles Explanation](#roles-explanation)
    - [Master Node Roles](#master-node-roles)
    - [Slave Node Roles](#slave-node-roles)
4. [Deliverables](#deliverables)
5. [Conclusion](#conclusion)

---

## Task Overview
This project utilizes Ansible playbooks and roles to automate the configuration of EC2 instances. The goal is to install and configure the necessary packages and services required for the environment, including Jenkins, Docker, SonarQube, and other dependencies.

The configuration is divided into two nodes:
- **Master Node**: Configures Jenkins and its dependencies.
- **Slave Node**: Configures Docker, SonarQube, Kubernetes CLI (`kubectl`), and other essential packages.

---

## Directory Structure
The directory structure is organized as follows:

```
master
├── Roles
│   ├── ansible.cfg
│   ├── inventory
│   ├── Jenkins
│   │   └── tasks
│   │       └── main.yml
│   └── playbook.yml

slave
├── Roles
│   ├── ansible.cfg
│   ├── Docker
│   │   ├── handlers
│   │   │   └── main.yml
│   │   ├── tasks
│   │   │   └── main.yml
│   │   └── vars
│   │       └── main.yml
│   ├── Kubectl
│   │   └── tasks
│   │       └── main.yml
│   ├── Packages
│   │   └── tasks
│   │       └── main.yml
│   ├── Sonarqube
│   │   ├── handlers
│   │   │   └── main.yml
│   │   ├── tasks
│   │   │   └── main.yml
│   │   └── vars
│   │       └── main.yml
│   └── playbook.yml
```

---

## Roles Explanation

### Master Node Roles
1. **Jenkins**
   - **Location**: `master/Roles/Jenkins/tasks/main.yml`
   - **Purpose**: This role installs and configures Jenkins on the master node. It ensures Jenkins is installed with all necessary plugins and dependencies.
   - **Key Tasks**:
     - Install Jenkins packages.
     - Configure Jenkins service.
     - Set up environment variables for Jenkins.

---

### Slave Node Roles
1. **Docker**
   - **Location**: `slave/Roles/Docker`
   - **Handlers**: Located in `handlers/main.yml` for managing Docker service restarts.
   - **Tasks**: Located in `tasks/main.yml` for installing and configuring Docker.
   - **Vars**: Located in `vars/main.yml` for defining Docker-specific variables.
   - **Purpose**: This role ensures Docker is installed and configured on the slave node.

2. **Kubectl**
   - **Location**: `slave/Roles/Kubectl/tasks/main.yml`
   - **Purpose**: Installs the Kubernetes CLI (`kubectl`) for managing AKS clusters.
   - **Key Tasks**:
     - Download and install `kubectl`.
     - Configure `kubectl` to access the cluster.

3. **Packages**
   - **Location**: `slave/Roles/Packages/tasks/main.yml`
   - **Purpose**: Installs general-purpose packages such as Git, Java, and other dependencies required by the environment.
   - **Key Tasks**:
     - Install system-wide packages.
     - Verify installations.

4. **Sonarqube**
   - **Location**: `slave/Roles/Sonarqube`
   - **Handlers**: Located in `handlers/main.yml` for managing SonarQube service restarts.
   - **Tasks**: Located in `tasks/main.yml` for installing and configuring SonarQube.
   - **Vars**: Located in `vars/main.yml` for defining SonarQube-specific variables.
   - **Purpose**: This role automates the installation and configuration of SonarQube for code quality analysis.

---

## Deliverables
- The Ansible playbooks and roles are committed to the repository.
- The roles are modular and reusable for various configurations.
- The `playbook.yml` files in both the `master` and `slave` directories orchestrate the execution of the roles.

---

## Conclusion
This structure ensures a clear separation of responsibilities, making it easier to manage and scale the configuration process. Each role is designed to handle specific tasks, ensuring modularity and reusability for future projects.
