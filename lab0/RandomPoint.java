import java.util.Random;

// TODO

public class RandomPoint extends Point {
    static Random rng = new Random(1);

    public RandomPoint(double minX, double maxX, double minY, double maxY) {
        super(minX+(maxX-minX)*rng.nextDouble(), minY+(maxY-minY)*rng.nextDouble());
    }
    
    public static void setSeed(int inputSeed) {
        RandomPoint.rng.setSeed(inputSeed);
    }
}
