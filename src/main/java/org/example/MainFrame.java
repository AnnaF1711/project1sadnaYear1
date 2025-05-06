package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainFrame extends JFrame {


    public static final int WINDOW_WIDTH=800;
    public static final int WINDOW_HEIGHT=800;

    private StartScreen startScreen;
    private SceneController scene;
    private EndScreen endScreen;

    public MainFrame() {
        setTitle("Diva Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        startScreen=new StartScreen(this);
        scene=new SceneController(this);
        endScreen= new EndScreen(this);

        startScreen.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        endScreen.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);

        add(startScreen);
        add(scene);
        add(endScreen);

        changeScreen("start");
        setVisible(true);

    }

    public void changeScreen (String screenName) {
        startScreen.setVisible(screenName.equals("start"));//אם שונה השם, תעבור לסצנה הבאה (ממומש במתודות אנימציה של הסצנות)
        scene.setVisible(screenName.equals("scene"));
        endScreen.setVisible(screenName.equals("end"));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new MainFrame();

    }
}