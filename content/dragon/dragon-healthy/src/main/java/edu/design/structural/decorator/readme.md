# Introduce
> Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

> -- Design Patterns: Elements of Reusable Object-Oriented Software

## Motivation

Extending an object�s functionality can be done statically (at compile time) by using inheritance however it might be necessary to extend an object�s functionality dynamically (at runtime) as an object is used.

Consider the typical example of a graphical window. To extend the functionality of the graphical window for example by adding a frame to the window, would require extending the window class to create a FramedWindow class. To create a framed window it is necessary to create an object of the FramedWindow class. However it would be impossible to start with a plain window and to extend its functionality at runtime to become a framed window.

## Intent

- The intent of this pattern is to add additional responsibilities dynamically to an object.

## Implementation

The figure below shows a UML class diagram for the Decorator Pattern:

 The participants classes in the decorator pattern are:

- **Component** - Interface for objects that can have responsibilities added to them dynamically.
- **ConcreteComponent** - Defines an object to which additional responsibilities can be added.
- **Decorator** - Maintains a reference to a Component object and defines an interface that conforms to Component's interface.
- **Concrete Decorators** - Concrete Decorators extend the functionality of the component by adding state or adding behavior.

## Description

The decorator pattern applies when there is a need to dynamically add as well as remove responsibilities to a class, and when subclassing would be impossible due to the large number of subclasses that could result.

## Applicability & Examples

### Example - Extending capabilities of a Graphical Window at runtime

In Graphical User Interface toolkits windows behaviors can be added dynamically by using the decorator design pattern.

## Specific problems and implementation

### Graphical User Interface Frameworks

GUI toolkits use decoration pattern to add functionalities dynamically as explained before.

### Related Patterns

- **Adapter Pattern** - A decorator is different from an adapter in that a decorator changes object's responsibilities, while an adapter changes an object interface.
- **Composite Pattern** - A decorator can be viewed as a degenerate composite with only one component. However, a decorator adds additional responsibilities.

## Consequences

- Decoration is more convenient for adding functionalities to objects instead of entire classes at runtime. With decoration it is also possible to remove the added functionalities dynamically.
- Decoration adds functionality to objects at runtime which would make debugging system functionality harder.

### Known Uses:

- GUI toolkits as has been previously explained.