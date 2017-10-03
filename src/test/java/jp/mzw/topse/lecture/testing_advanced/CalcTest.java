package jp.mzw.topse.lecture.testing_advanced;

import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

import static org.junit.Assert.*;

public class CalcTest extends JUnitStory {

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration().useStoryReporterBuilder(new StoryReporterBuilder().withFormats(Format.CONSOLE).withDefaultFormats());
	}

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(), this).createCandidateSteps();
	}

	private Calc calc;
	private int result;

	@Given("the system is ready")
	public void doGiven() {
		calc = new Calc();
	}

	@When("calculates $a plus $b")
	public void doWhen(int a, int b) {
		result = calc.add(a, b);
	}

	@Then("the result equals to $e")
	public void doThen(int e) {
		assertEquals(e, result);
	}
}
