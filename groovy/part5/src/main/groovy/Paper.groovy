class Paper extends Option {

  public Paper(String player) {
    super(player)
  }

  public String getCode() {
    return "p"
  }

  public int beats(Rock other) {
    return 1
  }

  public int beats(Scissors other) {
    return -1
  }

  public int beats(Option other) {
    return 0  
  }
}
