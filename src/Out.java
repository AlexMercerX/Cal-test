import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Out {
	
	public void compare() {
		// String filename1 = "H:/D4/rjgc/work1/answer.txt";
		// String filename2 = "H:/D4/rjgc/work1/do.txt";
		String filename1 = "answer.txt";
		String filename2 = "do.txt";
		int correct = 0;
		int error = 0;
		int no = 1;
		String error_no = "";
		String correct_no = "";
		File file1, file2;
		String answer = "";
		String answer_do = "";
		BufferedReader br1, br2;
		try {
			file1 = new File(filename1);
			br1 = new BufferedReader(new FileReader(file1));
			file2 = new File(filename2);
			br2 = new BufferedReader(new FileReader(file2));
			while ((answer = br1.readLine()) != null
					&& (answer_do = br2.readLine()) != null) {
				if (answer_do.equalsIgnoreCase(answer)) {
					correct++;
					correct_no = correct_no + no + ",";
					if ((correct % 5) == 0) {
						correct_no += "\n";
					}
				} else {
					error++;
					error_no = error_no + no + ",";
					if ((error % 5) == 0) {
						error_no += "\n";
					}
				}
				no++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("正确数：" + correct);
		System.out.println("错误数：" + error);
		System.out.println("正确题号：" + correct_no);
		System.out.println("错误题号：" + error_no);
	}
}
