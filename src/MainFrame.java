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
        this.add(topPanel,BorderLayout.NORTH);
        this.setVisible(true);
    }
}
