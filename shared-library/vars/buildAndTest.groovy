def call() {
    echo 'Building and testing the application...'  
    sh 'chmod +x ./gradlew'
    sh './gradlew build'
}
