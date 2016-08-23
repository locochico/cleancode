package print.primenumber.function;

public class PrintPrimes {
	static final int numberOfPrimes = 1000;
	static final int linesPerPage = 50;
	static final int columns = 4;
	
	public static void main(String[] args) {
		PrimePrinterHelper primePrinterHelper = new PrimePrinterHelper();
		int[] primes = primePrinterHelper.invoke();
		primePrinterHelper.printNumbers(primes, numberOfPrimes, linesPerPage, columns);
	}
}

class PrimePrinterHelper {
	private final int numberOfPrimes = 1000;
	private int ordmax = 30;
	private int primes[] = new int[numberOfPrimes + 1];
	private int candidate;
	private int primeIndex;
	private boolean possiblyPrime;
	private int ord;
	private int square;
	private int n;
	private int multiples[] = new int[ordmax + 1];
	
	public int[] invoke() {
		candidate = 1;
		primeIndex = 1;
		primes[1] = 2;
		ord = 2;
		square = 9;
		while (primeIndex < numberOfPrimes) {
			do {
				candidate = candidate + 2;
				if (candidate == square) {
					ord = ord + 1;
					square = primes[ord] * primes[ord];
					multiples[ord - 1] = candidate;
				}
				n = 2;
				possiblyPrime = true;
				while (n < ord && possiblyPrime) {
					while (multiples[n] < candidate)
						multiples[n] = multiples[n] + primes[n] + primes[n];
					if (multiples[n] == candidate)
						possiblyPrime = false;
					n = n + 1;
				}
			} while (!possiblyPrime);
			primeIndex = primeIndex + 1;
			primes[primeIndex] = candidate;
		}
		return primes;
	}
	
	public void printNumbers(int[] numbers, int numberOfPrimes, int linesPerPage, int columns) {
		new NumberPrinter(numbers, numberOfPrimes, linesPerPage, columns).invoke();
	}
}
