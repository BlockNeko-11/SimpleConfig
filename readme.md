# SimpleConfig
A Java library aims to ease configuration operations.

Written in pure Java.

## Features
### Support
| Provider        | Module            | Formats | Comment Support | Link                                                   | Note                                                                          |
|-----------------|-------------------|---------|-----------------|--------------------------------------------------------|-------------------------------------------------------------------------------|
| Gson            | `gson`            | json    | ❌               | [GitHub](https://github.com/google/gson)               | -                                                                             |
| SnakeYaml       | `snake-yaml`      | yaml    | ✅               | [BitBucket](https://bitbucket.org/snakeyaml/snakeyaml) | -                                                                             |
| Typesafe Config | `typesafe-config` | conf    | ✅               | [GitHub](https://github.com/lightbend/config)          | -                                                                             |
| Toml4J          | `toml4j`          | toml    | ✅               | [GitHub](https://github.com/mwanji/toml4j)             | Not support TOML spec version higher than [v0.4.0](https://toml.io/en/v0.4.0) |

## Requirements
- Java 8+

## Installation
### Maven

<details>
<summary>Repository</summary>
    
```xml
<project>
    <repositories>
        <repository>
            <id>jitpack</id>
            <url>https://jitpack.io/</url>
        </repository>
    </repositories>
</project>
```
</details>

<details>
<summary>Dependency</summary>

```xml
<project>
    <dependencies>
        <!-- module `core` -->
        <dependency>
            <groupId>com.github.BlockNeko-11.SimpleConfig</groupId>
            <artifactId>simpleconfig-core</artifactId>
            <version>[LATEST RELEASE]</version>
            <scope>compile</scope>
        </dependency>
        
        <!-- module `[CONFIG PROVIDER]` -->
        <dependency>
            <groupId>com.github.BlockNeko-11.SimpleConfig</groupId>
            <artifactId>simpleconfig-[CONFIG PROVIDER]</artifactId>
            <version>[LATEST RELEASE]</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```
</details>

### Gradle

<details>
<summary>Repository</summary>

```gradle
repositories {
    maven {
        name = "Jitpack"
        url = "https://jitpack.io/"
    }
}
```
</details>

<details>
<summary>Dependency</summary>

```groovy
dependencies {
    // module `core`
    implementation "com.github.BlockNeko-11.SimpleConfig:simpleconfig-core:[LATEST RELEASE]"
    
    // module `[CONFIG PROVIDER]`
    implementation "com.github.BlockNeko-11.SimpleConfig:simpleconfig-[CONFIG PROVIDER]:[LATEST RELEASE]"
}
```
</details>

## Build
1. Clone this repository
2. run `./gradlew build` in the project directory
