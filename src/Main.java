import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        World world = new World();
        new MainFrame(world);
        System.out.println(world.getMainFrame().getBounds().height);
    }
}
