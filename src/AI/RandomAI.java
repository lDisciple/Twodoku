/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import twodoku.TwodokuGame;


/**
 *
 * @author jonathan
 */
public class RandomAI extends AI{
    private boolean[][][] moves;
    private int width;
    private int height;
    /*****************************************************
    *Create an AI to play a TwodokuGame with random moves.
    *****************************************************/
    public RandomAI(int w, int h) {
        super(w, h);
    }

    /***********
    *Setup AI
    ***********/
    @Override
    public void init(int width, int height) {
        this.width = width;
        this.height = height;
        moves = new boolean[width*width][height*height][width*height];//x,y,num
        for (int x = 0; x < moves.length; x++) {
            for (int y = 0; y < moves[x].length; y++) {
                for (int num = 0; num < moves[x][y].length; num++) {
                    moves[x][y][num] = true;
                }
            }
        }
    }

    /********************************
    *Get the AIs move given the grid
    ********************************/
    @Override
    public int getMove(int[][] grid) {
        
        int moveIndex = (int)(Math.random()*width*width*height*height*width*height)+1;
        
        int x = 0;
        int y = 0;
        int num = 0;
        while(0 < moveIndex){
            x = 0;
            while (0 < moveIndex && x < moves.length) {
                y = 0;
                while (0 < moveIndex && y < moves[x].length) {
                    num = 0;
                    while (0 < moveIndex && num < moves[x][y].length) {    
                        if (moves[x][y][num] == true) {
                            moveIndex--;
                        }                            
                        num ++;
                    }
                    y++;
                }
                x++;
            }
            if (0 >= moveIndex) {
                x--;
                y--;
                removeMovesFromList(x, y, num);
                return TwodokuGame.convertMoveToInt(x, y, num);
            }
        }
        return 0;
    }

    /************************************
    *Tell the AI about the opponents move
    ************************************/
    @Override
    public void readOppenentsMove(int x, int y, int num, int[][] grid) {
        removeMovesFromList(x, y, num);
    }

    /*****************************************
    *Return the 3D array of move possibilities
    *****************************************/
    public boolean[][][] getMoves() {
        return moves;
    }
    
    /*************************************************
    *Remove move possibilities given a move was played
    **************************************************/
    private void removeMovesFromList(int x, int y, int num){
            num--;
            //Remove other moves
            for (int gridX = 0; gridX < moves.length; gridX++) {
                moves[gridX][y][num] = false;
            }
            for (int gridY = 0; gridY < moves[x].length; gridY++) {
                moves[x][gridY][num] = false;
            }
            for (int i = 0; i < width*height; i++) {
                moves[x][y][i] = false;
            }
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    moves[(x/width)*width + i][(y/height)*height + j][num] = false;
                }
            }
    }
}
