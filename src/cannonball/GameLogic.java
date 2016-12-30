/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Emil
 */
public class GameLogic implements Runnable {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ServerSocket serverSocket;

    private String ip = "localhost";
    private int port = 22222;
    private Scanner scanner = new Scanner(System.in);

    Player player = new Player();
    Ball ballT = new Ball();
    ArrayList<Ball> ball = new ArrayList<>();
    ArrayList<GameObject> gameObject = new ArrayList<>();

    public GameLogic() {
        System.out.println("Please input the IP: ");
        ip = scanner.nextLine();
        System.out.println("Please input the port: ");
        port = scanner.nextInt();
        while (port < 1 || port > 65535) {
            System.out.println("The port you entered was invalid, please input another port: ");
            port = scanner.nextInt();
        }
        if (!connect()) {
            initializeServer();
        }
        player.position.setBounds(250, 260, 100, 100);
//        for (Ball ball : ball) {
//            gameObject.add(ball);
//            // player.register(ball);
//        }
        gameObject.add(player);

    }

    private boolean connect() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
//			accepted = true;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
            return false;
        }
        System.out.println("Successfully connected to the server.");
        return true;
    }

    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
//		yourTurn = true;
//		circle = false;
    }

    public void update() {
        player.update(ball);

        ballT.position.setBounds(ballT.position.x + ballT.dx,
                ballT.position.y + ballT.dy,
                ballT.position.width,
                ballT.position.height);
        ballT.dy++;

        if (ballT.dy > 60) {
            ballT.dy = 30;
        }

    }
    public KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Key pressed: " + e.getKeyCode());
            if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
                // player.stateGame.goingLeft = true;

                int i;
                for (i = 0; i > -20; i--) {
                    player.dx = i;
                }
                i = 0;

            }
            if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
                // player.stateGame.goingRight = true;

                int i;
                for (i = 0; i > -20; i--) {
                    player.dy = i;
                }
                i = 0;

            }
            if (e.getKeyCode() == 44) {
                player.angle++;
                if(player.angle>9) player.angle=9;
                System.out.println("angle is " + player.angle);
            }
            if (e.getKeyCode() == 46) {
                player.angle--;
                if(player.angle<0) player.angle=0;
                System.out.println("angle is " + player.angle);

            }

            if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {

                int i;
                for (i = 0; i < 20; i++) {
                    player.dy = i;
                }
                i = 0;

            }
            if (e.getKeyCode() == 32) {

                ballT.position.setBounds(player.position.x + 70,
                        player.position.y + 15,
                        player.position.width / 3,
                        player.position.height / 3);
                int x=0,y=0;
                if(player.angle >=8 && player.angle<=9){ x = 6; y = 30;}
                if(player.angle >=7 && player.angle<8){ x = 9; y = 27;}
                if(player.angle >=6 && player.angle<7){ x = 12; y = 24;}
                if(player.angle >=5 && player.angle<6){ x = 15; y = 21;}
                if(player.angle >=4 && player.angle<5){ x = y = 18;}
                if(player.angle >=3 && player.angle<4){ x = 21; y = 15;}
                if(player.angle >=2 && player.angle<3){x = 24; y = 12;}
                if(player.angle >=1 && player.angle<2){x = 27; y = 9;}
                if(player.angle >=0 && player.angle<1){x = 30; y = 6;}
                ballT.dx =  x;
                ballT.dy = -y;
                

                gameObject.add(ballT);
                System.out.println("dx = " + ballT.dx + "dy = " + ballT.dy);

            }
            if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
                //player.stateGame.goingRight = true;

                int i;
                for (i = 0; i < 20; i++) {
                    player.dx = i;
                }
                i = 0;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Key released: " + e.getKeyCode());
            if (e.getKeyCode() == 37
                    || e.getKeyCode() == 39
                    || e.getKeyCode() == 65
                    || e.getKeyCode() == 68
                    || e.getKeyCode() == 40
                    || e.getKeyCode() == 83
                    || e.getKeyCode() == 38
                    || e.getKeyCode() == 87) {
                player.dx = 0;
                player.dy = 0;
//                player.stateGame.goingRight = false;
//                player.stateGame.goingLeft = false;

            }
        }
    };

    @Override
    public void run() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
