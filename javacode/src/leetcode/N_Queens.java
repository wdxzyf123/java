package leetcode;

import java.util.LinkedList;

/**
 * N皇后问题描述：
 * 给定一个NXN的棋盘，让你将N个皇后放置在棋盘上，是它们之间不能互相攻击，皇后之间可以攻击的范围包括：
 * 同一行，同一列，左上，左下，右上，右下的任意距离
 * n       solution(n)
 *  1       1
 *  2       0
 *  3       0
 *  4       2
 *  5       10
 *  6       4
 *  7       40
 *  8       92
 *  9       352
 *  10      724
 */
public class N_Queens {
    public static void main(String[] args) {
        //定义一个数据结构来存放棋盘。
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
        int n = 8;
        String [][] qipan = new String[n][n];
        for (int i = 0; i < qipan.length; i++) {
            for (int j = 0; j < qipan[0].length; j++) {
                qipan[i][j] = ".";
            }
        }
        Nqueens(qipan,0);
        System.out.println(res.size());

    }
    static LinkedList<String[][]> res = new LinkedList<>();
    public static void Nqueens(String[][] qipan, int row) {
        //结束条件是行数row大于棋盘qipan的行数，结束的时候应该把合法的棋盘摆放方式存入res
        if(row == qipan.length) {
            res.add(qipan);
            return;
        }
        //for循环选择，选择列
        int n = qipan[row].length;
        for (int i = 0; i < n; i++) {
            //判断合法性
            if(!isValid(qipan, row, i))
                continue;
            qipan[row][i] = "Q";
            Nqueens(qipan, row+1);
            //撤销
            qipan[row][i] = ".";
        }
    }
    public static boolean isValid(String[][] qipan, int row, int col) {
        int n = qipan.length;
        //正上 列
        for (int i = 0; i < row; i++) {
            if(qipan[i][col] == "Q")
                return false;
        }
        //右上
        for (int i = row-1,  j = col + 1; i >= 0 && j < n  ; i--,j++) {
            if(qipan[i][j] == "Q")
                return false;
        }
        //左上
        for (int i = row-1,  j = col - 1; i >= 0 && j >= 0  ; i--,j--) {
            if(qipan[i][j] == "Q")
                return false;
        }
        return true;
    }
}
