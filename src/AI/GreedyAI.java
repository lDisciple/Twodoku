package AI;

import java.util.ArrayList;
import twodoku.TwodokuGame;

/**
 *
 * @author jonathan
 */
public class GreedyAI extends AI{
    private boolean[][][] validMoves;
    private int totalMovesLeft;
    public GreedyAI(int w, int h) {
        super(w, h);
    }

    /***********
    *Setup AI
    ***********/
    @Override
    public void init(int width, int height) {
        totalMovesLeft = width*width*width*height*height*height;
        validMoves = new boolean[width*width][height*height][width*height];//x,y,num
        for (int x = 0; x < validMoves.length; x++) {
            for (int y = 0; y < validMoves[x].length; y++) {
                for (int num = 0; num < validMoves[x][y].length; num++) {
                    validMoves[x][y][num] = true;
                }
            }
        }
    }

    /********************************
    *Get the AIs move given the grid
    ********************************/
    @Override
    public int getMove(int[][] grid) {
        ArrayList<Integer> bestMoves = new ArrayList();
        int backupMove = 0;
        int mostRemoved = 0;
        for (int x = 0; x < validMoves.length; x++) {
            for (int y = 0; y < validMoves[x].length; y++) {
                for (int num = 0; num < validMoves[x][y].length; num++) {
                    if (validMoves[x][y][num]) {
                        backupMove = TwodokuGame.convertMoveToInt(x, y, num+1);
                        int movesRemoved = getAmountOfMovesRemoved(x, y, num+1);
                        if (movesRemoved > mostRemoved && totalMovesLeft-mostRemoved != 1) {
                            mostRemoved = movesRemoved;
                            bestMoves.clear();
                            bestMoves.add(backupMove);
                        }else{
                            if (movesRemoved == mostRemoved) {
                                bestMoves.add(backupMove);
                            }
                        }
                    }
                }
            }
        } 
        if (!bestMoves.isEmpty()) {
            int move = bestMoves.get((int)(Math.random()*bestMoves.size()));
            int x = TwodokuGame.getMoveIntX(move);
            int y = TwodokuGame.getMoveIntY(move);
            int num = TwodokuGame.getMoveIntNum(move);
            removeMovesFromList(x,y,num);
            return move;
        }else{
            return backupMove;//The only valid move left or 0
        }
    }

    /************************************
    *Tell the AI about the opponents move
    ************************************/
    @Override
    public void readOppenentsMove(int x, int y, int num, int[][] grid) {
        removeMovesFromList(x, y, num);
    }
    
    /*************************************************
    *Get how many moves will be removed by given move
    **************************************************/
    private int getAmountOfMovesRemoved(int x, int y, int num){
        int removed = 0;
        num--;
        //Remove other moves
        for (int gridX = 0; gridX < validMoves.length; gridX++) {
            if (validMoves[gridX][y][num] == true) {
                removed++;
            }
        }
        for (int gridY = 0; gridY < validMoves[x].length; gridY++) {
            if (validMoves[x][gridY][num] == true) {
                removed++;
            }
        }
        for (int i = 0; i < this.getWidth()*this.getHeight(); i++) {
            if (validMoves[x][y][i] == true) {
                removed++;
            }
        }
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight(); j++) {
                if (validMoves[(x/this.getWidth())*this.getWidth() + i][(y/this.getHeight())*this.getHeight() + j][num] == true) {
                    removed++;
                }
            }
        }
        return removed;
    }

    /*****************************************
    *Return the 3D array of move possibilities
    *****************************************/
    public boolean[][][] getMoves() {
        return validMoves;
    }
    
    /*************************************************
    *Remove move possibilities given a move was played
    **************************************************/
    private void removeMovesFromList(int x, int y, int num){
            num--;
            //Remove other moves
            for (int gridX = 0; gridX < validMoves.length; gridX++) {
                if (validMoves[gridX][y][num]) {
                    totalMovesLeft--;
                    validMoves[gridX][y][num] = false;
                }
            }
            for (int gridY = 0; gridY < validMoves[x].length; gridY++) {
                if (validMoves[x][gridY][num]) {
                    totalMovesLeft--;
                    validMoves[x][gridY][num] = false;
                }
            }
            for (int i = 0; i < this.getWidth()*this.getHeight(); i++) {
                if (validMoves[x][y][i]) {
                    totalMovesLeft--;
                    validMoves[x][y][i] = false;
                }else{
                }
            }
            for (int i = 0; i < this.getWidth(); i++) {
                for (int j = 0; j < this.getHeight(); j++) {
                    if (validMoves[(x/this.getWidth())*this.getWidth() + i][(y/this.getHeight())*this.getHeight() + j][num]) {
                        totalMovesLeft--;
                        validMoves[(x/this.getWidth())*this.getWidth() + i][(y/this.getHeight())*this.getHeight() + j][num] = false;
                    }
                }
            }
    }
}
