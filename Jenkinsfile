pipeline {
    agent any
    environment {
        SONARQUBE_ENV = 'SonarQube'            
        SONAR_TOKEN = credentials('sonar-token2') 
    }
    tools {
        maven 'maven 3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(SONARQUBE_ENV) {
                    sh """
                    mvn sonar:sonar \
                      -Dsonar.projectKey=fruit-app \
                      -Dsonar.host.url=http://54.175.122.140:9000 \
                      -Dsonar.login=$SONAR_TOKEN
                    """
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
}
