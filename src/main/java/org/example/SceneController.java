package org.example;

import javax.swing.*;
import java.awt.*;

public class SceneController extends JPanel implements SceneListener {
    private MainFrame mainFrame;
    private CardLayout cardLayout;
    private SceneRoom sceneRoom;
    private SceneJail sceneJail;
    private SceneStore sceneStore;
    private EndScreen endScreen;

    public SceneController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.cardLayout = new CardLayout();
        setLayout(this.cardLayout);

        this.sceneRoom = new SceneRoom(mainFrame);
        this.sceneRoom.setSceneListener(this);
        this.sceneStore = new SceneStore(mainFrame);
        this.sceneStore.setSceneListener(this);
        this.sceneJail = new SceneJail(mainFrame);
        this.sceneJail.setSceneListener(this);
        this.endScreen = new EndScreen(mainFrame);

        this.add(this.sceneRoom,"room");// מוסיף לפי הסדר הזה (קארד לייאאוט) יתחיל מפה
        this.add(this.sceneStore,"store");
        this.add(this.sceneJail,"jail");
        this.add(this.endScreen,"end");
    }

    public void showScene(String name) {
        this.cardLayout.show(this, name);
    }
    public void removeScene(String name) { // ממומש באון סין פינישד
        if (name.equals("room") && this.sceneRoom != null) {
            this.remove(this.sceneRoom);
            this.sceneRoom = null;
        } else if (name.equals("store") && this.sceneStore != null) {
            this.remove(this.sceneStore);
            this.sceneStore = null;
        } else if (name.equals("jail") && this.sceneJail != null) {
            this.remove(this.sceneJail);
            this.sceneJail = null;
        }
        this.revalidate();
        this.repaint();
    }
    public void onSceneFinished(String sceneName) {// ממומש בטיימר האחרון בכל סצנה
        this.removeScene(sceneName);
        if (sceneName.equals("room")) {
            this.showScene("store");
        } else if (sceneName.equals("store")) {
            this.showScene("jail");
        }else if(sceneName.equals("jail")){
           this.showScene("end");
        }
    }

    }

