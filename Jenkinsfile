pipeline {
    agent any
    stages {
        stage('GIT') {
            steps {
                git url: 'https://github.com/Emna123/backDevOps.git', branch: 'develop'
            }
        }
        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile'
            }
        }     
        stage('UNIT TEST'){
            steps {
                sh 'mvn test'
            }
            post {
              always {
                junit '**/target/surefire-reports/TEST-*.xml'
              }
            }

        }
        stage('SONARQUEBE') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
    }
    post {
        failure {
            mail to: 'emna.bentijani@esprit.tn',
            subject: 'Build failed',
            body: 'The build has failed. Please check Jenkins for details.'
        }
    }
}
