public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double x1 = this.xxPos;
        double y1 = this.yyPos;
        double x2 = p.xxPos;
        double y2 = p.yyPos;

        double xAbs = Math.abs(x1 - x2);
        double yAbs = Math.abs(y1 - y2);
        return Math.sqrt((xAbs * xAbs) + (yAbs * yAbs));
    }

    public double calcForceExertedBy(Planet p) {
        double radiusSquare = this.calcDistance(p) * this.calcDistance(p);
        return (G * this.mass * p.mass) / radiusSquare;
    }

    public double calcForceExertedByX(Planet p) {
        double distanceX = p.xxPos - this.xxPos;
        double force = this.calcForceExertedBy(p);
        double radius = this.calcDistance(p);
        return (force * distanceX) / radius;
    }

    public double calcForceExertedByY(Planet p) {
        double distanceY = p.yyPos - this.yyPos;
        double force = this.calcForceExertedBy(p);
        double radius = this.calcDistance(p);
        return (force * distanceY) / radius;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double sumForce = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            } else {
                sumForce += this.calcForceExertedByX(p);
            }
        }
        return sumForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double sumForce = 0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            } else {
                sumForce += this.calcForceExertedByY(p);
            }
        }
        return sumForce;
    }

    public void update(double dt, double fX, double fY) {
        double accelerationX = fX / this.mass;
        double accelerationY = fY / this.mass;

        this.xxVel += dt * accelerationX;
        this.yyVel += dt * accelerationY;

        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
