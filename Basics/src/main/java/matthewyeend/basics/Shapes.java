/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.basics;

/**
 *
 * @author matthewyeend
 */
public final class Shapes {

    private Shapes() {
    }

    public sealed interface Shape permits Circle, Rectangle {
    }

    public record Circle(double radius) implements Shape {
        public Circle {
            if (radius <= 0) {
                throw new IllegalArgumentException("Radius must be positive");
            }
        }
    }

    public record Rectangle(double width, double height) implements Shape {
        public Rectangle {
            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Dimensions must be positive");
            }
        }
    }

    public static double area(Shape shape) {
        return switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
        };
    }
}