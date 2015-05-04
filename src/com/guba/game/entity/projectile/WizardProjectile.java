package com.guba.game.entity.projectile;

import com.guba.game.entity.mob.OnlinePlayer;
import com.guba.game.entity.mob.Player;
import com.guba.game.entity.particle.Particle;
import com.guba.game.entity.spawner.ParticleSpawner;
import com.guba.game.entity.spawner.Spawner;
import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.level.Level;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-16
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class WizardProjectile extends Projectile{

    public static final int FIRE_RATE = 20; // Higher is slower

    public WizardProjectile(int x, int y, String shooter, double dir) {
        super(x, y, shooter, dir);
        range = 200;
        speed = 4;
        damage = 20;
        sprite = Sprite.projectile_wizard;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);

    }

    public void update() {

        Rectangle r1 = new Rectangle((int)x, (int)y, 7, 7);
        if (level.tileCollision((int)(x + nx), (int)(y + ny), 6, 5, 5)) {
            level.add(new ParticleSpawner((int)x, (int)y, 144, 7, level));
            remove();
        } else {
            Rectangle r3 = level.getClientPlayer().bodyBounds();
            for (OnlinePlayer player : Level.onlinePlayers) {
                Rectangle r2 = player.bodyBounds();
                if ((r1.intersects(r2) && !this.shooter.equals(player.getName()))  || (r1.intersects(r3) && this.shooter.equals(player.getName()))) {
                    level.add(new ParticleSpawner((int)x, (int)y, 144, 7, level));
                    remove();
                    System.out.println("Shooter: " + this.shooter + " Target: " + (r1.intersects(r2) ? player.getName() : level.getClientPlayer().getName()));
                }
            }
        }
        move();
    }



    public void move() {

        x += nx;
        y += ny;

        if (distance() > range) remove();
    }

    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x - 12, (int) y - 2, this);
    }
}
