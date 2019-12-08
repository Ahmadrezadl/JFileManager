import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MiddlePanel extends JPanel {
    private Logic logic;
    public File dir;
    public MiddlePanel(Logic logic,File f){
        super();
        this.logic = logic;
        this.dir = f;
        logic.setMiddlePanel(this);
        NewFlowLayout layout = new NewFlowLayout(FlowLayout.LEFT);
        this.setLayout(layout);
        try{
            File[] files = logic.getFileSystemView().getFiles(f, true);
            logic.getLink().setText(f.toString());
            for(File file : files)
            {
                addFile(file.getPath());
            }
        }
        catch (NullPointerException e)
        {
            File[] roots = logic.getFileSystemView().getRoots();
            logic.getLink().setText("This PC");
            File[] files = File.listRoots();
            for(File file : files)
            {
                addFile(file.getPath());
            }
        }
        this.setVisible(true);
    }
    public void addFile(String link)
    {
        FileButton f = new FileButton(link,logic);
        this.add(f);
    }
}
