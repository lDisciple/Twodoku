package Components;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import twodoku.Twodoku;

/**
 *
 * @author jonathan
 */
public abstract class GradientButton extends Button{
    private float darkness = 0.15f;
    private String text;
    private Font font;
    
    /****************************************
    *Create a button with the gradient style.
    ****************************************/
    public GradientButton(float xPos, float yPos, float width, float height,String buttonText) {
        super(xPos, yPos, width, height, null, true);
        font = Twodoku.HEADING_FONT.deriveFont(Font.PLAIN,27f);
        this.text = buttonText;
        DrawnObject def = new DrawnObject(xPos, yPos, width, height) {
            @Override
            public void draw() {
                float borderSize = 0.005f;
                float lineY = borderSize;
                StdDraw.setPenColor(200,200,200);
                StdDraw.filledRectangle(x, y, width/2f, height/2f);
                while (lineY < height-borderSize) {
                    //Gradient
                    /*
                    The gradient works on the equation of the top part of a circle.
                    I work from -1 to 1 based on the lineY (how far through the button it is)
                    and use the corresponding y values on a unit circle as a base for my gradient.
                    There is also an offset for some lightness and a darkness value
                    */
                    float graphX = (lineY/height)*2-1;
                    float lightness = (1-darkness)*0.7f;
                    float brightness = (float) Math.sqrt(1-graphX*graphX)*lightness+0.3f;
                    StdDraw.setPenColor(new Color(brightness,brightness,brightness));
                    StdDraw.filledRectangle(x, y+ lineY -height/2f, width/2f-borderSize, 0.0005f);
                    lineY += 0.001;
                }
                //Text
                StdDraw.setPenColor();
                StdDraw.setFont(font);
                StdDraw.text(x, y, text);
            }
        };
        DrawnObject hover = new DrawnObject(xPos, yPos, width, height) {
            @Override
            public void draw() {
                    float borderSize = 0.005f;
                    float lineY = borderSize;
                    StdDraw.setPenColor(200,200,255);
                    StdDraw.filledRectangle(x, y, width/2f, height/2f);
                    while (lineY < height-borderSize) {
                        //Gradient
                    float graphX = (lineY/height)*2-1;
                    float lightness = (1-(Math.min(1, darkness+0.2f)))*0.7f;
                    float brightness = (float) Math.sqrt(1-graphX*graphX)*lightness+0.3f;
                    StdDraw.setPenColor(new Color(brightness,brightness,brightness));
                        StdDraw.filledRectangle(x, y+ lineY -height/2f, width/2f-borderSize, 0.0005f);
                        lineY += 0.001;
                    }
                    //Text
                    StdDraw.setPenColor();
                    StdDraw.setFont(font);
                    StdDraw.text(x, y, text);
            }
        };
        this.setDefaultImage(def);
        this.setHoverImage(hover);
        this.setClickedImage(hover);
    }

    /***********************
    *Get the buttons text.
    ***********************/
    public String getText() {
        return text;
    }
    /***********************
    *Set the buttons text.
    ***********************/
    public void setText(String text) {
        this.text = text;
    }

    /***********************
    *Get the buttons font.
    ***********************/
    public Font getFont() {
        return font;
    }

    /***********************
    *Set the buttons font.
    ***********************/
    public void setFont(Font font) {
        this.font = font;
    }

    /****************************************************
    *The method that executes when the button is clicked
    ****************************************************/
    @Override
    protected abstract void clickEvent();

    /***********************
    *Get the buttons darkness value.
    ***********************/
    public float getDarkness() {
        return darkness;
    }
    /***********************
    *Set the buttons darkness value.
    ***********************/
    public void setDarkness(float darkness) {
        this.darkness = darkness;
    }
    
}
