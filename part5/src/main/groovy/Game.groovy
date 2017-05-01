class Game {
  Map rounds = [:]

  public void addRound(Round round) {
    rounds[round.o1.player] = rounds[round.o1.player] ?: []
    rounds[round.o2.player] = rounds[round.o2.player] ?: []

    def winner = round.winner?.player

    if (winner) {
      rounds[winner] = rounds[winner] ?: []
      rounds[winner] << round
    }
  }

  public int getScore(String player) {
    rounds[player]?.size() ?: 0
  }

  public String getSummary() {
    "Score is " + rounds.collect { player, r -> "${player}: ${getScore(player)}" }.join(", ")
  }
}
