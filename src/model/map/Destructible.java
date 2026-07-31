package model.map;

public interface Destructible {
    int getMaximumHp();
    int getHp();
    void setHp(int hp);
    default void damage(int amount){
        if(amount <= 0 ) return;
        setHp(Math.max(0 , getHp() - amount));
    }
    default boolean isDestroyed(){
        return getHp() <= 0;
    }
}
