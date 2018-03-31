import java.util.*;

public class Ques {
	public ArrayList<Object> createarithmetic(int range) {
		// �������ʽ
		ArrayList<Object> list = new ArrayList<Object>();
		// �����&��������
		NumOPHandle NumOP = new NumOPHandle();
		// ������
		Random rand = new Random();
		// ���������1~3��
		int operator_no = rand.nextInt(3) + 1;
		switch (operator_no) {
		// һ�������
		case 1:
			list.add(NumOP.createNum(range));
			list.add(NumOP.createOp());
			list.add(NumOP.createNum(range));
			break;
		case 2:
			// ���ſ�ʼλ��
			int bracket_s = rand.nextInt(3);
			// ���Ž���λ��
			int bracket_e = 0;
			// û������
			if (bracket_s == 0) {
				bracket_e = 0;
			}
			// ������
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
			// ���ſ�ʼλ��
			bracket_s = rand.nextInt(4);
			// ���Ž���λ��
			bracket_e = 0;
			// û������
			if (bracket_s == 0) {
				bracket_e = 0;
			}
			// ������
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
