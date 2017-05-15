class Rock extends Option {

  public Rock(String player) {
    super(player)
  }

  public String getCode() {
    return "r"
  }

  public int beats(Paper other) { 
    return -1
  }

  public int beats(Scissors other) {
    return 1
  }

  public int beats(Option other) { 
    return 0
  } 
}
