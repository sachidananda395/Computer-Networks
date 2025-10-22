/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hp
 */
public class Slidingprotocol {
    private int windowSize;
    private int[] frames;

    public Slidingprotocol(int windowSize, int frameCount) {
        this.windowSize = windowSize;
        this.frames = new int[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = i;
        }
    }

    public void sendFrames() {
        int sendIndex = 0;

        while (sendIndex < frames.length) {
            // Sender sends frames within the current window
            for (int i = 0; i < windowSize && (sendIndex + i) < frames.length; i++) {
                System.out.println("Sending frame: " + frames[sendIndex + i]);
//                try {
//                    Thread.sleep(500); // Delay for realistic sending
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
            }

            // Receiver acknowledges the next frame (lastFrame + 1)
            int ackFrame = sendIndex + windowSize;
            if (ackFrame < frames.length) {
                System.out.println("Receiving ack for frame: " + ackFrame);
            } else {
                System.out.println("Receiving ack for all frames. Transmission complete.");
            }


            // Move window forward, skipping next set of frames (like 0–3 → 5–8 → 10–13)
            sendIndex += windowSize + 1; // +1 to skip one frame each batch
        }

        System.out.println("All frames sent and acknowledged!");
    }

    public static void main(String[] args) {
        int windowSize = 3;
        int frameCount = 11;

        Slidingprotocol swp = new Slidingprotocol(windowSize, frameCount);
        swp.sendFrames();
    }
}

