class Program {

  static String wantToPlay
  static String playerChoice
  static String computerChoice
  static OptionFactory factory
  static Game game

  private static final String PLAY_PROMPT     = "Would you like to play Roshambo? (Yes) or (No)"

  static {
    factory = new OptionFactory()
  }

  public static void main(String[] args) {
    wantToPlay = prompt(PLAY_PROMPT)
    game = new Game()
    startGame()
  }

  private static void startGame() {
    if (wantToPlay == "yes") {
      playerChoice = prompt(availableOptions())

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
    if (playerChoice in factory.options) {

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
      playerChoice = prompt(availableOptions())
      makeSelection()
    }
  }

  protected static String availableOptions() {
    return factory.optionSummary
  }

  private static String prompt(String message) {
    return System.console().readLine(formatPrompt(message)).toLowerCase()
  }

  private static String formatPrompt(String message) {
    message + ": "
  }
}
