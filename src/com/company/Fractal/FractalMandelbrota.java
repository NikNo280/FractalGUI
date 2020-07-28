package com.company.Fractal;

import java.awt.*;


public class FractalMandelbrota implements IFractalAlgoritm {

    //Размеры окна
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 1024;

    // для центровки внутри фрейма
    private static final double DX = -640;
    private static final double DY = -517;

    //Функция прорисовки фрактала
    // Чем больше значение тем больше точки похожи на плоскость
    private double SX;         //0.008
    private double SY;         //0.008

    // число итераций, чем больше число - тем больше точек
    private int COUNT_ITER;          //500
    private int BAIL_OUT;         //16
    private int STEP_X;            //1
    private int STEP_Y;


    public FractalMandelbrota()
    {
        this.SX = 0.01;         //0.008
        this.SY = 0.01;         //0.008
        this.COUNT_ITER = 1500;          //500
        this.BAIL_OUT = 1111;         //16
        this.STEP_X = 10;            //1
        this.STEP_Y = 10;
    }

    public FractalMandelbrota(int COUNT_ITER, double SX, double SY, int BAIL_OUT, int STEP_X, int STEP_Y)
    {
        this.SX = SX;
        this.SY = SY;
        this.COUNT_ITER = COUNT_ITER;
        this.BAIL_OUT = BAIL_OUT;
        this.STEP_X = STEP_X;
        this.STEP_Y = STEP_Y;
    }

    @Override
    public void fractalPaint(Graphics graphics)
    {
        drawMandelbrot(graphics);
    }


    private void drawMandelbrot(Graphics g) {

        g.setColor(Color.BLACK); // устанавливаем цвет черный
        g.fillRect(0, 0, WIDTH, HEIGHT); // рисуем этим цветом весь фрейм

        for (int i = 0; i < WIDTH; i += STEP_X) {
            for (int j = 0; j < HEIGHT; j += STEP_Y) {
                double c = SX * (i + DX); // центрируем по X
                double d = SY * (j + DY); // центрируем по Y
                double x = c; // ось х
                double y = d; // ось y
                double t;
                int k = 0;
                g.setColor(new Color((float) Math.random(),
                        (float) Math.random(), (float) Math.random()));
                while (x * x + y * y < BAIL_OUT && k < COUNT_ITER) { // алгоритм
                    t = x * x - y * y + c;
                    y = 2 * x * y + d;
                    x = t;
                    g.drawOval((int) (4*x / SX - DX), (int) (4 * y / SY - DY), 1, 1);
                    ++k;
                }
            }
        }
    }
}
