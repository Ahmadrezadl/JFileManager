package GUI;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;

public class BottomBar extends JPanel {
    private Logic logic;
    private JLabel fileInfo;

    public BottomBar(Logic logic) {
        super();
        this.logic = logic;
        fileInfo = new JLabel("No file selected!");
        logic.setFileInfo(fileInfo);
        this.add(fileInfo);
    }
}
