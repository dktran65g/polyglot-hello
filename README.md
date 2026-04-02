# Polyglot Hello World

A multi-language hello world project orchestrated by Gradle, spanning four ecosystems: Python (PyPI), Node.js (npm), Rust (Cargo), and Go.

## Prerequisites

- **Java 11+** (for Gradle wrapper)
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
├── src/
│   └── main/
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

### Aggregate

| Task | Description |
|------|-------------|
| `runAll` | Run hello world in all four languages |
| `installAll` | Install dependencies for all ecosystems |
| `buildAll` | Build Go and Rust binaries |

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

### Go

- github.com/swaggo/http-swagger v1.3.4
