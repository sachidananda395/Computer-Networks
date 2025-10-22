/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hp
 */
import java.util.Arrays;

public class SlidingWindowProtocol {
    private int windowSize;
    private int[] frames;
    private boolean[] ack;

    public SlidingWindowProtocol(int windowSize, int frameCount) {
        this.windowSize = windowSize;
        this.frames = new int[frameCount];
        this.ack = new boolean[frameCount];

        for (int i = 0; i < frameCount; i++) {
            frames[i] = i;
            ack[i] = false;
        }
    }

    public void sendFrames() {
        int sendIndex = 0;

        while (sendIndex < frames.length) {
            // Send frames within window
            for (int i = 0; i < windowSize && (sendIndex + i) < frames.length; i++) {
                System.out.println("Sending frame: " + frames[sendIndex + i]);
            }

            // Receive acknowledgments for frames in window
            for (int i = 0; i < windowSize && (sendIndex + i) < frames.length; i++) {
                ack[sendIndex + i] = receiveAck(sendIndex + i);
            }

            // Slide the window forward for acknowledged frames
            while (sendIndex < frames.length && ack[sendIndex]) {
                sendIndex++;
            }
        }
    }

    private boolean receiveAck(int frame) {
        // Simulate acknowledgment reception
        System.out.println("Receiving ack for frame: " + frame);
        return true; // Assume ack is always received for simplicity
    }

    public static void main(String[] args) {
        int windowSize = 4;
        int frameCount = 10;

        SlidingWindowProtocol swp = new SlidingWindowProtocol(windowSize, frameCount);
        swp.sendFrames();
    }
}
