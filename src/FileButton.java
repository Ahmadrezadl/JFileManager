
import sun.awt.shell.ShellFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

public class FileButton extends JButton{
    private String name;
    private ImageIcon icon;
    private File file;
    private double bytes;
    private double kilobytes;
    private double megabytes;
    private double gigabytes;
    public FileButton(String link, Logic logic)
    {
        super();
        file = new File(link);
        bytes = file.length();
        kilobytes = (bytes / 1024);
        megabytes = (kilobytes / 1024);
        gigabytes = (megabytes / 1024);
        sun.awt.shell.ShellFolder sf;
        try {
            sf = sun.awt.shell.ShellFolder.getShellFolder(file);
            Icon icon = new ImageIcon(sf.getIcon(true));
            this.setIcon(icon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setForeground(null);
        this.setBackground(null);
        this.setContentAreaFilled(false);
        this.setToolTipText(kilobytes+"KB");
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
        g.drawImage(icon, -10, -10, null);
        g.dispose();
        int width = im.getWidth();
        int height = im.getHeight();
        System.out.println(width);
        System.out.println(height);
        final int maxHeight = 120;
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
