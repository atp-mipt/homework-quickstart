# Homework Quickstart

[![Actions Status: build](https://github.com/atp-mipt/homework-quickstart/workflows/build/badge.svg)](https://github.com/atp-mipt/homework-quickstart/actions?query=workflow%3A"build")
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.atp-fivt/homework-quickstart/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.atp-fivt/homework-quickstart)

Homework-quickstart is an Apache Maven project archetype pre-configured for 

* Java 11 by default (you can change the version when asked for `javaversion` property value or
  by passing `-Djavaversion=<YOUR_JAVA_VERSION>` in the command line)
* [JUnit 5](https://junit.org/junit5/)
* [AssertJ](https://assertj.github.io/doc/)
* [Approvals](https://github.com/approvals/approvaltests.java)
* [JaCoCo](https://www.eclemma.org/jacoco/)
* [Checkstyle](https://checkstyle.sourceforge.io/) with a pre-selected set of rules

## How to use

```
mvn archetype:generate -DarchetypeArtifactId=homework-quickstart
                       -DarchetypeGroupId=org.atp-fivt 
                       -DarchetypeVersion=1.03 
                       -DgroupId=<YOUR GROUP ID> 
                       -DartifactId=<YOUR ARTIFACT ID>
```

Demo video (in Russian)

[![Демо](https://img.youtube.com/vi/K0pEIyKCUug/0.jpg)](https://www.youtube.com/watch?v=K0pEIyKCUug)
