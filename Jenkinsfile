pipeline {
  agent any
  tools{
    maven "3.8.7"
  }
  stages {
    stage('Retrieve') {
      steps {
        echo "Retrieve code from the repository"
        git credentialsId: 'a834a799-e866-4032-b976-e7b80ebff681' url: 'https://github.com/tvasylchuk/OrangeHRManagerTestNG', branch: 'main'
      }
    }
    stage('Test') {
        step {
            echo "Run tests"
            sh 'mvn test'
        }
    }
  }
 post{
 always {
 echo "Archive report file"
 archiveArtifacts artifacts: 'Report/emailable-report.html', followSymlinks: false
 }
 }
}