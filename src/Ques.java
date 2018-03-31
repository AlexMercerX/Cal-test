import java.util.*;

public class Ques {
	public ArrayList<Object> createarithmetic(int range) {
		// 算数表达式
		ArrayList<Object> list = new ArrayList<Object>();
		// 随机数&随机运算符
		NumOPHandle NumOP = new NumOPHandle();
		// 控制数
		Random rand = new Random();
		// 运算符个数1~3个
		int operator_no = rand.nextInt(3) + 1;
		switch (operator_no) {
		// 一个运算符
		case 1:
			list.add(NumOP.createNum(range));
			list.add(NumOP.createOp());
			list.add(NumOP.createNum(range));
			break;
		case 2:
			// 括号开始位置
			int bracket_s = rand.nextInt(3);
			// 括号结束位置
			int bracket_e = 0;
			// 没有括号
			if (bracket_s == 0) {
				bracket_e = 0;
			}
			// 有括号
			else {
				bracket_e = bracket_s + 1;
			}
			for (int i = 1; i <= 3; i++) {
				if (i == bracket_s) {
					list.add("(");
				}
				list.add(NumOP.createNum(range));
				if (i == bracket_e) {
					list.add(")");
				}
				list.add(NumOP.createOp());
			}
			list.remove(list.size() - 1);
			break;
		case 3:
			// 括号开始位置
			bracket_s = rand.nextInt(4);
			// 括号结束位置
			bracket_e = 0;
			// 没有括号
			if (bracket_s == 0) {
				bracket_e = 0;
			}
			// 有括号
			else {
				bracket_e = bracket_s + rand.nextInt(2) + 1;
				if (bracket_e >= 4) {
					bracket_e = 4;
				}
			}
			for (int i = 1; i <= 4; i++) {
				if (i == bracket_s) {
					list.add("(");
				}
				list.add(NumOP.createNum(range));
				if (i == bracket_e) {
					list.add(")");
				}
				list.add(NumOP.createOp());
			}
			list.remove(list.size() - 1);
			break;
		default:
			list.add(NumOP.createNum(range));
			list.add(NumOP.createOp());
			list.add(NumOP.createNum(range));

			break;
		}
		return list;
	}

	public String toString(Stacks s) {
		Iterator<Object> itr = s.iterator();
		String s1 = "";
		while (itr.hasNext()) {
			s1 = itr.next().toString() + s1;
		}
		return s1;
	}

	public String toString(ArrayList<Object> list) {
		String s1 = "";
		for (int i = 0; i < list.size(); i++) {
			s1 += list.get(i).toString();
		}
		return s1;
	}
}
