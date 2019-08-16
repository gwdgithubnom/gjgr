package edu.design.structural.decorator;

/**
 * Created by gwd on 9/4/2016.
 * Concrete window create a window that is scrollable
 * Scrollable window creates a window that is scrollable.
 */
public class ScrollableWindow extends DecoratedWindow{
    /**
     * Additional State
     */
    private Object scrollBarObjectRepresentation = null;

    public ScrollableWindow(Window windowRefernce) {

        super(windowRefernce);
    }

    @Override
    public void renderWindow() {

        // render scroll bar
        renderScrollBarObject();

        // render decorated window
        super.renderWindow();
    }

    private void renderScrollBarObject() {

        // prepare scroll bar
        scrollBarObjectRepresentation = new  Object();


        // render scrollbar

    }
}
