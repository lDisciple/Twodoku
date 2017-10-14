/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

/**
 *
 * @author jonathan
 */
public class NumberButton extends GradientButton{
    private boolean selected = false;
    private NumberButton[] group;
    /****************************************************************************************
    *Create a gradient button that is in a group in which only one button can be selected
    ****************************************************************************************/
    public NumberButton(float xPos, float yPos, float width, float height, String buttonText) {
        super(xPos, yPos, width, height, buttonText);
    }
    
    /*********************
    *Set the buttons group
    *********************/
    public void setGroup(NumberButton[] buttons){
        group = buttons;
    }
    
    /*********************************
    *Set the button to selected or not
    **********************************/
    protected void setSelected(boolean selected){
        this.selected = selected;
    }

    /********************************
    *Check if the button is selected
    ********************************/
    public boolean isSelected() {
        return selected;
    }

    /********************************************************
    *The method that will execute on a successful mouse click
    ********************************************************/
    @Override
    protected void clickEvent() {
        for (NumberButton button : group) {
            button.setSelected(false);
        }
        selected = true;
        
    }
    
    /*********************************
    *Get the buttons Image to be drawn
    *********************************/
    @Override
    public DrawnObject getImage(){
        if (selected) {//Darkness based on whether selected or not.
            this.setDarkness(0.3f);
            status = 2;
        }else{
            this.setDarkness(0.15f);
        }
        return super.getImage();
    }
    
}
