package com.company.Fractal;

import java.awt.*;

public class FractalDragon implements IFractalAlgoritm {
    @Override
    public void fractalPaint(Graphics graphics)
    {
        levyPaint(500, 550, 800, 550, 20, 45, 1, graphics);
    }

    public void levyPaint(double x1, double y1, double x2, double y2, int n, double angleK, int k, Graphics g) {
        if (n == 0) {
            g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
        }
        else {
            double angle=angleK*Math.PI/180; //переводим углы в радианы
            angle *= k;
            double xx=Math.cos(angle)*((x1-x2)*Math.cos(angle)-(y1-y2)*Math.sin(angle))+x2;
            double yy=Math.cos(angle)*((x1-x2)*Math.sin(angle)+(y1-y2)*Math.cos(angle))+y2;
            levyPaint(x1, y1, xx, yy, n - 1, angleK,-1, g);
            levyPaint(xx, yy, x2, y2, n - 1, angleK,1, g);
        }
    }
}
