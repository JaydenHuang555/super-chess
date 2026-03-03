package jay.util.math.geom;

public class Translation2d {

    protected final double m_x;
    protected final double m_y;

    public Translation2d(double x, double y) {
        this.m_x = x;
        this.m_y = y;
    }

    public double norm() {
        return Math.hypot(m_x, m_y);
    }

    public double getX() {
        return m_x;
    }

    public double getY() {
        return m_y;
    }

    public Translation2d minus(double x, double y){
        return new Translation2d(this.m_x - x, this.m_y - y);
    }

    public Translation2d minus(Translation2d other) {
        return minus(other.m_x, other.m_y);
    }

    public Translation2d plus(double x, double y){
        return new Translation2d(this.m_x + x, this.m_y + y);
    }

    public Translation2d plus(Translation2d other) {
        return minus(other.m_x, other.m_y);
    }

    public double dot(Translation2d other) {
        return m_x * other.m_x + m_y * other.m_y;
    }

    public Translation2d floor() {
        return new Translation2d(
                Math.floor(m_x),
                Math.floor(m_y)
        );
    }

    public Translation2d round() {
        return new Translation2d(
                Math.round(m_x),
                Math.round(m_y)
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Translation2d other
                && Math.abs(other.m_x - m_x) < 1E-9
                && Math.abs(other.m_y - m_y) < 1E-9;
    }

    @Override
    public String toString() {
        return String.format("{X:%f, Y:%f}", m_x, m_y);
    }

}
