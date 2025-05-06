package org.example;

import javax.swing.*;
import java.awt.*;
//scene 2 סיימנו 05/05
public class SceneStore extends JPanel {//אין יותר אינטרקציה, היוזר רק קורא

    private JTextArea jTextArea;
    private MainFrame mainFrame;
    private Image backgroundImage;
    private Diva diva;
    private SceneListener sceneListener;
    private SoundPlayer soundPlayer;

    public SceneStore(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);
        this.backgroundImage = new ImageIcon(getClass().getResource("/divaStore.png")).getImage();
        this.jTextArea = new JTextArea();
        this.jTextArea.setEditable(false);
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setWrapStyleWord(true);
        this.jTextArea.setBounds(0, MainFrame.WINDOW_HEIGHT * 4 / 5, MainFrame.WINDOW_WIDTH, MainFrame.WINDOW_HEIGHT / 5);
        this.jTextArea.setBackground(new Color(176, 78, 69));
        this.jTextArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
        this.jTextArea.setOpaque(true);
        this.jTextArea.setForeground(Color.WHITE);
        this.jTextArea.setText("");
        this.add(this.jTextArea);

        //אנחנו רוצים שבאנימציה התיבה לא תוצג רק במצבים שהמסך מחשיך

        this.diva = new Diva();

        this.soundPlayer = new SoundPlayer();

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void setSceneListener(SceneListener listener) {
        this.sceneListener = listener;
    }

    public void animation() {
        this.diva.setInitialPositionOutsideScreen();
        this.diva.setToDefault();
        this.add(this.diva);
        this.revalidate();
        this.repaint();
        Timer enterTimer = new Timer(15, null);// מיקום התחלתי מחוץ למסך, אנימציית כניסה
        enterTimer.addActionListener(e1 -> {
            int x = this.diva.getX();
            if (x < Diva.DIVA_X) {
                this.diva.setLocation(x + 5, this.diva.getY());
                this.revalidate();
                this.repaint();

            } else {
                //אחרי שהיא נעצרה:
                enterTimer.stop();
                this.jTextArea.setText("Diva: Quick! Put this dress in your bag before we get caught!\n" +
                        "Don't worry, they won't check \uD83D\uDE0F \n" +
                        "I've done this like a dozen times before.");
                this.revalidate();
                this.repaint();

                Timer text1Timer =new Timer(14000,null);//השהייה אחרי הצגת הטקסט הקודם
                text1Timer.addActionListener(e2 -> {
                    this.jTextArea.setFont(new Font("Monospaced", Font.ITALIC, 22));
                    this.jTextArea.setText("*She puts the dress in your bag but you get caught*");
                    this.revalidate();
                    this.repaint();

                    Timer text2Timer = new Timer(6500, null);//השהייה אחרי הצגת הטקסט הקודם
                    text2Timer.addActionListener(e3 -> {
                        this.soundPlayer.playSiren();//מפעיל ל6 שניות (אורך סאונד)
                        this.diva.setToAngry();
                        this.jTextArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
                        this.jTextArea.setText("Diva: SECURITY! There she is! \nThat b*tch tried to steal a 1,500$ dress!");
                        this.revalidate();
                        this.repaint();

                        Timer divaRunTimer = new Timer(12,null);//היא בורחת
                        divaRunTimer.addActionListener(e -> {
                            int t = this.diva.getX();
                            if (t > -Diva.DIVA_WIDTH) { // עד שלא יצאה לגמרי
                                this.diva.setLocation(t - 5, this.diva.getY());
                                this.revalidate();
                                this.repaint();

                            } else {
                                divaRunTimer.stop();
                            }
                        });
                            divaRunTimer.start();

                        Timer darkenScreen = new Timer(5000, null);//השהייה אחרי שדיווה ברחה לפני ההחשכה
                        darkenScreen.addActionListener(e4 -> {
                                    this.backgroundImage = null;
                                    this.setBackground(Color.BLACK);
                                    this.jTextArea.setText("");
                                    this.jTextArea.setBackground(Color.BLACK);
                                    this.revalidate();
                                    this.repaint();
                            new Timer(3000, e5 -> {//השהייה קצרה אחרי החשכה לפני מעבר לסצנה הבאה
                                if (this.sceneListener != null) {
                                    this.sceneListener.onSceneFinished("store");
                                }
                            }).start();
                                });
                            darkenScreen.setRepeats(false);//ללא חזרות
                           darkenScreen.start();
                    });
                    text2Timer.setRepeats(false);
                    text2Timer.start();
                });
                text1Timer.setRepeats(false);
                text1Timer.start();
            }
        });

        enterTimer.start();
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