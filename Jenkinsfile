pipeline {
  agent any
  stages {
    stage('parallel test') {
      steps {
        git(url: 'https://github.com/tvasylchuk/OrangeHRManagerTestNG', branch: 'branch/testNG')
      }
    }

    stage('maven build') {
      steps {
        withMaven(maven: 'D:\\install\\apache-maven-3.8.7-bin\\apache-maven-3.8.7', jdk: 'C:\\Program Files\\Java\\jdk-19')
        bat 'mvn test'
      }
    }

    stage('') {
      steps {
        testNG(failureOnFailedTestConfig: true, reportFilenamePattern: '**/report_steam_tests/testng-results.xml', showFailedBuilds: true)
      }
    }

  }
}