# MUTAGEN

BPA 2018-2019
PAK4

\*1st place National Champion Software Engineering

\*1st place Best in Show

Richard H - Team Captain/Lead Programmer

Jeremy B - Programmer

Kyle P - Level Design/Logging

Kyle T - Graphic Designer

Ryan P - Music Design and Composition

Nicholas S - Piano Performance and Composition

## How to Run

### Requirements

- **Java JDK 8**: The game is compatible with Java 8. Ensure your `JAVA_HOME` points to a JDK 8 installation.

### Execution Instructions

1. Navigate to the `libGDX Workspace` directory:
   ```bash
   cd "libGDX Workspace"
   ```
2. Run the game using the Gradle wrapper:
   - **Windows**:
     ```powershell
     .\gradlew.bat desktop:run
     ```
   - **Linux/macOS**:
     ```bash
     ./gradlew desktop:run
     ```

## Distribution

### Building the Executable JAR

To create a single "Fat JAR" that contains all the game code, libraries, and assets for easy sharing:

1. Navigate to the `libGDX Workspace` directory.
2. Run the distribution task:
   ```bash
   ./gradlew desktop:dist
   ```
   (On Windows, use `.\gradlew.bat desktop:dist`)

The generated file will be located at:
`libGDX Workspace/desktop/build/libs/desktop-1.0.jar`

### Running the JAR

Once built, the game can be played by double-clicking the jar or by running:

```bash
java -jar desktop-1.0.jar
```

_(Players only need to have Java installed to run this file.)_
