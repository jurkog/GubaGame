package com.guba.game.level.tile.spawn_level;

import com.guba.game.graphics.Screen;
import com.guba.game.graphics.Sprite;
import com.guba.game.level.tile.Tile;

/**
 * Created by jurkoguba on 2013-12-31.
 */
public class CollisionTile extends Tile {
    public CollisionTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this);
    }

    public boolean solid() {
        return true;
    }

}