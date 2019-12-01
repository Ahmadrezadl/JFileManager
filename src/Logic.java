import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;

public class Logic {
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
    public static void clickBack(){
        System.out.println("Back Button Clicked");
    }

    public static void clickForward(){
        System.out.println("Forward Button Clicked");
    }

    public static void clickUp() {
        System.out.println("Up Clicked");
    }
    public static void clickRefresh() {
        System.out.println("Refresh Clicked");
    }

    public static void enterSearch(String index){
        System.out.println("Searched " + index);
    }

    public static void enterUrl(String url)
    {
        System.out.println("URL: " + url);
    }

    public static void createNewFile(){
        System.out.println("Create new file clicked");
    }

    public static void createNewFolder(){
        System.out.println("Create new folder clicked");
    }

    public static void deleteSelectedItems(){
        System.out.println("Delete selected items clicked");
    }

    public static void rename(){
        System.out.println("rename clicked");
    }

    public static void copy(){
        System.out.println("copy clicked");
    }

    public static void cut(){
        System.out.println("cut clicked");
    }

    public static void paste(){
        System.out.println("paste clicked");
    }

    public static void aboutUs(){
        System.out.println("aboutUs clicked");
    }

    public static void options(){
        System.out.println("options clicked");
    }

    public static void info(){
        System.out.println("info clicked");
    }
    public static void changeUrl(String newUrl){

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
}
