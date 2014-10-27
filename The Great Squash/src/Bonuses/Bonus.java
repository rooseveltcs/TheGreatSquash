/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bonuses;

/**
 *
 * @author ros_aljacobson001
 */
public class Bonus {
    private int A_C_BONUS = 0;
    private int M_P_BONUS = 0;
    private double HEALTH_BONUS = 0;
    private int MANA_BONUS = 0;
    
    public Bonus(int ac,int mp,double health,int mana){
        A_C_BONUS = ac;
        M_P_BONUS = mp;
        HEALTH_BONUS = health;
        MANA_BONUS = mana;
    }
    
    public void setACBonus(int aCBonus){
        A_C_BONUS = aCBonus;
    }
    
    public int getACBONUS(){
        return A_C_BONUS;
    }
    
}
