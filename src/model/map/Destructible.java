package model.map;

public interface Destructible {
    int getMaximumHP();
    int getHP();
    void setHP(int HP);
    void damage(int amount);
    default boolean isDestroyed(){
        return getHP() <= 0;
    }
}
