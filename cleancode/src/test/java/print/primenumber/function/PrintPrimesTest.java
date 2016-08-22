package print.primenumber.function;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class PrintPrimesTest {
	@Test
	public void should_print_result() {
		PrintPrimes.main(new String[0]);
	}
}