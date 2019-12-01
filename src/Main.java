import javax.swing.*;

public class Main {

    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Logic logic = new Logic();
        new MainFrame(logic);
        System.out.println(logic.getMainFrame().getBounds().height);
    }
}
