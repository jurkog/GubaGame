package com.guba.game.level.tile;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.level.tile.spawn_level.SpawnFloorTile;
import com.guba.game.level.tile.spawn_level.SpawnGrassTile;
import com.guba.game.level.tile.spawn_level.SpawnWallTile;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tile {

    public int x, y;
    public Sprite sprite;

    // Generic tiles
    public static Tile grass = new GrassTile(Sprite.grass);
    public static Tile rock = new RockTile(Sprite.rock);
    public static Tile flower = new FlowerTile(Sprite.flower);
    public static Tile hedge = new HedgeTile(Sprite.hedge);
    public static Tile voidTile = new VoidTile(Sprite.voidSprite);

    // Spawn tiles: Make sure to change the sprites later on.
    public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
    public static Tile spawn_wall = new SpawnWallTile(Sprite.spawn_wall);
    public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
    public static Tile spawn_boundary = new SpawnWallTile(Sprite.spawn_boundary);

    public final static int col_spawn_grass = 0xff00ff00;
    public final static int col_spawn_wall = 0xff000000;
    public final static int col_spawn_floor = 0xffa54900;
    public final static int col_spawn_player = 0xff00ffff;
    public final static int col_collision_tile = 0xffff00ff;


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen) {
    }

    public void setX() {
        this.x = x;
    }

    public void setY() {
        this.y = y;
    }

    public boolean solid() {
        return false;
    }

}
