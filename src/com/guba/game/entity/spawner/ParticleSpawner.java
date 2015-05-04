package com.guba.game.entity.spawner;

import com.guba.game.entity.particle.Particle;
import com.guba.game.level.Level;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-17
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParticleSpawner extends Spawner {

    private int life;

    public ParticleSpawner(int x, int y, int life, int amount, Level level) {
        super(x, y, Type.PARTICLE, amount, level);
        this.life = life;
        for (int i = 0; i < amount; i++) {
                level.add(new Particle(x, y, life));
        }
    }
}
