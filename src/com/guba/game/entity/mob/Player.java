package com.guba.game.entity.mob;

import com.guba.game.Game;
import com.guba.game.entity.Item;
import com.guba.game.entity.Weapon;
import com.guba.game.entity.projectile.Projectile;
import com.guba.game.entity.projectile.WizardProjectile;
import com.guba.game.graphics.*;
import com.guba.game.input.Keyboard;
import com.guba.game.input.Mouse;
import com.guba.game.level.Level;
import com.guba.game.level.tile.Tile;
import com.guba.game.level.tile.spawn_level.CollisionTile;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 9:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends Mob {
    private Keyboard input;
    protected Sprite sprite;
    private int fireRate = 0, dir = 2;
    protected boolean walking = false;
    public static String name;
    public Weapon currentWeapon = null;
    protected Tile collisionTile;


    protected  AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
    protected  AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
    protected  AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
    protected  AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
    protected  AnimatedSprite animSprite = null;

    protected StringConverter convert = new StringConverter();

    protected Sprite nameSprite;

    // Player interface

    public static Item[] inventory = new Item[8];


    public Player(int x, int y, String currentWeapon) {
        this.x = x;
        this.y = y;
        if (currentWeapon.equals("gun_glock_right"))
            this.currentWeapon = Weapon.gun_glock;
        else
            this.currentWeapon = Weapon.fist;
        animSprite = down;
        sprite = SpriteSheet.player_up.getSprites()[0];
        collisionTile = new CollisionTile(Sprite.collision_sprite);

    }

    public String getName() {
        return name;
    }

    public Player(int x, int y, Keyboard input) {
        animSprite = down;
        this.x = x;
        this.y = y;
        this.input = input;
        this.currentWeapon = Weapon.fist;
        String testName;
        do {
        testName = (JOptionPane.showInputDialog("Enter your name:")).toLowerCase();
        } while (testName.length() >= 16 || testName.length() <= 0);

        this.name = testName;
        int[] spriteInt = convert.createPixelTextArray(this.name);
        nameSprite = new Sprite(spriteInt, (spriteInt.length/5), 5);
        sprite = SpriteSheet.player_up.getSprites()[0];
        fireRate = WizardProjectile.FIRE_RATE;

    }

    public Player getPlayer() {
        return this;
    }

    public void inputInventory(Item item) {
        for (int i = 0; i < 8; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                return;
            }
        }
    }

    public void dropItem(int index) {
        try {
        if (index >= 0 && index <= 7 && input.inventoryRendering && inventory[index] != null) {

        Item dropItem = new Item(inventory[index], getX(), getY());
            System.out.println(dropItem.getId());
        if (dropItem != null ) {
            level.add(dropItem);
            if (inventory[index] instanceof Weapon && currentWeapon != null)
                currentWeapon = null;

            inventory[index] = null;
            input.inventoryRendering = !input.inventoryRendering;

            Game.out.println("drop"+"/"+getX()+"/"+getY()+"/"+dropItem.getId());
            Game.out.flush();
            System.out.println("Item on map added: " + level.itemsOnMap.get(0).getId());
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void grabItem() {
        for (int i = 0; i < level.itemsOnMap.size(); i++) {
            int dX = Math.abs(getX() - level.itemsOnMap.get(i).getX());
            int dY = Math.abs(getY() - level.itemsOnMap.get(i).getY());
            double distance = Math.sqrt(dX * dX + dY * dY);
            if (distance <= Level.ITEM_GRAB_RADIUS) {
                try {

                Item grabItem = new Item(level.itemsOnMap.get(i).getSprite(), level.itemsOnMap.get(i).getId());
                    Game.out.println("grab"+"/"+getX()+"/"+getY()+"/"+name+"/"+grabItem.getId());
                    Game.out.flush();

                    //
                    String[] parseWeapon = grabItem.getId().split("_");
                    if (parseWeapon[0].equals("gun")) {
                        if (parseWeapon[1].equals("glock"))
                            inputInventory(Weapon.gun_glock);
                    }
                    //



                level.itemsOnMap.get(i).remove();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Rectangle bounds() {
        return new Rectangle(x - 8, y + 2, 16, 16);
    }

    public Rectangle bodyBounds() {
        return new Rectangle(x - 4, y - 12, 16, 32);
    }

    public void update() {

        if (Mouse.selectedInventory >= 0 && Mouse.selectedInventory <= 7 && inventory[Mouse.selectedInventory] instanceof Weapon) {
            currentWeapon = (Weapon)inventory[Mouse.selectedInventory];

            System.out.println("Has it selected?! "+ currentWeapon.getId());
        } else if (Mouse.selectedInventory >= 0 && Mouse.selectedInventory <= 7 && !(inventory[Mouse.selectedInventory] instanceof Weapon)) {
            currentWeapon = Weapon.fist;

        }

            if (walking) animSprite.update();
        else animSprite.setFrame(1); // [1] happens to be stationary
        if (fireRate > 0) fireRate--;
        int xa = 0, ya = 0;
        if (input.up) {
            animSprite = up;
            ya--;
            if (input.left) {
                xa--;
                animSprite = left;
            }
            else if (input.right) {
                xa++;
                animSprite = right;
            }



        } else
        if (input.down) {
            animSprite = down;
            ya++;
            if (input.left) {
                xa--;
                animSprite = left;
            }
            else if (input.right) {
                xa++;
                animSprite = right;
            }
        } else
        if (input.left) {
            xa--;
            animSprite = left;
            if (input.down) {
                ya++;
            }
            else if (input.up) {
                ya--;
            }


        } else
        if (input.right) {
            animSprite = right;
            xa++;
            if (input.down) {
                ya++;

            }
            else if (input.up) {
                ya--;
            }

        }



        if ((xa != 0 && ya == 0) || ((xa == 0 && ya != 0))) {
            move(xa, ya, bounds());

            walking = true;
        } else if (xa != 0 && ya != 0) {

//            tick++;
//            if (tick % 2 == 0) {
                move(xa, ya, bounds());
            walking = true;
//            }
        } else
             walking = false;



        clear();
        updateShooting();
    }

    private void clear() {
        for (int i = 0; i < level.projectiles.size(); i++) {
            Projectile p = level.projectiles.get(i);
            if (p.isRemoved()) level.projectiles.remove(i);
        }
    }

    private void updateShooting() {
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);

            shoot(x, y, dir);

            fireRate = WizardProjectile.FIRE_RATE;
        }
    }

    public void render(Screen screen) {
        sprite = animSprite.getSprite();

        if (currentWeapon.equals(Weapon.fist)) { // Renders gun and player
            screen.renderMob(x - 16, y - 16, sprite);
        } else {
            if (animSprite == up) {
                screen.renderItem(x, y, currentWeapon.getSprite(), true);
                screen.renderMob(x - 16, y - 16, sprite);
            } else if (animSprite == right) {
                screen.renderMob(x - 16, y - 16, sprite);
                screen.renderItem(x - 4, y, currentWeapon.getSprite(), true);
            } else if (animSprite == down) {
                screen.renderMob(x - 16, y - 16, sprite);
                screen.renderItem(x - 20, y, currentWeapon.getOppositeSprite(), true);
            } else if (animSprite == left) {
                screen.renderItem(x - 16, y, currentWeapon.getOppositeSprite(), true);
                screen.renderMob(x - 16, y - 16, sprite);
            }
        }


        if (input.inventoryRendering) { // Makes selected inventory blocks beige while unselected grey
            for (int i = 0; i < 8; i++) {
                if (i != Mouse.selectedInventory)
                screen.renderSprite(16 * i + (screen.width - 16 * 8), screen.height - 16, Sprite.inventory_item_index, false);
                else {
                screen.renderSprite(16 * i + (screen.width - 16 * 8), screen.height - 16, Sprite.inventory_item_selected, false);

                }
        }

            if (input.inventoryRendering) { // Deals with rendering sprites into inventory
                for (int i = 0; i < 8; i++) {
                    Item inventoryItem = level.getClientPlayer().inventory[i];
                    if (inventoryItem != null) {
                        if (inventoryItem.getId().equals("gun_glock_right"))
                            screen.renderItem(16 * i + (screen.width - 16 * 8), screen.height - 16, Sprite.gun_glock_right, false);


                    }
                }
            }
        }



        screen.renderItem(x - nameSprite.getWidth() / 2, y - 24, nameSprite, true);  // -16 // -24 // Renders name
    }
}