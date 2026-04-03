# Polyglot Hello World

A multi-language hello world project orchestrated by Gradle, spanning five ecosystems: Python (PyPI), Node.js (npm), Rust (Cargo), Go, and Java (Maven).

## Prerequisites

- **Java 11+** (for Gradle wrapper and Maven)
- **Maven 3.x**
- **Python 3.x**
- **Node.js / npm**
- **Go 1.23+**
- **Rust / Cargo**

## Project Structure

```
polyglot-hello/
├── build.gradle.kts          # Gradle build orchestrating all ecosystems
├── settings.gradle.kts
├── gradlew / gradlew.bat     # Gradle wrapper scripts
├── requirements.txt           # Python (PyPI) dependencies
├── package.json               # Node.js (npm) dependencies
├── Cargo.toml                 # Rust (Cargo) dependencies
├── go.mod                     # Go module dependencies
├── pom.xml                    # Java (Maven) dependencies
├── packages/
│   ├── py-greeter/            # Python internal package
│   ├── node-greeter/          # Node.js internal package
│   └── go-greeter/            # Go internal package
├── src/
│   └── main/
│       ├── java/com/polyglot/hello/Hello.java
│       ├── python/hello.py
│       ├── js/hello.js
│       └── rs/main.rs
└── cmd/
    └── hello/main.go
```

## Quick Start

Run all hello worlds:

```bash
./gradlew runAll
```

## Gradle Tasks

### Per-language

| Task | Description |
|------|-------------|
| `pythonSetup` | Create Python virtual environment |
| `pythonInstall` | Install PyPI dependencies into venv |
| `pythonRun` | Run Python hello world |
| `npmInstall` | Install npm dependencies |
| `npmRun` | Run Node.js hello world |
| `goBuild` | Build Go binary to `build/hello-go` |
| `goRun` | Run Go hello world |
| `goModTidy` | Run `go mod tidy` |
| `cargoBuild` | Build Rust binary (release) |
| `cargoRun` | Run Rust hello world |
| `mvnPackage` | Build Java project with Maven |
| `mvnRun` | Run Java hello world |
| `mvnResolve` | Resolve Maven dependencies |

### Aggregate

| Task | Description |
|------|-------------|
| `runAll` | Run hello world in all five languages |
| `installAll` | Install dependencies for all ecosystems |
| `buildAll` | Build Go, Rust, and Java binaries |

## Dependencies

### Python (PyPI) — Primary Language

- openapi-spec-validator 0.8.4
- datamodel-code-generator 0.55.0
- mkdocs-material 9.7.5 (and related mkdocs plugins)
- pymarkdownlnt 0.9.36
- yamllint 1.38.0

### Node.js (npm)

- react 19.2.4, react-dom 19.2.4
- vite 8.0.3, @vitejs/plugin-react 6.0.1
- tailwindcss 4.2.2
- typescript 6.0.2
- @biomejs/biome 2.4.10
- @tanstack/react-query 5.96.1

### Rust (Cargo)

- axum 0.8.8, tower-http 0.6.8
- tokio 1.50.0, tokio-stream 0.1.18
- tonic 0.14.5, prost 0.14.3
- serde 1.0.228, serde_json 1.0.149
- reqwest 0.13.2, rusqlite 0.39.0
- clap 4.6.0, chrono 0.4.44, uuid 1.23.0

### Java (Maven)

- com.google.guava:guava 33.4.0-jre
- org.slf4j:slf4j-api 2.0.17
- ch.qos.logback:logback-classic 1.5.18
- com.fasterxml.jackson.core:jackson-databind 2.18.3
- org.apache.commons:commons-lang3 3.17.0

### Go

- github.com/swaggo/http-swagger v1.3.4

## Internal/Local Packages

Each ecosystem imports a local package that exports a `greet(name)` function.

| Ecosystem | Package Name | Location | How it's referenced |
|-----------|-------------|----------|---------------------|
| Python | `greeter` | `packages/py-greeter/` | `-e ./packages/py-greeter` in `requirements.txt` |
| Node.js | `@polyglot/greeter` | `packages/node-greeter/` | `"file:packages/node-greeter"` in `package.json` |
| Go | `go-greeter` | `packages/go-greeter/` | `replace` directive in `go.mod` |

## Manifests

Each ecosystem uses a manifest file to declare dependencies.

| Ecosystem | Manifest | How to create |
|-----------|----------|---------------|
| Python (PyPI) | `requirements.txt` | Author manually or `pip freeze > requirements.txt` |
| Node.js (npm) | `package.json` | `npm init -y` then `npm install <pkg> --save` |
| Rust (Cargo) | `Cargo.toml` | `cargo init` then `cargo add <pkg>` |
| Go | `go.mod` | `go mod init <module>` then `go get <pkg>@<version>` |
| Java (Maven) | `pom.xml` | `mvn archetype:generate` or author manually |

### Generating Manifests

**Python (PyPI)** - `requirements.txt`
```bash
# Option 1: author manually
echo "openapi-spec-validator==0.8.4" >> requirements.txt

# Option 2: generate from existing environment
venv/bin/pip freeze > requirements.txt
```

**Node.js (npm)** - `package.json`
```bash
npm init -y
npm install react react-dom --save
# package.json dependencies section is auto-updated
```

**Rust (Cargo)** - `Cargo.toml`
```bash
cargo init
cargo add serde tokio axum
# Cargo.toml [dependencies] section is auto-updated
```

**Go** - `go.mod`
```bash
go mod init github.com/your-org/your-project
go get github.com/swaggo/http-swagger@v1.3.4
# go.mod require block is auto-updated
```

**Java (Maven)** - `pom.xml`
```bash
mvn archetype:generate -DgroupId=com.polyglot -DartifactId=hello -DarchetypeArtifactId=maven-archetype-quickstart
# Then add <dependency> blocks to pom.xml manually, or use:
mvn versions:use-latest-releases
```

## Lockfiles

| Ecosystem | Manifest | Lockfile | Format |
|-----------|----------|----------|--------|
| Python (PyPI) | `requirements.txt` | `requirements.lock` | pip freeze |
| Node.js (npm) | `package.json` | `package-lock.json` | npm v3 |
| Rust (Cargo) | `Cargo.toml` | `Cargo.lock` | Cargo native |
| Go | `go.mod` | `go.sum` | Go native |
| Java (Maven) | `pom.xml` | `bom.json` | CycloneDX 1.6 |

The Java lockfile is generated by the [CycloneDX Maven plugin](https://github.com/CycloneDX/cyclonedx-maven-plugin) during `mvn package`. It includes all resolved dependencies with package URLs, hashes, and the full dependency graph.

### Generating Lockfiles

**Python (PyPI)**
```bash
python3 -m venv venv
venv/bin/pip install -r requirements.txt
venv/bin/pip freeze > requirements.lock
```

**Node.js (npm)**
```bash
npm install --legacy-peer-deps
# package-lock.json is auto-generated
```

**Rust (Cargo)**
```bash
cargo build
# Cargo.lock is auto-generated
```

**Go**
```bash
go mod tidy
# go.sum is auto-generated
```

**Java (Maven)**
```bash
mvn package -DskipTests
# bom.json is generated in target/, copy to root
cp target/bom.json bom.json
```

Or use Gradle to install dependencies and generate lockfiles for all ecosystems at once:
```bash
./gradlew installAll
```

## Socket Scan Standard

```bash
socket scan create --repo polyglot-hello --branch main --default-branch --org <Your_OrgName> . --report
```

## Socket Scan Reachability

```bash
socket scan create --reach --repo polyglot-hello --branch main --default-branch --org <Your_OrgName> . --report
```
