package com.example.dungeoncrawler.model;

public class Floor {

    Tile[][] map;
    public NPC[] enemies;

    public Floor(int depth, int size) {
        createMap(size, size - 1 + depth);
        enemies = new NPC[depth];

        for (int i = 0; i < depth; i++) {
            enemies[i] = new NPC(Race.values()[(int) Math.random() * 7], Caste.values()[(int) Math.random() * 6], true, depth);
        }
    }

    public void createMap(int sizeX, int sizeY) {
        this.map = new Tile[sizeX][sizeY];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Tile(i, j);
            }
        }
    }
}
