/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author jonathan
 */
public class MessageArea extends DrawnObject{
    private Color backColor = Color.WHITE;
    private Color fontColor = Color.BLACK;
    private Font font;
    private String message= "";
    
    /************************************************
    *Create a rectangular area for displaying messages
    *************************************************/
    public MessageArea(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /*****************************
    *Draw call of the Message area
    *****************************/
    @Override
    public void draw() {
        StdDraw.setPenColor(backColor);
        StdDraw.filledRectangle(x, y, width, height);
        StdDraw.setPenColor(fontColor);
        if (font != null) {
            StdDraw.setFont(font);
        }else{
            StdDraw.setFont();
        }
        String[] lines = message.split("\n");
        float fontSize = StdDraw.getFont().getSize()/600f;
        float lineSpacing = 0.002f;
        float linesHeight = (lineSpacing+ fontSize)*lines.length;
        for (int i = 0; i < lines.length; i++) {
            StdDraw.text(x, y + linesHeight/2f - ((i+0.5f)*(lineSpacing+fontSize)), lines[i]);
        }
        
        StdDraw.setPenRadius(0.001);
        StdDraw.rectangle(x, y, width-0.005, height-0.005);
        StdDraw.setPenRadius(0.002);
        StdDraw.rectangle(x, y, width, height);
    }

    /********************************
    *Get the buttons background color.
    ********************************/
    public Color getBackgroundColor() {
        return backColor;
    }

    /********************************
    *Set the buttons background color.
    ********************************/
    public void setBackgroundColor(Color backColor) {
        this.backColor = backColor;
    }

    /********************************
    *Get the buttons font color.
    ********************************/
    public Color getFontColor() {
        return fontColor;
    }

    /********************************
    *Set the buttons font color.
    ********************************/
    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    /*********************
    *Get the buttons font.
    *********************/
    public Font getFont() {
        return font;
    }

    /*********************
    *Get the buttons font.
    *********************/
    public void setFont(Font font) {
        this.font = font;
    }

    /************************
    *Get the buttons message.
    *************************/
    public String getMessage() {
        return message;
    }

    /************************
    *Set the buttons message.
    ************************/
    public void setMessage(String message) {
        this.message = message;
    }
    
}
