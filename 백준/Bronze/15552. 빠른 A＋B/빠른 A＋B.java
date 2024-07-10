import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(reader.readLine());

        for(int i = 0; i < T; i++){
            String input = reader.readLine();
            String[] parts = input.split(" ");

            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt((parts[1]));

            writer.write(a+b + "\n");
        }

        writer.flush();
        reader.close();
        writer.close();
    }
}
