public class Planet {
    // 实例变量
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    final static double G = 6.67e-11;


    // 构造函数1
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    // 构造函数2
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * 计算两行星之间的距离
     * @param p
     * @return
     */
    public double calcDistance(Planet p) {
        double result = 0;
        double xDistance = Math.abs(this.xxPos - p.xxPos);
        double yDistance = Math.abs(this.yyPos - p.yyPos);
        result = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
        return result;
    }

    /**
     * 计算一个行星对另一个行星施加的力
     * @param p
     * @return
     */
    public double calcForceExertedBy(Planet p) {
        return (G * p.mass * this.mass) / (Math.pow(calcDistance(p), 2));
    }

    /**
     * 计算两行星之间x方向与y方向上的力，其中要注意从属关系，即dx的正负
     * @param p
     * @return
     */
    public double calcForceExertedByX(Planet p) {
        double distance = p.xxPos - this.xxPos;
        return (calcForceExertedBy(p) * distance) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double distance = p.yyPos - this.yyPos;
        return (calcForceExertedBy(p) * distance) / calcDistance(p);
    }

    /**
     * 对一系列行星求力和
     * @param pArray
     * @return
     */
    public double calcNetForceExertedByX(Planet[] pArray) {
        double result = 0;
        for(Planet p:pArray) {
            if (!this.equals(p)) {
                result += calcForceExertedByX(p);
            }
        }
        return result;
    }

    public double calcNetForceExertedByY(Planet[] pArray) {
        double result = 0;
        for(Planet p:pArray) {
            if (!this.equals(p)) {
                result += calcForceExertedByY(p);
            }
        }
        return result;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;

        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
}
