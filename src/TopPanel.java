import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private World world;
    public TopPanel(World world) {
        super();
        this.world = world;
        this.setLayout(new BorderLayout());
        MenusPanel menusPanel = new MenusPanel(world);
        world.setMenusPanel(menusPanel);
        this.add(menusPanel,BorderLayout.NORTH);
        TopBar topBar = new TopBar(world);
        this.add(topBar,BorderLayout.SOUTH);
    }


}
