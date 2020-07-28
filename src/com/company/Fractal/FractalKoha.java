package com.company.Fractal;

import java.awt.*;

public class FractalKoha implements IFractalAlgoritm {
    @Override
    public void fractalPaint(Graphics graphics)
    {

        fractalKoha(500, 400, 800, 400, 650, 600, 7, graphics);
        fractalKoha(800, 400, 650, 600, 500, 400, 7, graphics);
        fractalKoha(650, 600, 500, 400, 800, 400, 7, graphics);
    }

    static int fractalKoha(int x1, int y1, int x2, int y2, int x3, int y3, int n, Graphics graphics)
    {
        //n -количество итераций
        if (n > 0)  //условие выхода из рекурсии
        {
            //средняя треть отрезка
            int x4 = (x2 + 2 * x1) /3;
            int y4 = (y2 + 2 * y1) /3;

            int x5 = (2 * x2 + x1) / 3;
            int y5 = (y1 + 2 * y2) / 3;
            //координаты вершины угла

            int xs = (x2 + x1) / 2;
            int ys = (y2 + y1) / 2;

            int xn = (4 * xs - x3) / 3;
            int yn = (4 * ys - y3) / 3;

            //рисуем его
            graphics.drawLine(x4, y4, xn, yn);
            graphics.drawLine(x5, y5, xn, yn);
            graphics.drawLine(x4, y4, x5, y5);

            //рекурсивно вызываем функцию нужное число раз
            fractalKoha(x4, y4, xn, yn, x5, y5, n - 1, graphics);
            fractalKoha(xn, yn, x5, y5, x4, y4, n - 1, graphics);
            fractalKoha(x1, y1, x4, y4, (2 * x1 + x3) / 3, (2 * y1 + y3) / 3, n - 1, graphics);
            fractalKoha(x5, y5, x2, y2, (2 * x2 + x3) / 3, (2 * y2 + y3) / 3, n - 1, graphics);

        }
        return n;
    }
}
