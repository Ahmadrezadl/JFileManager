import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class LeftPanel extends JPanel {
    private World world;
    ArrayList<DefaultMutableTreeNode> nodes;
    public LeftPanel(World world)
    {
        super();
        this.setLayout(new GridLayout(0,1,2,2));
        File[] roots = world.getFileSystemView().getRoots();
        nodes = new ArrayList<>();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot.getName());
            root.add( node );
            File[] files = world.getFileSystemView().getFiles(fileSystemRoot, true);
            for (File file : files) {
                if (file.isDirectory()) {
                    DefaultMutableTreeNode n;
                    if(file.toString().equals("This PC") || file.toString().equals("Network") || file.toString().equals("OneDrive") || file.toString().equals("Libraries"))
                        n = new DefaultMutableTreeNode(file);
                    else
                        n= new DefaultMutableTreeNode(file.getName());
                    node.add(n);
                }
            }
            //
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);
        tree.setRootVisible(false);
       // tree.addTreeSelectionListener(treeSelectionListener);
     //   tree.setCellRenderer(new com.github.filemanager.FileTreeCellRenderer());
        tree.expandRow(0);
        JScrollPane treeScroll = new JScrollPane(tree);
        JScrollPane scroll = new JScrollPane(tree);
        System.out.println(world.getMainFrame().getBounds().height);
        scroll.setPreferredSize(new Dimension(300, 300));
        this.add(scroll);
    }
}
