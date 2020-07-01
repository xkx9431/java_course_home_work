package home_work.week03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PokerPlayer {
    /**
     * name: 玩家姓名
     * handsPokers： 手牌
     */
    char name;
    private List<Poker> handsPokers;

    public PokerPlayer(char name) {
        this.name = name;
        handsPokers = new ArrayList<>();
    }


    public void add(Poker e){
        handsPokers.add( e);
    }

    public String showHandsPokers(){
        Collections.sort(handsPokers);
        StringBuilder handsOn =  new StringBuilder();
        Iterator iterator = handsPokers.iterator();
        while (iterator.hasNext()){
            handsOn.append(iterator.next().toString() + ", ");
        }
        return  handsOn.toString();
    }




}
