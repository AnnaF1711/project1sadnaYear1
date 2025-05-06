package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EndScreen extends JPanel {
    private MainFrame mainFrame;
    private Image backgroundImage;

    public EndScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.backgroundImage = new ImageIcon(getClass().getResource("/endScreen.png")).getImage();
        this.setFocusable(true);
        this.requestFocusInWindow();
}
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0,getWidth(),getHeight(), this);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(visible) {
            this.requestFocusInWindow();
        }
    }
}
