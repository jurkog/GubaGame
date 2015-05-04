package com.guba.game.level.tile;

import com.guba.game.graphics.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-16
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class HedgeTile extends Tile {

    public HedgeTile(Sprite sprite) {
        super(sprite);
    }

    public boolean solid() {
        return true;
    }

    public boolean breakable() {
        return true;
    }
}
