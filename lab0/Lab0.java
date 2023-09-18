import java.util.Scanner;

/**
 * CS2030S Lab 0: Estimating Pi with Monte Carlo
 * Semester 2, 2022/23
 *
 * <p>This program takes in two command line arguments: the 
 * number of points and a random seed.  It runs the
 * Monte Carlo simulation with the given argument and print
 * out the estimated pi value.
 *
 * @author Keagan Pang 
 */

class Lab0 {

  // TODO 
  static double estimatePi(int numOfPoints, int seed) {
    RandomPoint.setSeed(seed);
    Circle testCircle = new Circle(new Point(0.5,0.5), 0.5);
    double pointsGenerated = 0;
    double pointsInside = 0;
    
    while (pointsGenerated < numOfPoints) {
        RandomPoint rngPoint = new RandomPoint(0,1,0,1);
        pointsGenerated += 1;
        if (testCircle.contains(rngPoint)) {
            pointsInside += 1;
        }
    }

    return (pointsInside/numOfPoints) * 4; 
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numOfPoints = sc.nextInt();
    int seed = sc.nextInt();

    double pi = estimatePi(numOfPoints, seed);

    System.out.println(pi);
    sc.close();
  }
}
