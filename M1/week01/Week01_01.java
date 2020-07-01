package home_work.wee01;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class Week01_01 {
    public static void main(String[] args) {
        //problem1
       System.out.println(" problem 1 ");
       whichyear();

        //problem2
        System.out.println(" problem 2 ");
       prefectNum();

        //problem3
        System.out.println(" problem 3 ");
        luckyNums();

        //problem5
        System.out.println(" problem 5 ");
        printBoard();




    }

    private static void printBoard() {
        char [][] board =  new char [17][17];
        board[0][0] = ' ';
        for(int i = 1; i< 11;i++){
            board[0][i] =  (char) ( i - 1  + '0') ;
        }
        for(int i = 11; i<17; i++ ){
            board[0][i] = (char)(i - 11  + 97);
        }
        for(int i = 1; i<17; i++){
            Arrays.fill(board[i],'+');
            board[i][0] = board[0][i];
        }
        for(int i = 0; i< 16; i++){
            for(int j = 0; j< 16; j++){
                System.out.print( board[i][j]);
                System.out.print( ' ');
            }
            System.out.println();
        }

    }

    /**
     * print the lucky num
     */
    private static void luckyNums() {

        int [] reds = new int[33];
        for(int i = 1; i<=33;i++){
            reds[i-1] = i;
        }

        // select one and move to the end of the array
        for (int i = 0; i< 6; i++){
            int num = (int) Math.floor(Math.random()*(33 - i));
            int tmp = reds[33-i-1];
            reds[33-i-1] = reds[num];
            reds[num] = tmp;
        }

        System.out.println("the red num is: " +  reds[32] + " - " + reds[31]  + " - "+ reds[30] + " - "+ reds[29] + " - "+ reds[28] + " - "+ reds[27]);
        System.out.println("the blue num is: " +  (int) Math.ceil(Math.random()*16) );

    }

    /**
     * problem2, print all prefect num
     */
    private static void prefectNum() {
        int sum = 0;
        for(int i = 1; i<1000; i++){
            sum = 0;
            for(int j= 1; j < i; j++){
                if(i%j==0){
                    sum +=j;
                }
            }
            if(sum == i) System.out.println(i);

        }

    }

    /**
     * return which day is today,according to user's input.
     */
    private static void whichyear() {
        // get input
        Scanner scan = new Scanner(System.in);
        System.out.println("calculate which day is today,such as the Jan, 1st is 1");
        System.out.println("please enter the year:[ xxxx ]");
        int year = scan.nextInt();
        System.out.println("please enter the month:[ 1-12 ]");
        int month = scan.nextInt();
        System.out.println("please enter the day:[1-31]");
        int day = scan.nextInt();


        int res = 0;


        // cal whether the year is leap
        boolean isLeap = false;
        if(year%4==0){
            isLeap  =true;
            if(year%100 == 0 && year % 400 !=0){
                isLeap = false;
            }
        }
        int [] months = { 31,28,31,30,31,30,31,31,30,31,30,31 };
        if(isLeap){
            months[1] = 29;
        }
        for (int i = 0; i< month; i++){
            res += months[i];
        }
        System.out.printf("the date: " + year + "-" + month + "-"+day + " is the " + res + " days of this year" );
    }

}
