package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class FastAttack extends Skill {
    // class specific atributes
    private int damage;

    // class constants
    private static final String SPRITE_PATH = "assets//attackSkill.png";
    private static final String SKILL_NAME = "Fast-Attack";
    private static final int INITIAL_LEVEL = 1;
    private static final int INITIAL_DAMAGE = 20;
    private static final int LEVEL_UP_DAMAGE = 5;
    private static final int INITIAL_COST = 5;
    private static final int LEVEL_UP_COST = 2;
    private static final double INITIAL_HIT_CHANCE = 0.9;
    private static final int PERCENTAGE = 100;


    public FastAttack(){
        super.setName(SKILL_NAME);
        super.setSpritePath(SPRITE_PATH);
        super.setCost(INITIAL_COST);
        super.setHitChance(INITIAL_HIT_CHANCE);
        super.setSkillLevel(INITIAL_LEVEL);
        this.setDamage(INITIAL_DAMAGE);
        this.description = "Attack for " + this.damage + " damage with a " + 
                            (this.hitChance * PERCENTAGE) + "% chance to hit the target";
    }

    // getters && setters
    private void setDamage(int value){
        this.damage = value;
    }
    private int getDamage(){
        return this.damage;
    }

    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter){
        if(this.didItHit()){
            int currentDamage = this.getDamage();
            int targetShield = targetCharacter.getShield();
            int targetHealth  = targetCharacter.getLife();

            if(targetShield <= 0){
                targetHealth -= currentDamage;
            }else
                targetShield--;

            targetCharacter.setShield(targetShield);
            targetCharacter.setLife(targetHealth);
            System.out.println(targetCharacter.getLife());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean upgradeEffect(){
        if(this.isMaxLevel()){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentDamage = this.getDamage();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setDamage(currentDamage + LEVEL_UP_DAMAGE);
            this.setCost(currentCost + LEVEL_UP_COST);
            return true;
        }
    }

}
