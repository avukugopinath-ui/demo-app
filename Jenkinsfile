pipeline {
    agent any

    environment {
        IMAGE_NAME = "demo-app"
        VM_IP = "34.14.192.106"
        VM_USER = "ubuntu"
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/avukugopinath-ui/demo-app.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t demo-app .'
            }
        }

        stage('Deploy') {
            steps {
                sh """
            docker compose down || true
            docker compose up -d --build
            """
            }
        }
    }
}

