import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenusPanel extends JPanel {
    private Logic logic;
    public MenusPanel(Logic logic)
    {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBackground(Color.WHITE);
        this.logic = logic;
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
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.createNewFile();
            }
        });
        newFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.createNewFolder();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.deleteSelectedItems();
            }
        });

        //Edit
        JMenu edit = new JMenu("Edit",true);
        jMenuBar.add(edit);
        JMenuItem rename = new JMenuItem("Rename Item");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste");
        edit.add(rename); edit.add(copy); edit.add(cut); edit.add(paste);
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.rename();
            }
        });
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.copy();
            }
        });
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.cut();
            }
        });
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.paste();
            }
        });

        //Help
        JMenu help = new JMenu("Help",true);
        jMenuBar.add(help);
        JMenuItem aboutUs = new JMenuItem("About Us");
        JMenuItem options = new JMenuItem("Options");
        JMenuItem info = new JMenuItem("Info");
        help.add(aboutUs); help.add(options); help.add(info);
        aboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.aboutUs();
            }
        });
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.options();
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logic.info();
            }
        });
    }
}
