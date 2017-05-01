class Program {

  static String wantToPlay
  static String playerChoice
  static String computerChoice
  static Game game

  private static final String PLAY_PROMPT     = "Would you like to play Roshambo? (Yes) or (No)"
  private static final String OPTIONS_PROMPT  = "Choose from either (R)ock, (P)aper, or (S)cissors"

  public static void main(String[] args) {
    game = new Game()

    wantToPlay = prompt(PLAY_PROMPT)

    startGame()
  }

  private static void startGame() {
    if (wantToPlay == "yes") {
      playerChoice = prompt(OPTIONS_PROMPT)

      makeSelection()
    }
    else if (wantToPlay == "no") {
      System.out.println("Good Bye")
    }
    else {
      wantToPlay = prompt(PLAY_PROMPT)
      startGame()
    }
  }

  private static void makeSelection() {
    if (playerChoice == "r" || playerChoice == "p" || playerChoice == "s") {

      OptionFactory factory = new OptionFactory()
      Option player = factory.make(playerChoice, "Player")
      Option computer = factory.random("Computer")

      def round = new Round(player, computer)
      game.addRound(round)

      System.out.println(round.toString())
      System.out.println(round.summary)
      System.out.println(game.summary)

      wantToPlay = prompt(PLAY_PROMPT)
      
      if (wantToPlay == "yes" || wantToPlay == "no") {
        startGame()
      }
      else {
        wantToPlay = prompt(PLAY_PROMPT)
        startGame()
      }
    }
    else {
      playerChoice = prompt(OPTIONS_PROMPT)
      makeSelection()
    }
  }

  private static String prompt(String message) {
    return System.console().readLine(formatPrompt(message)).toLowerCase()
  }

  private static String formatPrompt(String message) {
    message + ": "
  }
}
