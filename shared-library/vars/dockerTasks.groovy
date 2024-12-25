// vars/dockerTasks.groovy
def buildDockerImage(String imageName, String buildNumber) {
    echo "Building Docker image: ${imageName}:${buildNumber}..."
    sh """
        docker build -t ${imageName}:${buildNumber} .

    """
}

def dockerLogin(String username, String password) {
    echo "Logging in to Docker Hub..."
    sh """
        echo "${password}" | docker login -u "${username}" --password-stdin

    """
}

def pushDockerImage(String imageName, String buildNumber, String repoName) {
    echo "Tagging and pushing Docker image to Docker Hub..."
    sh """
        docker tag ${imageName}:${buildNumber} ${repoName}/${imageName}:latest
        docker push ${repoName}/${imageName}:latest

    """
}

def dockerCleanup() {
    echo "Cleaning up Docker images..."
    sh """
        docker logout
        docker image prune -f
        
    """
}
