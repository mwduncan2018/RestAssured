pipeline {
  agent any
  parameters {
    booleanParam(name: 'RC', defaultValue: false, description: 'Is this a Release Candidate?')
  }
  
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
      environment {
        VERSION_SUFFIX = "${sh(script:'if [ "${RC}" == "false" ] ; then echo -n "${VERSION_RC}+ci.${BUILD_NUMBER}"; else echo -n "${VERSION_RC}"; fi', returnStdout: true)}"
      }
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
    stage('Publish') {
      when {
        expression { return params.RC }
      }
      steps {
        echo 'Inside Publish stage'
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

