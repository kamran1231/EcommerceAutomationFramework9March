pipeline {

    agent any

    stages {

        stage('Checkout Code') {

            steps {

                git branch: 'main',
                url: 'https://github.com/kamran1231/EcommerceAutomationFramework9March.git'

            }

        }

        stage('Start Docker') {

            steps {

                echo 'Starting Docker Containers...'

                bat 'docker compose down'
                bat 'docker compose up -d'

            }

        }

        stage('Run Tests') {

            steps {

                echo 'Running Test Cases...'

                bat 'mvn clean test -DrunMode=docker'

            }

        }

    }

    post {

        always {

            echo 'Stopping Docker Containers...'

            // Always stop Docker (even if tests fail)
            bat 'docker compose down'

            echo 'Archiving Extent Reports...'

            // Archive Extent reports
            archiveArtifacts artifacts: 'reports/**',
            fingerprint: true

            echo 'Archiving TestNG Reports...'

            // Archive TestNG reports
            archiveArtifacts artifacts: 'test-output/**',
            fingerprint: true

        }

    }

}