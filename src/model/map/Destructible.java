package model.map;

public interface Destructible {
    int getMaximumHP();
    int getHP();
    void setHP(int HP);
    default void damage(int amount){
        if(amount <= 0 ) return;
        setHP(Math.max(0 , getHP() - amount));
    }
    default boolean isDestroyed(){
        return getHP() <= 0;
    }
}
