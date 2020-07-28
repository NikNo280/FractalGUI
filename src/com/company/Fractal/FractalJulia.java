package com.company.Fractal;

import java.awt.image.BufferedImage;
import java.awt.*;


public class FractalJulia implements IFractalAlgoritm {
    private int MAX_ITERATIONS;             //300
    private double ZOOM;                    //1
    private double CX;                      //-0.7
    private double CY;                      //0.27015
    private double MOVE_X;                  //0
    private double MOVE_Y;                  //0

    public FractalJulia()
    {
        this.MAX_ITERATIONS = 300;
        this.ZOOM = 1;
        this.CX = -0.7;
        this.CY = 0.27015;
        this.MOVE_X = 0;
        this.MOVE_Y = 0;
    }

    public FractalJulia(int MAX_ITERATIONS, double ZOOM, double CX, double CY, double MOVE_X, double MOVE_Y)
    {
        this.MAX_ITERATIONS = MAX_ITERATIONS;
        this.ZOOM = ZOOM;
        this.CX = CX;
        this.CY = CY;
        this.MOVE_X = MOVE_X;
        this.MOVE_Y = MOVE_Y;
    }

    @Override
    public void fractalPaint(Graphics graphics)
    {
        drawJuliaSet(graphics);
    }

    //Функция прорисовки фрактала

    void drawJuliaSet(Graphics g) {
        int w = 1280;
        int h = 1024;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                double zx = 1.5 * (x - w / 2) / (0.5 * ZOOM * w) + MOVE_X;
                double zy = (y - h / 2) / (0.5 * ZOOM * h) + MOVE_Y;
                float i = MAX_ITERATIONS;
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + CX;
                    zy = 2.0 * zx * zy + CY;
                    zx = tmp;
                    i--;
                }
                int c = Color.HSBtoRGB((MAX_ITERATIONS / i) % 1, 1, i > 0 ? 1 : 0);
                image.setRGB(x, y, c);
            }
        }
        g.drawImage(image, 0, 0, null);
    }
}
