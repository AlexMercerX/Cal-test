import java.util.*;

public class NumOPHandle {
	// 生成随机运算符
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
				operator = "÷";
				break;
			default:
				operator = "+";
				break;
			}
			return operator;
		}
 	
 	// 生成随机数<包括分数= =>	
 	public Num createNum(int range) {
 		Num num = new Num();
 		Random rand = new Random();
 		// 生成分子，分母
 		int numerator = rand.nextInt(range + 1);
 		int denominator = rand.nextInt(10) + 1;
 		// 随机生成整数
 		if (rand.nextInt(1) == 1) {
 			num.setNumerator(numerator);
 			num.setDenominator(1);
 		} else {
 			num.setNumerator(numerator);
 			num.setDenominator(denominator);
 		}
 		// 约分
 		num = reduction(num);
 		return num;
 	}

 	// 约分方法
 	public Num reduction(Num num) {
 		// 取得分子，分母
 		int numerator = num.getNumerator();
 		int denominator = num.getDenominator();
 		if (numerator == 0) {
 			num.setDenominator(1);
 			return num;
 		}
 		// 计算最大公约数
 		int divisor = getMaxDivisor(numerator, denominator);
 		if (divisor == 1) {
 			return num;
 		} else {
 			num.setNumerator(num.getNumerator() / divisor);
 			num.setDenominator(num.getDenominator() / divisor);
 			return num;
 		}
 	}

 	// 求最大公约数
 	public int getMaxDivisor(int numerator, int denominator) {
 		if (denominator == 0) {
 			return numerator;
 		} else {
 			return getMaxDivisor(denominator, numerator % denominator);
 		}
 	}
    
}
