// vars/openshiftTasks.groovy
def loginOpenShift(String server, String token) {
    echo "Logging in to OpenShift..."
    sh """
        oc login ${server} --token=${token} --insecure-skip-tls-verify
    """
}

def deployToOpenShift(String deploymentPath) {
    echo "Deploying application to OpenShift..."
    sh """
        oc apply -f ${deploymentPath}/deployment.yaml

        # Expose the deployment
        oc expose deployment/ivolve --port=90 --name=ivolve-deploy
        oc expose service/ivolve-deploy --port=90 --name=ivolve-expose

        # Verify deployment
        oc get deployments
        oc get services
        oc get pods
    """
}
