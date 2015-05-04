package com.guba.game.level.tile;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class VoidTile extends Tile {

    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

}
