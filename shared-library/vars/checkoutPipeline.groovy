// vars/gitCheckout.groovy
def call(String repoUrl, String branch) {
    
    git branch: branch, url: repoUrl
    
}
