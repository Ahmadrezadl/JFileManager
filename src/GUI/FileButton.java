package GUI;

import Logic.Logic;
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileButton extends JPanel{
    private String name;
    private Icon icon;
    private File file;
    private double bytes;
    private int kilobytes;
    private int megabytes;
    private int gigabytes;
    private JButton button;
    private JButton label;
    private Logic logic;
    public FileButton(String link, Logic logic)
    {
        super();
        this.logic = logic;
        FileButton self = this;
        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());
        file = new File(link);
        bytes = file.length();
        kilobytes = (int) (bytes / 1024);
        megabytes = (kilobytes / 1024);
        gigabytes = (megabytes / 1024);
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
                    logic.goDirectory(file);
                else
                    logic.openFile(file);
            }
        });
        popupMenu.add(open);


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


        try {

            icon = new ImageIcon(getBufferedImage(file));
            button.setIcon(icon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        label = new JButton();
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setForeground(null);
        button.setBackground(null);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setToolTipText(kilobytes+"KB");
        label.setBorderPainted(false);
        label.setFocusable(false);
        label.setBorder(null);
        label.setForeground(null);
        label.setBackground(null);
        label.setContentAreaFilled(false);
        label.setToolTipText(kilobytes+"KB");
        if(logic.getFileSystemView().getSystemDisplayName(file).length() >= 26)
            label.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file).substring(0,12) +"<br>"+ logic.getFileSystemView().getSystemDisplayName(file).substring(12,25)   +"...</html>");
        else if(logic.getFileSystemView().getSystemDisplayName(file).length() <=14)
            label.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file)+"<br></html>");
        else
            label.setText("<html>" + logic.getFileSystemView().getSystemDisplayName(file).substring(0,12) +"<br>"+ logic.getFileSystemView().getSystemDisplayName(file).substring(12) + "</html>");
        this.add(button,BorderLayout.NORTH);
        this.add(label,BorderLayout.SOUTH);
        MouseListener mouseclick = (new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()== MouseEvent.BUTTON3){
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
                else {
                    if (e.getClickCount() == 2) {
                        if (file.isDirectory())
                            logic.goDirectory(file);
                        else
                            logic.openFile(file);
                    }


                }
                if (logic.getSelectedFile() != null)
                    logic.getSelectedFile().setBackground(Color.WHITE);
                logic.setSelectedFile(self);
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
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(logic.getSelectedFile().equals(self))
                    logic.rename();
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
}
