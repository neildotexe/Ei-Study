# Design Patterns Implemented

## Behavioral Patterns

1. **Observer Pattern**
   - Implemented in: `Behavioural.java`
   - Description: Defines a one-to-many dependency between objects. When one object changes state, all its dependents are notified and updated automatically.
   - Use case: News agency notifying multiple news channels about breaking news.

2. **Strategy Pattern**
   - Implemented in: `Behavioural.java`
   - Description: Defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.
   - Use case: Different payment methods (credit card, UPI) for a shopping cart checkout.

## Creational Patterns

1. **Singleton Pattern**
   - Implemented in: `Creational.java`
   - Description: Ensures a class has only one instance and provides a global point of access to it.
   - Use case: Managing a single database connection throughout the application.

2. **Factory Method Pattern**
   - Implemented in: `Creational.java`
   - Description: Defines an interface for creating an object, but lets subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses.
   - Use case: Creating different types of vehicles (car, motorcycle, truck) without specifying their exact classes.

## Structural Patterns

1. **Adapter Pattern**
   - Implemented in: `Structural.java`
   - Description: Converts the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces.
   - Use case: Adapting a legacy payment system to work with a new e-commerce platform.

2. **Composite Pattern**
   - Implemented in: `Structural.java`
   - Description: Composes objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.
   - Use case: Representing a file system with directories and files, where both are treated as file system items.

Each pattern is implemented with a practical example to demonstrate its real-world application. The implementations can be found in their respective Java files as indicated above.

## Running the Examples

To run the examples, compile and execute each Java file separately using the following commands:

```bash
javac Behavioural.java
java Behavioural

javac Creational.java
java Creational

javac Structural.java
java Structural
```

These commands will compile the Java files and then run the compiled classes, demonstrating the implementation of each design pattern.
