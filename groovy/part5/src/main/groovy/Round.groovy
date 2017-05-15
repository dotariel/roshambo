class Round {
  
  Option o1
  Option o2

  public Round(Option o1, Option o2) {
    this.o1 = o1
    this.o2 = o2
  }

  public Option getWinner() {
    if (o1.beats(o2) ==  1) return o1
    if (o1.beats(o2) == -1) return o2

    return null
  }

  public String getSummary() {
    winner ? "${winner.player} wins." : "The result is a tie."
  }

  public String toString() {
    "${o1.player} chose ${o1.code} and ${o2.player} chose ${o2.code}"
  }
}
