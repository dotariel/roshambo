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

[logo]: https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/Pierre_ciseaux_feuille_l%C3%A9zard_spock.svg/768px-Pierre_ciseaux_feuille_l%C3%A9zard_spock.svg.png