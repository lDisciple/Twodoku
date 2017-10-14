package GraphicalInterface.Events;

import twodoku.TwodokuGame;

/**
 *
 * @author jonathan
 */
public class MakeMoveEvent extends Event{
    int move;

    /*************************************
    *An Event for when a move must be made
    *************************************/
    public MakeMoveEvent(int move) {
        this.move = move;
    }

    /***********************************
    *Get the converted move int
    * See TwodokuGame.convertMoveToInt()
    ************************************/
    public int getMove() {
        return move;
    }
    
    /*********************
    *Get the moves x value
    *********************/
    public int getX(){
        int x = TwodokuGame.getMoveIntX(move);
        return x;
    }
    
    /*********************
    *Get the moves y value
    *********************/
    public int getY(){
        int x = TwodokuGame.getMoveIntX(move);
        int y = TwodokuGame.getMoveIntY(move);
        return y;
    }
    
    /**************************
    *Get the moves number value
    **************************/
    public int getNum(){
        int x = TwodokuGame.getMoveIntX(move);
        int y = TwodokuGame.getMoveIntY(move);
        int num = TwodokuGame.getMoveIntNum(move);
        return num;
    }
}
