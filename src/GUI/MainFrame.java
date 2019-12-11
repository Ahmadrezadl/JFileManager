package GUI;

import Logic.Logic;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    private Logic logic;
    public JScrollPane scrollPane;
    public MainFrame(Logic logic){
        super("JFileManager");
        this.logic = logic;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800,800));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        logic.setMainFrame(this);
        this.setLayout(new BorderLayout());
        TopPanel topPanel = new TopPanel(logic);
        logic.setTopPanel(topPanel);
        this.add(topPanel,BorderLayout.NORTH);
        LeftPanel leftPanel = new LeftPanel(logic);
        this.add(leftPanel,BorderLayout.WEST);
        BottomBar bottomBar = new BottomBar(logic);
        this.add(bottomBar,BorderLayout.SOUTH);
        scrollPane = new JScrollPane(new MiddlePanel(logic,new File(System.getProperty("user.home")),""));
        scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);

        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(74 , 139 , 62);
            }
        });
        this.add(scrollPane,BorderLayout.CENTER);
        this.setVisible(true);

    }
}
