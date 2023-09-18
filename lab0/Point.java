/**
 * CS2030S Lab 0: Point.java
 * Semester 2, 2022/23
 *
 * <p>The Point class encapsulates a point on a 2D plane.
 *
 * @author Keagan Pang
 */
class Point {
  // TODO
  private double x;
  private double y;

  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  //accessor method
  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public String toString() {
    return "(" + String.valueOf(this.x) + ", " + String.valueOf(this.y) + ")";
  }

}
