def call(String repoUrl, String branch = 'main') {
    stage('Git Checkout') {
        steps {
            script {
                echo 'Cloning code from Git repository...'
            }
            git branch: branch, url: repoUrl
        }
    }
}
