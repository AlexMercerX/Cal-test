import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class UI {

	public static void main(String[] args) throws NumberFormatException,
	IOException
	{
		int choose = 0;
		InputStreamReader is_reader = new InputStreamReader(System.in);
		while (choose != 3) {
			printView();
			choose = Integer.parseInt(new BufferedReader(is_reader).readLine());
			switch (choose) {
			case 1:
				creatQues();
				System.out.println("��Ŀ����������ļ������ɣ�");
				System.out.println("�뽫����������ļ���Ӧλ�ã�");
				break;
			case 2:
				checkAnswer();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("��������Ч��������");
				break;
			}
		}

	}
	
	public static void printView() {
		System.out.println("��ѡ�������");
		System.out.println("1:������Ŀ");
		System.out.println("2:���մ�");
		System.out.println("3:�˳�");
	}

	public static void creatQues() throws NumberFormatException, IOException {
		Num result = new Num();
		Ques question = new Ques();
		QuesHandle handle = new QuesHandle();
		Check chk = new Check();
		chk.checkfile();
		// Ĭ�����ַ�Χ
		int range = 10;
		// Ĭ����Ŀ��
		int ques_no = 10;
		InputStreamReader is_reader = new InputStreamReader(System.in);
		System.out.print("���������ַ�Χ��");
		range = Integer.parseInt(new BufferedReader(is_reader).readLine());
		System.out.print("��������Ŀ����");
		ques_no = Integer.parseInt(new BufferedReader(is_reader).readLine());
		// ��������ɵı��ʽ
		List<Object> ques = new ArrayList<Object>();
		// ��Ŵ�
		List<Object> answer = new ArrayList<Object>();
		for (int i = 1; i <= ques_no; i++) {
			// ���ɱ��ʽ
			ArrayList<Object> list = question.createarithmetic(range);
			// �����沨��ʽ
			ArrayList<Object> right = handle.toRPN(list);

			// �����
			result = handle.countRPN(right);
			// �ж���Ч��
			if (chk.checkRPN(question.toString(list)) && chk.checkanswer(result)) {
				ques.add(list);
				answer.add(result);
			} else {
				i--;
			}
		}
		handle.output(ques, answer);
	}

	public static void checkAnswer() {
		Out out = new Out();
		out.compare();
	}

}
