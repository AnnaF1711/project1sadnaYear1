package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final int WINDOW_WIDTH=900;
    public static final int WINDOW_HEIGHT=700;
    public static void main(String[] args) {
        JFrame window = new JFrame("Diva Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        Menu menu = new Menu(WINDOW_WIDTH/(4*3),0,);

    }
}