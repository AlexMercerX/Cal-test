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
				System.out.println("题目，答案与答题文件已生成！");
				System.out.println("请将答案填入答题文件对应位置！");
				break;
			case 2:
				checkAnswer();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("请输入有效操作数！");
				break;
			}
		}

	}
	
	public static void printView() {
		System.out.println("请选择操作：");
		System.out.println("1:生成题目");
		System.out.println("2:对照答案");
		System.out.println("3:退出");
	}

	public static void creatQues() throws NumberFormatException, IOException {
		Num result = new Num();
		Ques question = new Ques();
		QuesHandle handle = new QuesHandle();
		Check chk = new Check();
		chk.checkfile();
		// 默认数字范围
		int range = 10;
		// 默认题目数
		int ques_no = 10;
		InputStreamReader is_reader = new InputStreamReader(System.in);
		System.out.print("请输入数字范围：");
		range = Integer.parseInt(new BufferedReader(is_reader).readLine());
		System.out.print("请输入题目数：");
		ques_no = Integer.parseInt(new BufferedReader(is_reader).readLine());
		// 存放已生成的表达式
		List<Object> ques = new ArrayList<Object>();
		// 存放答案
		List<Object> answer = new ArrayList<Object>();
		for (int i = 1; i <= ques_no; i++) {
			// 生成表达式
			ArrayList<Object> list = question.createarithmetic(range);
			// 生成逆波兰式
			ArrayList<Object> right = handle.toRPN(list);

			// 计算答案
			result = handle.countRPN(right);
			// 判断有效性
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
