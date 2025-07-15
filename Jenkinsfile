pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
        jdk 'JDK 17'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        stage('Package Docker Image') {
            steps {
                sh 'docker build -t furit-shop .'
            }
        }
    }
}
