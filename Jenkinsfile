pipeline {
  environment{
    repository = "saehoon0501/action-springboot"
    DOCKERHUB_CREDENTIALS = credentials('dockerhub-jenkins')
  }
  agent any
  tools{
    gradle 'gradle'
    dockerTool 'docker'
  }
  stages {
    stage('Build') {
      steps {
        sh './gradlew clean build'
      }
    }
    // stage('Test'){
    //   steps{
    //     sh 'react-scripts test'
    //   }
    // }
    stage('Build Image') {
            steps {
                sh 'docker build -t action-springboot .'
                sh 'docker tag action-springboot:latest saehoon0501/action-springboot:latest'
            }
        }
    stage('Docker Push') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin' // docker hub 로그인
                sh 'docker push $repository:latest'
            }
    }
    stage('cleaning up'){
      steps{
        sh "docker rmi $repository:latest" // docker image 제거
      }
    }
 }
}
