package com.guba.game.level.tile.spawn_level;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.level.tile.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: jurkoguba
 * Date: 2013-12-15
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpawnGrassTile extends Tile {
    public SpawnGrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }
}
