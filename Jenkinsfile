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
        stage('Count test_table') {
            steps {
                script {
                    // Import the Sql class
                    import groovy.sql.Sql

                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.jdbc.Driver")

                    // Create a new Sql instance and execute the query
                    def sql = Sql.newInstance("jdbc:mysql://mysql:3306/tpachato", "root","root", "com.mysql.jdbc.Driver")

                    // Print the results
                    echo rows.dump()
                }
            }
        }
        stage('MVN TEST') {
            steps {
                sh 'mvn test'
            }
            post {
              always {
                junit '**/target/surefire-reports/TEST-*.xml'
              }
            }

        }
        stage('MVN SONARQUEBE') {
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
