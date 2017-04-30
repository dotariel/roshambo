# Goals
- Refactor single-class application to remove code duplication
- Add unit tests for refactored methods

# Thoughts
- There are many side effects to contend with
- All the inputs, outputs to/from the console need to be pushed to the edges, so that we can isolate the functionality we want to test.

#### Changes
We can start by making some simple refactorings just to move some code around.
  - move Strings to constants/methods for better isolation
  - add `prompt()` method to clean up some duplicated code.
  - add unit tests for Program
    - `should_format_prompt_message` - Isolate the message prompt from the console interaction
    - `should_read_console_input` - Mock `System.console()` to prove that we can return the input in lower-case.
    - `should_build_score_message` - Isolate the score message

# Deep Dive
We need a way to test the basic functional requirements of this game. We don't have programmatic proof that rock beats scissors, scissors beats paper, etc. Let's try to write a few test cases that will allow us to make sure that
the current implementation at least achieves the desired functionality, prior to us begginning to refactor. 

Let's prove that _rock_ beats _scissors_.

#### Start with the "public" interface
The _only_ discernable interface to this application is the `main()` method. Our test case begins by setting up the
necessary inputs conditions, mocking/stubbing where necessary, and then validating the results.

First, we have to mock `System.console()` so that we can intercept any interaction with a user. By reviewing the code, we can see that there are three calls to `readLine()` to gather input from the user, so we will need to simulate each required response.

```
    // Mock the interaction with the user
    MockFor consoleMock = new MockFor(Console)
    consoleMock.demand.readLine { String s -> "yes" }  // Do you want to play?
    consoleMock.demand.readLine { String s -> "r" }    // Make your choice
    consoleMock.demand.readLine { String s -> "no" }   // Play again?
    def console = consoleMock.proxyInstance()

    // Intercept the call to console() and return our mocked console
    System.metaClass.'static'.console = { console }

```

Next, we will have to stub out the computer's random response, so that we can control the result we are comparing against. Fortunately, the developer
has isolated this into a method that we can stub out.

```
  Program.metaClass.'static'.computerChoice = { "s" } // Return scissors
```

Next, we have to validate that the correct winner was determined. The only way we have to prove that is by inspecting the output returned to the console. We will need to intercept the calls to `System.out.println()` so that we can collect the results and verify them.

```
    // Intercept the call to println() so that we can inspect the message returned
    def output = []
    
    System.out.metaClass.invokeMethod = { String methodName, args -> 
      if (methodName == 'println') output << args[0]
    }
```

Next, now that we have the interceptors in place, we can run the program.

```
    // Run program
    Program.main([] as String[])
```

Finally, we can inspect our collected output for the expected responses.

```
    // Verify results
    assert output[0] == 'You chose r and the computer chose s'
    assert output[1] == 'You win'
    assert output[2] == 'Score is You = 1 and the Machine = 0'
    assert output[3] == 'Keep beating the machine'
    assert output[4] == 'Good Bye'
```