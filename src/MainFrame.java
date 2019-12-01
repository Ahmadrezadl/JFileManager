import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    private Logic logic;
    public MainFrame(Logic logic){
        super("JFileManager");
        this.logic = logic;
        logic.setMainFrame(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        TopPanel topPanel = new TopPanel(logic);
        logic.setTopPanel(topPanel);
        this.add(topPanel,BorderLayout.NORTH);
        LeftPanel leftPanel = new LeftPanel(logic);
        this.add(leftPanel,BorderLayout.WEST);
        MiddlePanel middlePanel = new MiddlePanel(logic);
        this.add(middlePanel,BorderLayout.CENTER);
        this.setVisible(true);

    }
}
