
package twodoku;


/**
 *
 * @author jonathan
 */
public class TwodokuGame {
    private static final int MAX_MOVE_NUM = 255;
    private final int[][] grid;
    private final int[][] player1Grid;
    private final int[][] player2Grid;
    private final boolean[][][] moves;
    private final int width;
    private final int height;
    private boolean gameover;
    
    public TwodokuGame(int w, int h) {
            width = w;
            height = h;
            grid = new int[width*width][height*height];
            player1Grid = new int[width*width][height*height];
            player2Grid = new int[width*width][height*height];
            
            moves = new boolean[width*width][height*height][height*width];//x,y,num
            for (int x = 0; x < moves.length; x++) {
                for (int y = 0; y < moves[x].length; y++) {
                    for (int num = 0; num < moves[x][y].length; num++) {
                        moves[x][y][num] = true;
                    }
                }
            }
    }
    /***********************************************************
    *Plays a move for player 0 and returns if move was successful
    * Using a converted move int (See convertMoveToInt)
    ***********************************************************/
    public boolean makeMove(int move){
        return makeMove(move, 0);
    }
    /***********************************************************
    *Plays a move for a player and returns if move was successful
    * Using a converted move int (See convertMoveToInt)
    ***********************************************************/
    public boolean makeMove(int move, int player){
        if (move > 0) {
            int x = getMoveIntX(move);
            int y = getMoveIntY(move);
            int num = getMoveIntNum(move)-1;
            return makeMove(x,y,num,player);
        }else{
            return false;
        }
    }
    /***********************************************************
    *Plays a move for a player and returns if move was successful
    * Using move components
    ***********************************************************/
    public boolean makeMove(int x, int y, int num,  int player){
        if (moves[x][y][num]) {
            grid[x][y] = num + 1;
            if (player == 0) {
                player1Grid[x][y] = num + 1;
            }else{
                player2Grid[x][y] = num + 1;
            }
            //Remove other moves
            for (int gridX = 0; gridX < grid.length; gridX++) {
                moves[gridX][y][num] = false;
            }
            for (int gridY = 0; gridY < grid[x].length; gridY++) {
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
            return true;
        }else{
            return false;
        }
    }
    /*******************
    *Set a cell manually
    *******************/
    public void setCell(int x, int y, int num){
        grid[x][y] = num;
    }
    
    /************************************
    *Check if the game state is gameover
    ************************************/
    public boolean isGameOver(){
        if (!gameover) {
            gameover = checkForLegalMoves(moves);
        }
        return gameover;
    }

    /*************************
    *Set the game to gameover
    *************************/
    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
    
    /**************************
    *Check if legal moves exist
    **************************/
    private boolean checkForLegalMoves(boolean[][][] moves) {
        for (int x = 0; x < moves.length; x++) {
            for (int y = 0; y < moves[x].length; y++) {
                for (int num = 0; num < moves[x][y].length; num++) {
                    if (moves[x][y][num] == true) {
                        return false;
                    }
                }
            }
        }
        gameover = true;
        return true;
    }

    /*******************
    *Return the grid
    *******************/
    public int[][] getGrid() {
        return grid;
    }

    /**********************************
    *Return the grid of player 1s moves
    ***********************************/
    public int[][] getPlayer1Grid() {
        return player1Grid;
    }

    /**********************************
    *Return the grid of player 1s moves
    ***********************************/
    public int[][] getPlayer2Grid() {
        return player2Grid;
    }

    /**********************
    *Return the grids width
    **********************/
    public int getGridWidth() {
        return width*width;
    }

    /**********************
    *Return the grids height
    **********************/
    public int getGridHeight() {
        return height*height;
    }
    
    /***************************
    *Return a larger cells width
    ***************************/
    public int getBlockWidth(){
        return width;
    }
    
    /***************************
    *Return a larger cells height
    ***************************/
    public int getBlockHeight(){
        return height;
    }
    
    /************************************************************
    *Return the maximum number playable for the width and height
    ************************************************************/
    public int getMaximumNumber(){
        return width*height;
    }

    /***********************
    *Check if a move is legal
    ************************/
    public boolean isMoveLegal(int x, int y, int num) {
        if (x < moves.length && y < moves[x].length && num < moves[x][y].length) {
            return moves[x][y][num-1];
        }else{
            return false;
        }
        
    }
    
    /******************************************************************************************************
    Convert a move's x,y and number value to a single int that can be decrypted back to the original values
    ******************************************************************************************************/
    public static int convertMoveToInt(int x, int y, int num){
        /*
        To hide the 3 ints in one int value I:
        First start by determining a largest number, bigger than any value, for now 255 works.
        Then take the first integer number and add the second times the max value to create a total value.
        This allows one to say the total integer divided by the maximum number(MaxNum) is the second number.
        And the total modulus(%) MaxNum is the first value.
        Then do the same step with the total and third value with a maximum number of MaxNum+1.
        And can then retrieve each value in the same way.
        */
        return x*MAX_MOVE_NUM*(MAX_MOVE_NUM+1)+ y*MAX_MOVE_NUM + num;
    }
    
    /****************************************************************
    Get a move's X value from a converted int. See "convertMoveToInt"
    ****************************************************************/
    public static int getMoveIntX(int move){
        int num = move%MAX_MOVE_NUM;
        int y = ((move-num)%(MAX_MOVE_NUM+1))/MAX_MOVE_NUM;
        int x = ((move-num-y*MAX_MOVE_NUM)/((MAX_MOVE_NUM+1)*MAX_MOVE_NUM));
        return x;
    }
    
    /****************************************************************
    Get a move's Y value from a converted int. See "convertMoveToInt"
    ****************************************************************/
    public static int getMoveIntY(int move){
        int num = move%MAX_MOVE_NUM;
        int y = (((move-num)/MAX_MOVE_NUM)%(MAX_MOVE_NUM+1));
        return y;
    }
        /****************************************************************
    Get a move's number value from a converted int. See "convertMoveToInt"
    ****************************************************************/
    public static int getMoveIntNum(int move){
        int num = move%MAX_MOVE_NUM;
        return num;
    }
}
