package GUI;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TopPanel extends JPanel {
    private Logic logic;
    public TopPanel(Logic logic) {
        super();
        this.logic = logic;
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        MenusPanel menusPanel = new MenusPanel(logic);
        logic.setMenusPanel(menusPanel);
        this.add(menusPanel,BorderLayout.NORTH);
        TopBar topBar = null;
        try {
            topBar = new TopBar(logic);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(topBar,BorderLayout.SOUTH);
    }

}
