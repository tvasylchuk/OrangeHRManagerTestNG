pipeline {
  agent any
  stages {
    stage('parallel test') {
      steps {
        git(url: 'https://github.com/tvasylchuk/OrangeHRManagerTestNG', branch: 'branch/testNG')
        bat 'mvn test'
        archiveArtifacts '**/Report'
      }
    }

  }
}