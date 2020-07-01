package home_work.week2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 双人对战五子棋，
 * 棋子标志  ‘o’  vs  ‘x’
 *
 * border：char[][]  棋盘 17*17, 落子空间 16*16
 * turns: int 下棋的轮数，每次落子后加1
 * player1,2 : int 选手
 * row,col :  int 当前落子的位置
 * curPlayer： 当前落子用户
 * */

public class FiveChessGame {
    // property variables
    private char [][] border =  new char [17][17];
    private int turns = -1;
    private boolean gameOver = false;
    private char player1;
    private char player2;
    private char curPlayer;
    private char borderEle = '+';

    private int row,col;

    Scanner sc = new Scanner(System.in);


    /**
     * 游戏入口
     * 1. 初始化棋盘
     * 2.初始化选手，决定不同棋子的归属
     * 3.开始游戏，中间判断谁落子，落子是否合法，以及落子后游戏是否结束
     * 4.游戏结束
     */

    public void play() {

        //init the border
        this.initBorder();

        // init the player, for who first play,using which color
        this.initPlayer();

        // take turns to setBorder
        while(!gameOver){
            this.turns();
        }

        // game over,print the winner
        System.out.println("game is Over, winner is " + (this.turns%2==0 ? player1 + " ->player1": player2 + " ->player2"));
    }

    /**
     * 决定选手1,2谁用不同的棋子，随机生成
     */
    private void initPlayer() {
        if(Math.random()>0.5){
            this.player1 = 'o';
            this.player2 = 'x';
        }else{
            this.player1 = 'x';
            this.player2 = 'o';
        }
        System.out.println("Game begin, player 1 is using " + this.player1 + " and player2 is using " + this.player2 );
    }

    /**
     * 每轮流程
     * 1. 判断当前落子用户
     * 2. 落子后打印棋盘
     * 3. 判断当前是否游戏结束。
     */
    private void turns() {
        this.switchPlayer();
        this.showBorder();
        gameOver = isWin(row+1,col+1,curPlayer);
    }

    /**
     *  print the current  chess border
     */
    private void showBorder() {
        for(int i = 0; i< 17; i++){
            for(int j = 0; j< 17; j++){
                System.out.print( border[i][j]);
                System.out.print( ' ');
            }
            System.out.println();
        }
    }

    /**
     * calculate is current player win,check rows, cols and leftDiagonal, rightDiagonals
     * if there 5 in Continuous,game over
     * curRow ： border数组中实际行位置
     * curCol ： border数组中实际列位置
     */
    private boolean isWin(int curRow, int curCol, char curPlayer) {
        int count = 1;

        int d1,d2; // two direction search

        // check rows
        d1 = curRow-1;
        d2 = curRow+1;
        while( d1>0 && border[d1][curCol]== curPlayer){
            count++;
            d1--;
        }
        while(d2<17&&border[d2][curCol] == curPlayer){
            count++;
            d2++;
        }
        if(count>=5){
            return true;
        }

        // check cols
        count = 1;
        d1 = curCol-1;
        d2 = curCol+1;
        while( d1>0 && border[curRow][d1]== curPlayer){
            count++;
            d1--;
        }
        while(d2<17&&border[curRow][d2] == curPlayer){
            count++;
            d2++;
        }
        if(count>=5){
            return true;
        }

        // check left Diagonal
        count = 1;
        d1 = curRow-1;
        d2 = curCol-1;
        while( d1>0 &&d2>0 && border[d1][d2]== curPlayer){
            count++;
            d1--;
            d2--;
        }
        d1 = curRow+1;
        d2 = curCol+1;
        while(d1<17&&d2<17&&border[d1][d2] == curPlayer){
            count++;
            d2++;
            d1++;
        }
        if(count>=5){
            return true;
        }

        // check right Diagonal
        count = 1;
        d1 = curRow-1;
        d2 = curCol+1;
        while( d1>0 &&d2<17 && border[d1][d2]== curPlayer){
            count++;
            d1--;
            d2++;
        }
        d1 = curRow+1;
        d2 = curCol-1;
        while(d1<17&&d2>0&&border[d1][d2] == curPlayer){
            count++;
            d1++;
            d2--;
        }
        if(count>=5){
            return true;
        }

        return false;

    }

    /**
     *     set border[row][col]into symbol,if illegal,re input!
     *     chess：  当前chess 应该的类型
     *     row,col 当前用户输入的落子位置
     */
    private void setBorder(int row,int col, char chess ) {

        // check row,col range
        if(row < 0 || row > 15 || col< 0 || col> 15 ){
            System.out.println("the row or col range is not correct, please input num [0-15]");
            this.turns--;
            switchPlayer();
        } else if ( border[row+1][col+1] != borderEle ) { // 当前位置已使用
            System.out.println("the position may not be available, please check and re-input");
            this.turns--;
            switchPlayer();
        }
        border[row+1][col+1] = chess;
    }

    /**
     * according turns to switch player to set chess.
     */
    private void switchPlayer() {
        this.turns++;
        // 根据轮数，计算当前落子用户
        curPlayer = getCurPlayer();
        System.out.println("Now in player "+ curPlayer + " turn, please input [row],[col] to set !" );
        // 得到输入
        row = sc.nextInt();
        col = sc.nextInt();
        //落子
        setBorder(row,col,curPlayer);
    }

    /**
     * 初始化棋盘
     * 棋盘落子区域单元使用 “+” 标记
     * 落子区域 16*16
     */
    private void initBorder() {
        // set up first row
        border[0][0] = ' ';
        for(int i = 1; i< 11;i++){
            border[0][i] =  (char) ( i - 1  + '0') ;
        }
        for(int i = 11; i<17; i++ ){
            border[0][i] = (char)(i - 11  + 97);
        }
        for(int i = 1; i<17; i++){
            Arrays.fill(border[i],'+');
            border[i][0] = border[0][i];
        }
    }

    /**
     * @return  根据轮次来决定当前落子的选手
     */
    private char getCurPlayer(){
        return this.turns%2==0 ? player1: player2;
    }
}
