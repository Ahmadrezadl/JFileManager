import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private World world;
    public MainFrame(World world){
        super("JFileManager");
        this.world = world;
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());

    }
}
