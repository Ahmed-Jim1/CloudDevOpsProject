// vars/deployToKubernetes.groovy

def call(String namespace) {
    // Use credentials to fetch the Kubernetes kubeconfig
    withCredentials([file(credentialsId: 'aks', variable: 'KUBECONFIG')]) {
        // Deploy to Kubernetes using the provided namespace
        sh """
            export KUBECONFIG=${KUBECONFIG}
            kubectl create deployment ivolve --image=docker.io/ahmedmahmood44/ivolve:latest --namespace=${namespace}
            kubectl get pods --namespace=${namespace}
        """
    }
}

