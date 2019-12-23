package GUI;

import Logic.Logic;
import Logic.Methods;
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileButton extends JPanel{
    private String name;
    private Icon icon;
    private File file;
    public int kilobytes;
    public int megabytes;
    private JButton button;
    private JButton label;
    private Logic logic;
    private int numFiles = 0;
    private int numFolders = 0;
    public FileButton(String link, Logic logic)
    {
        super();
        this.logic = logic;
        FileButton self = this;
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        file = new File(link);
        if(file.isDirectory())
        {
                File[] files = logic.getFileSystemView().getFiles(file, true);
                for(File file : files)
                {
                    if(file.isDirectory())numFolders++;
                    else numFiles++;
                }
        }
        double bytes = file.length();
        kilobytes = (int) (bytes / 1024);
        megabytes = (kilobytes / 1024);
        button = new JButton();
        JPopupMenu popupMenu = new JPopupMenu();
        ActionListener hideMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupMenu.setVisible(false);
            }
        };

        JMenuItem open = new JMenuItem("Open");

        open.addActionListener(hideMenu);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (file.isDirectory())
                {
                    logic.getMemento().addRight(file.getParent());
                    logic.goDirectory(file);
                }
                else
                    logic.openFile(file);
            }
        });
        popupMenu.add(open);

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(hideMenu);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.copy();
            }
        });
        popupMenu.add(copy);

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(hideMenu);
        cut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.cut();
            }
        });
        popupMenu.add(cut);



        JMenuItem rename = new JMenuItem("Rename");
        rename.addActionListener(hideMenu);
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.rename(self);
            }
        });
        popupMenu.add(rename);


        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(hideMenu);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.deleteSelectedItems();
            }
        });
        popupMenu.add(delete);


        JMenuItem properties = new JMenuItem("Properties");
        properties.addActionListener(hideMenu);
        properties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.showProperties();
            }
        });
        popupMenu.add(properties);

        if(logic.isLarge())
        {
            try {

                icon = new ImageIcon(getBufferedImage(file));
                button.setIcon(icon);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            button.setIcon(logic.getFileSystemView().getSystemIcon(file));
        }

        label = new JButton();
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setForeground(null);
        button.setBackground(null);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);

        BasicFileAttributes infos = null;

        try {
            infos = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            long milliseconds = infos.creationTime().to(TimeUnit.MILLISECONDS);
            Date creationDate = null;
            if((milliseconds > Long.MIN_VALUE) && (milliseconds < Long.MAX_VALUE))
            {
                 creationDate =
                        new Date(infos.creationTime().to(TimeUnit.MILLISECONDS));

            }
            if(file.isDirectory())
            button.setToolTipText("<html>Date Created: " + creationDate.getDate()+ "/" +
                    (creationDate.getMonth() + 1) + "/" +
                    (creationDate.getYear() + 1900) +
                    "<br>Size: " + kilobytes+"KB" +
                    "<br>Folders: " + numFolders+ "<br>Files:"+ numFiles + "</html>");
            else
                button.setToolTipText("<html>Date Created: " + creationDate.getDate()+ "/" +
                        (creationDate.getMonth() + 1) + "/" +
                        (creationDate.getYear() + 1900) +
                        "<br>Size: " + kilobytes+"KB" + "</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        label.setBorderPainted(false);
        label.setFocusable(false);
        label.setBorder(null);
        label.setForeground(null);
        label.setBackground(null);
        label.setContentAreaFilled(false);
        label.setToolTipText(kilobytes+"KB");
        if(logic.isLarge())
        {
            if(logic.getFileSystemView().getSystemDisplayName(file).length() >= 23)
                label.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file).substring(0,9) +"<br>"+ logic.getFileSystemView().getSystemDisplayName(file).substring(9,23)   +"...</html>");
            else if(logic.getFileSystemView().getSystemDisplayName(file).length() <=11)
                label.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file)+"<br></html>");
            else
                label.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file).substring(0,9) +"<br>"+ logic.getFileSystemView().getSystemDisplayName(file).substring(9) + "</html>");
        }
        else
        {
            if(logic.getFileSystemView().getSystemDisplayName(file).length() >= 25)
                button.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file).substring(0,22)+"...</html>");
            else
                button.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file) + "</html>");
        }
        if(logic.isLarge())
        {
            this.add(button,BorderLayout.NORTH);
            this.add(label,BorderLayout.SOUTH);
        }
        else
        {
            this.add(button,BorderLayout.WEST);
            this.add(label,BorderLayout.CENTER);
        }

        MouseListener mouseclick = (new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()== MouseEvent.BUTTON3){
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
                else {
                    if (e.getClickCount() == 2) {
                        if (file.isDirectory())
                        {
                            logic.getMemento().addRight(logic.getMiddlePanel().dir.getAbsolutePath());
                            logic.goDirectory(file);
                        }
                        else
                            logic.openFile(file);
                    }


                }
                if (logic.getSelectedFile() != null)
                    logic.getSelectedFile().setBackground(Color.WHITE);
                logic.setSelectedFile(self);
                button.requestFocus();
                self.setBackground(new Color(0x3500A3));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(logic.getSelectedFile() != self)
                    self.setBackground(new Color(0xF6CAF2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                    if(logic.getSelectedFile() != self)
                    self.setBackground(Color.WHITE);
            }
        });
        button.addMouseListener(mouseclick);
        label.addMouseListener(mouseclick);
        button.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (logic.getSelectedFile() != null)
                {
                    logic.getSelectedFile().setBackground(Color.WHITE);
                }

                logic.setSelectedFile(self);
                self.setBackground(new Color(0x757BFF));

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        button.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    manager.focusNextComponent();
                }
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    logic.clickUp();
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    manager.focusPreviousComponent();
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    if (file.isDirectory())
                    {
                        logic.getMemento().addRight(logic.getMiddlePanel().dir.getAbsolutePath());
                        logic.goDirectory(file);
                    }
                    else
                        logic.openFile(file);
                }
                if(e.getKeyCode() == KeyEvent.VK_DELETE)
                {
                    logic.deleteSelectedItems();
                }

            }
        });
        this.setMaximumSize(new Dimension(new Dimension(2000,32)));
        this.setVisible(true);
    }

    public boolean isDirectory(){
        return file.isDirectory();
    }
    public boolean isFile(){
        return file.isFile();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.label.setText(name);
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    private BufferedImage getBufferedImage(final File pFile)
            throws FileNotFoundException {
        Image icon = ShellFolder.getShellFolder(pFile).getIcon(true);
        BufferedImage im = new BufferedImage(icon.getWidth(null),
                icon.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = im.createGraphics();
        g.drawImage(icon, 0, 0, null);
        g.dispose();
        int width = im.getWidth();
        int height = im.getHeight();
        final int maxHeight = 100;
        double scaleValue = 0;
        if (height > width)
            scaleValue = 100;
        else
            scaleValue = maxHeight / width;
        final int scaledWidth = (int) (im.getWidth() * scaleValue);
        final int scaledHeigh = (int) (im.getHeight() * scaleValue);
        BufferedImage resized = new BufferedImage(scaledWidth, scaledHeigh,
                im.getType());
        g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(im, 0, 0, scaledWidth, scaledHeigh, 0, 0, im.getWidth(),
                im.getHeight(), null);
        g.dispose();
        return resized;
    }

    public int getNumFiles() {
        return numFiles;
    }

    public int getNumFolders() {
        return numFolders;
    }
}
