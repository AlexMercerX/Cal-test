import java.util.*;

public class NumOPHandle {
	// ������������
		public String createOp() {
			String operator = null;
			Random rand = new Random();
			int n = rand.nextInt(4);
			switch (n) {
			case 0:
				operator = "+";
				break;
			case 1:
				operator = "-";
				break;
			case 2:
				operator = "*";
				break;
			case 3:
				operator = "��";
				break;
			default:
				operator = "+";
				break;
			}
			return operator;
		}
 	
 	// ���������<��������= =>	
 	public Num createNum(int range) {
 		Num num = new Num();
 		Random rand = new Random();
 		// ���ɷ��ӣ���ĸ
 		int numerator = rand.nextInt(range + 1);
 		int denominator = rand.nextInt(10) + 1;
 		// �����������
 		if (rand.nextInt(1) == 1) {
 			num.setNumerator(numerator);
 			num.setDenominator(1);
 		} else {
 			num.setNumerator(numerator);
 			num.setDenominator(denominator);
 		}
 		// Լ��
 		num = reduction(num);
 		return num;
 	}

 	// Լ�ַ���
 	public Num reduction(Num num) {
 		// ȡ�÷��ӣ���ĸ
 		int numerator = num.getNumerator();
 		int denominator = num.getDenominator();
 		if (numerator == 0) {
 			num.setDenominator(1);
 			return num;
 		}
 		// �������Լ��
 		int divisor = getMaxDivisor(numerator, denominator);
 		if (divisor == 1) {
 			return num;
 		} else {
 			num.setNumerator(num.getNumerator() / divisor);
 			num.setDenominator(num.getDenominator() / divisor);
 			return num;
 		}
 	}

 	// �����Լ��
 	public int getMaxDivisor(int numerator, int denominator) {
 		if (denominator == 0) {
 			return numerator;
 		} else {
 			return getMaxDivisor(denominator, numerator % denominator);
 		}
 	}
    
}
