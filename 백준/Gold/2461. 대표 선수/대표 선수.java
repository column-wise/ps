import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int totalStudents = N*M;
		int result = Integer.MAX_VALUE;

		int[] studentsInRange = new int[N];
		int coveredClasses = 0;
		List<Student> students = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				students.add(new Student(i, Integer.parseInt(st.nextToken())));
			}
		}

		students.sort(new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return o1.compareTo(o2);
			}
		});


		int minPointer = 0;
		for(int maxPointer = 0; maxPointer < totalStudents; maxPointer++) {
			Student curMaxStudent = students.get(maxPointer);
			studentsInRange[curMaxStudent.classNum] ++;
			if(studentsInRange[curMaxStudent.classNum] == 1) {
				coveredClasses++;
			}

			while(coveredClasses == N) {
				Student curMinStudent = students.get(minPointer);
				studentsInRange[curMinStudent.classNum] --;
				if(studentsInRange[curMinStudent.classNum] == 0) {
					result = Math.min(result, curMaxStudent.status - curMinStudent.status);
					coveredClasses--;
				}
				minPointer ++;
			}
		}

		System.out.println(result);
	}

	private static class Student implements Comparable<Student> {
		int classNum;
		int status;

		Student(int classNum, int status) {
			this.classNum = classNum;
			this.status = status;
		}

		public int compareTo(Student o) {
			return Integer.compare(status, o.status);
		}
	}
}
