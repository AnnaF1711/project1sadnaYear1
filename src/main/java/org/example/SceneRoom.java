package org.example;

import javax.swing.*;
import java.awt.*;

//scene 1 סיימנו 05/05
public class SceneRoom extends JPanel implements DialogListener {

    private JTextArea jTextArea;
    private MainFrame mainFrame;
    private Image backgroundImage;
    private Menu menu;
    private Diva diva;
    private int dialogStep = 0;
    private SceneListener sceneListener;


    public void setSceneListener(SceneListener listener) {
        this.sceneListener = listener;
    }

    public SceneRoom(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(null);

        this.backgroundImage = new ImageIcon(getClass().getResource("/divaRoom.png")).getImage();

        this.jTextArea = new JTextArea();
        this.jTextArea.setEditable(false);
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setWrapStyleWord(true);
        this.jTextArea.setBackground(new Color(115, 41, 23));
        this.jTextArea.setBounds(0, MainFrame.WINDOW_HEIGHT*4/5, MainFrame.WINDOW_WIDTH*3/4, MainFrame.WINDOW_HEIGHT);
        this.jTextArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
        this.jTextArea.setOpaque(true);
        this.jTextArea.setForeground(Color.WHITE);
        this.add(this.jTextArea);

        this.jTextArea.setText("Diva: Hi! I'm Diva and I'm stuck with you, you have to help me, and I'm not taking no for an answer.\nWill you help?");

        this.menu = new Menu(MainFrame.WINDOW_WIDTH * 3 / 4, 0, MainFrame.WINDOW_WIDTH / 4, MainFrame.WINDOW_HEIGHT);
        this.menu.setDialogListener(this);
        this.add(this.menu);

        this.diva = new Diva();
        this.diva.setBounds(Diva.DIVA_X, Diva.DIVA_Y, Diva.DIVA_WIDTH, Diva.DIVA_HEIGHT);
        this.diva.setToDefault();
        this.add(this.diva);


        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void onOptionSelected(String text) {//יורש מממשק אז לא שמים במתודה של setVisible
        if (this.dialogStep == 0) {
            if (text.equals("button1")) {
                this.jTextArea.setText("Diva: Great! I have an invitation to this phenomenal fashion show. \nWill you help me shoplift?");
                this.revalidate();
                this.repaint();

            } else {
                this.jTextArea.setText("Diva: Yes, you do! I just asked out of politeness. I have an invitation to this phenomenal fashion show.\nWill you help me shoplift?");
                this.revalidate();
                this.repaint();
            }
            this.dialogStep++;
        } else if (this.dialogStep == 1) {
            if (text.equals("button1")) {
                this.jTextArea.setText("Diva: Awesome, let's go!");
                this.revalidate();
                this.repaint();
            } else {
                this.jTextArea.setText("Diva: B*tch, don't be a wuss or you're gonna regret it.\nWe're going!");
                this.revalidate();
                this.repaint();
            }
            this.dialogStep++;
            this.diva.setToHappy();
            this.add(this.diva);
            this.revalidate();
            this.repaint();
        }
        if (this.dialogStep == 2) { // חייב איף
            startDivaExitAnimation();
        }
    }

    private void startDivaExitAnimation() {
        this.remove(menu);
        this.jTextArea.setBounds(0, MainFrame.WINDOW_HEIGHT * 4 / 5, MainFrame.WINDOW_WIDTH,
                MainFrame.WINDOW_HEIGHT / 5);
        this.revalidate();
        this.repaint();
        // ההשהייה והאנימציה צריכות להיות שתיהן בטיימר כדי שירוצו בזמנים הנכונים
        Timer exitTimer = new Timer(15, null);
        exitTimer.addActionListener(e -> {
            int x = this.diva.getX();
            if (x > -Diva.DIVA_WIDTH) { // עד שלא יצאה לגמרי
                this.diva.setLocation(x - 5, this.diva.getY());
                this.revalidate();
                this.repaint();
            } else {
                // אחרי שדייוה יצאה מהמסך (בסוף הטיימר):
                exitTimer.stop();
                this.jTextArea.setText("");
                this.backgroundImage = null; // מחיקת הרקע כדי שנוכל להפוך לשחור
                setBackground(Color.BLACK);
                this.jTextArea.setBackground(Color.BLACK);
                this.revalidate();
                this.repaint();
                new Timer(3000, ev -> {//השהייה אחרי המסך השחור לפני החלפת הסצנה
                    if (this.sceneListener != null) {
                        this.sceneListener.onSceneFinished("room");
                    }
                }).start();
            }
        });

        exitTimer.setInitialDelay(2500); // השהייה ל2.5 שניות לפני שטיימר האנימציה מתחיל, אחרי הלחיצה של הכפתור האחרונה
        exitTimer.start();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.requestFocusInWindow();
        }
    }
    }

