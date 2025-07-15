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
                      -Dsonar.host.url=http:http://3.86.244.52:9000 \
                      -Dsonar.login=ff2dee9735ddb4b3e9ccc0ed3c6e578a61495fea
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
