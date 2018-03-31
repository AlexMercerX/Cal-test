import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class QuesHandle {
		// �沨��ʽת��
		public ArrayList<Object> toRPN(ArrayList<Object> list) {
			ArrayList<Object> right = new ArrayList<Object>();// �洢������ʽ
			Stacks aStack = new Stacks();// ջ
			String operator;
			int position = 0;// ��ǰָ��λ��
			while (true) {
				// ��ǰָ��Ϊ����
				if (isOperator(list.get(position).toString())) {
					// ջΪ�գ���ָ��Ϊ����ֱ�ӽ�ջ
					if (aStack.top == -1
							|| ((String) list.get(position)).equals("(")) {
						aStack.push(list.get(position));
					} else {
						// ָ��Ϊ��
						if (((String) list.get(position)).equals(")")) {
							// ��ջ�ڣ�����������ջ
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
								// ջ��Ϊ�գ��ж����ȼ�
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
				// ������ջ
				else {
					right.add(list.get(position));
				}
				position++;
				if (position >= list.size())
					break;
			}
			// ջ��ʣ���������ջ
			while (aStack.top != -1) {
				operator = (String) aStack.pop();
				if (!operator.equals("("))
					right.add(operator);
			}
			return right;
		}

		// �ж��Ƿ�Ϊ�����
		public static boolean isOperator(String operator) {
			if (operator.equals("+") || operator.equals("-")|| operator.equals("*") || operator.equals("��") || operator.equals("(") || operator.equals(")"))
				return true;
			else
				return false;
		}

		// ���ò������ŵ����ȼ���
		public static boolean priority(String operatorout, String operatorin) {
			int m = 0, n = 0;
			String addop[][] = { { "+", "-", "*", "��", "(", ")" },
					{ "+", "-", "*", "��", "(", ")" } };
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

		// �沨��ʽ����
		public Num countRPN(ArrayList<Object> right) {
			// ջ
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

		// ����������
		private Num twoResult(String is, Num op1, Num op2) {
			// �����
			Num result = new Num();
			NumOPHandle NumOP = new NumOPHandle();
			// ��ȡ���ӷ�ĸ
			int n1 = op1.getNumerator();
			int d1 = op1.getDenominator();
			int n2 = op2.getNumerator();
			int d2 = op2.getDenominator();
			// �ж������
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
			case "��":
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
		
		// ���
		@SuppressWarnings("unchecked")
		public void output(List<Object> list1, List<Object> list2) {
			Ques question = new Ques();
			int i = 0;
			try {
				// �ҵ�TXTλ��
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
				// �ļ�λ��
				// String file1 = "H:/D4/rjgc/work1/answer.txt";
				// String file2 = "H:/D4/rjgc/work1/do.txt";
				String file3 = "answer.txt";
				String file4 = "do.txt";
				// ���������
				BufferedWriter out = null;
				BufferedWriter out_do = null;
				try {
					// ��λ���λ��
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
