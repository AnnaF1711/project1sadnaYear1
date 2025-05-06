//26/04 - עשינו כפתורים של הדיאלוגים

package org.example;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    private DialogListener dialogListener;
    private static final String[] BUTTON1_TEXT = { "<html><center>I guess...</center></html>", "<html><center>Hell yeah!</center></html>" };
    private static final String[] BUTTON2_TEXT = { "<html><center>Ughhh, do I have to do this?</center></html>",
            "<html><center>Isn't this illegal?</center></html>"}; // לא חורג ובאמצעלא חורג ובאמצע
    private String currentText1;
    private String currentText2;


    public Menu(int x, int y,int width, int height) {
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(197, 84, 75));

        GridLayout gridLayout = new GridLayout(2, 1, 0, 30);
        this.setLayout(gridLayout);
        this.currentText1 = BUTTON1_TEXT[0];
        this.currentText2 = BUTTON2_TEXT[0];

        JButton button1 = new JButton("button1");
        button1.setBackground(new Color(242, 235, 232));
        button1.setOpaque(true); // ביטול שקיפות
        button1.setFont(new Font("Monospaced", Font.PLAIN, 15));
        button1.setText(this.currentText1);
        button1.setForeground(new Color(95, 18, 3)); // צבע הטקסט
        button1.setBorder(BorderFactory.createLineBorder(new Color(108, 49, 34), 3)); // כשמוסיפים את זה הרקע עובד

        this.add(button1);
        button1.setFocusable(true);
        button1.requestFocusInWindow();

        JButton button2 = new JButton("button2");
        button2.setBackground(new Color(242, 235, 232));
        button2.setOpaque(true);
        button2.setFont(new Font("Monospaced", Font.PLAIN, 15));
        button2.setText(this.currentText2);
        button2.setForeground(new Color(95, 18, 3));
        button2.setBorder(BorderFactory.createLineBorder(new Color(108, 49, 34), 3)); // כשמוסיפים את זה הרקע עובד


        this.add(button2);
        button2.setFocusable(true);
        button2.requestFocusInWindow();

        this.setBorder(BorderFactory.createEmptyBorder(38, 24, 65, 24)); // למעלה ולמטה בהתאם לטקסט בכפתורים


        button1.addMouseListener(new MouseAdapter() { // הטקסט ישתנה כל לחיצת עכבר
            public void mouseClicked(MouseEvent event) {
                if (dialogListener != null) {
                    dialogListener.onOptionSelected("button1");
                }
                changeButton1Text(button1);
                changeButton2Text(button2);
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (dialogListener != null) {
                    dialogListener.onOptionSelected("button2");
                }
                changeButton1Text(button1);
                changeButton2Text(button2);
            }
        });
    }

    public void changeButton1Text(JButton button) {
        if (this.currentText1.equals(BUTTON1_TEXT[0])) {
            this.currentText1 = BUTTON1_TEXT[1];
            button.setText(this.currentText1);
            this.repaint();
            this.revalidate();
        }
    }

    public void changeButton2Text(JButton button) {
        if (this.currentText2.equals(BUTTON2_TEXT[0])) {
            this.currentText2 = BUTTON2_TEXT[1];
            button.setText(this.currentText2);
            this.repaint();
            this.revalidate();
        }
    }

    public void setDialogListener(DialogListener listener) {
        this.dialogListener = listener;
    }
    }
