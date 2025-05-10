import java.util.*;
import java.lang.Math;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = -1;
        int maxHealth = health;
        int curHealth = health;
        int maxDuration = bandage[0];
        int heal = bandage[1];
        int extraHeal = bandage[2];
        int turn = 1;
        int duration = 0;
        
        Queue<Attack> queue = new LinkedList<>();
        for(int[] attack : attacks) {
            queue.add(new Attack(attack[0], attack[1]));
        }
        
        while(curHealth > 0 && !queue.isEmpty()) {
            if(turn == queue.peek().turn) {
                Attack a = queue.poll();
                System.out.println(a.turn + " " + a.damage);
                curHealth -= a.damage;
                duration = 0;
            } else {
                curHealth = Math.min(curHealth + heal, maxHealth);
                duration += 1;
                if(duration == maxDuration) {
                    curHealth = Math.min(curHealth + extraHeal, maxHealth);
                    duration = 0;
                }
            }
            turn ++;
        }
        
        if(curHealth > 0) {
            answer = curHealth;
        } else {
            answer = -1;
        }
        
        return answer;
    }
    
    private class Attack {
        int turn;
        int damage;
        
        Attack(int turn, int damage) {
            this.turn = turn;
            this.damage = damage;
        }
    }
}