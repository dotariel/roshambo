import org.junit.*
import groovy.mock.interceptor.*

class OptionFactoryTest {

  @Test(expected=InvalidOption)
  public void should_throw_exception_if_invalid_option() {
    new OptionFactory().make("foo", "fred")
  }
 
  @Test
  public void should_make_option_from_string() {
    assert new OptionFactory().make("s", "fred") instanceof Scissors
    assert new OptionFactory().make("r", "fred") instanceof Rock
    assert new OptionFactory().make("p", "fred") instanceof Paper
  }

  @Test
  public void should_provide_list_of_options() {
    assert new OptionFactory().options == ['r', 'p', 's']
  }

  @Test
  public void should_get_random_option() {
    assert new OptionFactory().random("fred") instanceof Option
  }

  @Test
  public void should_get_options_summary() {
    assert new OptionFactory().optionSummary == 'Options: r=Rock, p=Paper, s=Scissors'
  }
}
