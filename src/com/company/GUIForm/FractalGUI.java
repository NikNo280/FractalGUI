package com.company.GUIForm;

import com.company.Fractal.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FractalGUI extends JFrame{
    private JButton buttonPaint = new JButton("Нарисовать");
    private JButton buttonAnimationStartLevy = new JButton("Анимация (вкл)");
    private JButton buttonAnimationStopLevy = new JButton("(выкл)");
    private JButton buttonAnimationStartDragon = new JButton("Анимация (вкл)");
    private JButton buttonSaveArg = new JButton("Сохранить изменения");
    private JLabel countIterLabel = new JLabel("Прорисовка:");
    private JLabel CXYLabel = new JLabel("Параметры(X,Y)");
    private Color fractalColor = new Color(0,0,0);
    private JTextField countIteration = new JTextField("300");
    private JTextField TextFieldCX = new JTextField("-0.7");
    private JTextField TextFieldCY = new JTextField("0.27015");
    private JLabel countZoomLabel = new JLabel("Увеличение:");
    private JTextField countZoom = new JTextField("1");
    private JLabel XYLabel = new JLabel("Смещение(X,Y)");
    private JTextField TextFieldX = new JTextField("0");
    private JTextField TextFieldY = new JTextField("0");
    private IFractalAlgoritm strategy;
    Timer mTimerLevy = new Timer(225, new ButtonAnimatonListenerLevy());
    Timer mTimerDragon = new Timer(225, new ButtonAnimatonListenerDragon());
    String[] itemComboBoxColor = {
            "Черный",
            "Красный",
            "Оранжевый",
            "Желтый",
            "Зеленый",
            "Голубой",
            "Синий",
            "Фиолетовый"
    };
    JComboBox comboBoxColor = new JComboBox(itemComboBoxColor);
    String[] itemComboBoxAlgoritm = {
            "Кривая Леви",
            "Дракон Хартера-Хейтуэя",
            "Снежинка Коха",
            "Элипс внутри элипса",
            "Множество Жулиа",
            "Мандельбротовы облака"

    };
    JComboBox comboBoxAlgoritm = new JComboBox(itemComboBoxAlgoritm);

    public FractalGUI(){
        super("Fractal Paint");
        this.setBounds(650, 200, 1280, 1024);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        buttonAnimationStartLevy.setBounds(0, 60, 120, 30);
        buttonAnimationStopLevy.setBounds(120, 60, 90, 30);
        buttonAnimationStartDragon.setBounds(0, 60, 120, 30);
        countIterLabel.setBounds(0,30,105,30);
        countIteration.setBounds(0,60,105,30);
        buttonPaint.setBounds(0, 960, 1280, 30);
        comboBoxAlgoritm.setBounds(0, 0, 210, 30);
        comboBoxColor.setBounds(0, 30, 210, 30);
        buttonSaveArg.setBounds(0,150,210,30);
        CXYLabel.setBounds(105,30,105,30);
        TextFieldCX.setBounds(105,60,53,30);
        TextFieldCY.setBounds(158,60,52,30);
        countZoomLabel.setBounds(0,90,105,30);
        XYLabel.setBounds(105,90,105,30);
        countZoom.setBounds(0,120,105,30);
        TextFieldX.setBounds(105,120,53,30);
        TextFieldY.setBounds(158,120,53,30);
        add(countZoom);
        add(TextFieldX);
        add(TextFieldY);
        add(countZoomLabel);
        add(XYLabel);
        add(TextFieldCY);
        add(TextFieldCX);
        add(CXYLabel);
        add(buttonSaveArg);
        add(countIterLabel);
        add(countIteration);
        add(buttonPaint);
        add(buttonAnimationStartDragon);
        add(buttonAnimationStopLevy);
        add(buttonAnimationStartLevy);
        add(comboBoxAlgoritm);
        add(comboBoxColor);

        countZoomLabel.setVisible(false);
        XYLabel.setVisible(false);
        countIterLabel.setVisible(false);
        countIteration.setVisible(false);
        buttonSaveArg.setVisible(false);
        CXYLabel.setVisible(false);
        TextFieldCX.setVisible(false);
        TextFieldCY.setVisible(false);
        countZoom.setVisible(false);
        TextFieldX.setVisible(false);
        TextFieldY.setVisible(false);
        buttonAnimationStartDragon.setVisible(false);
        comboBoxColor.addActionListener(new editColorEventListener());
        comboBoxAlgoritm.setEditable(true);
        comboBoxAlgoritm.addActionListener(new editAlgoritmEventListener());
        buttonPaint.addActionListener(new ButtonEventListener());
        buttonSaveArg.addActionListener(new ButtonSaveArgListener());
        buttonAnimationStartLevy.addActionListener(new ButtonAnimatonListenerLevy());
        buttonAnimationStopLevy.addActionListener(new ButtonAnimatonStopListenerLevy());
        buttonAnimationStartDragon.addActionListener(new ButtonAnimatonListenerDragon());
        strategy = new FractalLevy();
    }


    class ButtonSaveArgListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            int MAX_ITERATION = Integer.valueOf(countIteration.getText());
            double ZOOM = Double.parseDouble(countZoom.getText());
            double CX = Double.parseDouble(TextFieldCX.getText());
            double CY = Double.parseDouble(TextFieldCY.getText());
            double MOVE_X = Double.parseDouble(TextFieldX.getText());
            double MOVE_Y = Double.parseDouble(TextFieldY.getText());
            String item = (String)comboBoxAlgoritm.getSelectedItem();
            if(item == "Множество Жулиа")
            {
                strategy = new FractalJulia(MAX_ITERATION, ZOOM, CX, CY, MOVE_X, MOVE_Y);
            }
            else if(item == "Мандельбротовы облака")
            {
                strategy = new FractalMandelbrota(MAX_ITERATION, CX, CY, (int)ZOOM, (int)MOVE_X, (int)MOVE_Y);
            }
        }
    }


    class ButtonAnimatonStopListenerLevy implements ActionListener{
        public void actionPerformed(ActionEvent event){
            mTimerLevy.stop();
            mTimerDragon.stop();
        }
    }

    class ButtonAnimatonListenerLevy implements ActionListener{
        int angle = 0;
        FractalLevy fractal = new FractalLevy();
        public void actionPerformed(ActionEvent event){
            Graphics graphics = getGraphics();
            graphics.setColor(fractalColor);
            graphics.clearRect(220,0, 1280, 954);
            if(angle == 181)
            {
                angle = 0;
            }
            fractal.levyPaint(500, 550, 800, 550, 17, angle, graphics);
            angle++;
            mTimerLevy.start();
        }
    }

    class ButtonAnimatonListenerDragon implements ActionListener{
        int angle = 0;
        FractalDragon fractal = new FractalDragon();
        public void actionPerformed(ActionEvent event){
            Graphics graphics = getGraphics();
            graphics.clearRect(220,0, 1280, 954);
            graphics.setColor(fractalColor);
            if(angle == 181)
            {

                angle = 0;
            }
            fractal.levyPaint(500, 550, 800, 550, 17, angle, 1, graphics);
            angle++;
            mTimerDragon.start();
        }

    }

    class ButtonEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            mTimerLevy.stop();
            mTimerDragon.stop();
            Graphics graphics = getGraphics();
            graphics.clearRect(220,0, 1280, 954);
            graphics.setColor(fractalColor);
            strategy.fractalPaint(graphics);
            componentRepaint();
        }
    }

    class editColorEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String item = (String)comboBoxColor.getSelectedItem();
            if(item == "Черный")
            {
                fractalColor = new Color(0,0,0);
            }
            else if(item == "Красный")
            {
                fractalColor = new Color(255,0,0);
            }
            else if(item == "Оранжевый")
            {
                fractalColor = new Color(255, 153, 34);
            }
            else if(item == "Желтый")
            {
                fractalColor = new Color(226, 200, 59);
            }
            else if(item == "Зеленый")
            {
                fractalColor = new Color(84, 167, 4);
            }
            else if(item == "Голубой")
            {
                fractalColor = new Color(82, 164, 167);
            }
            else if(item == "Синий")
            {
                fractalColor = new Color(0,0,255);
            }
            else if(item == "Фиолетовый")
            {
                fractalColor = new Color(135, 27, 167);
            }
        }
    }

    class editAlgoritmEventListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String item = (String)comboBoxAlgoritm.getSelectedItem();

            buttonAnimationStartLevy.setVisible(false);
            buttonAnimationStopLevy.setVisible(false);
            buttonAnimationStartDragon.setVisible(false);
            comboBoxColor.setVisible(true);
            countIteration.setVisible(false);
            countIterLabel.setVisible(false);
            buttonSaveArg.setVisible(false);
            CXYLabel.setVisible(false);
            TextFieldCX.setVisible(false);
            TextFieldCY.setVisible(false);
            countZoomLabel.setVisible(false);
            XYLabel.setVisible(false);
            countZoom.setVisible(false);
            TextFieldX.setVisible(false);
            TextFieldY.setVisible(false);
            mTimerLevy.stop();
            mTimerDragon.stop();
            repaint();
            if(item == "Кривая Леви")
            {
                strategy = new FractalLevy();
                buttonAnimationStartLevy.setVisible(true);
                buttonAnimationStopLevy.setVisible(true);
            }
            else if(item == "Дракон Хартера-Хейтуэя")
            {
                strategy = new FractalDragon();
                buttonAnimationStartDragon.setVisible(true);
                buttonAnimationStopLevy.setVisible(true);
            }
            else if(item == "Элипс внутри элипса")
            {
                strategy = new FractalCircleInCircle();
            }
            else if(item == "Снежинка Коха")
            {
                strategy = new FractalKoha();
            }
            else if(item == "Множество Жулиа")
            {
                countIterLabel.setText("Прорисовка:");
                CXYLabel.setText("Параметры(X,Y)");
                countIteration.setText("300");
                TextFieldCX.setText("-0.7");
                TextFieldCY.setText("0.27015");
                countZoomLabel.setText("Увеличение:");
                countZoom.setText("1");
                XYLabel.setText("Смещение(X,Y)");
                TextFieldX.setText("0");
                TextFieldY.setText("0");
                strategy = new FractalJulia();
                comboBoxColor.setVisible(false);
                countIteration.setVisible(true);
                countIterLabel.setVisible(true);
                buttonSaveArg.setVisible(true);
                CXYLabel.setVisible(true);
                TextFieldCX.setVisible(true);
                TextFieldCY.setVisible(true);
                countZoomLabel.setVisible(true);
                XYLabel.setVisible(true);
                countZoom.setVisible(true);
                TextFieldX.setVisible(true);
                TextFieldY.setVisible(true);
            }
            else if(item == "Мандельбротовы облака")
            {
                countIterLabel.setText("Прорисовка:");
                CXYLabel.setText("Параметры(X,Y)");
                countIteration.setText("1500");
                TextFieldCX.setText("0.01");
                TextFieldCY.setText("0.01");
                countZoomLabel.setText("Плотность:");
                countZoom.setText("1111");
                XYLabel.setText("Смещение(X,Y)");
                TextFieldX.setText("10");
                TextFieldY.setText("10");
                strategy = new FractalMandelbrota();
                comboBoxColor.setVisible(false);
                countIteration.setVisible(true);
                countIterLabel.setVisible(true);
                buttonSaveArg.setVisible(true);
                CXYLabel.setVisible(true);
                TextFieldCX.setVisible(true);
                TextFieldCY.setVisible(true);
                countZoomLabel.setVisible(true);
                XYLabel.setVisible(true);
                countZoom.setVisible(true);
                TextFieldX.setVisible(true);
                TextFieldY.setVisible(true);
            }
        }
    }
    void componentRepaint()
    {
        buttonAnimationStartLevy.repaint();
        buttonAnimationStopLevy.repaint();
        buttonAnimationStartDragon.repaint();
        countIteration.repaint();
        buttonPaint.repaint();
        comboBoxAlgoritm.repaint();
        comboBoxColor.repaint();
        countIterLabel.repaint();
        buttonSaveArg.repaint();
        CXYLabel.repaint();
        TextFieldCX.repaint();
        TextFieldCY.repaint();
        countZoomLabel.repaint();
        XYLabel.repaint();
        countZoom.repaint();
        TextFieldX.repaint();
        TextFieldY.repaint();
    }
}
