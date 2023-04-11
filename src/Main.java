public class Main {
    public static void main(String[] args) {
        Interval i = new Interval(400, 2, Interval.Scale.MAJOR);
        i.setNarrationMode(true);
        try {
            i.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}