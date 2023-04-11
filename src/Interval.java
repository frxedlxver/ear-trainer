import javax.sound.sampled.LineUnavailableException;

public class Interval {

    public enum Scale {
        MAJOR,
        MINOR
    }

    protected static final double SEMITONE_RATIO = 1.059;
    protected static final int[] MAJOR_INTERVALS = {0, 2, 4, 5, 7, 9, 11, 12};
    protected static final int[] MINOR_INTERVALS = {0, 1, 3, 5, 6, 8, 10, 12};


    private final Tone rootTone;
    private final Tone secondTone;
    private final int interval;
    private boolean narrateActivity = false;

    public Interval(double rootFreq, int interval, Scale scale) {

        double secondFreq = switch (scale) {
            case MAJOR -> rootFreq * (Math.pow(SEMITONE_RATIO, MAJOR_INTERVALS[interval - 1]));
            case MINOR -> rootFreq * (Math.pow(SEMITONE_RATIO, MINOR_INTERVALS[interval - 1]));
        };

        this.interval = interval;
        this.rootTone = new Tone(rootFreq);
        this.secondTone = new Tone(secondFreq);
    }

    public int getInterval() {
        return interval;
    }

    public void setNarrationMode(boolean b) {
        narrateActivity = b;
    }

    public void play() throws LineUnavailableException, InterruptedException {
        if (narrateActivity) {
            System.out.println("Playing first tone");
        }
        rootTone.play();
        Thread.sleep(1000);
        if (narrateActivity) {
            System.out.println("Playing second tone");
        }
        secondTone.play();
        Thread.sleep(1000);
    }
}
