import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double sum = 0;
        double grade = 0;
        for(int i = 0; i<20; i++){
            String[] input = reader.readLine().split(" ");
            double credit = Double.parseDouble(input[1]);
            switch (input[2]){
                case "A+":
                    grade += 4.5*credit;
                    sum += credit;
                    break;
                case "A0":
                    grade += 4.0*credit;
                    sum += credit;
                    break;
                case "B+":
                    grade += 3.5*credit;
                    sum += credit;
                    break;
                case "B0":
                    grade += 3.0*credit;
                    sum += credit;
                    break;
                case "C+":
                    grade += 2.5*credit;
                    sum += credit;
                    break;
                case "C0":
                    grade += 2.0*credit;
                    sum += credit;
                    break;
                case "D+":
                    grade += 1.5*credit;
                    sum += credit;
                    break;
                case "D0":
                    grade += credit;
                    sum += credit;
                    break;
                case "F":
                    sum += credit;
                    break;
            }
        }

        System.out.println(grade/sum);
        reader.close();
    }
}
