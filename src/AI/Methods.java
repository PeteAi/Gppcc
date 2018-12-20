package AI;

public class Methods {
    public static float dist(float x1, float y1, float x2, float y2) {
        float a = x1 - x2;
        float b = y1 - y2;
        float c = (float) Math.sqrt(a * a + b * b);
        return c;
    }
}
