# SerialPortExperiments
Testing serial port communication with Java and <a href="https://github.com/Fazecast/jSerialComm">jSerialComm</a>.

The first experiment uses the following Arduino code for testing:

```c
long counter = 0;

void setup() {
  // start serial port at 9600 bps:
  Serial.begin(9600);
  while (!Serial) {
    // wait for serial port to connect.
  }
}

void loop() {
  Serial.print("Hello from Arduino: ");
  Serial.print(counter);
  Serial.print("\n"); // we use plain '\n' for newline
  delay(1000);
  counter++;
}
```

## How to Build and run the Experiment

### Requirements

- Java >= 11
- Internet connection (dependencies are downloaded automatically)
- IDE: [Gradle](http://www.gradle.org/) Plugin (not necessary for command line usage)

- Connected Arduino Uno with demo code shown above

### IDE

Open the `comport-01` [Gradle](http://www.gradle.org/) project in your favourite IDE (tested with IntelliJ 2019) and run it
by calling the `run` task.

### Command Line

Navigate to the [Gradle](http://www.gradle.org/) project (e.g., `path/to/comport-01`) and enter the following command

#### Bash (Linux/OS X/Cygwin/other Unix-like shell)

    bash gradlew run
    
#### Windows (CMD)

    gradlew run

#### Windows (PowerShell)

    .\gradlew run

### Expected Behavior:

If the program finds a connected Arduino it is expected to show the following output:

```bash
> reading from port: Arduino Uno (COM5)
> reading: Hello from Arduino: 0
> reading: Hello from Arduino: 1
> reading: Hello from Arduino: 2
> reading: Hello from Arduino: 3
```

If the program is started without connecting the Arduino first, it is expected to throw an exception.

```bash
Exception in thread "main" java.lang.RuntimeException: Cannot find Arduino COM Port
        at comport.App.lambda$main$1(App.java:41)
        at java.base/java.util.Optional.orElseThrow(Optional.java:408)
        at comport.App.main(App.java:41)
```
