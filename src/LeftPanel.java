import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;


public class LeftPanel extends JPanel {
    private World world;
    public LeftPanel(World world)
    {
        super();
        File[] roots = world.getFileSystemView().getRoots();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
            root.add( node );
            //showChildren(node);
            //
            File[] files = world.getFileSystemView().getFiles(fileSystemRoot, true);
            for (File file : files) {
                if (file.isDirectory()) {
                    node.add(new DefaultMutableTreeNode(file));
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
        this.add(tree);
    }
}
