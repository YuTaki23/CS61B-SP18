public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int useless = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        Planet[] planets = new Planet[N];
        double radius = in.readDouble();

        for(int i = 0; i < N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFilename = in.readString();

            Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFilename);
            planets[i] = p;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);

        double time = 0;
        int numOfBodies = planets.length;
        while(time < T) {
            // create xForces and yForces arrays
            double[] xForces = new double[numOfBodies];
            double[] yForces = new double[numOfBodies];

            // calculate the net x and y forces for each body, store them respectively
            for(int i = 0; i < numOfBodies; i++) {
                Planet curBody = planets[i];
                double curForceX = 0, curForceY = 0;
                for(int j = 0; j < numOfBodies; j++) {
                    if(j != i) {
                        curForceX += curBody.calcForceExertedByX(planets[j]);
                        curForceY += curBody.calcForceExertedByY(planets[j]);
                    }

                }
                xForces[i] = curForceX;
                yForces[i] = curForceY;
            }

            // update each body's position, velocity and acceleration
            for(int i = 0; i < numOfBodies; i++) planets[i].update(dt, xForces[i], yForces[i]);


            // Draw the background image.
            StdDraw.picture(1, 1, "images/starfield.jpg", radius * 2, radius * 2);

            // Draw all of the Bodies
            for(Planet body : planets) body.draw();

            // show the offscreen buffer
            StdDraw.show();

            // pause the animation for 10 ms
            StdDraw.pause(10);

            // increase time by dt
            time += dt;

        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}