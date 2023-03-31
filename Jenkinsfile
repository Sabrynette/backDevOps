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
        
        # stage("Nexus") {
        #     steps {
        #         script {
        #             pom = readMavenPom file: "pom.xml";
        #             filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
        #             echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
        #             artifactPath = filesByGlob[0].path;
        #             artifactExists = fileExists artifactPath;
        #             if(artifactExists) {
        #                 echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
        #                 nexusArtifactUploader(
        #                     nexusVersion: 'nexus3',
        #                     protocol: 'http',
        #                     nexusUrl: '172.10.6.100:8081',
        #                     groupId: 'pom.com.esprit.examen',
        #                     version: 'pom.1.0',
        #                     repository: 'deploymentRepo',
        #                     credentialsId: 'jenkins-user',
        #                     artifacts: [
        #                         [artifactId: 'pom.tpAchatProject',
        #                         classifier: '',
        #                         file: artifactPath,
        #                         type: pom.packaging],
        #                         [artifactId: 'pom.tpAchatProject',
        #                         classifier: '',
        #                         file: "pom.xml",
        #                         type: "pom"]
        #                     ]
        #                 );
        #             } else {
        #                 error "*** File: ${artifactPath}, could not be found";
        #             }
        #         }
        #     }
        # }
    }
    post {
        failure {
            mail to: 'emna.bentijani@esprit.tn',
            subject: 'Build failed',
            body: 'The build has failed. Please check Jenkins for details.'
        }
    }
}
