public class NBody {
    public static final String imgPath = "images/starfield.jpg";
    public static void main(String[] args) {
        String TString = args[0];
        String dtString = args[1];
        String filename = args[2];
        double T = Double.parseDouble(TString);
        double dt = Double.parseDouble(dtString);
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imgPath);
        StdDraw.show();

        for (Planet planet : planets) {
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int j = 0; j < planets.length; j++) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, imgPath);
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
    }
    public static double readRadius(String filename) {
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int totalPlanets = in.readInt();
        Planet[] planets = new Planet[totalPlanets];
        double radius = in.readDouble();

        for (int i = 0; i < totalPlanets; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }
}
