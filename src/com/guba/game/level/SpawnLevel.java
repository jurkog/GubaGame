package com.guba.game.level;

import com.guba.game.entity.mob.Chaser;
import com.guba.game.entity.mob.Dummy;
import com.guba.game.level.tile.GrassTile;
import com.guba.game.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-15
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class SpawnLevel extends Level{

    public SpawnLevel(String path) {
        super(path);

    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception! Could not load level file!");
        }

    }

    protected void generateLevel() {


    }

}
