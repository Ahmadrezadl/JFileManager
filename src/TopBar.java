import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel {
    private Logic logic;
    private JTextField link;
    private JTextField search;
    public TopBar(Logic logic) {
        super();
        this.setBackground(Color.WHITE);
        this.logic = logic;
        this.setLayout(new BorderLayout());
        JPanel left = new JPanel();
        left.setLayout(new FlowLayout());
        JPanel right = new JPanel();
        right.setLayout(new FlowLayout());
        JButton back = new JButton("Back");
        JButton forward = new JButton("forward");
        JButton up = new JButton("up");
        link = new JTextField();
        logic.setLink(link);
        Font font = new Font("DL" ,Font.PLAIN, 18);
        link.setPreferredSize(new Dimension(400,25));
        link.setFont(font);
        JButton refresh = new JButton("Refresh");
        search = new JTextField();
        search.setFont(font);
        search.setPreferredSize(new Dimension(200,25));
        logic.setSearch(search);
        left.add(back); left.add(forward); left.add(up);
        this.add(link,BorderLayout.CENTER); right.add(refresh); right.add(search);
        this.add(left,BorderLayout.WEST);
        this.add(right,BorderLayout.EAST);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.clickBack();
            }
        });
        forward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.clickForward();
            }
        });
        up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.clickUp();

            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.enterSearch(search.getText());
            }
        });
        link.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.enterUrl(link.getText());
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                logic.clickRefresh();
            }
        });
    }

}
