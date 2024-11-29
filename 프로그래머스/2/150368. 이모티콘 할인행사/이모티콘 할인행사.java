import java.util.*;

class Solution {
    static int[] answer;
    static int maxSubscriber;
    static int[][] emoticonsSaleValues;
    static int numOfEmoticons;
    static int numOfUsers;
    static List<List<Integer>> combinations;
    
    public int[] solution(int[][] users, int[] emoticons) {
        answer = new int[2];
        maxSubscriber = 0;
        numOfEmoticons = emoticons.length;
        numOfUsers = users.length;
        emoticonsSaleValues = new int[numOfEmoticons][5];
        for(int i = 0; i < numOfEmoticons; i++) {
            for(int j = 0; j < 4; j++) {
                emoticonsSaleValues[i][j+1] = emoticons[i] / 10 * (10-(j+1));
            }
        }
        
        for(int i = 0; i < numOfEmoticons; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(emoticonsSaleValues[i][j+1] + " ");
            }
            System.out.println();
        }
        
        combinations = new ArrayList<>();
        
        generateCombination(0, numOfEmoticons, new ArrayList<>());
        
        for(List<Integer> combination : combinations) {
            int subscriber = 0;
            int emoticonTotalSales = 0;
            for(int[] user : users) {
                double criteria = user[0]/10.0;
                if(criteria > 4) continue;
                
                int sum = 0;
                for(int i = 0; i < numOfEmoticons; i++) {
                    if(combination.get(i) >= criteria) {
                        sum += emoticonsSaleValues[i][combination.get(i)];
                    }
                }
                if(sum >= user[1]) {
                    subscriber ++;
                } else {
                    emoticonTotalSales += sum;
                }
            }
            if(maxSubscriber < subscriber) {
                maxSubscriber = subscriber;
                answer[0] = subscriber;
                answer[1] = emoticonTotalSales;
                System.out.println(combination);
            } else if(maxSubscriber == subscriber && emoticonTotalSales > answer[1]) {
                answer[1] = emoticonTotalSales;
            }
        }
        
        return answer;
    }
    
    public static void generateCombination(int depth, int target, List<Integer> combination) {
        if(depth == target) {
            combinations.add(new ArrayList<>(combination));
            return;
        }
        
        for(int i = 1; i < 5; i++) {
            combination.add(i);
            generateCombination(depth+1, target, combination);
            combination.remove(combination.size()-1);
        }
    }
}