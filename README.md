# A Java Interpreter for "Bhailang"
An interpreter written in Java for the toy programming language ['Bhailang'](https://bhailang.js.org/).

### Steps to run
Ensure that Java 21 or higher is installed on your system and is defautled for this project. 
1. Run `./gradlew build` from the project root
2. Step 1 will create a jar file under `build/libs` by the name of `blint-1.0.jar`. Copy this file to a place where it is easile accessible. 
3. Run `java -jar blint-1.0.jar code.bl`. Here the file `code.bl` should contain your Bhailang code.
4. See your code running!