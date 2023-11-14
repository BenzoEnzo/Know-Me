pipeline {
    agent any
    tools {
    maven 'maven'
    }
    stages {
           stage('Build and Test km-service-dto') {
               steps {
                   dir('km-service-dto') {
                       sh 'mvn clean package'
                   }
               }
           }
        stage('Build and Test km-kafka-centrala') {
            steps {
                dir('km-kafka-centrala') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Build and Test know-me-socket') {
            steps {
                dir('know-me-socket') {
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
        stage('Build and Test km-server') {
            steps {
                dir('km-server') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Build and Test know-me-uploader') {
            steps {
                dir('know-me-uploader') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Build km-browser-gui') {
            steps {
                dir('km-browser-gui') {
                    sh 'mvn clean install'
                    sh 'npm install'
                    sh 'npm run build'
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
