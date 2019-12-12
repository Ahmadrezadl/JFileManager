package GUI;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;

public class BottomBar extends JPanel implements Runnable{
    private Logic logic;
    private JLabel fileInfo;
    public JProgressBar loadingBar;
    public BottomBar(Logic logic) {
        super();
        this.logic = logic;
        this.setLayout(new BorderLayout());
        fileInfo = new JLabel("No file selected!");
        logic.setFileInfo(fileInfo);
        this.add(fileInfo,BorderLayout.WEST);
        loadingBar = new JProgressBar();
        this.add(loadingBar,BorderLayout.EAST);
        logic.setBottomBar(this);
        Thread t = new Thread(this);
        t.start();

    }

    public JLabel getFileInfo() {
        return fileInfo;
    }
    @Override
    public void run() {
        while (true)
        {
            if(loadingBar!=null)
            {
                loadingBar.setValue(logic.loaded.intValue());
                setVisible(true);
                loadingBar.repaint();

            }
        }
    }
}
