import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Logic {
    private MiddlePanel middlePanel;
    private MainFrame mainFrame;
    private MenusPanel menusPanel;
    private TopPanel topPanel;
    private FileSystemView fileSystemView;
    private Desktop desktop;
    private JTextField link;
    private JTextField search;


    public Logic(){
        fileSystemView = FileSystemView.getFileSystemView();
        desktop = Desktop.getDesktop();
    }

    public void clickBack(){
         goDirectory(middlePanel.dir.getParentFile());
    }



    public MiddlePanel getMiddlePanel() {
        return middlePanel;
    }

    public void setMiddlePanel(MiddlePanel middlePanel) {
        this.middlePanel = middlePanel;
    }

    public static void clickForward(){
        System.out.println("Forward Button Clicked");
        // TODO: 12/1/2019
    }

    public void clickUp() {
        goDirectory(middlePanel.dir.getParentFile());
    }
    public void clickRefresh() {
        goDirectory(middlePanel.dir);
    }

    public static void enterSearch(String index){
        System.out.println("Searched " + index);
        /// TODO: 12/1/2019
    }

    public  void enterUrl(String url)
    {
        goDirectory(new File(url));
    }

    public static void createNewFile(){
        System.out.println("Create new file clicked");
        // TODO: 12/1/2019
    }

    public static void createNewFolder(){
        System.out.println("Create new folder clicked");
        // TODO: 12/1/2019
    }

    public static void deleteSelectedItems(){
        System.out.println("Delete selected items clicked");
        // TODO: 12/1/2019
    }

    public static void rename(){
        System.out.println("rename clicked");
        // TODO: 12/1/2019
    }

    public static void copy(){
        System.out.println("copy clicked");
        // TODO: 12/1/2019
    }

    public static void cut(){
        System.out.println("cut clicked");
        // TODO: 12/1/2019
    }

    public static void paste(){
        System.out.println("paste clicked");
        // TODO: 12/1/2019
    }

    public static void aboutUs(){
        System.out.println("aboutUs clicked");
        // TODO: 12/1/2019
    }

    public static void options(){
        System.out.println("options clicked");
        // TODO: 12/1/2019
    }

    public static void info(){
        System.out.println("info clicked");
        // TODO: 12/1/2019
    }
    public void changeUrlText(String newUrl){
        search.setText(newUrl);
    }



    //Not For Logic-------------------------------------------------------------------------------

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

    public JTextField getLink() {
        return link;
    }

    public void setLink(JTextField link) {
        this.link = link;
    }

    public JTextField getSearch() {
        return search;
    }

    public void setSearch(JTextField search) {
        this.search = search;
    }

    public void goDirectory(File file) {
        mainFrame.scrollPane.getViewport().remove(middlePanel);
        mainFrame.scrollPane.getViewport().add(new JScrollPane(new MiddlePanel(this,file)),BorderLayout.CENTER);
        mainFrame.setVisible(true);
        middlePanel.setVisible(true);
    }

    public void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
