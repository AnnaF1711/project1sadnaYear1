package org.example;

import javax.swing.*;
import java.awt.*;

//scene 3 סיימנו 05/05
public class SceneJail extends JPanel {
    private JTextArea jTextArea;
    public static final int TEXT_WIDTH = MainFrame.WINDOW_WIDTH / 5;
    public static final int TEXT_HEIGHT = MainFrame.WINDOW_HEIGHT / 5;
    private MainFrame mainFrame;
    private Image backgroundImage;
    private SceneListener sceneListener;

    public SceneJail(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.backgroundImage = new ImageIcon(getClass().getResource("/divaJail.png")).getImage();
        this.jTextArea = new JTextArea();
        this.jTextArea.setEditable(false);
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setWrapStyleWord(true);
        this.jTextArea.setBackground(new Color(131, 131, 111));
        this.jTextArea.setBounds(0, MainFrame.WINDOW_HEIGHT * 4 / 5, MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT / 5);
        this.jTextArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
        this.jTextArea.setOpaque(true);
        this.jTextArea.setForeground(Color.BLACK);

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setSceneListener(SceneListener listener) {
        this.sceneListener = listener;
    }


    public void animation(){
        Timer text1Timer = new Timer (3000,null);//השהייה לפני הצגת הטקסט
        text1Timer.addActionListener(e1 -> {
            this.jTextArea.setText("Diva: It's nothing personal darling, \nyou're just not a diva like me \uD83D\uDE0C \nI guess I could visit you sometimes haha 😉");
            this.add(this.jTextArea);//הוספה כאן ולא בבנאי כי נרצה שיוצג הטקסט 3 שניות אחרי החלפת הסצנה
            this.revalidate();
            this.repaint();

        Timer text2Timer = new Timer (7000,null);//השהייה לפני הטקסט הקודם לפני החשכה
        text2Timer.addActionListener(e2 -> {
            this.jTextArea.setFont(new Font("Monospaced", Font.ITALIC, 22));
            this.jTextArea.setText("*Diva betrayed you and you cry yourself to sleep...*");
            this.backgroundImage = null;
            this.setBackground(Color.BLACK);

            new Timer(6000, ev -> {//השהייה קצרה לפני מעבר לסצנה הבאה (סיום)
                if (this.sceneListener != null) {
                    this.sceneListener.onSceneFinished("jail");
                }
            }).start();
            this.revalidate();
            this.repaint();
        });
        text2Timer.setRepeats(false);
        text2Timer.start();
        });
        text1Timer.setRepeats(false);
        text1Timer.start();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.requestFocusInWindow();
            this.animation();//ממומש כאן ולא בבנאי כדי לשמור על סדר הריצה בסצנות
        }
    }
}
