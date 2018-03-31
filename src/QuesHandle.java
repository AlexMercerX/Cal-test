import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class QuesHandle {
		// 逆波兰式转换
		public ArrayList<Object> toRPN(ArrayList<Object> list) {
			ArrayList<Object> right = new ArrayList<Object>();// 存储右序表达式
			Stacks aStack = new Stacks();// 栈
			String operator;
			int position = 0;// 当前指针位置
			while (true) {
				// 当前指针为符号
				if (isOperator(list.get(position).toString())) {
					// 栈为空，或指针为（，直接进栈
					if (aStack.top == -1
							|| ((String) list.get(position)).equals("(")) {
						aStack.push(list.get(position));
					} else {
						// 指针为）
						if (((String) list.get(position)).equals(")")) {
							// 将栈内（后的运算符出栈
							while (true) {
								if (aStack.top != -1
										&& !((String) aStack.top()).equals("(")) {
									operator = (String) aStack.pop();
									right.add(operator);
								} else {
									if (aStack.top != -1)
										aStack.pop();
									break;
								}
							}
						} else {
							while (true) {
								// 栈不为空，判断优先级
								if (aStack.top != -1
										&& priority((String) list.get(position),
												(String) aStack.top())) {
									operator = (String) aStack.pop();
									if (!operator.equals("("))
										right.add(operator);
								} else {
									break;
								}

							}
							aStack.push(list.get(position));
						}
					}
				}
				// 数字入栈
				else {
					right.add(list.get(position));
				}
				position++;
				if (position >= list.size())
					break;
			}
			// 栈内剩余运算符出栈
			while (aStack.top != -1) {
				operator = (String) aStack.pop();
				if (!operator.equals("("))
					right.add(operator);
			}
			return right;
		}

		// 判断是否为运算符
		public static boolean isOperator(String operator) {
			if (operator.equals("+") || operator.equals("-")|| operator.equals("*") || operator.equals("÷") || operator.equals("(") || operator.equals(")"))
				return true;
			else
				return false;
		}

		// 设置操作符号的优先级别
		public static boolean priority(String operatorout, String operatorin) {
			int m = 0, n = 0;
			String addop[][] = { { "+", "-", "*", "÷", "(", ")" },
					{ "+", "-", "*", "÷", "(", ")" } };
			int first[][] = { { 1, 1, 2, 2, 2, 0 }, { 1, 1, 2, 2, 2, 0 },
					{ 1, 1, 1, 1, 2, 0 }, { 1, 1, 1, 1, 2, 0 },
					{ 2, 2, 2, 2, 2, 0 }, { 2, 2, 2, 2, 2, 2 } };
			for (int i = 0; i < 6; i++) {
				if (operatorin.equalsIgnoreCase(addop[0][i]))
					m = i;
			}
			for (int i = 0; i < 6; i++) {
				if (operatorout.equalsIgnoreCase(addop[1][i]))
					n = i;
			}
			if (first[m][n] == 1) {
				return true;
			} else
				return false;
		}

		// 逆波兰式计算
		public Num countRPN(ArrayList<Object> right) {
			// 栈
			Stacks aStack = new Stacks();
			Num op1, op2, result = null;
			String is = null;
			Iterator<Object> it = right.iterator();

			while (it.hasNext()) {
				Object ob = it.next();
				is = ob.toString();
				if (isOperator(is)) {
					op2 =  (Num) aStack.pop();
					op1 =  (Num) aStack.pop();
					Num do_0 = twoResult(is, op1, op2);
					if (do_0.getDenominator() == 0) {
						return (new Num(-1, 1));
					}
					aStack.push(do_0);
				} else
					aStack.push(ob);
			}
			result = (Num) aStack.pop();
			return result;
		}

		// 计算两个数
		private Num twoResult(String is, Num op1, Num op2) {
			// 结果数
			Num result = new Num();
			NumOPHandle NumOP = new NumOPHandle();
			// 获取分子分母
			int n1 = op1.getNumerator();
			int d1 = op1.getDenominator();
			int n2 = op2.getNumerator();
			int d2 = op2.getDenominator();
			// 判断运算符
			switch (is) {
			case "+":
				if (d1 != d2) {
					n1 = n1 * d2;
					n2 = n2 * d1;
					d1 = d1 * d2;
					d2 = d1;
					result.setNumerator(n1 + n2);
					result.setDenominator(d1);
					result = NumOP.reduction(result);
				} else {
					result.setNumerator(n1 + n2);
					result.setDenominator(d1);
					result = NumOP.reduction(result);
				}
				break;
			case "-":
				if (d1 != d2) {
					n1 = n1 * d2;
					n2 = n2 * d1;
					d1 = d1 * d2;
					d2 = d1;
					result.setNumerator(n1 - n2);
					result.setDenominator(d1);
					result = NumOP.reduction(result);
				} else {
					result.setNumerator(n1 - n2);
					result.setDenominator(d1);
					result = NumOP.reduction(result);
				}
				break;
			case "*":
				result.setNumerator(n1 * n2);
				result.setDenominator(d1 * d2);
				result = NumOP.reduction(result);
				break;
			case "÷":
				if (n2 == 0) {
					result = new Num(0, 0);
					break;
				}
				result.setNumerator(n1 * d2);
				result.setDenominator(d1 * n2);
				result = NumOP.reduction(result);
				break;
			default:
				break;
			}
			return result;
		}
		
		// 输出
		@SuppressWarnings("unchecked")
		public void output(List<Object> list1, List<Object> list2) {
			Ques question = new Ques();
			int i = 0;
			try {
				// 找到TXT位置
				// String file = "H:/D4/rjgc/work1/timu.txt";
				String file1 = "timu.txt";
				BufferedWriter out = null;
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(file1, true)));
					Iterator<Object> itr = list1.iterator();
					while (itr.hasNext()) {
						if (i >= list1.size()) {
							break;
						}
						String timu = (i + 1) + ","
								+ question.toString((ArrayList<Object>) list1.get(i));
						out.write(timu);
						out.newLine();
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			i = 0;
			try {
				// 文件位置
				// String file1 = "H:/D4/rjgc/work1/answer.txt";
				// String file2 = "H:/D4/rjgc/work1/do.txt";
				String file3 = "answer.txt";
				String file4 = "do.txt";
				// 创建输出流
				BufferedWriter out = null;
				BufferedWriter out_do = null;
				try {
					// 定位输出位置
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(file3, true)));
					out_do = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(file4, true)));
					Iterator<Object> itr = list2.iterator();
					while (itr.hasNext()) {
						if (i >= list2.size()) {
							break;
						}
						String answer = (i + 1) + "," + list2.get(i).toString();
						out.write(answer);
						out.newLine();
						out_do.write((i + 1) + ",");
						out_do.newLine();
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						out.close();
						out_do.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
}
