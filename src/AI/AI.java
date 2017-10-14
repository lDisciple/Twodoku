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
public abstract class AI {
    private int width, height;
    
    /***********************************
    *Create an AI to play a TwodokuGame.
    ***********************************/
    public AI(int w, int h){
        this.width = w;
        this.height = h;
        init(w,h);
    }
    
    /************************************
    *Prepare the AI for the new grid
    ************************************/
    public abstract void init(int width, int height);
    /********************************
    *Get the AIs move given the grid
    ********************************/
    public abstract int getMove(int[][] grid);
    /************************************
    *Tell the AI about the opponents move
    * Using move components
    ************************************/
    public abstract void readOppenentsMove(int x, int y, int num, int[][] grid);
    /************************************
    *Tell the AI about the opponents move
    * Using a converted move int (See TwodokuGame.convertMoveToInt)
    ************************************/
    public void readOppenentsMove(int move, int[][] grid){
        int x = TwodokuGame.getMoveIntX(move);
        int y = TwodokuGame.getMoveIntY(move);
        int num = TwodokuGame.getMoveIntNum(move);
        if (move > 0) {
            readOppenentsMove(x, y, num, grid);
        }
    }
    
    /*******************
    *Get the grid width
    *******************/
    public int getWidth() {
        return width;
    }

    /*******************
    *Get the grid height
    *******************/
    public int getHeight() {
        return height;
    }
    /*************************
     *Method for comparing AIs
     ************************/
    public static void main(String[] args){
    	int w = Integer.parseInt(args[1]);
    	int h = Integer.parseInt(args[0]);
    	int plays = Integer.parseInt(args[3]);
    	playAIGame(new RandomAI(w,h),new RandomAI(w,h),plays,w,h);
    	playAIGame(new RandomAI(w,h),new GreedyAI(w,h),plays,w,h);
    	playAIGame(new GreedyAI(w,h),new GreedyAI(w,h),plays,w,h);
    }
    /*************************
     *Play a game with 2 AIs
     ************************/
    private static void playAIGame(AI pc1,AI pc2,int plays, int w, int h){
        int win1 = 0;
        int turns = 0;
        for (int i = 0; i < plays; i++) {
            boolean isLastDraw = false;
            TwodokuGame game = new TwodokuGame(w, h);
            pc1.init(w,h);
            pc2.init(w,h);
            while (!isLastDraw) {  
                isLastDraw = game.isGameOver();//Draws a last time to show final move according to this variable
                if (!game.isGameOver()) {
                    int move = pc1.getMove(game.getGrid());
                    pc2.readOppenentsMove(move, game.getGrid());
                    game.makeMove(move);
                    turns++;
                    if (!game.isGameOver()) {
                        move = pc2.getMove(game.getGrid());
                    
                        pc1.readOppenentsMove(move, game.getGrid());
                        game.makeMove(move);
                        turns++;
                    }else{
                        win1 ++;
                    }
                }
            }
            
        }
        System.out.printf("%n-----------------------%n%s vs. %s%n-----------------------%nAI 1 wins: %d\tAI 2 wins: %d%nAverage Game Length: %.2f turns",
        		pc1.getClass().getSimpleName(),
        		pc2.getClass().getSimpleName(),
        		win1,
        		plays-win1,
        		turns/(float)plays);
    }
    
}
