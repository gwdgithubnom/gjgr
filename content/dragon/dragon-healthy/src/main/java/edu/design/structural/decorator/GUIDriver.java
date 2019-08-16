package edu.design.structural.decorator;

/**
 * Created by gwd on 9/4/2016.
 */
public class GUIDriver {

    public static void main(String[] args) {

        // create a new window

        Window window = new SimpleWindow();

        window.renderWindow();

        // at some point later
        // maybe text size becomes larger than the window
        // thus the scrolling behavior must be added

        // decorate old window
        window = new ScrollableWindow(window);

        //  now window object
        // has additional behavior / state

        window.renderWindow();

    }
}
