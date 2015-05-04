package com.guba.game.level.tile;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-14
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

}
