def call(String repoName, String dockerTag, String email) {
    script {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
            withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                sh "git config user.email '${email}'" 
                sh "git config user.name '${GIT_USERNAME}'" 
                sh "sed -i 's+ahmedmahmood44/ivolve-app.*+ahmedmahmood44/ivolve-app:${dockerTag}+g' deployment.yaml" 
                sh "git add deployment.yaml"
                sh "git commit -m 'Updated deployment manifest with Docker tag: ${dockerTag} (Build: ${env.BUILD_NUMBER})'"
                sh """
                    git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/${GIT_USERNAME}/${repoName}.git HEAD:main
                """
            }
        }
    }
}

