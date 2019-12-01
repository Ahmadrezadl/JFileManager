import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel {
    private Logic logic;
    public TopPanel(Logic logic) {
        super();
        this.logic = logic;
        this.setLayout(new BorderLayout());
        MenusPanel menusPanel = new MenusPanel(logic);
        logic.setMenusPanel(menusPanel);
        this.add(menusPanel,BorderLayout.NORTH);
        TopBar topBar = new TopBar(logic);
        this.add(topBar,BorderLayout.SOUTH);
    }

}
