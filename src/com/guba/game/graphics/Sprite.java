package com.guba.game.graphics;

import com.guba.game.level.tile.Tile;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-13
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class Sprite {

    public final int SIZE;
    private int x, y, width, height;
    public int[] pixels;
    protected SpriteSheet sheet;
    public char id;

    Color myColor = new Color(0x1B87E0, true);




    public static Sprite grass = new Sprite(16, 0, 1, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite hedge = new Sprite(16, 4, 4, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    // Spawn level Sprites here:
    public static Sprite spawn_grass = new Sprite(16, 0, 1, SpriteSheet.tiles);
    public static Sprite spawn_wall = new Sprite(16, 4, 1, SpriteSheet.tiles);
    public static Sprite spawn_floor = new Sprite(16, 2, 1, SpriteSheet.tiles);
    public static Sprite spawn_boundary = new Sprite(16, 0, 2, SpriteSheet.tiles);

    // MOB Sprites
    public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_up);
    public static Sprite collision_sprite = new Sprite(16, Tile.col_collision_tile);

    // Projectile Sprites
    public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectiles_wizard);

    // Gun Sprites
    public static Sprite gun_glock_right = new Sprite(16, 0, 0, SpriteSheet.gun_glock);
    public static Sprite gun_glock_left = new Sprite(16, 1, 0, SpriteSheet.gun_glock);


    // Particles
    public static Sprite particle_normal = new Sprite(3, 0xaaaaaa);


    // Inventory Sprite
    public static Sprite inventory_item_index = new Sprite(16, 6, 3, SpriteSheet.tiles);
    public static Sprite inventory_item_selected = new Sprite(16, 7, 3, SpriteSheet.tiles);



    protected Sprite(SpriteSheet sheet, int width, int height) {
            SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;

    }

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();

    }

    public Sprite(int width, int height, int x, int y, SpriteSheet sheet, char c) {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        this.x = x;
        this.y = y;
        this.sheet = sheet;
        this.id = c;
        load();

    }

    public Sprite(int width, int height, int colour) {
        SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        setColour(colour);
    }
    public Sprite(int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = pixels;

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getId() { return id; }

    public Sprite(int size, int colour) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.width = size;
        this.height = size;
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < width * height; i++) {
            pixels[i] = colour;
        }
    }

    private void load() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];

            }
        }
    }


}
