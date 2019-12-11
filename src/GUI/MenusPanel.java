package GUI;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

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
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem newFolder = new JMenuItem("Create New Folder");
        newFolder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem delete = new JMenuItem("Delete Selected Item(s)");
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));
        file.add(newFile); file.add(newFolder); file.add(delete);
        newFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.createNewFile();
            }
        });
        newFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.createNewFolder();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.deleteSelectedItems();
            }
        });

        //Edit
        JMenu edit = new JMenu("Edit",true);
        jMenuBar.add(edit);
        JMenuItem rename = new JMenuItem("Rename Item");
        rename.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        edit.add(rename); edit.add(copy); edit.add(cut); edit.add(paste);
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.rename();
            }
        });
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.copy();
            }
        });
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.cut();
            }
        });
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logic.paste();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
                logic.aboutUs();
            }
        });
        options.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.options();
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.info();
            }
        });
    }
}
