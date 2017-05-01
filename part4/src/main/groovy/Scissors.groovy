class Scissors extends Option {

  public Scissors(String player) {
    super(player)
  }

  public String getCode() {
    return "s"
  }

  public int beats(Paper other) { 
    return 1
  }

  public int beats(Rock other) {
    return -1
  }

  public int beats(Option other) { 
    return 0
  } 
}
