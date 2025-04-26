//26/04 - עשינו כפתורים של הדיאלוגים

package org.example;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    private static final String[] BUTTON1_TEXT={"I guess...","Hell yeah!"};
    private static final String[] BUTTON2_TEXT={"Ugh do I have to?","Isn't this illegal?"};
    private String currentText1;
    private String currentText2;
    public Menu(int x, int y,int width, int height) {
        this.setBounds(x,y,width,height);
        this.setBackground(Color.white);
        this.currentText1=BUTTON1_TEXT[0];
        this.currentText2=BUTTON2_TEXT[0];
        GridLayout layout = new GridLayout(2,1);
        this.setLayout(layout);
        JButton button1=new JButton("button1");
        button1.setText(BUTTON1_TEXT[0]);
        this.add(button1);
        JButton button2=new JButton("button2");
        button2.setText(BUTTON2_TEXT[0]);
        this.add(button2);

        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                changeButton1Text(button1);
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                changeButton2Text(button2);
            }
        });
        }

    public void changeButton1Text(JButton button) {
        if (this.currentText1.equals(BUTTON1_TEXT[0])) {
            this.currentText1 = BUTTON1_TEXT[1];
            button.setText(this.currentText1);
        }
    }
    public void changeButton2Text(JButton button) {
        if (this.currentText2.equals(BUTTON2_TEXT[0])) {
            this.currentText2 = BUTTON2_TEXT[1];
            button.setText(currentText2);
        }
    }
    }
