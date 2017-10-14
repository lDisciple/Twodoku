/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalInterface;

import Components.Button;
import Components.DrawnObject;
import Components.GradientButton;
import GraphicalInterface.Events.*;
import static GraphicalInterface.Menu.game;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import twodoku.TwodokuGame;

/**
 *
 * @author jonathan
 */
public class MainMenu extends Menu{

    public MainMenu() {
        game = new TwodokuGame(3, 3);
        events.add(new ChangeGridEvent(3,3));
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale();
        StdDraw.setYscale();
        generalClearColor = new Color(252, 251, 239);
        usePlayerColors(false);
        TITLE.setY(7.5f/9);
        PARTICLE_GENERATOR.setShowing(false);
        GradientButton aiNormalButton = new GradientButton(0.5f, 5.6f/9f, 5.5f/9f, 1f/9f, "Play versus AI"){
            @Override
            protected void clickEvent() {
                events.add(new ChangeMenuEvent(1));
                events.add(new SetAILevelEvent(0));
                events.add(new SetGamemodeEvent(0));
            }

        };
        GradientButton aiGoodButton = new GradientButton(0.5f, 4.2f/9f, 5.5f/9f, 1f/9f, "Play Greedy AI"){
            @Override
            protected void clickEvent() {
                events.add(new ChangeMenuEvent(1));
                events.add(new SetAILevelEvent(1));
                events.add(new SetGamemodeEvent(0));
            }

        };
        Button pvpButton = new GradientButton(0.5f, 2.8f/9f, 5.5f/9f, 1f/9f, "Play with a friend"){
            @Override
            protected void clickEvent() {
                events.add(new ChangeMenuEvent(1));
                events.add(new SetGamemodeEvent(1));
            }

        };
        GradientButton exitButton = new GradientButton(0.5f, 1.3f/9f, 4f/9f, 0.8f/9f,"Exit"){
            @Override
            protected void clickEvent() {
                System.exit(0);
            }

        };
        exitButton.setDarkness(0.3f);

        sceneObjects = new DrawnObject[]{
                SNAKE_BACKGROUND,
                TITLE,
                aiNormalButton,
                aiGoodButton,
                pvpButton,
                exitButton,

        };
    }

    /****************
    *Update the menu
    ****************/
    @Override
    public void update(long timePassed) {
    }
    
}
