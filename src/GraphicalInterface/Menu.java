package GraphicalInterface;

import GraphicalInterface.Events.Event;
import Components.Button;
import Components.DrawnObject;
import Components.MessageArea;
import Components.ParticleGenerator;
import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashSet;
import twodoku.MouseClickHandlerThread;
import static twodoku.Twodoku.HEADING_FONT;
import static twodoku.TwodokuGame.convertMoveToInt;
import static twodoku.TwodokuGame.getMoveIntX;
import static twodoku.TwodokuGame.getMoveIntY;
import twodoku.TwodokuGame;

/**
 *
 * @author jonathan
 */
public abstract class Menu {
    protected static Color generalClearColor;
    protected static TwodokuGame game;
    protected LinkedHashSet<Event> events = new LinkedHashSet();
    protected int changeMenu = -1;
    protected DrawnObject[] sceneObjects;
    private static boolean usePlayerColors = false;
    
    //<editor-fold defaultstate="collapsed" desc="Base components">
        public static final ParticleGenerator PARTICLE_GENERATOR = new ParticleGenerator(0.5f, 0.5f, 1f, 1f, 500);
        public static final DrawnObject TITLE = new DrawnObject(0.5f, 7.5f/9, 1f, 0.2f) {//Title
                @Override
                public void draw() {
                    StdDraw.setPenColor(StdDraw.GRAY);
                    //Slanted lines
                    StdDraw.setPenRadius();
                    float lineX = -0.3f;
                    while (lineX < 1) {
                        StdDraw.line(lineX, y+0.5f/9, lineX+1/9f, y-0.5f/9);
                        lineX += 1/25f;
                    }
                    StdDraw.setPenColor();
                    StdDraw.setFont(HEADING_FONT);
                    StdDraw.text(1.5/9f-0.5f + x, y, "T");
                    StdDraw.text(2.5/9f-0.5f + x, y, "w");
                    StdDraw.text(3.5/9f-0.5f + x, y, "o");
                    StdDraw.text(4.5/9f-0.5f + x, y, "d");
                    StdDraw.text(5.5/9f-0.5f + x, y, "o");
                    StdDraw.text(6.5/9f-0.5f + x, y, "k");
                    StdDraw.text(7.5/9f-0.5f + x, y, "u");
                }
        };
        /**********************
        *Message area component
        ***********************/
        public static final MessageArea MESSAGE_AREA = new MessageArea(0.5f,0.5f,0.2f,0.1f);
        /***********************
        *Basic 9x9 grid background
        ************************/
        public static final DrawnObject BASIC_BACKGROUND = new DrawnObject(0.5f, 0.5f, 1f, 1f) {
            @Override
            public void draw() {
                //Grid
                //Draw Vertical lines
                StdDraw.setPenColor(StdDraw.GRAY);
                for (int i = 0; i < game.getGridWidth(); i++) {
                    if (i%game.getBlockWidth() == 0) {
                        StdDraw.setPenRadius(0.005);
                    }else{
                        StdDraw.setPenRadius();
                    }
                    StdDraw.line((float)i/game.getGridWidth(), 0, (float)i/game.getGridWidth(), 1);
                }
                //Draw Horizontal Lines
                for (int i = 0; i < game.getGridHeight(); i++) {
                    if (i%game.getBlockHeight() == 0) {
                        StdDraw.setPenRadius(0.005);
                    }else{
                        StdDraw.setPenRadius();
                    }
                    StdDraw.line(0, (float)i/game.getGridHeight(), 1, (float)i/game.getGridHeight());
                }
                StdDraw.setFont(HEADING_FONT.deriveFont(Font.PLAIN,25));
                StdDraw.setPenColor();
                for (int i = 0; i < game.getGridWidth(); i++) {
                    for (int j = 0; j < game.getGridHeight(); j++) {
                        if (game.getGrid()[i][j] != 0) {
                            StdDraw.text((i+0.5f)/game.getGridWidth(),
                            (0.5f+j)/game.getGridHeight(),
                            ""+game.getGrid()[i][j]);
                        }
                    }
                }
            }
        };
        /*********************************************************
        *Grid background with a trail of numbers behind the mouse
        *********************************************************/
        public static final Button SNAKE_BACKGROUND = new Button(0.5f, 0.5f, 1f, 0.2f, BASIC_BACKGROUND, true) {
            protected int[] followMoves = new int[9];
            protected int lastGridWidth;
            protected int lastGridHeight;
            
            @Override
            public void mouseMoved(float mx, float my){
                //Check if grid changed
                lastGridWidth = game.getBlockWidth();
                lastGridHeight = game.getBlockHeight();
                if (lastGridWidth != game.getBlockWidth() || lastGridHeight != game.getBlockHeight()) {
                    followMoves = new int[9];
                }
                
                //Add numbers to grid as mouse goes over blocks
                
                mx = Math.min(mx*game.getBlockWidth()*game.getBlockHeight(), game.getBlockWidth()*game.getBlockHeight()-0.001f);
                my = Math.min(my*game.getBlockWidth()*game.getBlockHeight(), game.getBlockWidth()*game.getBlockHeight()-0.001f);
                
                if (game.getGrid()[(int)mx][(int)my] == 0 && !(mx ==0 && my == 0) && (int)my != 7) {
                    game.getGrid()[getMoveIntX(followMoves[followMoves.length-1])]
                        [getMoveIntY(followMoves[followMoves.length-1])] = 0;
                    int newNum = (int)(game.getBlockWidth()*game.getBlockHeight()*Math.random())+1;
                    game.getGrid()[(int)mx][(int)my] = newNum;
                    for (int i = followMoves.length-1; i > 0; i--) {
                        followMoves[i] = followMoves[i-1];
                    }
                    followMoves[0] = convertMoveToInt((int)mx, (int)my, newNum);
                    game.setCell((int)mx, (int)my, newNum);
                }
            }

            @Override
            protected void clickEvent() {
            }
        };
        /*******************************************
        *Grid background that scales with dimensions
        ********************************************/
        public static final DrawnObject DYNAMIC_BACKGROUND = new DrawnObject(0.5f, 0.5f, 1f, 1f) {
            private double[] cellXValues;
            private double[] cellYValues;
            private double[] blockXValues;
            private double[] blockYValues;
            
            
            @Override
            public void draw() {
                //Grid
                float cellSize;
                if (width/game.getGridWidth() > height/game.getGridHeight()) {
                    cellSize = height/game.getGridHeight();
                }else{
                    cellSize = width/game.getGridWidth();
                }
                //Clear behind grid
                StdDraw.setPenColor(generalClearColor);
                StdDraw.filledRectangle(x, y, cellSize*game.getGridWidth()/2f, cellSize*game.getGridHeight()/2f);
                //Draw Vertical lines
                StdDraw.setPenColor(StdDraw.GRAY);
                for (int i = 0; i <= game.getGridWidth(); i++) {
                    if (i%game.getBlockWidth() == 0) {
                        StdDraw.setPenRadius(0.005);
                    }else{
                        StdDraw.setPenRadius();
                    }
                    StdDraw.line(
                            i*cellSize + x - (cellSize*game.getGridWidth())/2,
                            y - (cellSize*game.getGridHeight())/2f,
                            i*cellSize + x - (cellSize*game.getGridWidth())/2,
                            y + (cellSize*game.getGridHeight())/2f);
                }
                //Draw Horizontal Lines
                for (int i = 0; i <= game.getGridHeight(); i++) {
                    if (i%game.getBlockHeight() == 0) {
                        StdDraw.setPenRadius(0.005);
                    }else{
                        StdDraw.setPenRadius();
                    }
                    StdDraw.line(
                            x - (cellSize*game.getGridWidth())/2f,
                            i*cellSize + y - (cellSize*game.getGridHeight())/2f,
                            x + (cellSize*game.getGridWidth())/2f,
                            i*cellSize + y - (cellSize*game.getGridHeight())/2f);
                }        
                
                StdDraw.setFont(HEADING_FONT.deriveFont(Font.PLAIN,450*cellSize));
                StdDraw.setPenColor(usePlayerColors ? Color.BLUE : Color.black);
                for (int i = 0; i < game.getGridWidth(); i++) {
                    for (int j = 0; j < game.getGridHeight(); j++) {
                        if (game.getPlayer1Grid()[i][j] != 0) {
                            StdDraw.text(
                                    i*cellSize + x - (cellSize*game.getGridWidth())/2 + cellSize/2f,
                                    (game.getGridHeight()-j-1)*cellSize + y - (cellSize*game.getGridHeight())/2 + cellSize/2f,
                                    ""+game.getPlayer1Grid()[i][j]);
                        }
                    }
                }
                StdDraw.setPenColor(usePlayerColors ? Color.RED : Color.black);
                for (int i = 0; i < game.getGridWidth(); i++) {
                    for (int j = 0; j < game.getGridHeight(); j++) {
                        if (game.getPlayer2Grid()[i][j] != 0) {
                            StdDraw.text(
                                    i*cellSize + x - (cellSize*game.getGridWidth())/2 + cellSize/2f,
                                    (game.getGridHeight()-j-1)*cellSize + y - (cellSize*game.getGridHeight())/2 + cellSize/2f,
                                    ""+game.getPlayer2Grid()[i][j]);
                        }
                    }
                }
            }
        };
//</editor-fold>
        
    
    /****************
    *Update the menu
    ****************/
    public abstract void update(long timePassed);
    
    /**************************************
    *Update the menu based on mouse clicks
    **************************************/
    public void updateMouse(MouseClickHandlerThread mouseHandler){
        for (DrawnObject sceneObject : sceneObjects) {
            if (sceneObject instanceof Button) {
                if (mouseHandler.isClicked()) {
                    ((Button)sceneObject).mouseClicked((float)mouseHandler.getClickX(), (float)mouseHandler.getClickY());
                }else{
                    ((Button)sceneObject).mouseMoved((float)StdDraw.mouseX(), (float)StdDraw.mouseY());
                }
            }
        }
        mouseHandler.reset();
    }
    
    /**************
    *Draw the menu
    ***************/
    public void drawMenu(){
        for (DrawnObject scnobj : sceneObjects) {
            scnobj.draw();
        }
    }
    
    /*********************
    *Get the events logged
    *********************/
    public Event[] getEvents(){
        return events.toArray(new Event[events.size()]);
    }
    
    /*******************
    *Clear logged events
    *******************/
    public void clearEvents(){
        events.clear();
    }
    
    /******************************
    *Log an event (See Event class)
    ******************************/
    public void invokeEvent(Event event){
        events.add(event);
    }
    
    /********************************************
    *Set the background color for StdDraw.clear()
    *********************************************/
    public Color getclearColor(){
        return generalClearColor;
    }

    /*****************************************
    *Check if to colour separate players moves
    ******************************************/
    public static boolean usingPlayerColors() {
        return usePlayerColors;
    }

    /*********************************************
    *Set whether to colour separate players moves
    *********************************************/
    public static void usePlayerColors(boolean usePlayerColors) {
        Menu.usePlayerColors = usePlayerColors;
    }
    
    
}
