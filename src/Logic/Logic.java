package Logic;

import GUI.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Logic {
    private MiddlePanel middlePanel;
    private MainFrame mainFrame;
    private MenusPanel menusPanel;
    private TopPanel topPanel;
    private FileSystemView fileSystemView;
    private Desktop desktop;
    private JTextField link;
    private JTextField search;
    private FileButton selectedFile;
    public KeyListener focusManager;

    public Logic(){
        fileSystemView = FileSystemView.getFileSystemView();
        desktop = Desktop.getDesktop();
        focusManager = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
                    manager.focusNextComponent();
                    System.out.println("Kir");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public void clickBack(){
         goDirectory(middlePanel.dir.getParentFile());
    }


    public void setVisibility ()
    {

    }
    public MiddlePanel getMiddlePanel() {
        return middlePanel;
    }

    public void setMiddlePanel(MiddlePanel middlePanel) {
        this.middlePanel = middlePanel;
    }

    public void clickForward(){
        System.out.println("Forward Button Clicked");
        // TODO: 12/1/2019
    }

    public void clickUp() {
        goDirectory(middlePanel.dir.getParentFile());
    }
    public void clickRefresh() {
        goDirectory(middlePanel.dir);
    }

    public  void enterSearch(File file,String index){
        mainFrame.scrollPane.getViewport().remove(middlePanel);
        mainFrame.scrollPane.getViewport().add(new JScrollPane(new MiddlePanel(this,file,index)),BorderLayout.CENTER);
        mainFrame.setVisible(true);
        middlePanel.setVisible(true);
    }

    public FileButton getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(FileButton selectedFile) {
        this.selectedFile = selectedFile;
    }

    public  void enterUrl(String url)
    {
        File f = new File(url);
        if (!f.isDirectory())
        {
            if(f.isFile())
                openFile(f);
            else
                goDirectory(null);
        }
        else
        {
            goDirectory(f);
        }


    }

    public void createNewFile() {
        String name = JOptionPane.showInputDialog("Enter Name of your new file: " , "New File");
        if (!name.equals("")) {
            try{
                boolean duplicate = false;
                for (File f : fileSystemView.getFiles(middlePanel.dir , true)) {
                    if (f.getName().equals(name)) {
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) {
                    JOptionPane.showMessageDialog(null ,
//                        good thinking!!
                            "File Exists. Choose another name." ,
                            "Name is Duplicate" ,
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    new FileOutputStream(middlePanel.dir + "\\" + name);
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null ,
//                        good thinking!!
                        e ,
                        "Cannot create file" ,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        clickRefresh();
        middlePanel.setVisible(true);
    }

    public  void createNewFolder(){
        String name = JOptionPane.showInputDialog("Enter Name of your new folder: " , "New Folder");
        if (!name.equals("")) {
            try{
                boolean duplicate = false;
                for (File f : fileSystemView.getFiles(middlePanel.dir , true)) {
                    if (f.getName().equals(name)) {
                        duplicate = true;
                        break;
                    }
                }
                if (duplicate) {
                    JOptionPane.showMessageDialog(null ,
//                        good thinking!!
                            "Folder Exists. Choose another name." ,
                            "Name is Duplicate" ,
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    File f = new File(middlePanel.dir + "\\" + name);
                    f.mkdir();

                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null ,
//                        good thinking!!
                        e ,
                        "Cannot create file" ,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        middlePanel.setVisible(true);
        mainFrame.scrollPane.setVisible(true);
        clickRefresh();

    }

    public  void deleteSelectedItems(){
        String[]entries = selectedFile.getFile().list();
        if (entries != null) {
            for(String s: entries){
                File currentFile = new File(selectedFile.getFile().getPath(),s);
                 currentFile.delete();
            }
        }
        boolean res = selectedFile.getFile().delete();
        if(!res)
            showErrorMessage("You can't Delete this file","Error!");
        else
        selectedFile.setVisible(false);
    }

    public  void rename(){
        if (selectedFile==null) {
            showErrorMessage("No file selected to rename.","Select File");
            return;
        }

        String renameTo = JOptionPane.showInputDialog("Choose new name",getSelectedFile().getName());
        if (renameTo!=null) {
            File newFileName = new File(getSelectedFile().getFile().getParent() + "\\" + renameTo);
            boolean res = selectedFile.getFile().renameTo(newFileName);
            if(!res)
                showErrorMessage("You can't rename this file to this name" , "Rename not completed!");
            else
            selectedFile.setName(renameTo);
        }
        getSelectedFile().setVisible(true);
    }
    public void rename(FileButton self) {

        if (self==null) {
            showErrorMessage("No file selected to rename.","Select File");
            return;
        }

        String renameTo = JOptionPane.showInputDialog("Choose new name",getSelectedFile().getName());
        if (renameTo!=null) {
            File newFileName = new File(getSelectedFile().getFile().getParent() + "\\" + renameTo);
            self.getFile().renameTo(newFileName);
            self.setName(renameTo);
        }
        self.setVisible(true);
    }

    public  void copy(){
        System.out.println("copy clicked");
        // TODO: 12/1/2019
    }

    public  void cut(){
        System.out.println("cut clicked");
        // TODO: 12/1/2019
    }

    public  void paste(){
    }

    public  void aboutUs(){
        System.out.println("aboutUs clicked");
        // TODO: 12/1/2019
    }

    public  void options(){
        System.out.println("options clicked");
        // TODO: 12/1/2019
    }

    public  void info(){
        System.out.println("info clicked");
        // TODO: 12/1/2019
    }
    public void changeUrlText(String newUrl){
        search.setText(newUrl);
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

    public void showProperties()
    {
        BasicFileAttributes infos = null;
        try {
            infos = Files.readAttributes(selectedFile.getFile().toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long milliseconds = infos.creationTime().to(TimeUnit.MILLISECONDS);
        Date creationDate = null;
        if((milliseconds > Long.MIN_VALUE) && (milliseconds < Long.MAX_VALUE))
        {
            creationDate =
                    new Date(infos.creationTime().to(TimeUnit.MILLISECONDS));

        }
        String created = creationDate.getDate()+ "/" +
                (creationDate.getMonth() + 1) + "/" +
                (creationDate.getYear() + 1900);
        String size = ""+selectedFile.megabytes + "MB (" +
                selectedFile.kilobytes + " KiloBytes)";
        if(selectedFile.isDirectory())
        Methods.showProperties("Folder",selectedFile.getFile().getAbsolutePath(),size,
                created,selectedFile.getNumFiles()+ "Files , "  + selectedFile.getNumFolders() + " Folders");
    }
    public void goDirectory(File file) {
        mainFrame.scrollPane.getViewport().remove(middlePanel);
        mainFrame.setVisible(true);
        middlePanel.setVisible(true);
        mainFrame.scrollPane.getViewport().add(new MiddlePanel(this,file,""),BorderLayout.CENTER);
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

    private void showErrorMessage(String errorMessage, String errorTitle) {
        JOptionPane.showMessageDialog(
                mainFrame,
                errorMessage,
                errorTitle,
                JOptionPane.ERROR_MESSAGE
        );
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



}
