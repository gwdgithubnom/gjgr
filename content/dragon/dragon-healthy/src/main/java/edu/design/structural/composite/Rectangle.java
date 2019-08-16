package edu.design.structural.composite;

/**
 * Created by gwd on 9/4/2016.
 * Rectangle is comosite
 * Complex Shape
 */
public class Rectangle implements Shape {
    // List of shapes forming the rectangle
    // rectangle is centered around origin
    Shape[] rectangleEdges = {new Line(-1,-1,1,-1),new Line(-1,1,1,1),new Line(-1,-1,-1,1),new Line(1,-1,1,1)};



    @Override
    public Shape[] explodeShape() {

        return rectangleEdges;

    }

    /**
     * this method is implemented directly in basic shapes
     * in complex shapes this method is implemented using delegation
     */
    public void renderShapeToScreen() {


        for(Shape s : rectangleEdges){

            // delegate to child objects
            s.renderShapeToScreen();

        }

    }

    /**
     * Implementation of the add to shape method
     * @param shape
     */
    public void addToShape(Shape shape){

        throw new RuntimeException("Cannot add a shape to simple shapes ...");
    }
}
