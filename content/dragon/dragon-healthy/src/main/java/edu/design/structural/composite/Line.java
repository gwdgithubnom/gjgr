package edu.design.structural.composite;

/**
 * Created by gwd on 9/4/2016.
 * Line is a basic shape that does not support adding shapes
 */
public class Line implements Shape {
    /**
     * Create a line between point1 and point2
     * @param point1X
     * @param point1Y
     * @param point2X
     * @param point2Y
     */
    public Line(int point1X, int point1Y, int point2X, int point2Y) {

    }

    @Override
    public Shape[] explodeShape() {


        // making a simple shape explode would return only the shape itself, there are no parts of this shape

        Shape[] shapeParts = {this};

        return shapeParts;

    }

    /**
     * this method must be implemented in this simple shape
     */
    public void renderShapeToScreen() {


        // logic to render this shape to screen

    }

    /**
     * Implementation of the add to shape method
     * @param shape
     */
    public void addToShape(Shape shape){

        throw new RuntimeException("Cannot add a shape to simple shapes ...");
    }

}
