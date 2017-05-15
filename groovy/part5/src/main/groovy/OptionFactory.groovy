class OptionFactory {

  private static List<Option> options = [Rock, Paper, Scissors, Spock, Lizard]

  public Option make(String code, String player) {
    Option option = options.collect { o -> o.newInstance(player) }.find { o -> o.code == code }

    if (!option) 
      throw new InvalidOption()

    return option
  }

  public List<String> getOptions() {
    this.optionInstances.collect { o -> o.code }
  }

  private List<Option> getOptionInstances() {
    options.collect { o -> o.newInstance() } 
  }

  public String getOptionSummary() {
    "Options: " + this.optionInstances.collect { o -> "${o.code}=${o.class.simpleName}" }.join(", ")
  }

  public Option random(String player) {
    options[new Random().nextInt(options.size())].newInstance(player)
  }
}
