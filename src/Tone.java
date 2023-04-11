import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {

    protected static final int SAMPLE_RATE = 16 * 1024;
    protected static final int SAMPLE_SIZE = 8;
    protected static final int CHANNELS = 1;
    protected static final int DEFAULT_LENGTH_MS = 1000;

    private double freq;
    private int lengthms;
    private byte[] sineWaveBuffer;

    public Tone(double freq, int lengthms) {
        this.freq = freq;
        this.lengthms = lengthms;
        this.sineWaveBuffer = createSineBuffer(freq, lengthms);
    }

    public Tone(double freq) {
        this(freq, DEFAULT_LENGTH_MS);
    }

    public void play() throws LineUnavailableException {
        AudioFormat af = new AudioFormat(SAMPLE_RATE, SAMPLE_SIZE, CHANNELS, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, SAMPLE_RATE);
        line.start();

        line.write(sineWaveBuffer, 0, sineWaveBuffer.length);
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
        updateSineBuffer();
    }

    public int getLengthms() {
        return lengthms;
    }

    public void setLengthms(int lengthms) {
        this.lengthms = lengthms;
        updateSineBuffer();
    }

    private void updateSineBuffer() {
        this.sineWaveBuffer = createSineBuffer(this.freq, this.lengthms);
    }
    public static byte[] createSineBuffer(double freq, int lengthms) {
        int samples = (lengthms * SAMPLE_RATE / 1000);
        byte[] output = new byte[samples];

        double period = (double) SAMPLE_RATE / freq;
        for (int i = 0; i < samples; i++) {
            double angle = Math.sin(Math.PI * i / period);
            output[i] = (byte) (angle * 127f);
        }

        return output;
    }
}
