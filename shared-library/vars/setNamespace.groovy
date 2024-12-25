// vars/setNamespace.groovy

def call(String branchName) {
    def namespace = ""

    if (branchName.contains("Dev")) {
        namespace = "dev"
    } else if (branchName.contains("Test")) {
        namespace = "test"
    } else if (branchName == "main" || branchName.contains("Prod")) {
        namespace = "prod"
    } else {
        error "Branch name '${branchName}' does not match any known namespace"
    }
    
    echo "Deploying to namespace: ${namespace}"
    return namespace
}
