package org.example;

import javax.swing.*;
import java.awt.*;

public class Diva extends JPanel {
    public static final int DIVA_X=MainFrame.WINDOW_WIDTH/8;
    public static final int DIVA_Y=MainFrame.WINDOW_HEIGHT/3;
    public static final int DIVA_WIDTH = MainFrame.WINDOW_WIDTH/2;
    public static final int DIVA_HEIGHT=MainFrame.WINDOW_HEIGHT/2;
    private Image angryDiva;
    private Image defaultDiva;
    private Image happyDiva;
    private Image currentImage;

    public Diva() {
        this.defaultDiva=new ImageIcon(getClass().getResource("/defaultDiva.png")).getImage();
        this.happyDiva=new ImageIcon(getClass().getResource("/happyDiva.png")).getImage();
        this.angryDiva=new ImageIcon(getClass().getResource("/angryDiva.png")).getImage();
        this.currentImage=defaultDiva;
        this.setOpaque(false);
        // לא מגדירים גבולות בבנאי של אובייקט שרוצים שיוחל בפאנל אחר
    }
    public void setToDefault() {
        this.currentImage= this.defaultDiva;
        this.repaint();
    }

    public void setToHappy() {
        this.currentImage =this.happyDiva;
        this.repaint();
    }

    public void setToAngry() {
        this.currentImage = this.angryDiva;
        this.repaint();
    }

    public void setInitialPositionOutsideScreen() {
        setBounds(-DIVA_WIDTH, DIVA_Y, DIVA_WIDTH, DIVA_HEIGHT); // מיקום מחוץ למסך
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.currentImage, 0, 0, getWidth(), getHeight(), this);
    }
    //לא צריך setVisibility כי דיווה מתעדכנת במסך, רק המיקום שלה משתנה

}
