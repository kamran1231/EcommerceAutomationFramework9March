pipeline {

    agent any

    stages {

        stage('Checkout Code') {

            steps {

                git 'https://github.com/kamran1231/EcommerceAutomationFramework9March.git'

            }

        }

        stage('Start Docker') {

            steps {

                bat 'docker compose up -d'

            }

        }

        stage('Run Tests') {

            steps {

                bat 'mvn clean test -DrunMode=docker'

            }

        }

        stage('Stop Docker') {

            steps {

                bat 'docker compose down'

            }

        }

    }

}