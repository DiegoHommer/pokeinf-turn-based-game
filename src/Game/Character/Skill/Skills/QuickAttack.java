package Game.Character.Skill.Skills;

import Game.Character.Character;
import Game.Character.Skill.Skill;

public class QuickAttack extends Skill {
    //efeito da classe ataquerapido
    private int damage;

    //constantes de controle
    private final int INITIAL_DAMAGE = 20;
    private final int DAMAGE_LEVEL_UP = 5;
    private final int INITIAL_COST = 5;
    private final int COST_LEVEL_UP = 2;
    private final double HIT_CHANCE = 0.7;


    public QuickAttack(){
        this.name="Ataque Rápido";
        this.spritePath="assets//attackSkill.png";
        this.cost=INITIAL_COST;
        this.damage=INITIAL_DAMAGE;
        this.hitChance=HIT_CHANCE;
        this.skillLevel=1;
        this.description = "Ataca por " + this.damage + " de dano";
    }

    public int getDamage(){
        return this.damage;
    }

    // Getters e setters importantes para a classe
    private void setDamage(int value){
        this.damage = value;
    }

    @Override
    // Não melhora o efeito caso ele estiver no nivel maximo, senão, adiciona 5 ao dano causado
    public boolean upgradeEffect(){
        if(this.isMaxLevel()){
            return false;
        }else {
            int currentLevel = this.getSkillLevel();
            int currentDamage = this.getDamage();
            int currentCost = this.getCost();

            this.setSkillLevel(currentLevel + 1);
            this.setDamage(currentDamage + DAMAGE_LEVEL_UP);
            this.setCost(currentCost + COST_LEVEL_UP);
            return true;
        }
    }

    @Override
    public boolean applyEffect(Character casterCharacter, Character targetCharacter) {
        if (this.didItHit()) {
            int currentDamage = this.getDamage();
            int targetShield = targetCharacter.getShield();
            int targetLife = targetCharacter.getLife();

            targetShield = targetShield - 1;
            if (targetShield < 0) {
                targetLife = targetLife - currentDamage;
                targetShield = 0;
                if (targetLife < 0) {
                    targetLife = 0;
                }
            }

            targetCharacter.setShield(targetShield);
            targetCharacter.setLife(targetLife);
            return true;
        } else {
            return false;
        }
    }

}
