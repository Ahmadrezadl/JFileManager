package GUI;

import Logic.Logic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BottomBar extends JPanel {
    private Logic logic;
    private JLabel fileInfo;
    BufferedImage icon1 , icon2;
    public JProgressBar loadingBar;
    public BottomBar(Logic logic) {
        super();
        this.logic = logic;
        try {
            icon2 = ImageIO.read(getClass().getResource("..\\Sprites\\" + "view1.png"));
            icon1 = ImageIO.read(getClass().getResource("..\\Sprites\\" + "view2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        fileInfo = new JLabel("No file selected!");
        logic.setFileInfo(fileInfo);
        this.add(fileInfo,BorderLayout.WEST);
        loadingBar = new JProgressBar();
        logic.setBottomBar(this);
        JButton changeView = new JButton(new ImageIcon(icon1));
        changeView.setBorderPainted(false);
        changeView.setBorder(null);
        changeView.setForeground(null);
        changeView.setBackground(null);
        changeView.setFocusPainted(false);
        changeView.setContentAreaFilled(false);
        changeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.setLarge(!logic.isLarge());
                if(logic.isLarge())
                    changeView.setIcon(new ImageIcon(icon1));
                else
                    changeView.setIcon(new ImageIcon(icon2));
                logic.clickRefresh();

            }
        });
        this.add(changeView,BorderLayout.EAST);

    }

    public JLabel getFileInfo() {
        return fileInfo;
    }

}
