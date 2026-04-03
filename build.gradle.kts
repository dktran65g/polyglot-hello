plugins {
    id("base")
}

// =============================================================================
// Python (primary language) - uses pip + venv
// =============================================================================
tasks.register<Exec>("pythonSetup") {
    description = "Create Python virtual environment and install dependencies"
    commandLine("python3", "-m", "venv", "venv")
}

tasks.register<Exec>("pythonInstall") {
    description = "Install Python (PyPI) dependencies"
    dependsOn("pythonSetup")
    commandLine("venv/bin/pip", "install", "-r", "requirements.txt")
}

tasks.register<Exec>("pythonRun") {
    description = "Run Python hello world"
    dependsOn("pythonInstall")
    commandLine("venv/bin/python", "src/main/python/hello.py")
}

// =============================================================================
// Go
// =============================================================================
tasks.register<Exec>("goModTidy") {
    description = "Run go mod tidy"
    commandLine("go", "mod", "tidy")
}

tasks.register<Exec>("goBuild") {
    description = "Build Go hello world"
    commandLine("go", "build", "-o", "build/hello-go", "./cmd/hello")
}

tasks.register<Exec>("goRun") {
    description = "Run Go hello world"
    dependsOn("goBuild")
    commandLine("build/hello-go")
}

// =============================================================================
// Rust (Cargo)
// =============================================================================
tasks.register<Exec>("cargoBuild") {
    description = "Build Rust hello world"
    commandLine("cargo", "build", "--release")
}

tasks.register<Exec>("cargoRun") {
    description = "Run Rust hello world"
    dependsOn("cargoBuild")
    commandLine("cargo", "run", "--release")
}

// =============================================================================
// Node.js (npm)
// =============================================================================
tasks.register<Exec>("npmInstall") {
    description = "Install npm dependencies"
    commandLine("npm", "install", "--legacy-peer-deps")
}

tasks.register<Exec>("npmRun") {
    description = "Run Node.js hello world"
    dependsOn("npmInstall")
    commandLine("node", "src/main/js/hello.js")
}

// =============================================================================
// Java (Maven)
// =============================================================================
tasks.register<Exec>("mvnPackage") {
    description = "Build Java project with Maven"
    commandLine("mvn", "package", "-q", "-DskipTests")
}

tasks.register<Exec>("mvnRun") {
    description = "Run Java hello world"
    dependsOn("mvnPackage")
    commandLine("java", "-cp", "target/hello-1.0.0.jar", "com.polyglot.hello.Hello")
}

tasks.register<Exec>("mvnResolve") {
    description = "Resolve Maven dependencies"
    commandLine("mvn", "dependency:resolve", "-q")
}

// =============================================================================
// Aggregate tasks
// =============================================================================
tasks.register("installAll") {
    description = "Install all dependencies across all ecosystems"
    dependsOn("pythonInstall", "npmInstall", "cargoBuild", "goModTidy", "mvnResolve")
}

tasks.register("runAll") {
    description = "Run hello world in all languages"
    dependsOn("pythonRun", "goRun", "cargoRun", "npmRun", "mvnRun")
}

tasks.register("buildAll") {
    description = "Build all language targets"
    dependsOn("cargoBuild", "goBuild", "mvnPackage")
}
