# Homework Quickstart

[![Actions Status: build](https://github.com/atp-mipt/homework-quickstart/workflows/build/badge.svg)](https://github.com/atp-mipt/homework-quickstart/actions?query=workflow%3A"build")
![Maven Central Version](https://img.shields.io/maven-central/v/org.atp-fivt/homework-quickstart?color=green)

Homework-quickstart is an Apache Maven project archetype pre-configured for

* Your current Java runtime by default — Java 17 or newer is required. The target
  release is fixed when the project is generated; to pin a specific release instead,
  see [Choosing the Java version](#choosing-the-java-version).
* [JUnit 6](https://junit.org/junit6/)
* [AssertJ](https://assertj.github.io/doc/)
* [Approvals](https://github.com/approvals/approvaltests.java)
* [JaCoCo](https://www.eclemma.org/jacoco/)
* [Checkstyle](https://checkstyle.sourceforge.io/) with a pre-selected set of rules
* [export-maven-plugin](https://github.com/atp-mipt/export-maven-plugin) — run
  `mvn export:export` to bundle the project into a `target/export.zip` for submission

## How to use

First, [install Maven](https://maven.apache.org/install.html) — Maven 3.9 or newer is
required.

Then run the command for your shell:

**Bash (Linux / macOS):**

```bash
mvn archetype:generate \
    -DarchetypeGroupId=org.atp-fivt \
    -DarchetypeArtifactId=homework-quickstart \
    -DarchetypeVersion=3.0
```

**Windows (cmd):**

```bat
mvn archetype:generate ^
    -DarchetypeGroupId=org.atp-fivt ^
    -DarchetypeArtifactId=homework-quickstart ^
    -DarchetypeVersion=3.0
```

### What you'll be asked

`archetype:generate` runs interactively and asks for a few properties, then shows them
for confirmation (press Enter to accept):

| Property | What it is |
|----------|------------|
| `groupId` | Maven group identifier for your project, e.g. `org.example` or your study-group id. |
| `artifactId` | Project name — becomes the generated folder name and the Maven artifact id. |
| `version` | Initial project version; the default `1.0-SNAPSHOT` is fine. |
| `package` | Root Java package for your source files. Defaults to the `groupId`. |
| `javaversion` | Target Java release. Default `auto` = your current Java runtime (see [Choosing the Java version](#choosing-the-java-version)). |

## Choosing the Java version

By default the generated project targets the Java runtime you generate it with, and
that release is baked into the project's `pom.xml`. To target a specific release
instead, set the `javaversion` property — either answer the interactive prompt, or
pass it on the command line:

```
-Djavaversion=17
```

The value must be between `17` and your current Java runtime version. Anything
outside that range aborts generation with an error.
