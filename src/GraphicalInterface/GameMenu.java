/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Components.Button;
import Components.DrawnObject;
import Components.GradientButton;
import Components.NumberButton;
import GraphicalInterface.Events.ChangeMenuEvent;
import GraphicalInterface.Events.MakeMoveEvent;
import static GraphicalInterface.Menu.game;
import static GraphicalInterface.Menu.generalClearColor;
import java.awt.Color;
import java.awt.Font;
import static twodoku.Twodoku.HEADING_FONT;
import twodoku.TwodokuGame;


/**
 *
 * @author jonathan
 */
public class GameMenu extends Menu{
    private int pveWins = 0;
    private int pvpWins = 0;
    private int pveLosses = 0;
    private int pvpLosses = 0;
    private String curMessage;//Keeps track of the message to be able to update the message area.(UpdateStats)
    private final int gamemode;
    private final GradientButton surrenderButton; 
    
    
    /**************
    *Setup the menu
    ***************/
    public GameMenu(TwodokuGame game, int gamemode) {
        Menu.game = game;
        this.gamemode = gamemode;
        DYNAMIC_BACKGROUND.setWidth(0.9f);
        DYNAMIC_BACKGROUND.setHeight(0.75f);
        DYNAMIC_BACKGROUND.setX(0.5f);
        DYNAMIC_BACKGROUND.setY(0.6f);

        PARTICLE_GENERATOR.setShowing(true);

        MESSAGE_AREA.setX(0.67f);
        MESSAGE_AREA.setY(0.1f);
        MESSAGE_AREA.setWidth(0.32f);
        MESSAGE_AREA.setHeight(0.07f);
        MESSAGE_AREA.setFont(HEADING_FONT.deriveFont(Font.PLAIN, 18));
        
        generalClearColor = new Color(252, 251, 239);
        usePlayerColors(true);
        setText(
            "Welcome to Twodoku\n"
            + "Select a number and\n"
            + "select a square to play in.");



        surrenderButton = new GradientButton(0.18f, 0.14f, 0.3f, 0.07f, "Surrender") {
            @Override
            protected void clickEvent() {
                if (this.getText().equals("Surrender")) {
                    game.setGameover(true);
                }else{
                    events.add(new ChangeMenuEvent(1));
                }
            }
        };
        surrenderButton.setFont(HEADING_FONT.deriveFont(Font.PLAIN, 20));
        GradientButton exitButton = new GradientButton(0.18f, 0.05f, 0.3f, 0.07f, "Leave session") {
            @Override
            protected void clickEvent() {
                events.add(new ChangeMenuEvent(0));
            }
        };
        exitButton.setFont(HEADING_FONT.deriveFont(Font.PLAIN, 20));

        NumberButton[] numberButtons = new NumberButton[game.getMaximumNumber()];
        float xSpacing = 0;
        float ySpacing = 0;
        float xOffset = 0;
        float yOffset = 0;
        float buttonWidth = 0;
        float buttonHeight = 0;
        if (game.getBlockWidth() == game.getBlockHeight()) {//Put buttons on the left for 2x2 and 3x3
            ySpacing = 0.01f;
            buttonWidth = 0.06f;
            buttonHeight = (DYNAMIC_BACKGROUND.getHeight()-ySpacing*numberButtons.length)/numberButtons.length;
            ySpacing += buttonHeight;
            yOffset = DYNAMIC_BACKGROUND.getY() + DYNAMIC_BACKGROUND.getHeight()/2f - buttonHeight;
        }else{
            xSpacing = 0.01f;
            buttonHeight = 0.06f;
            buttonWidth = (DYNAMIC_BACKGROUND.getWidth()-xSpacing*numberButtons.length)/numberButtons.length;
            xSpacing += buttonWidth;
            xOffset = DYNAMIC_BACKGROUND.getX() - DYNAMIC_BACKGROUND.getWidth()/2f;
            yOffset = 1-buttonHeight;
        }
        for (int i = 0; i < game.getMaximumNumber(); i++) {
            numberButtons[i] = new NumberButton(
                    buttonWidth/2f + i*xSpacing + xOffset,
                    buttonHeight/2f + i*-ySpacing + yOffset,
                    buttonWidth, buttonHeight, ""+(i+1));
            //Set button font size by amount of buttons
            numberButtons[i].setFont(HEADING_FONT.deriveFont(Font.PLAIN, 500/(game.getMaximumNumber()+10)));
        }
        Button interactiveGrid = new Button(0.5f, 0.6f, 0.9f, 0.75f, DYNAMIC_BACKGROUND, true){
            private float mouseX = 0;
            private float mouseY = 0;

            @Override
            public void mouseClicked(float mx, float my){
                mouseX = mx;
                mouseY = my;
                super.mouseClicked(mx, my);
            }

            @Override
            protected void clickEvent() {
                if (!game.isGameOver()) {
                    float cellSize;
                    if (width/game.getGridWidth() > height/game.getGridHeight()) {
                        cellSize = height/game.getGridHeight();
                    }else{
                        cellSize = width/game.getGridWidth();
                    }
                    float gridX = (mouseX-x+cellSize*game.getGridWidth()/2f)/(cellSize*game.getGridWidth());
                    float gridY = (mouseY-y+cellSize*game.getGridHeight()/2f)/(cellSize*game.getGridHeight());
                    int cellX = (int) (gridX*game.getGridWidth());
                    int cellY = game.getGridHeight() - (int) (gridY*game.getGridHeight()) - 1;
                    if (cellX >= 0 && cellY >= 0 && cellX < game.getGridWidth()&& cellY < game.getGridHeight()) {
                        int num = 0;
                        for (int i = 0; i < numberButtons.length; i++) {
                            if (numberButtons[i].isSelected()) {
                                num = i+1;
                                events.add(new MakeMoveEvent(twodoku.TwodokuGame.convertMoveToInt(cellX,cellY,num)));
                                break;
                            }
                        }
                    }
                }
            }

        };

        sceneObjects = new DrawnObject[5+numberButtons.length];
        sceneObjects[0] = PARTICLE_GENERATOR;
        sceneObjects[1] = interactiveGrid;
        sceneObjects[2] = surrenderButton;
        sceneObjects[3] = exitButton;
        sceneObjects[4] = MESSAGE_AREA;
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i].setGroup(numberButtons);
            sceneObjects[i+5] = numberButtons[i];
        }
    }
    
    /*************************************
    *Set the message for the message area
    *************************************/
    public void setText(String message){
        curMessage = message;
        if (gamemode == 0) {
            MESSAGE_AREA.setMessage(
                "Total wins: " + pveWins
                +"         Total losses: " + pveLosses + "\n"
                + curMessage);
        }else{
            MESSAGE_AREA.setMessage(
                "Player 1: " + pvpWins
                +"         Player 2: " + pvpLosses + "\n"
                + curMessage);
        }
    }
    
    /******************************
    *Update the win/loss statistics
    ******************************/
    public void updateStats(int pveWins,int pveLosses, int pvpWins, int pvpLosses){
        this.pveWins = pveWins;
        this.pveLosses = pveLosses;
        this.pvpWins = pvpWins;
        this.pvpLosses = pvpLosses;
        setText(curMessage);
    }

    /****************
    *Update the menu
    ****************/
    @Override
    public void update(long timePassed) {
        if (game.isGameOver()) {
            surrenderButton.setText("Play again");
        }else{
            surrenderButton.setText("Surrender");
        }
        if (PARTICLE_GENERATOR.isShowing()) {
            PARTICLE_GENERATOR.update(timePassed);
        }
    }

    
}
