package GUI;

import Logic.Logic;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;


public class LeftPanel extends JPanel {
    private Logic logic;
    ArrayList<DefaultMutableTreeNode> nodes;
    public LeftPanel(Logic logic)
    {
        super();
        this.setLayout(new GridLayout(0,1,2,2));
        File[] roots = logic.getFileSystemView().getRoots();
        nodes = new ArrayList<>();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        for (File fileSystemRoot : roots) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot.getName());
            root.add( node );
            File[] files = logic.getFileSystemView().getFiles(fileSystemRoot, true);
            for (File file : files) {
                if (file.isDirectory()) {
                    DefaultMutableTreeNode n;
                    Icon icon = logic.getFileSystemView().getSystemIcon(file);
                    n = new DefaultMutableTreeNode(logic.getFileSystemView().getSystemDisplayName(file));

                    node.add(n);
                }
            }
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);
        tree.setRootVisible(false);
        tree.expandRow(0);
        JScrollPane treeScroll = new JScrollPane(tree);
        JScrollPane scroll = new JScrollPane(tree);
        System.out.println(logic.getMainFrame().getBounds().height);
        scroll.setPreferredSize(new Dimension(300, 300));
        this.add(scroll);
    }
}
