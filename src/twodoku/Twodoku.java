package twodoku;

import AI.AI;
import AI.GreedyAI;
import AI.RandomAI;
import GraphicalInterface.Events.*;
import GraphicalInterface.GameMenu;
import GraphicalInterface.MainMenu;
import GraphicalInterface.Menu;
import GraphicalInterface.SizeMenu;
import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author jonathan
 */
public class Twodoku {
    
    public static Font HEADING_FONT;
    
    private static long lastTime = System.currentTimeMillis();
    
    
    /**********
    Main method
    **********/
    public static void main(String[] args) {
        loadFonts();
        HEADING_FONT = new Font("FORCED SQUARE",Font.PLAIN,48);
        if(args.length == 3 ){
        	if (args[2].equals("2")) {
                graphicalGame();
            }else{
                commandLineGame(
                        Integer.parseInt(args[1]),//width
                        Integer.parseInt(args[0]),//height
                        Integer.parseInt(args[2])//gamemode
                        );
            }
        }else{
	    	if(args.length > 0 && args[2].equals("compare")){
	        	AI.main(args);
	    	}else{
	    		graphicalGame();
	    	}
    	}
    }

    /************************************
    Plays the game using the command line
    ************************************/
    private static void commandLineGame(int width, int height, int mode){
            System.out.println(
"___\u001B[32m/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\u001B[0m_________________________________________\u001B[32m/\\\\\\\u001B[0m____________________________________________\u001B[32m        \n" +
" \u001B[0m_\u001B[32m\\///////\\\\\\/////\u001B[0m_________________________________________\u001B[32m\\/\\\\\\\u001B[0m_________________\u001B[32m/\\\\\\\u001B[0m_______________________\u001B[32m       \n" +
"  \u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m______________________________________________\u001B[32m\\/\\\\\\\u001B[0m________________\u001B[32m\\/\\\\\\\u001B[0m_______________________\u001B[32m      \n" +
"   \u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m________\u001B[32m/\\\\\u001B[0m____\u001B[32m/\\\\\u001B[0m___\u001B[32m/\\\\\u001B[0m_____\u001B[32m/\\\\\\\\\\\u001B[0m___________\u001B[32m\\/\\\\\\\u001B[0m______\u001B[32m/\\\\\\\\\\\u001B[0m____\u001B[32m\\/\\\\\\\\\\\\\\\\\u001B[0m_____\u001B[32m/\\\\\\\u001B[0m____\u001B[32m/\\\\\\\u001B[0m_\u001B[32m     \n" +
"    \u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m__\u001B[32m/\\\\\\\\\u001B[0m_\u001B[32m/\\\\\\\u001B[0m___\u001B[32m/\\\\\\///\\\\\\\u001B[0m____\u001B[32m/\\\\\\\\\\\\\\\\\\\u001B[0m____\u001B[32m/\\\\\\///\\\\\\\u001B[0m__\u001B[32m\\/\\\\\\////\\\\\\\u001B[0m__\u001B[32m\\/\\\\\\\u001B[0m___\u001B[32m\\/\\\\\\\u001B[0m_\u001B[32m    \n" +
"     \u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m_______\u001B[32m\\//\\\\\\/\\\\\\\\\\/\\\\\\\u001B[0m___\u001B[32m/\\\\\\\u001B[0m__\u001B[32m\\//\\\\\\\u001B[0m__\u001B[32m/\\\\\\////\\\\\\\u001B[0m___\u001B[32m/\\\\\\\u001B[0m__\u001B[32m\\//\\\\\\\u001B[0m_\u001B[32m\\/\\\\\\\\\\\\\\\\/\u001B[0m___\u001B[32m\\/\\\\\\\u001B[0m___\u001B[32m\\/\\\\\\\u001B[0m_\u001B[32m   \n" +
"      \u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m________\u001B[32m\\//\\\\\\\\\\/\\\\\\\\\\\u001B[0m___\u001B[32m\\//\\\\\\\u001B[0m__\u001B[32m/\\\\\\\u001B[0m__\u001B[32m\\/\\\\\\\u001B[0m__\u001B[32m\\/\\\\\\\u001B[0m__\u001B[32m\\//\\\\\\\u001B[0m__\u001B[32m/\\\\\\\u001B[0m__\u001B[32m\\/\\\\\\///\\\\\\\u001B[0m___\u001B[32m\\/\\\\\\\u001B[0m___\u001B[32m\\/\\\\\\\u001B[0m_\u001B[32m  \n" +
"       \u001B[0m_______\u001B[32m\\/\\\\\\\u001B[0m_________\u001B[32m\\//\\\\\\\\//\\\\\\\u001B[0m_____\u001B[32m\\///\\\\\\\\\\/\u001B[0m___\u001B[32m\\//\\\\\\\\\\\\\\/\\\\\u001B[0m__\u001B[32m\\///\\\\\\\\\\/\u001B[0m___\u001B[32m\\/\\\\\\\u001B[0m_\u001B[32m\\///\\\\\\\u001B[0m_\u001B[32m\\//\\\\\\\\\\\\\\\\\\\u001B[0m__\u001B[32m \n" +
"        \u001B[0m_______\u001B[32m\\///\u001B[0m___________\u001B[32m\\///\u001B[0m__\u001B[32m\\///\u001B[0m________\u001B[32m\\/////\u001B[0m______\u001B[32m\\///////\\//\u001B[0m_____\u001B[32m\\/////\u001B[0m_____\u001B[32m\\///\u001B[0m____\u001B[32m\\///\u001B[0m___\u001B[32m\\/////////\u001B[0m__");
            int gameWidth = width;
            int gameHeight = height;
            int gamemode = mode;
            TwodokuGame game = new TwodokuGame(gameWidth, gameHeight);
            AI computerPlayer = new GreedyAI(gameWidth, gameHeight);
            
            boolean player1Turn = Math.random() > 0.5;
            boolean isLastDraw = false;
            int lastPlayedX = 0;
            int lastPlayedY = 0;

            while (!isLastDraw) { 
                //<editor-fold defaultstate="collapsed" desc="grid printing">
                //Create grid format
                for (int row = 0; row < game.getBlockHeight()*game.getBlockHeight() + 1; row++) {
                    //If border of bigger cell then make the - line thick (=)
                    String horiLine = "-";
                    if (row%game.getBlockHeight() == 0) {
                        horiLine = "=";
                    }
                    //Draw +--+---+--- horizontal lines
                    for (int col = 0; col < game.getBlockWidth()*game.getBlockWidth(); col++) {
                        //Add an extra + if the horizontal line is a larger cell border
                        if (col %game.getBlockWidth() == 0) {
                            System.out.print("+");
                        }
                        System.out.print("+" + horiLine + horiLine + horiLine);
                    }
                    System.out.println("++");
                    //Draw cell horizontal lines |   |   | 3 |
                    if (row < game.getGrid()[0].length) {
                        for (int col = 0; col < game.getBlockWidth()*game.getBlockWidth(); col++) {
                            //Add an extra | if the horizontal line is a larger cell border
                            if (col %game.getBlockWidth() == 0) {
                                System.out.print("|");
                            }
                            if (col == lastPlayedX && row == lastPlayedY) {
                                System.out.print("| " + (game.getGrid()[col][row] == 0 ? "  " : String.format("\u001B[32m%-2s\u001B[0m", game.getGrid()[col][row])));
                            }else{
                                System.out.print("| " + (game.getGrid()[col][row] == 0 ? "  " : String.format("%-2s", game.getGrid()[col][row])));
                            }
                                
                            
                        }
                        System.out.println("||"); 
                    }    
                }
                //</editor-fold>   
                isLastDraw = game.isGameOver();//Draws a last time to show final move according to this variable
                if (!game.isGameOver()) {
                    if ((player1Turn || gamemode == 1) && gamemode != 1337) {//Player move
                        int x = StdIn.readInt()-1;
                        int y = StdIn.readInt()-1;
                        int num = StdIn.readInt();
                        if (game.isMoveLegal(x, y, num)){
                            
                            game.makeMove(TwodokuGame.convertMoveToInt(x, y, num));
                            computerPlayer.readOppenentsMove(x, y, num, game.getGrid());
                            lastPlayedX = x;
                            lastPlayedY = y;
                        }else{
                            StdOut.println("Illegal move");
                            System.exit(0);
                        }
                        player1Turn = false;
                    }else{//Computer move
                        int move = computerPlayer.getMove(game.getGrid());
                        game.makeMove(move);
                        StdOut.println("\nComputer played " + (TwodokuGame.getMoveIntNum(move)+1) + " at: "
                                + TwodokuGame.getMoveIntX(move) + "," + (TwodokuGame.getMoveIntY(move)+1));
                        lastPlayedX = TwodokuGame.getMoveIntX(move);
                        lastPlayedY = TwodokuGame.getMoveIntY(move);
                        player1Turn = true;
                    }
                }else{
                    if (gamemode == 0) {
                        if (!player1Turn) {
                            StdOut.println("You win!");
                        }else{
                            StdOut.println("The computer won, better luck next time!");
                        }
                    }else{
                        if (player1Turn) {
                            StdOut.println("Player 1 wins!");
                        }else{
                            StdOut.println("Player 2 wins!!");
                        }
                    }
                }
            }
    }
    
    /***************************
    Plays the game using the GUI
    ****************************/
    private static void graphicalGame() {
        //Game variables
        AI computerPlayer = new RandomAI(3, 3);
        int gameWidth = 3;
        int gameHeight = 3;
        int gamemode = 0;
        TwodokuGame game = new TwodokuGame(gameWidth, gameHeight);
        boolean useGoodAI = false;
        //GUI variables
        MouseClickHandlerThread mouseHandler = new MouseClickHandlerThread();
        new Thread(mouseHandler).start();
        Menu currentMenu = new MainMenu();
        //Statistics variables
        int pveWins = 0; 
        int pveLosses =  0; 
        int pvpWins = 0; 
        int pvpLosses =  0; 
        
        StdDraw.enableDoubleBuffering();
        StdAudio.loop("/Audio/background.wav");
        int turn = 0;
        try{
            while (true) {
                //Event handling
                currentMenu.updateMouse(mouseHandler);
                
                Event[] events = currentMenu.getEvents();
                currentMenu.clearEvents();//Cleared now so that computer can insert a move request
                for (Event e : events) {
                    switch (e.getId()) {
                        case "ChangeGridEvent":
                            gameWidth = ((ChangeGridEvent)e).getWidth();
                            gameHeight = ((ChangeGridEvent)e).getHeight();
                            break;
                        case "SetGamemodeEvent":
                            gamemode = ((SetGamemodeEvent)e).getGamemode();
                            break;
                        case "SetAILevelEvent":
                            useGoodAI = ((SetAILevelEvent)e).getAILevel() == 1;
                            break;
                        case "ChangeMenuEvent":
                            switch (((ChangeMenuEvent)e).getMenuID()) {
                                case 0:
                                    currentMenu = new MainMenu();
                                    break;
                                case 1:
                                    currentMenu = new SizeMenu(gamemode);
                                    break;
                                case 2:
                                    game = new TwodokuGame(gameWidth, gameHeight);
                                    turn = 0;
                                    GameMenu gMenu = new GameMenu(game, gamemode);
                                    gMenu.updateStats(pveWins, pveLosses, pvpWins, pvpLosses);
                                    currentMenu = gMenu; 
                                    if (useGoodAI) {
                                        computerPlayer = new RandomAI(gameWidth, gameHeight);
                                    }else{
                                        computerPlayer = new GreedyAI(gameWidth, gameHeight);
                                    }
                                    
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            break;
                        case "MakeMoveEvent":
                            MakeMoveEvent mme = (MakeMoveEvent)e;
                            //Specify names to be displayed based on gamemode
                            String name1 = gamemode == 0 ? "You" : "Player 1";
                            String name2 = gamemode == 0 ? "The computer" : "Player 2";
                            //Make move, the method returns true if move is valid
                            if (game.makeMove(mme.getMove(), turn%2)) {
                                if (game.isGameOver()) {
                                    StdAudio.play("/Audio/victory.wav");
                                    ((GameMenu)currentMenu).setText(
                                    (turn % 2 == 0 ? name1 : name2) + " win" + (name1.equals("You") ? "" : "s") + "!\n"
                                    + "Click \"play again\" for another challenge.");
                                }else{
                                    //Play next move
                                    ((GameMenu)currentMenu).setText(
                                    (turn % 2 == 0 ? name1 : name2) + " played at " + mme.getNum() + ":\n"
                                            + (mme.getX()+1) + " : " + (mme.getY()+1));
                                    //If its PvE and players turn then let the computer go immediately after the player
                                    if (gamemode == 0 && turn % 2 == 0) {
                                        computerPlayer.readOppenentsMove(mme.getMove(), game.getGrid());
                                        int move = computerPlayer.getMove(game.getGrid());
                                        //Create an event to handle all the makeMove logistics automatically (comes back to this loop)
                                        currentMenu.invokeEvent(new MakeMoveEvent(move));
                                    }
                                    turn++;
                                    StdAudio.play("/Audio/wood.wav");
                                }
                            }else{
                                game.setGameover(true);
                                StdAudio.play("/Audio/boo.wav");
                                ((GameMenu)currentMenu).setText(
                                "You made an illegal move\n"
                                + "The " + (turn%2==1 ? name1 : name2) + " wins this round!\n"
                                + "Click play again for another challenge.");
                            }
                            if (game.isGameOver()) {
                                if (gamemode == 0) {
                                    if (turn%2 == 1) {
                                        pveWins++;
                                    }else{
                                        pveLosses++;
                                    }
                                }else{
                                    if (turn%2 == 1) {
                                        pvpWins++;
                                    }else{
                                        pvpLosses++;
                                    }
                                }
                                ((GameMenu)currentMenu).updateStats(pveWins, pveLosses, pvpWins, pvpLosses);
                            }
                            break;
                        default:
                            System.err.println(e.getId() + " not recognised");
                    }
                }
                
                StdDraw.clear();
                currentMenu.drawMenu();
                StdDraw.show();
                
                currentMenu.update(System.currentTimeMillis()-lastTime);
                lastTime = System.currentTimeMillis();
            }
        }finally{
            mouseHandler.stopRunning();
            StdAudio.close();
        }
    }
    
    /***************************
    Loads fonts used in the game
    ***************************/
    public static void loadFonts(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Twodoku.class.getResourceAsStream("/Fonts/FORCED SQUARE.ttf")));
        } catch (FontFormatException | IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR: Fonts not loaded", "Error", JOptionPane.ERROR_MESSAGE, null);
            System.err.println(ex);
            System.exit(0);
        }
    }
}
