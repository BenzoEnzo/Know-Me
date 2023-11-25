pipeline {
    agent any
    tools {
    maven 'maven'
    }
    stages {
           stage('Build and Test km-service-dto') {
               steps {
                   dir('km-service-dto') {
                       sh 'mvn clean install'
                   }
               }
           }
        stage('Build and Test know-me-profile') {
            steps {
                dir('know-me-profile') {
                    sh 'mvn clean install'
                }
            }
        }
    }
    post {
        success {
            echo 'Build successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
