pipeline{
    agent any

    environment {
        DOCKER_IMAGE = "weather-backend"   /* Name of the Docker image */
        REGISTRY_URL = "manvendra183" /* Docker registry */
        REGISTRY_CREDENTIALS = "docker-credentials-id" /* Docker credentials */
        MAVEN_HOME = tool('Maven_3_9_6') 
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }
     parameters {
            string(name: 'BRANCH_NAME', defaultValue: 'main', description: 'Enter the branch name')
        }
    tools{
        maven 'Maven_3_9_6'
    }
    stages{
        stage("Build maven project"){
            steps{
                checkout scmGit(branches: [[name: "*/${params.BRANCH_NAME}"]], extensions: [], userRemoteConfigs: [[url: 'https://github.com/abloo/weather-microservice.git']])
                sh 'mvn clean package'
            }
        }
         stage('Run JUnit Tests') {
                    steps {
                        echo "Running unit tests"
                        sh 'mvn test'
                    }
                }
        stage("Build Docker Image"){
            steps {
                script {
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }
 stage('Push Docker Image to Registry') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: REGISTRY_CREDENTIALS, passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                        sh "docker login -u $USERNAME -p $PASSWORD"
                    }
                    sh "docker tag $DOCKER_IMAGE $REGISTRY_URL/$DOCKER_IMAGE:latest"
                    sh "docker push $REGISTRY_URL/$DOCKER_IMAGE:latest"
                }
            }

            }
    }

}