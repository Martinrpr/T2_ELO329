import javax.sound.sampled.*;

/**
 * Genera y reproduce un pitido corto mediante síntesis PCM.
 * No requiere archivos de audio externos.
 */
public class SoundPlayer {

    private static final float SAMPLE_RATE = 44100f;
    private static final int   DURATION_MS = 120;
    private static final double FREQUENCY  = 880.0; // La5

    public static void playBeep() {
        new Thread(() -> {
            try {
                int numSamples = (int) (SAMPLE_RATE * DURATION_MS / 1000.0);
                byte[] buf = new byte[numSamples];
                for (int i = 0; i < numSamples; i++) {
                    double t = i / SAMPLE_RATE;
                    // tono puro con fade-out suave al final
                    double envelope = 1.0 - (double) i / numSamples;
                    buf[i] = (byte) (127 * Math.sin(2 * Math.PI * FREQUENCY * t) * envelope);
                }
                AudioFormat fmt = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, fmt);
                SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(fmt);
                line.start();
                line.write(buf, 0, buf.length);
                line.drain();
                line.close();
            } catch (Exception ignored) {}
        }, "beep-thread").start();
    }
}
