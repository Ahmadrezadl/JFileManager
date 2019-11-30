import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class World {
    private MainFrame mainFrame;
    private MenusPanel menusPanel;
    private TopPanel topPanel;
    private FileSystemView fileSystemView;
    private Desktop desktop;

    public World(){
        fileSystemView = FileSystemView.getFileSystemView();
        desktop = Desktop.getDesktop();
    }
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MenusPanel getMenusPanel() {
        return menusPanel;
    }

    public void setMenusPanel(MenusPanel menusPanel) {
        this.menusPanel = menusPanel;
    }

    public TopPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(TopPanel topPanel) {
        this.topPanel = topPanel;
    }

    public FileSystemView getFileSystemView() {
        return fileSystemView;
    }

    public void setFileSystemView(FileSystemView fileSystemView) {
        this.fileSystemView = fileSystemView;
    }

    public Desktop getDesktop() {
        return desktop;
    }

    public void setDesktop(Desktop desktop) {
        this.desktop = desktop;
    }
}
