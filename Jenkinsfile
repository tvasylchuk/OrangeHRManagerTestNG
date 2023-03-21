pipeline {
  agent any
  tools{
    maven "3.8.7"
  }
  stages {
    stage('Retrieve') {
      steps {
            echo "Retrieve code from the repository"
            git branch: 'main', credentialsId: 'a834a799-e866-4032-b976-e7b80ebff681', url: 'https://github.com/tvasylchuk/OrangeHRManagerTestNG'
      }
    }
    stage("Selenium Server Run"){
        steps {
            echo "Run Selenium Server"
            withEnv(['SELENIUM_HOME = C:\\webdrivers']){
                bat 'java -jar %SELENIUM_HOME%\\selenium-server-4.8.1.jar standalone --port 4446'
            }
        }
    }
    stage('Test') {
        steps {
        catchError{
            echo "Run tests"
            bat 'mvn test'
            }
        }
    }
    stage('Report') {
        steps{
            testNG reportFilenamePattern: '**/Report/testng-results.xml'
            publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false,
            reportDir: 'Report', reportFiles: 'index.html', reportName: 'HTML Report',
            reportTitles: '', useWrapperFileDirectly: true])
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