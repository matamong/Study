package springbook.learningtest.template;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;

public class CalcSumTest {
	Calculator calc;
	String numFilePath;
	
	@Before
	public void setUp() {
		this.calc = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}
	
	@Test
	public void sumOfNumbers() throws IOException {
		assertThat(calc.calcSum(this.numFilePath), is(10));
	}
	
	@Test
	public void multiplyOfNumbers() throws IOException{
		assertThat(calc.calcMultiply(this.numFilePath), is(24));
	}
	
	@Test
	public void concatenate() throws IOException{
		assertThat(calc.concatenate(this.numFilePath), is("1234"));
	}

}
