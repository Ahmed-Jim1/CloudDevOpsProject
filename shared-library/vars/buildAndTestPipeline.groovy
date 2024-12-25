def call(String gradlePath = '/opt/gradle/latest/bin') {
    environment {
        GRADLE_HOME = '/opt/gradle/latest'
        PATH = "${gradlePath}:${env.PATH}"
    }

    stage('Prepare Gradle') {
        steps {
            script {
                echo 'Setting permissions for gradlew script...'
            }
            sh 'chmod +x ./gradlew'
        }
    }

    stage('Unit Test') {
        steps {
            script {
                echo 'Running Unit Tests...'
            }
            sh './gradlew test'
        }
    }

    stage('Build JAR') {
        steps {
            script {
                echo 'Building JAR file...'
            }
            sh './gradlew build'
        }
    }
}
