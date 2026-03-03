package jay.util.math.geom;

public class GeomUtil {

    public static final Translation2d TRANSLATION2D_ZERO = new Translation2d(0.0, 0.0);
    public static final Rotation2d ROTATION2D_PI = new Rotation2d(Math.PI);

    public static Translation2d flipAcrossY(Translation2d origin, Translation2d translation) {
        double deltaX = translation.minus(origin).getX();
        return translation.minus(deltaX * 2.0, 0.0);
    }

    public static Translation2d flipAcrossY(double distance, Translation2d translation) {
        return new Translation2d(distance - translation.getX(), translation.getY());
    }

    public static Translation2d rotateAround(Translation2d center, Translation2d translation, Rotation2d rotation) {
        return new Translation2d(
                (translation.getX() - center.getX()) * rotation.getCos() - (translation.getY() - center.getY()) * rotation.getSin() + center.getX(),
                (translation.getX() - center.getX()) * rotation.getSin() + (translation.getY() - center.getY()) * rotation.getCos() + center.getY()
        );
    }

    public static Translation2d rotateAround(Translation2d center, Translation2d translation) {
        return rotateAround(center, translation, ROTATION2D_PI);
    }

    public static double distance(Translation2d translation1, Translation2d translation2) {
        return translation1.minus(translation2).norm();
    }
}
