import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    private World world;
    public TopBar(World world) {
        super();
        this.setBackground(Color.WHITE);
        this.world = world;
        this.setLayout(new BorderLayout());
        JPanel left = new JPanel();
        left.setLayout(new FlowLayout());
        JPanel right = new JPanel();
        right.setLayout(new FlowLayout());
        JButton back = new JButton("Back");
        JButton forward = new JButton("forward");
        JButton up = new JButton("up");
        JTextArea link = new JTextArea();
        Font font = new Font("DL" ,Font.PLAIN, 18);
        link.setPreferredSize(new Dimension(400,25));
        link.setFont(font);
        JButton refresh = new JButton("Refresh");
        JTextArea search = new JTextArea();
        search.setFont(font);
        search.setPreferredSize(new Dimension(200,25));
        left.add(back); left.add(forward); left.add(up);
        this.add(link,BorderLayout.CENTER); right.add(refresh); right.add(search);
        this.add(left,BorderLayout.WEST);
        this.add(right,BorderLayout.EAST);

    }
}
