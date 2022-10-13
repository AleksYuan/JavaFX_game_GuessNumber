package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program implements a simple server that listens to port 8189 and echoes back all client
 * input.
 *
 * @author Cay Horstmann
 * @version 1.20 2004-08-03
 */
public class GameServer {

    static int minValue;
    static int maxValue;
    static int randomNumber;
    static int liveCount = 10;


    public static void main(String[] args) {
        try {
            // establish server socket
            ServerSocket s = new ServerSocket(8200);

            while (true) {
                // wait for client connection
                Socket incoming = s.accept();
                try {
                    InputStream inStream = incoming.getInputStream();
                    OutputStream outStream = incoming.getOutputStream();

                    Scanner in = new Scanner(inStream);
                    PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);

                    out.println("Hello! Enter BYE to exit or play a Game (you have 10 chances)");
                    out.println("Set minimum value of range");

                    while (in.hasNextLine()) {
                        try {
                            setMinValue(in.nextInt());
                            break;
                        } catch (Exception e) {
                        }
                    }

                    out.println("Set maximum value of range");
                    while (true) {
                        try {
                            setMaxValue(in.nextInt());
                            break;
                        } catch (Exception e) {
                        }
                    }

                    setRandomNumber();
                    System.out.println(randomNumber);

                    while (true) {

                        if (liveCount == 0) {
                            out.println("YOU LOOSE!! It was number " + randomNumber);
                            out.println("Game Again, u have 10 attempt");
                            setLiveCount(10);
                        } else {

                            try {
                                Integer data = in.nextInt();

                                if (data == randomNumber) {
                                    out.println("YOU WIN!!!");
                                    setRandomNumber();
                                } else {
                                    out.println("NOT, you are wrong, try again");
                                    liveCount -= 1;
                                }
                            } catch (Exception e) {
                                String line = in.nextLine();

                                if (line.trim().equalsIgnoreCase("bye")) {
                                    break;
                                } else {
                                    out.println("I dont understand");
                                    liveCount -= 1;
                                }
                            }
                        }
                    }
                } finally {
                    incoming.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRandomNumber() {
        randomNumber = (int) (Math.random() * (maxValue - minValue) + minValue + 1);
    }

    public static void setMinValue(int minValue) {
        GameServer.minValue = minValue;
    }

    public static void setMaxValue(int maxValue) {
        GameServer.maxValue = maxValue;
    }

    public static void setLiveCount(int liveCount) {
        GameServer.liveCount = liveCount;
    }
}
