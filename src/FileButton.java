
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
        this.setLayout(new BorderLayout());
        file = new File(link);
        bytes = file.length();
        kilobytes = (int) (bytes / 1024);
        megabytes = (kilobytes / 1024);
        gigabytes = (megabytes / 1024);
        button = new JButton();
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
        button.setContentAreaFilled(false);
        button.setToolTipText(kilobytes+"KB");
        label.setBorderPainted(false);
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
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (file.isDirectory())
                        logic.goDirectory(file);
                    else
                        logic.openFile(file);
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

    public boolean isDirectory(){
        return file.isDirectory();
    }
    public boolean isFile(){
        return file.isFile();
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
        System.out.println(width);
        System.out.println(height);
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
