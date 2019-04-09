node {
    stage("Test") {
        sh "mvn test";
    }
    stage("sonar") {
        sh "mvn sonar:sonar";
    }
    stage("package") {
        sh "mvn clean package -Dmaven.test.skip=true"
    }
}
