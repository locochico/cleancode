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
    private PrintStream out;

    @Before
    public void setUp() throws FileNotFoundException {
        out = System.out;
        System.setOut(new PrintStream(new FileOutputStream("lead")));
    }

   @After
    public void tearDown() {
        System.setOut(out);
        new File("lead").delete();
    }

    @Test
    public void
    makeSureMatchesGold() throws IOException {
        new PrintPrimes().main(new String[0]);
        BufferedReader lead = new BufferedReader(new FileReader("lead"));
        BufferedReader gold = new BufferedReader(new FileReader("src/test/java/print/primenumber/function/gold"));
        String line;
        while((line = gold.readLine()) != null)
            assertEquals(line, lead.readLine());
      //  assertEquals(null, lead.readLine());
    }
}