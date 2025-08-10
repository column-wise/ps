import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		List<String> mbtis;

		for(int test_case = 1; test_case <= T; test_case++) {
			mbtis = new ArrayList<>();
			int max = 8;
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				String mbti = st.nextToken();
				mbtis.add(mbti);
			}

			if(mbtis.size() > 48) {
				sb.append("0").append("\n");
			} else {
				for(int i = 0; i < mbtis.size(); i++) {
					for(int j = i+1; j < mbtis.size(); j++) {
						for(int k = j+1; k < mbtis.size(); k++) {
							int distance = 0;
							distance += getDistance(mbtis.get(i), mbtis.get(j));
							distance += getDistance(mbtis.get(j), mbtis.get(k));
							distance += getDistance(mbtis.get(k), mbtis.get(i));
							max = Math.min(max, distance);
						}
					}
				}
				sb.append(max).append("\n");
			}
		}

		System.out.println(sb);
	}

	private static int getDistance (String a, String b) {
		int distance = 0;
		for(int i = 0; i < 4; i++) {
			char c1 = a.charAt(i);
			char c2 = b.charAt(i);
			if(c1 != c2) distance++;
		}

		return distance;
	}
}
