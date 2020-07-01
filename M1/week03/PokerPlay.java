package home_work.week03;

import java.util.ArrayList;
import java.util.Collections;

public class PokerPlay {
    // 扑克牌
    private Pokers pokers = new Pokers();

    // 扑克牌玩家
    private PokerPlayer playA = new PokerPlayer('A');
    private PokerPlayer playB = new PokerPlayer('B');
    private PokerPlayer playC = new PokerPlayer('C');

    /**
     *  剩余底牌数
     */
    private int leaveNum = 3;



    // 打印当前牌
    public void showPokers(){
        System.out.println(pokers);
    }

    //洗牌
    public void shufflePokers(){
        pokers.shuffle();
    }

    //分发手牌
    public void  distributePoker(){
        for(int i= 0;i<pokers.size()-leaveNum; i++ ){
            if(i%leaveNum==0){
                playA.add(pokers.getPorker(i));
            } else if( i% leaveNum ==1){
                playB.add(pokers.getPorker(i));
            } else if( i% leaveNum == 2 ){
                playC.add(pokers.getPorker(i));
            }
        }
    }

    // 查看手牌
    private void checkHands() {
        System.out.println("当前玩家为：" + playA.name);
        System.out.println("手牌为：" +  playA.showHandsPokers());
        System.out.println("当前玩家为：" + playB.name);
        System.out.println("手牌为：" +  playB.showHandsPokers());
        System.out.println("当前玩家为：" + playC.name);
        System.out.println("手牌为：" +  playC.showHandsPokers());
    }

    // 查看底牌
    private void checkLeaves() {
        System.out.println(" 剩余底牌为： ");
        for(int i = 0; i< leaveNum; i++){
            System.out.printf( pokers.getPorker(pokers.size() -1 -i).toString() + "  ");
        }
    }


    /**
     * poker 游戏入口
     */
    public static void main(String[] args) {

        //game init  and shuffle
        PokerPlay pokerPlayGame = new PokerPlay();
        pokerPlayGame.showPokers();
        System.out.println("洗牌……");
        pokerPlayGame.shufflePokers();
        pokerPlayGame.showPokers();

        //分发手牌
        pokerPlayGame.distributePoker();

        // 查看手牌
        pokerPlayGame.checkHands();

        // 查看底牌
        pokerPlayGame.checkLeaves();

    }

}
