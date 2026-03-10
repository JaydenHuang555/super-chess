package jay.util.math.geom;

import jay.util.math.MathUtil;

public class Rotation2d {

    public static final Rotation2d ZERO = new Rotation2d(0.0);
    public static final Rotation2d CCLD90 = new Rotation2d(Math.PI / 2.0);
    public static final Rotation2d CCD90 = new Rotation2d(-Math.PI / 2.0);
    public static final Rotation2d PI = new Rotation2d(Math.PI);

    private final double m_value;
    private final double m_cos, m_sin;

    public Rotation2d(double radians) {
        m_value = radians;
        this.m_cos = Math.cos(radians);
        this.m_sin = Math.sin(radians);
    }

    public static Rotation2d fromDegrees(double degrees) {
        return new Rotation2d(degrees * (180.0/Math.PI));
    }

    public static Rotation2d fromRadians(double radians) {
        return new Rotation2d(radians);
    }

    public double get() {
        return m_value;
    }

    public double getWrapped() {
        return MathUtil.angleModulus(get());
    }

    public double getCos() {
        return m_cos;
    }

    public double getSin() {
        return m_sin;
    }

}
