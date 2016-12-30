/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cannonball;

import java.awt.AWTException;
import java.awt.Robot;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emil
 */
public class GameLogic implements Runnable {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private ServerSocket serverSocket;
    private Thread thread;
    private boolean accepted;
    private boolean host;


    private String ip = "localhost";
    private int port = 22222;
    private Scanner scanner = new Scanner(System.in);

    Player player = new Player();
    Player opponent =  new Player();
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
        if (!connect()) initializeServer();
        player.position.setBounds(250, 500, 50, 50);
        opponent.position.setBounds(1000, 500, 50, 50);
//        for (Ball ball : ball) {
//            gameObject.add(ball);
//            // player.register(ball);
//        }
        gameObject.add(player);
        gameObject.add(opponent);
        thread = new Thread(this, "Cannon");
        thread.start();
    }

    private boolean connect() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
                        System.out.println("dis initialized");
        
		accepted = true;
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
		host = true;
	}
    
    private void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void checkQueue(){
    
        try {
            if(dis!=null){
            int keyCode = dis.readInt();
            if(host){
            if(keyCode==-1){
                action(opponent,keyCode,false );
            }
            else{
                action(opponent,keyCode,true );
            }}
            else{
            if(keyCode==-1){
                action(player,keyCode,false );
            }
            else{
                action(player,keyCode,true );
            }
            }
           }
	} catch (IOException e) {
            e.printStackTrace();		
        }
        
        
    }
    
    
    public void update() {
        player.update(ball);
        opponent.update(ball);
        
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
            try {
                    // player.stateGame.goingLeft = true;
                if(dos!=null){
                    dos.write(e.getKeyCode());
                    dos.flush();
                }
                } catch (IOException ex) {
                    Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
            System.out.println("Key pressed: " + e.getKeyCode());
            if(host){
           action(player, e.getKeyCode(), true);
            }
            else{
            action(opponent, e.getKeyCode(), true);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("Key released: " + e.getKeyCode());
            if (       e.getKeyCode() == 37
                    || e.getKeyCode() == 39
                    || e.getKeyCode() == 65
                    || e.getKeyCode() == 68
                    || e.getKeyCode() == 40
                    || e.getKeyCode() == 83
                    || e.getKeyCode() == 38
                    || e.getKeyCode() == 87) {
                if(host){
                 action(player, e.getKeyCode(), false);
                }
                else{
                action(opponent, e.getKeyCode(), false);
                }
                try {
                    // player.stateGame.goingLeft = true;
                    if(dos!=null){
                    dos.write(-1);
                    dos.flush();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
//                player.stateGame.goingRight = false;
//                player.stateGame.goingLeft = false;

            }
        }
    };

    public void action(Player player, int code, boolean isPress ){
    if(isPress){
    if (code== 37 || code == 65) {
                int i;
                for (i = 0; i > -20; i--) {
                    player.dx = i;
                }
                i = 0;

            }
            if (code == 38 || code == 87) {
                // player.stateGame.goingRight = true;

                int i;
                for (i = 0; i > -20; i--) {
                    player.dy = i;
                }
                i = 0;

            }
            if (code == 44) {
                player.angle++;
                if(player.angle>9) player.angle=9;
                System.out.println("angle is " + player.angle);
            }
            if (code == 46) {
                player.angle--;
                if(player.angle<0) player.angle=0;
                System.out.println("angle is " + player.angle);

            }
            
            if (code == 40 || code == 83) {
                // player.stateGame.goingRight = true;

                int i;
                for (i = 0; i < 20; i++) {
                    player.dy = i;
                }
                i = 0;

            }
            if (code == 32) {
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
               if(host){
                    ballT.dx =  x;
                    ballT.dy = -y;
               }
               else {
                    ballT.dx =  -x;
                    ballT.dy =  -y;
               }
                
                

                gameObject.add(ballT);
                System.out.println("dx = " + ballT.dx + "dy = " + ballT.dy);

            }
            if (code == 39 || code == 68) {
                //player.stateGame.goingRight = true;

                int i;
                for (i = 0; i < 20; i++) {
                    player.dx = i;
                }
                i = 0;

            }
    }
    else {
        player.dx=0;
        player.dy=0;
    }
    }
    
    
    @Override
    public void run() {
       checkQueue();
       update();
       
       if(!host&&!accepted){
           listenForServerRequest();
       }
    }

}
