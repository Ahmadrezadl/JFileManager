import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MiddlePanel extends JPanel {
    private Logic logic;
    public MiddlePanel(Logic logic){
        super();
        this.logic = logic;
        this.setLayout(new FlowLayout());
        File[] roots = logic.getFileSystemView().getRoots();
        for(File root : roots)
        {
            File[] files = logic.getFileSystemView().getFiles(root, true);
            for(File file : files)
            {
                addFile(file.getPath());
            }
        }
    }
    public void addFile(String link)
    {
        FileButton f = new FileButton(link,logic);
        this.add(f);
    }
}
