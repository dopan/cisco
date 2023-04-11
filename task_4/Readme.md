# Task 4

Quick and dirty program counting distinct words from a file

## Assumptions

Let's define word with regular expression `/W` matching letters with inclusion of the underscore and digits `[A-Za-z0-9_]`.

### Limitations

Does not work with any other characters or diacritics.

## Run

To compile and run tests: `mvn clean test`

To run program counting and printing out occurences from file `src/main/resources/words.txt`: 
`mvn exec:java -Dexec.mainClass="org.cisco.interview.Main"`
