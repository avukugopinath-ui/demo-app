pipeline {
    agent any

    environment {
        IMAGE_NAME = "demo-app"
        VM_IP = "YOUR_VM_IP"
        VM_USER = "YOUR_VM_USER"
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/avukugopinath-ui/demo-app.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t demo-app .'
            }
        }

        stage('Deploy to GCP VM') {
            steps {
                sh """
                docker save -o demo-app.tar demo-app
                scp demo-app.tar ${VM_USER}@${VM_IP}:~
                scp docker-compose.yml ${VM_USER}@${VM_IP}:~

                ssh ${VM_USER}@${VM_IP} '
                    docker load -i demo-app.tar
                    docker-compose down || true
                    docker-compose up -d
                '
                """
            }
        }
    }
}