package com.guba.game.entity.spawner;

import com.guba.game.entity.Entity;
import com.guba.game.entity.particle.Particle;
import com.guba.game.level.Level;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-17
 * Time: 10:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Spawner extends Entity {

    public enum Type {
        MOB, PARTICLE;
    }

    private Type type;

    public Spawner(int x, int y, Type type, int amount, Level level) {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;

    }
}
