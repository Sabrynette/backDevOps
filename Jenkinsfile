pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Emna123/backDevOps.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    
    post {
        failure {
            mail to: 'emnatijani77@gmail.com',
                subject: 'Build Failed',
                body: 'The build has failed. Please check Jenkins for more details.'
        }
    }
}
