package com.example.a8queensgame;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Point;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    int[] outLocation = new int[2];
    float xcoor;
    float ycoor;
    char[][] board = new char[2000][2000];
    List queens = new LinkedList();
    int queensLeft = 8;




    public void blueQueen(View view) {
        /* gets the views ID*/
        ImageButton selected = findViewById(view.getId());
        /* get the buttons location on screen*/
        selected.getLocationInWindow(outLocation);
        xcoor = selected.getX();
        ycoor = selected.getY();

        if (board[(int) xcoor][(int) ycoor] == 'Q') {
            board[(int) xcoor][(int)ycoor] = ' ';
            selected.setBackgroundResource(R.drawable.solidblue);

            /*removes queen from the diagonal list*/
            Point queen= new Point((int)xcoor, (int)ycoor);
            queens.remove(queen);

            /*updates the queens left text field */
            queensLeft ++;
            TextView queensText= findViewById(R.id.queen_text);
            queensText.setText("Queens left to place: "+queensLeft);

        } else {
            boolean row = checkrow(ycoor, board);

            boolean column = checkcolumn(xcoor, board);
            boolean diag = checkDiag(xcoor, ycoor, queens);

            if (column) {
                if (row) {
                    if(diag) {
                        board[(int) xcoor][(int) ycoor] = 'Q';
                        selected.setBackgroundResource(R.drawable.queen_blue);

                        /*adds the queen to the list that checks diagonals*/
                        Point queen = new Point((int) xcoor, (int) ycoor);
                        queens.add(queen);

                        /*Updates the Queens left to place text */
                        queensLeft --;
                        TextView queenText= findViewById(R.id.queen_text);
                        queenText.setText("Queens left to place: "+ queensLeft);
                        if(queensLeft ==0){
                            TextView win = findViewById(R.id.winner);
                            win.setVisibility(win.VISIBLE);
                        }


                    }else{
                        Toast.makeText(this, "Error: Already queen on DIAGONAL", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Error: Already queen in that ROW", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Error: Already queen in that COLUMN", Toast.LENGTH_LONG).show();
            }
        }
     }





    public void whiteQueen(View view){
        /* gets the views ID*/
        ImageButton selected = findViewById(view.getId());
        /* get the buttons location on screen*/
        selected.getLocationInWindow(outLocation);
        xcoor = selected.getX();
        ycoor = selected.getY();

        if (board[(int) xcoor][(int) ycoor] == 'Q') {
            board[(int) xcoor][(int)ycoor] = ' ';
            selected.setBackgroundResource(R.drawable.solidwhite);
            Point queen= new Point((int)xcoor, (int)ycoor);
            queens.remove(queen);

            /*updates the queens left text field */
            queensLeft ++;
            TextView queensText= findViewById(R.id.queen_text);
            queensText.setText("Queens left to place: "+queensLeft);

        } else {
            boolean row = checkrow(ycoor, board);

            boolean column = checkcolumn(xcoor, board);
            boolean diag = checkDiag(xcoor, ycoor, queens);

            if (column) {
                if (row) {
                    if(diag) {
                        board[(int) xcoor][(int) ycoor] = 'Q';
                        selected.setBackgroundResource(R.drawable.queen_white);
                        Point queen = new Point((int) xcoor, (int) ycoor);
                        queens.add(queen);

                        /*Updates the Queens left to place text */
                        queensLeft --;
                        TextView queenText= findViewById(R.id.queen_text);
                        queenText.setText("Queens left to place: "+ queensLeft);
                        if(queensLeft ==0){
                            TextView win = findViewById(R.id.winner);
                            win.setVisibility(win.VISIBLE);
                        }

                    }else{
                        Toast.makeText(this, "Error: Already queen on DIAGONAL", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Error: Already queen in that ROW", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Error: Already queen in that COLUMN", Toast.LENGTH_LONG).show();
            }
        }
    }





    void restart(View v) {
        ViewGroup grid = (ViewGroup)findViewById(R.id.chess_board);
        for(int j=0; j<board.length; j++){
            for(int k=0; k<board.length; k++){
                board[j][k]= ' ';
                /*solution[j][k] =0;*/
            }
         queens.clear();
        }

        queensLeft=8;
        TextView queensText= findViewById(R.id.queen_text);
        queensText.setText("Queens left to place: 8");
        TextView winner = findViewById(R.id.winner);
        winner.setTextColor(Color.GREEN);
        winner.setText("You Win!");
        winner.setVisibility(winner.INVISIBLE);
        for (int i = 0; i < grid.getChildCount(); i++) {

            View child = grid.getChildAt(i);
            child.setClickable(true);
            int id= child.getId();
            switch(id){
                case R.id.b1:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b2:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b3:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b4:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b5:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b6:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b7:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b8:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b9:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b10:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b11:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b12:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b13:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b14:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b15:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b16:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b17:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b18:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b19:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b20:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b21:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b22:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b23:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b24:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b25:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b26:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b27:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b28:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b29:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b30:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b31:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b32:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b33:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b34:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b35:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b36:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b37:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b38:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b39:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b40:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b41:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b42:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b43:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b44:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b45:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b46:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b47:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b48:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b49:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b50:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b51:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b52:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b53:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b54:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b55:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b56:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b57:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b58:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b59:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b60:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b61:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b62:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
                case R.id.b63:
                    child.setBackgroundResource(R.drawable.solidwhite);
                    break;
                case R.id.b64:
                    child.setBackgroundResource(R.drawable.solidblue);
                    break;
            }


        }
    }

    public boolean checkcolumn(float potentialx , char[][] board){

        for(int r= 0; r<board.length; r++){
            if(board[(int) potentialx][r] == 'Q'){
                return false;
            }
        }
        return true;
    }


    public boolean checkrow(float potentialy, char[][] board){
        for(int c=0; c<board.length; c++ ){
            if(board[c][(int) potentialy]== 'Q'){
                return false;
            }
        }
        return true;
    }


    public boolean checkDiag(float x, float y, List list){

        for(int i=0; i<list.size(); i++){
            Point element = (Point) list.get(i);
            int qx= element.x;
            int qy= element.y;

            int absX = java.lang.Math.abs(qx-(int)x);
            int absY = java.lang.Math.abs(qy-(int)y);

            if(absX == absY){
                return false;
            }
        }

        return true;
    }


    public void solve(View view) {
       boolean canItBeDone =solveit(queensLeft);
       if(!canItBeDone){
           TextView fail = findViewById(R.id.winner);
           fail.setText("No Solution");
           fail.setTextColor(Color.RED);


           fail.setVisibility(fail.VISIBLE);
       }


    }

    public boolean solveit(int remaining){

        if(remaining <= 0){
            return true;
        }



        ViewGroup grid = (ViewGroup)findViewById(R.id.chess_board);
        for(int i =0; i<grid.getChildCount(); i++){

            View child = grid.getChildAt(i);
            int checkX = (int)child.getX();
            int checkY= (int)child.getY();

            if(checkcolumn(child.getX(), board)) {


                    if (checkrow(child.getY(), board)) {

                            if (checkDiag(child.getX(), child.getY(), queens)) {

                                board[checkX][checkY] = 'Q';
                                Point newPoint = new Point((int) child.getX(), (int) child.getY());
                                queens.add(newPoint);
                                queensLeft--;
                                if (solveit(queensLeft)) {
                                    child.setBackgroundResource(R.drawable.goldqueen);
                                    return true;
                                } else {
                                    board[checkX][checkY] = ' ';
                                    queens.remove(newPoint);
                                    queensLeft++;
                                }
                            }
                        }



                }

        child.setClickable(false);
        }


        return false;
    }
}
