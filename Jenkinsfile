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
stages {
        stage('Count test_table') {
            steps {
                script {
                    // Copy and paste the script here
                    import groovy.sql.Sql
                    Class.forName("com.mysql.jdbc.Driver")
                    def sql = Sql.newInstance("jdbc:mysql://mysql:3306/test_db", "user","passwd", "com.mysql.jdbc.Driver")
                    def rows = sql.execute "select count(*) from test_table;"
                    echo rows.dump()
                }
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
