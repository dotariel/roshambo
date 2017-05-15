# Running Tests
To run any of the tests locally, from within the directory, run Gradle:
```
gradle <part>:clean <part>:test
```

# Run the Application
To run a given version of the application, build the jar and execute it:

```
gradle <part>:jar
java -jar <part>/build/lib/<part>.jar
```
