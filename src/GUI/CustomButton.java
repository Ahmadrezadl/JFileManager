package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CustomButton extends JButton {
    private Image icon1;
    private Image icon2;
    public CustomButton(String image1,String image2, int size)
    {
        super();
        setBorderPainted(false);
        setFocusable(false);
        setBorder(null);
        setForeground(null);
        setBackground(null);
        setContentAreaFilled(false);

        try {
            icon1 = ImageIO.read(getClass().getResource("..\\Sprites\\" + image1));
            icon2 = ImageIO.read(getClass().getResource("..\\Sprites\\" + image2));
            icon1 = icon1.getScaledInstance(size,size,1);
            icon2 = icon2.getScaledInstance(size,size,1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setIcon(new ImageIcon(icon1));

        CustomButton self = this;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                self.setIcon(new ImageIcon(icon2));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                self.setIcon(new ImageIcon(icon1));

            }
        });
    }
    public CustomButton()
    {
        super();
        setBorderPainted(false);
        setBorder(null);
        setForeground(null);
        setBackground(null);
        setContentAreaFilled(false);
    }
}
