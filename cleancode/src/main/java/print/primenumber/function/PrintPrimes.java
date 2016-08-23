package print.primenumber.function;

public class PrintPrimes {
	static final int numberOfPrimes = 1000;
	static final int linesPerPage = 50;
	static final int columns = 4;
	
	public static void main(String[] args) {
		PrimeGenerator primeGenerator = new PrimeGenerator();
		int[] primes = primeGenerator.generatorPrimes(numberOfPrimes);
		new NumberPrinter(linesPerPage, columns).print(primes, numberOfPrimes);		
	}
}


