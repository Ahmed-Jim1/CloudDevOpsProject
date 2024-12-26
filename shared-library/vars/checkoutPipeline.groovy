// vars/gitCheckout.groovy
def call(String repoUrl, String branch) {
    echo "Cloning code from Git repository: ${repoUrl} (Branch: ${branch})"
    git branch: branch, url: repoUrl
}
