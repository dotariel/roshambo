class Lizard extends Option {

  public Lizard(String player) {
    super(player)
  }

  public String getCode() {
    return "l"
  }

  public int beats(Rock other) {
    return -1
  }

  public int beats(Scissors other) {
    return -1
  }

  public int beats(Paper other) {
    return 1
  }

  public int beats(Spock other) {
    return 1
  }

  public int beats(Option other) {
    return 0  
  }
}
