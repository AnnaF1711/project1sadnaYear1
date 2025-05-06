//29/04 סיימנו עם המחלקה הזאת
package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.event.KeyEvent;
public class StartScreen extends JPanel implements KeyListener {
    private Image backgroundImage;
    private MainFrame mainFrame;
    private SoundPlayer soundPlayer;

    public StartScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.backgroundImage = new ImageIcon(getClass().getResource("/startScreenDiva.png")).getImage();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                mainFrame.changeScreen("scene");
            }
        });
        this.soundPlayer = new SoundPlayer();
        this.soundPlayer.playStoreMusic();
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0,getWidth(),getHeight(), this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if(visible) {
            this.requestFocusInWindow();
        }
    }
}
