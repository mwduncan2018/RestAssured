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
        auditTools()
      }
    }
    stage('Build') {
      environment {
        VERSION_SUFFIX = getVersionSuffix()
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

String getVersionSuffix() {
  if (params.RC) {
    return env.VERSION_RC
  } else {
    return env.VERSION_RC + '+ci.' + env.BUILD_NUMBER
  }
}

void auditTools() {
  sh '''
    git version
    java --version
  '''  
}
