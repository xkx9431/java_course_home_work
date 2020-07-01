package home_work.week03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * pokers： 一副54张的扑克牌
 */
public class Pokers {
    static public  ArrayList<Poker> pokers = new ArrayList<>();
    {
        for(int i =0; i<13;i++){
            pokers.add( new Poker( i,PokerSymbol.Heart ));
            pokers.add( new Poker( i,PokerSymbol.Diam ));
            pokers.add( new Poker( i,PokerSymbol.Clubs ));
            pokers.add( new Poker( i,PokerSymbol.Spades ));

        }
        pokers.add(new Poker(52,PokerSymbol.joker));
        pokers.add(new Poker(53,PokerSymbol.JOKER));
    }

    /**
     * 用于初始化牌：
     */
    public void init(){
        for(int i =0; i<13;i++){
            pokers.set(i,new Poker( i,PokerSymbol.Heart ));
            pokers.set(i,new Poker( i,PokerSymbol.Diam ));
            pokers.set(i,new Poker( i,PokerSymbol.Clubs ));
            pokers.set(i,new Poker( i,PokerSymbol.Spades ));
        }
        pokers.set(52,new Poker(52, PokerSymbol.joker));
        pokers.set(53, new Poker(53,PokerSymbol.JOKER));
    }

    /**
     * 洗牌
     */
    static void  shuffle(){
        Collections.shuffle(pokers);
    }

    @Override
    public String toString() {
        Iterator iterator = pokers.iterator();
        StringBuilder pokersStr = new StringBuilder();
        while(iterator.hasNext()){
            pokersStr.append(iterator.next().toString() + "\n");
        }
        return  pokersStr.toString();
    }

    public int size(){
        return pokers.size();
    }

    public Poker getPorker(int i){
        return  pokers.get(i);
    }

//    public static void main(String[] args) {
//        Pokers ps1 = new Pokers();
//        ps1.init();
//        ps1.shuffle();
//        System.out.println(ps1);
//    }

}
