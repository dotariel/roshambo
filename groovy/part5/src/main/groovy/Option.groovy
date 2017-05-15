abstract class Option {

  String player

  public Option() {
    this("default")
  }

  public Option(String player) {
    this.player = player
  }

  public abstract String getCode()
  public abstract int beats(Option other)
}