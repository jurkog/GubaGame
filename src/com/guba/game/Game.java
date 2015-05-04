package com.guba.game;

import com.guba.game.entity.Item;
import com.guba.game.entity.Weapon;
import com.guba.game.entity.mob.OnlinePlayer;
import com.guba.game.entity.mob.Player;
import com.guba.game.entity.projectile.Projectile;
import com.guba.game.entity.projectile.WizardProjectile;
import com.guba.game.graphics.Screen;
import com.guba.game.input.Keyboard;
import com.guba.game.input.Mouse;
import com.guba.game.level.Level;
import com.guba.game.level.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Arc2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-12
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game extends Canvas implements Runnable {
    // Canvas Configuration
    private static int width = 300;
    private static int height = width / 16 * 9;
    private static int scale = 4;

    // Basic Level loading
    private Thread thread, udpMovementThread, tcpMainThread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;

    private Screen screen;

    // Buffered Image and Buffer Strategy
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    public static BufferStrategy bs;

    // Network and I/O Streams
    public static DatagramSocket sendSocket;
    public static InetAddress IPAddress;
    public static String IPString = "localhost";    //"173.214.165.195"

    public static Socket socket;
    public static PrintWriter out;
    public static BufferedReader in;

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();

        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        level.add(player);
        addKeyListener(key);
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public synchronized void start() {
        running = true;

        thread = new Thread(this, "Display");
        udpMovementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Setting up MasterServer to ask for port
                    sendSocket = new DatagramSocket(); //"173.214.165.195"

                    IPAddress = InetAddress.getByName(IPString);
                    byte[] messageToByte = new byte[1024];
                    messageToByte = new String("port/"+Player.name).toLowerCase().trim().getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(messageToByte, messageToByte.length, IPAddress, 5000);
                    sendSocket.send(sendPacket);

                    while (true) {
                        byte[] receiveData = new byte[1024];

                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        sendSocket.receive(receivePacket);
                        String message = new String(receivePacket.getData()).toLowerCase().trim();
                        System.out.println("Message: " + message);
                        String data[] = message.split("/");
                        if (data[0].equals("move")) {
                            for (OnlinePlayer player : Level.onlinePlayers) {
                                if (player.getName().toLowerCase().equals(data[3].toLowerCase())) {
                                    System.out.println("Moving player: " + player.getName());

                                    player.moveLocation(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[4]));

                                }
                            }
                        } else if (data[0].equals("stop")) {
                            for (OnlinePlayer player : Level.onlinePlayers) {
                                if (player.getName().toLowerCase().equals(data[1].toLowerCase())) {
                                    System.out.println("Stoping player: " + player.getName());

                                    player.stopWalking();

                                }
                            }
                        } else if (data[0].equals("shoot")) {
                            shoot(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], Float.parseFloat(data[4]));

                        }

                    }

                } catch (IOException e) {
                    System.exit(0);
                    System.out.println("[0] Had a problem establishing connection with host.");
                    e.printStackTrace();
                }
            }
        });

        tcpMainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(IPString, 5000);
                    out = new PrintWriter(socket.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Message: " + message);
                        message.trim();
                        String data[] = message.split("/");
                        if (data[0].equals("connect")) {
                            System.out.println("Adding player message:" + message);
                            OnlinePlayer addPlayer = new OnlinePlayer(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3].toLowerCase(), data[4], Integer.parseInt(data[5]));

                            Level.onlinePlayers.add(addPlayer);
                        } else if(data[0].equals("send")) {
                            out.println("connect/304/992/" + Player.name + "/null/1");
                            out.flush();
                            System.out.println("Sent connection message.");
                        } else if (data[0].equals("udpstart")) {
                            udpMovementThread.start();
                        } else if (data[0].equals("disconnect")) {
                            for (OnlinePlayer player : Level.onlinePlayers) {
                                if (player.getName().toLowerCase().equals(data[1])) {
                                    player.remove();
                                }
                            }
                        } else if (data[0].equals("drop")) {
                            level.add(new Item(Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3]));
                        } else if (data[0].equals("inventory")) {
                            System.out.println("Gun: " + data[1]);
                            for (int i = 0; i < 8; i++) {
                                if (Player.inventory[i] == null) {
                                    String[] itemParse = data[1].split("_");
                                    if (itemParse[0].equals("gun"))
                                        Player.inventory[i] = Weapon.gun_glock;
                                    else
                                        Player.inventory[i] = new Item(data[1]);

                                    break;

                                }
                            }
                        } else if (data[0].equals("grab")) {
                            System.out.println("Grab message");
                            for (int i = 0; i < Level.itemsOnMap.size(); i++) {
                                Item item = Level.itemsOnMap.get(i);
                                int dX = Math.abs(Integer.parseInt(data[1]) - item.getX());
                                int dY = Math.abs(Integer.parseInt(data[2]) - item.getY());
                                double distance = Math.sqrt(dX * dX + dY * dY);

                                if (distance <= 16) {
                                    item.remove();
                                }
                            }
                        } else if (data[0].equals("weapon")) {
                            for (OnlinePlayer player : Level.onlinePlayers) {
                                if (player.getName().equals(data[2])) {
                                    if (data[1].equals("gun_glock_right")) {
                                        player.currentWeapon = Weapon.gun_glock;
                                    } else {
                                        player.currentWeapon = Weapon.fist;
                                    }
                                }
                            }
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });



        thread.start();
        tcpMainThread.start();

    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
            udpMovementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;

        requestFocus();


            while (running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while (delta >= 1) {
                    update();
                    updates++;
                    delta--;
                    render();
                    frames++;

                }

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    frame.setTitle("Game | " + updates + " ups, " + frames + " fps");
                    frames = 0;
                    updates = 0;
                }

    }
        stop();
    }

    public void update() {
        key.update();
        level.update();

    }

    public void render() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        int xScroll = player.getX() - screen.width / 2;
        int yScroll = player.getY() - screen.height / 2;
        level.render(xScroll, yScroll, screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    // Engine / Server unrelated methods

    protected void shoot(int x, int y, String name, double dir) {
        for (OnlinePlayer onlinePlayer : Level.onlinePlayers) {
            if (onlinePlayer.getName().toLowerCase().equals(name)) {
                Projectile p = new WizardProjectile(x, y, name, dir);
                level.add(p);

            }
        }
    }

    public static void main (String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Game.out.println("disconnect");
                    Game.out.flush();

                    System.exit(0);
                } catch (Exception e1) {e1.printStackTrace();}

            }
        });
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }
}
