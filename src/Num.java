public class Num {
	private int numerator; // ·Ö×Ó
	private int denominator; // ·ÖÄ¸

	public Num() {
	}

	public Num(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	@Override
	public String toString() {
		if (denominator == 1) {
			return String.valueOf(numerator);
		} else if (numerator > denominator) {
			int Be = numerator / denominator;
			int Af = numerator % denominator;
			return String.valueOf(Be + "`" + Af + "/" + denominator);
		} else if (numerator == 0) {
			return "0";
		}
		return numerator + "/" + denominator;
	}
}
