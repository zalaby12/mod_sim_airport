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


To see various output included, run 
  ```
  java -jar sim.jar -v
  ```


For comparison, Delta's yearly revenue is about $40 billion
Over 365 days, that turns out to be approximately $109.5 million a day.

Moreover, Delta schedules approximately 120.6 million passengers a year, or about 330,000 a day. 

Delta, for instance, has many other planes in the fleet, and the simplification here is to focus more on passenger distribution
than specific distribution of planes. This could obviously affect results, as smaller planes, more than
likely, require less overhead to operate and maintain and would cost less to passengers.  

To account for this, the parameters were fine-tuned to match approximate number of passengers per day
and the estimated revenue per day for Delta. There are significantly less flights than anticipated, as 
would be expected from only considering the 747 lineup. However, the importance is considering
the number of passengers per day and the typical amount earned in a day, for comparison. 

Scheduling about 850 flights tends to just overschedule a typical number of passengers per day, 
when only considering 747 aircraft.

The mean ticket price was moved from 509.14 to 330.00 to reflect the reality of more varied
ticket prices among aircraft and routes. 