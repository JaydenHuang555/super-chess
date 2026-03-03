package jay.util.math.geom;

public class Line2d {

    private final Translation2d m_start, m_end;

    public Line2d(Translation2d start, Translation2d end) {
        this.m_start = start;
        this.m_end = end;
    }

    public Translation2d getStart() {
        return m_start;
    }

    public Translation2d getEnd() {
        return m_end;
    }

    public double getMidpoint() {
        return m_start.plus(m_end).norm() / 2.0;
    }

    public double getDistance() {
        return GeomUtil.distance(m_start, m_end);
    }

    public double getSlope() {
        return (m_end.getY() - m_start.getY()) / (m_end.getX() - m_start.getX());
    }

}
