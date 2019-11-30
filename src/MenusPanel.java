import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenusPanel extends JPanel {
    private World world;
    public MenusPanel(World world)
    {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.WHITE);
        this.world = world;
        JMenuBar jMenuBar = new JMenuBar();
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(jMenuBar);

        //File
        JMenu file = new JMenu("File",true);
        jMenuBar.add(file);
        JMenuItem newFile = new JMenuItem("Create New File");
        JMenuItem newFolder = new JMenuItem("Create New Folder");
        JMenuItem delete = new JMenuItem("Delete Selected Item(s)");
        file.add(newFile); file.add(newFolder); file.add(delete);

        //Edit
        JMenu edit = new JMenu("Edit",true);
        jMenuBar.add(edit);
        JMenuItem rename = new JMenuItem("Rename Item");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste");
        edit.add(rename); edit.add(copy); edit.add(cut); edit.add(paste);

        //Help
        JMenu help = new JMenu("Help",true);
        jMenuBar.add(help);
        JMenuItem aboutUs = new JMenuItem("About Us");
        JMenuItem options = new JMenuItem("Options");
        JMenuItem info = new JMenuItem("Info");
        help.add(aboutUs); help.add(options); help.add(info);
    }
}
