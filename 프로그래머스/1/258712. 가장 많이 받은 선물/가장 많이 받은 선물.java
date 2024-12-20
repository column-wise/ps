import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        StringTokenizer st;
        int answer = 0;
        Map<String, Integer> name2id = new HashMap<>();
        int[][] giftsCount = new int[friends.length][friends.length];
        int[] giftsFactor = new int[friends.length];
        int[] giftsToReceive = new int[friends.length];

        for(int i = 0; i < friends.length; i++) {
            name2id.put(friends[i], i);
        }

        for(int i = 0; i < gifts.length; i++) {
            st = new StringTokenizer(gifts[i]);
            int fromId = name2id.get(st.nextToken());
            int toId = name2id.get(st.nextToken());

            giftsCount[fromId][toId]++;
            giftsFactor[fromId]++;
            giftsFactor[toId]--;
        }

        for(int i = 0; i < friends.length; i++) {
            for(int j = 0; j < friends.length; j++) {
                if(i == j) continue;

                if(giftsCount[i][j] > giftsCount[j][i]) {
                    giftsToReceive[i]++;
                } else if(giftsCount[i][j] < giftsCount[j][i]) {
                    giftsToReceive[j]++;
                } else {
                    if(giftsFactor[i] > giftsFactor[j]) {
                        giftsToReceive[i]++;
                    } else if(giftsFactor[i] < giftsFactor[j]) {
                        giftsToReceive[j]++;
                    }
                }
            }
        }
        
        Arrays.sort(giftsToReceive);
        answer = giftsToReceive[friends.length - 1] / 2;
        
        return answer;
    }
}