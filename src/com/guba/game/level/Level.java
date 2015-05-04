package com.guba.game.level;

import com.guba.game.entity.Entity;
import com.guba.game.entity.Item;
import com.guba.game.entity.mob.OnlinePlayer;
import com.guba.game.entity.mob.Player;
import com.guba.game.entity.particle.Particle;
import com.guba.game.entity.projectile.Projectile;
import com.guba.game.graphics.Screen;
import com.guba.game.level.tile.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 1:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Level {

    protected int width, height;
    protected int[] tilesInt;
    protected int[] tiles;
    public final static int ITEM_GRAB_RADIUS = 16;
    private List<Entity> entities = new ArrayList<Entity>();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
    private List<Particle> particles = new ArrayList<Particle>();
    public static List<Item> itemsOnMap = new ArrayList<Item>();

    public static List<Player> players = new ArrayList<Player>();
    public static List<OnlinePlayer> onlinePlayers = new ArrayList<OnlinePlayer>();

    public static Level spawn = new SpawnLevel("/com/guba/game/graphics/res/levels/spawn.png");


    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();

    }

    public Level(String path) {
        loadLevel(path);
        generateLevel();

    }

    protected void generateLevel() {

    }

    protected void loadLevel(String path) {

    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }

        for (int i = 0; i < itemsOnMap.size(); i++) {
            itemsOnMap.get(i).update();
        }

        for (int i = 0; i < onlinePlayers.size(); i++) {
            onlinePlayers.get(i).update();
        }

        remove();
    }

    public void remove() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) entities.remove(i);
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) projectiles.remove(i);
        }

        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) particles.remove(i);
        }

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isRemoved()) players.remove(i);
        }

        for (int i = 0; i < itemsOnMap.size(); i++) {
            if (itemsOnMap.get(i).isRemoved()) itemsOnMap.remove(i);
        }

        for (int i = 0; i < onlinePlayers.size(); i++) {
            if (onlinePlayers.get(i).isRemoved()) onlinePlayers.remove(i);
        }

    }

    private void time() {

    }

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) { // C = corners
            int xt = (x + c % 2 * size - xOffset) >> 4;
            int yt = (y + c / 2 * size - yOffset) >> 4;
            if (getTile(xt, yt).solid())
                solid = true;
        }
        return solid;
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++){
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);

                }
            }
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }

        for (int i = 0; i < itemsOnMap.size(); i++) {
            itemsOnMap.get(i).render(screen);
        }

        for (int i = 0; i < onlinePlayers.size(); i++) {
            onlinePlayers.get(i).render(screen);
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(screen);
        }

    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Projectile) {
            projectiles.add((Projectile) e);
        } else if (e instanceof Player) {
            players.add((Player) e);
        } else if (e instanceof Item) {
            itemsOnMap.add((Item) e);
        } else
            entities.add(e);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayersAt(int index) {
        return players.get(index);
    }

    public Player getClientPlayer() { // This is you essentially since you get added to the game.
        return players.get(0);
    }


    public Tile getTile(int x, int y) {
        if (x < 0 || x > width-1 || y < 0 || y > height-1) return Tile.spawn_boundary;
        if (tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
        if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
        if (tiles[x + y * width] == Tile.col_spawn_wall) return Tile.spawn_wall;
        return Tile.spawn_boundary;
    }

    public boolean getPlayer(int x, int y) {
        for (OnlinePlayer onlinePlayer : onlinePlayers) {
            if (onlinePlayer.getX() == x || onlinePlayer.getY() == y)
                return true;
        }
        return false;
    }

}
