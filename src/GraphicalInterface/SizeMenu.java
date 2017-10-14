/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import AI.RandomAI;
import Components.Button;
import Components.DrawnObject;
import Components.GradientButton;
import GraphicalInterface.Events.ChangeGridEvent;
import GraphicalInterface.Events.ChangeMenuEvent;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import twodoku.TwodokuGame;

/**
 *
 * @author jonathan
 */
public class SizeMenu extends Menu{
    private RandomAI computerPlayer;
    private long computerCooldown;

    /**************
    *Setup the menu
    ***************/
    public SizeMenu(int gamemode) {
        events.add(new ChangeGridEvent(3,3));
        game = new TwodokuGame(3, 3);
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale();
        StdDraw.setYscale();
        TITLE.setY(8.5f/9);
        DYNAMIC_BACKGROUND.setWidth(1f);
        DYNAMIC_BACKGROUND.setHeight(1f);
        DYNAMIC_BACKGROUND.setX(0.5f);
        DYNAMIC_BACKGROUND.setY(0.5f);

        PARTICLE_GENERATOR.setShowing(false);

        MESSAGE_AREA.setX(0.5f);
        MESSAGE_AREA.setY(7.5f/9f +0.25f/9);
        MESSAGE_AREA.setWidth(0.3f);
        MESSAGE_AREA.setBackgroundColor(generalClearColor);
        MESSAGE_AREA.setHeight(0.25f/9);
        MESSAGE_AREA.setMessage(gamemode == 0 ? "Player vs. Computer" : "Player vs. Player");

        generalClearColor = new Color(252, 251, 239);
        usePlayerColors(false);
        GradientButton backButton = new GradientButton(0.5f, 1.3f/9f, 4f/9f, 0.8f/9f, "Back"){
            @Override
            protected void clickEvent() {
                events.add(new ChangeMenuEvent(0));
            }

        };
        final int[] heights = new int[]{2,3,4,2,2,2,3};
        final int[] widths = new int[]{2,3,4,3,4,5,4};
        Button[] sizeButtons = new Button[widths.length+1];//+1 for random button
        for (int i = 0; i <= widths.length; i++) {
            final int curIndex = i;
            Button sizeButton;
            if (i < widths.length) {
                //Create buttons for grid sizes based on position in loop
                sizeButton = new GradientButton(0.25f + (i%2)/2f, (6f-(int)(i/2))/9f, 3f/9f, 0.8f/9f, heights[curIndex]+" x "+widths[curIndex]){
                    @Override
                    protected void clickEvent() {
                        events.add(new ChangeGridEvent(widths[curIndex], heights[curIndex]));
                        events.add(new ChangeMenuEvent(2));
                    }

                    @Override
                    public void mouseMoved(float mx, float my){
                        super.mouseMoved(mx, my);
                        if ((game.getBlockWidth() != widths[curIndex] || game.getBlockHeight() != heights[curIndex]) && inBounds(mx, my)) {
                            game = new TwodokuGame(widths[curIndex], heights[curIndex]);
                        }
                    }

                };
            }else{
                //Create the final random button
                sizeButton = new GradientButton(0.25f + (i%2)/2f, (6f-(int)(i/2))/9f, 3f/9f, 0.8f/9f, "Random"){
                    private final int[] gridWidths = widths;
                    private final int[] gridHeights = heights;
                    @Override
                    protected void clickEvent() {
                        int index = (int)(Math.random()*gridWidths.length);
                        events.add(new ChangeGridEvent(gridWidths[index], gridHeights[index]));
                        events.add(new ChangeMenuEvent(2));
                    }

                };
            }
            sizeButtons[i] = sizeButton;
        }
        
        backButton.setDarkness(0.3f);
        sceneObjects = new DrawnObject[4+sizeButtons.length];
        
        sceneObjects[0] = DYNAMIC_BACKGROUND;
        sceneObjects[1] = TITLE;
        sceneObjects[2] = MESSAGE_AREA;
        sceneObjects[3] = backButton;
        for (int i = 0; i < sizeButtons.length; i++) {
            sceneObjects[4+i] = sizeButtons[i];
        }
        computerPlayer = new RandomAI(game.getBlockWidth(), game.getBlockHeight());
    }

    /****************
    *Update the menu
    ****************/
    @Override
    public void update(long timePassed) {
        computerCooldown += timePassed;
        if (game != null) {//Regulate the aout filling
            if (game.getBlockWidth() != computerPlayer.getWidth() || game.getBlockHeight()!= computerPlayer.getHeight()) {
                computerPlayer = new RandomAI(game.getBlockWidth(), game.getBlockHeight());
            }
            if (game.isGameOver()) {
                if (computerCooldown > 1000) {
                    computerCooldown %= 1000;
                    game = new TwodokuGame(game.getBlockWidth(), game.getBlockHeight());
                    computerPlayer = new RandomAI(game.getBlockWidth(), game.getBlockHeight());
                }

            }else{
                if (computerCooldown > 100) {
                    computerCooldown %= 100;
                    game.makeMove(computerPlayer.getMove(game.getGrid()));
                }
            }
        }
    }
    
}
