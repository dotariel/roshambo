class OptionFactory {

  private static List<Option> options = [Rock, Paper, Scissors]

  public Option make(String code, String player) {
    Option option = options.collect { o -> o.newInstance(player) }.find { o -> o.code == code }

    if (!option) 
      throw new InvalidOption()

    return option
  }

  public List<String> getOptions() {
    options.collect { o -> o.newInstance() }.collect { o -> o.code }
  }

  public Option random(String player) {
    options[new Random().nextInt(options.size())].newInstance(player)
  }
}
