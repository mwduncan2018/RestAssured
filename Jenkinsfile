pipeline {
  agent any

  environment {
    RELEASE='9.7'
    BUILD_NUMBER='2'
    DEMO='Pluralsight'
  }
  
  stages {
    stage('Build') {
      environment {
        LOG_LEVEL='INFO'
      }
      parallel {
        stage('linux-arm64') {
          steps {
            echo "Hey! This is stage name ${STAGE_NAME}"
            echo "This is build number ${BUILD_NUMBER} for release ${RELEASE} for ${DEMO} demo and log level is ${LOG_LEVEL}"
            sh '''
              chmod +x temp.sh
              ./temp.sh
            '''
          }          
        }
        stage('linux-amd64') {
          steps {
            echo "Hey! This is stage name ${STAGE_NAME}"
            echo "This is build number ${BUILD_NUMBER} for release ${RELEASE} for ${DEMO} demo and log level is ${LOG_LEVEL}"
            sh '''
              chmod +x temp.sh
              ./temp.sh
            '''
          }
        }
        stage('windows-amd64') {
          steps {
            echo "Hey! This is stage name ${STAGE_NAME}"
            echo "This is build number ${BUILD_NUMBER} for release ${RELEASE} for ${DEMO} demo and log level is ${LOG_LEVEL}"
            sh '''
              chmod +x temp.sh
              ./temp.sh
            '''
          }
        }
      }
    }      
    stage('Test') {
      environment {
        LOG_LEVEL='WARNING'
      }
      steps {
        echo 'TEST STAGE!'
        echo "Release is ${RELEASE} and demo is ${DEMO} and log level is ${LOG_LEVEL}"
        writeFile file: 'test-results.txt', text: 'Testing passes!'
      }
    }
    stage('Deploy') {
      input {
        message 'Hey, do you want to deploy?'
        ok 'DO IT NOW!'
        parameters {
          string(name: 'TARGET_ENVIRONMENT', defaultValue: 'PROD', description: 'Target deployment environment')
        }
      }
      steps {
        echo "Deploying release ${RELEASE} to environment ${TARGET_ENVIRONMENT}"
      }
    }
  }
  post {
    success {
      archiveArtifacts 'test-results.txt'
    }
    always {
      echo 'This always prints!'
    }
  }
}
