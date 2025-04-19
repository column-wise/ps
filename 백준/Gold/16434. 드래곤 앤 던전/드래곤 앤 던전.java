import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Room[] rooms;
	static long initialAtk;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		initialAtk = Integer.parseInt(st.nextToken());

		rooms = new Room[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int hp = Integer.parseInt(st.nextToken());
			rooms[i] = new Room(type, a, hp);
		}

		long left = 1;
		long right = (long) 9e18;

		while(left <= right) {
			long maxHp = (left + right) / 2;
			if(canBeatDragon(maxHp)) {
				right = maxHp - 1;
			} else {
				left = maxHp + 1;
			}
		}

		System.out.println(left);
	}

	private static class Room {
		int type;
		int a;
		int hp;

		public Room (int type, int a, int hp) {
			this.type = type;
			this.a = a;
			this.hp = hp;
		}
	}

	private static boolean canBeatDragon (long maxHp) {
		long currentAtk = initialAtk;
		long currentHp = maxHp;

		for(Room room : rooms) {
			if(room.type == 1) {
				long attackCount = (room.hp - 1) / currentAtk;
				currentHp -= attackCount * room.a;
				if(currentHp <= 0) {
					return false;
				}
			} else {
				currentAtk += room.a;
				currentHp = Math.min(currentHp + room.hp, maxHp);
			}
		}

		return true;
	}
}
