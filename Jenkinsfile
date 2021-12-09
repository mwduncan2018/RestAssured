pipeline {
  agent any

  environment {
    VERSION='8.7'
    VERSION_RC='rc.2'
    DEMO='Pluralsight Reusable Pipelines'
  }
  
  stages {
    stage('Audit tools') {
      steps {
        sh '''
          git version
          java --version
        '''
      }
    }
    stage('Build') {
      steps {
        echo "Building version ${VERSION} with suffix: ${VERSION_RC}"
      }
    }      
    stage('Test') {
      steps {
        echo 'TEST STAGE!'
        writeFile file: 'test-results.txt', text: 'Testing passes!'
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
