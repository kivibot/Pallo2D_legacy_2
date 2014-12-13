package fi.kivibot.pallo2d.rendering;

import java.util.ArrayList;

/**
 *
 * @author Nicklas
 */
public class TileMap extends Geometry {

    private TileSet tileSet;
    private int width, height;
    private int[][] map;

    public TileMap(TileSet tileSet, int width, int height) {
        this.tileSet = tileSet;
        this.width = width;
        this.height = height;
        this.map = new int[width][height];
        ArrayList<Texture> tl = new ArrayList<>();
        tl.add(tileSet.getTexture());
        setTextures(tl);
    }

    public void setTile(int x, int y, int d) {
        map[x][y] = d;
    }
    
    public int getTile(int x, int y) {
        return map[x][y];
    }

    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
        ArrayList<Texture> tl = new ArrayList<>();
        tl.add(tileSet.getTexture());
        setTextures(tl);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
