package home_work.week03;

/**
 * 扑克牌枚举类
 * 大小王： JOKER("JOKER"),joker("joker")
 * 四种花色：Heart("♥"),Diam("♦"),Spades("♠"),Clubs("♣")
 */
public enum PokerSymbol {
   Heart("♥"),Diam("♦"),Spades("♠"),Clubs("♣"),JOKER("JOKER"),joker("joker");
   private String symbol;

   PokerSymbol(String symbol){
       this.symbol = symbol;
   }

    /**
     * @return 从写扑克花色打印方法
     */
    @Override
    public String toString() {
        return symbol;
    }
}
