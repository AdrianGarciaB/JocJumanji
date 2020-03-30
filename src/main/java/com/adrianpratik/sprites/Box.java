package com.adrianpratik.sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Box {
    public static void drawLimits(GraphicsContext gc, double x, double y, double width, double height, int size){
        gc.setFill(Color.RED);
        gc.fillRect(x, y, width, size);
        gc.fillRect(x, y+height, width, size);
        gc.fillRect(x, y, size, height);
        gc.fillRect(width+x, y, size, height);

    }
}
