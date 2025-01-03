def call(String namespace) {
    // Use credentials to fetch the Kubernetes kubeconfig
    withCredentials([file(credentialsId: 'aks', variable: 'KUBECONFIG')]) {
        // Deploy to Kubernetes using the provided namespace
        sh """
            export KUBECONFIG=${KUBECONFIG}
            kubectl apply -f ivolve-app.yaml --namespace=${namespace}
            sleep 5
            kubectl get pods --namespace=${namespace}
            kubectl get svc --namespace=${namespace}
        """
    }
}
