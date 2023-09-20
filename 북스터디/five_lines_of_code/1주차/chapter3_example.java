class Game {
    void draw() {
        var canvas = getCanvas();
        var g = canvas.getContext("2d");

        g.clearRect(0, 0, canvas.width, canvas.height);

        // Draw map
        drawMap(g);

        // Draw player
        drawPlayer(g);
    }

    void drawMap(CanvasRenderingContext2D g) {
        for (let y = 0; y < map.length; y++) {
            for (let x = 0; x < map[y].length; x++) {
                if (map[y][x] == Tile.FLUX) {
                    g.fillStyle = "#ccffcc";
                } else if (map[y][x] == Tile.UNBREAKABLE) {
                    g.fillStyle = "#999999";
                } else if (map[y][x] == Tile.STONE || map[y][x] == Tile.FALLING_STONE) {
                    g.fillStyle = "#0000cc";
                } else if (map[y][x] == Tile.BOX || map[y][x] == Tile.FALLING_BOX) {
                    g.fillStyle = "#8b4513";
                } else if (map[y][x] == Tile.KEY1 || map[y][x] == Tile.LOCK1) {
                    g.fillStyle = "#ffcc00";
                } else if (map[y][x] == Tile.KEY2 || map[y][x] == Tile.LOCK2) {
                    g.fillStyle = "#00ccff";
                }

                if (map[y][x] != Tile.AIR && map[y][x] != Tile.PLAYER) {
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    void drawPlayer(CanvasRenderingContext2D g) {
        g.fillStyle = "#ff0000";
        g.fillRect(playerx * TILE_SIZE, playery * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
}