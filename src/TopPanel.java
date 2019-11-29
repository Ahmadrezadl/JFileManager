import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private World world;

    public TopPanel(World world) {
        super();
        this.world = world;
        this.setLayout(new BorderLayout());
        MenusPanel menusPanel = new MenusPanel(world);
        this.add(menusPanel,BorderLayout.NORTH);

    }


}
