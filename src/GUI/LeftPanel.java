package GUI;

import Logic.Logic;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
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
        this.logic = logic;
        logic.setLeftPanel(this);
        this.setLayout(new GridLayout(0,1,2,2));

        DefaultMutableTreeNode style=new DefaultMutableTreeNode("This PC");
        File[] files = File.listRoots();
        for(File file : files)
        {
            style.add(new DefaultMutableTreeNode(file.getPath()));
        }
        DefaultTreeModel treeModel = new DefaultTreeModel(style);
        JTree tree = new JTree(treeModel);
        JScrollPane scroll = new JScrollPane(tree);
        scroll.setPreferredSize(new Dimension(300, 300));
        this.add(scroll);
        tree.addTreeSelectionListener(e -> {
            String node = e.getPath().toString();
            node = node.substring(9,node.length()-1);
            String[] nodes = node.split(", ");
            String pathFile = nodes[0].substring(1);
            for(int i = 1;i < nodes.length;i++)
            pathFile+= "\\" + nodes[i];
            System.out.println(pathFile);
            DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

            File[] tmp = logic.getFileSystemView().getFiles(new File(pathFile),true);
            for(File f : tmp)
            {
                selectedNode.add(new DefaultMutableTreeNode(f.getName()));
                logic.goDirectory(new File (pathFile));
            }
        });
    }
}
