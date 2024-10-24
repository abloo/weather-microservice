pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = "weather-backend"
        REGISTRY_URL = "manvendra183"
        REGISTRY_CREDENTIALS = "docker-credentials-id"
        MAVEN_HOME = tool('Maven_3_9_6') 
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }
    
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Enter the branch name')
    }

    tools {
        maven 'Maven_3_9_6'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH_NAME}",
                    url: 'https://github.com/abloo/weather-microservice.git'
            }
        }

        stage("Build Maven Project") {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run JUnit Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage("Build Docker Image") {
            steps {
                bat 'docker build -t weather-backend .'
            }
        }

        stage('Push Docker Image to Registry') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: REGISTRY_CREDENTIALS, passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                        bat 'docker login -u %USERNAME% -p %PASSWORD%'
                    }
                    bat 'docker tag weather-backend %REGISTRY_URL%/weather-backend:latest'
                    bat 'docker push %REGISTRY_URL%/weather-backend:latest'
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}