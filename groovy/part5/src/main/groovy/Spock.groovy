class Spock extends Option {

  public Spock(String player) {
    super(player)
  }

  public String getCode() {
    return "v"
  }

  public int beats(Rock other) {
    return 1
  }

  public int beats(Scissors other) {
    return 1
  }

  public int beats(Paper other) {
    return -1
  }

  public int beats(Lizard other) {
    return -1
  }

  public int beats(Option other) {
    return 0  
  }
}
