To run on environments with the java compiler: 
```
  make
```

If "command not found", run 
  ```
  make jar
  ```

in an environment with the java compiler and then copy the jar and output folder (it contains the classes) and run 
  ```
  java -jar sim.jar
  ```


To run the tests, add JUnit to the classpath (manually, or through an IDE) and then run the TestSuite. Before handing in, tests were run in IntelliJ both individually and as a Suite. There was a tendancy for IntelliJ to not recognize the TestSuite class, and I more often than not had to execute the test twice for it to produce results. 
