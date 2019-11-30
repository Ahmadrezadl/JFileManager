import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    private World world;
    public TopBar(World world) {
        super();
        this.world = world;
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        JButton back = new JButton("Back");
        JButton forward = new JButton("forward");
        JButton up = new JButton("up");
        JTextArea link = new JTextArea(1,45);
        JButton refresh = new JButton("Refresh");
        JTextArea search = new JTextArea(1,10);
        this.add(back); this.add(forward); this.add(up);
        this.add(link); this.add(refresh); this.add(search);


    }
}
