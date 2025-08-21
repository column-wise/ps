import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        List<String> messages = new ArrayList<>();
        
        Map<String, String> id2nickname = new HashMap<>();
        
        for(String r : record) {
            String[] inputs = r.split(" ");
            
            if(inputs.length < 3) continue;
            
            String command = inputs[0];
            String uid = inputs[1];
            String nickname = inputs[2];
            
            if("Enter".equals(command) || "Change".equals(command)) {
                id2nickname.put(uid, nickname);
            }
        }
        
        for(String r : record) {
            String[] inputs = r.split(" ");
            String command = inputs[0];
            String uid = inputs[1];
            
            if("Enter".equals(command)) {
                messages.add(id2nickname.get(uid)+"님이 들어왔습니다.");
            } else if("Leave".equals(command)) {
                messages.add(id2nickname.get(uid)+"님이 나갔습니다.");
            }
        }
        
        String[] answer = new String[messages.size()];
        for(int i = 0; i < messages.size(); i++) {
            answer[i] = messages.get(i);
        }
        return answer;
    }
}