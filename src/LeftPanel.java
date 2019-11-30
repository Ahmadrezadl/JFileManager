import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;


public class LeftPanel extends JPanel {
    private World world;
    public LeftPanel(World world)
    {
        super();
        DefaultMutableTreeNode files =new DefaultMutableTreeNode("My Computer");
        DefaultMutableTreeNode ahmadrezadl=new DefaultMutableTreeNode("ahmadrezadl");
        files.add(ahmadrezadl);
        JTree jt=new JTree(files);
        this.add(jt);
    }
}
