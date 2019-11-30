import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private World world;
    public MainFrame(World world){
        super("JFileManager");
        this.world = world;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        TopPanel topPanel = new TopPanel(world);
        world.setTopPanel(topPanel);
        this.add(topPanel,BorderLayout.NORTH);
        LeftPanel leftPanel = new LeftPanel(world);
        this.add(leftPanel,BorderLayout.WEST);
        this.setVisible(true);
    }
}
