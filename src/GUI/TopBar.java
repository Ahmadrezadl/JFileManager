package GUI;

import Logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class TopBar extends JPanel {
    private Logic logic;
    private JTextField link;
    private JTextField search;
    public TopBar(Logic logic) throws IOException {
        super();
        this.setBackground(Color.WHITE);
        this.logic = logic;
        this.setLayout(new BorderLayout());
        JPanel left = new JPanel();
        left.setLayout(new FlowLayout());
        JPanel right = new JPanel();
        right.setLayout(new FlowLayout());
        CustomButton back = new CustomButton("left1.png","left2.png",25);

        CustomButton forward = new CustomButton("right1.png","right2.png",25);

        CustomButton up = new CustomButton("up1.png","up2.png",32);
        link = new JTextField();
        logic.setLink(link);
        Font font = new Font("DL" ,Font.PLAIN, 18);
        link.setPreferredSize(new Dimension(400,25));
        link.setFont(font);
        CustomButton refresh = new CustomButton("refresh1.png" , "refresh2.png" , 32);
        search = new HintTextField("Search...");
        search.setFont(font);
        search.setToolTipText("Search something");
        search.setPreferredSize(new Dimension(200,25));
        logic.setSearch(search);
        left.setBackground(Color.white);
        left.add(back); left.add(forward); left.add(up);
        right.add(refresh); right.add(search);
        this.add(link,BorderLayout.CENTER);
        this.add(left,BorderLayout.WEST);
        this.add(right,BorderLayout.EAST);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.clickBack();
            }
        });
        forward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.clickForward();
            }
        });
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.clickUp();

            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.enterSearch(new File(link.getText()),search.getText());
            }
        });
        link.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.enterUrl(link.getText());
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                logic.clickRefresh();

            }
        });
    }

}
