package GUI;
import Logic.Logic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MiddlePanel extends JPanel {
    private Logic logic;
    public File dir;
    public MiddlePanel(Logic logic,File f,String search){
        super();

        this.logic = logic;
        this.dir = f;
        this.setBackground(Color.WHITE);
        logic.setMiddlePanel(this);
        NewFlowLayout layout = new NewFlowLayout(FlowLayout.LEFT);

        this.setLayout(layout);
        try{
            File[] files = logic.getFileSystemView().getFiles(f, true);
            logic.getLink().setText(f.toString());
            for(File file : files)
            {
                if((search.equals("") || file.getName().toLowerCase().contains(search)))
                {
                    addFile(file.getPath());
                }
            }
        }
        catch (NullPointerException e)
        {
            File[] roots = logic.getFileSystemView().getRoots();
            logic.getLink().setText("This PC");
            File[] files = File.listRoots();
            for(File file : files)
            {
                addFile(file.getPath());
            }
        }
//        Logic.getLoadingBar().setValue(100);
        if(logic.getMainFrame().scrollPane != null)
            logic.getMainFrame().scrollPane.setVisible(true);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (logic.getSelectedFile() != null)
                    logic.getSelectedFile().setBackground(Color.WHITE);
                logic.setSelectedFile(null);
                if (e.getButton()== MouseEvent.BUTTON3)
                {
                    JPopupMenu file = new JPopupMenu();
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
                    file.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setVisible(true);

    }
    public void addFile(String link)
    {
        FileButton f = new FileButton(link,logic);
        this.add(f);
    }

}
