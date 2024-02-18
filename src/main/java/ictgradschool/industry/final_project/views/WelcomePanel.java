package ictgradschool.industry.final_project.views;

import javax.swing.*;

//package ictgradschool.industry.final_project.views;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//
//public class WelcomePanel extends JPanel {
//    private AppFrame appFrame;
//    private File currentFileStore;
//
//    public WelcomePanel(AppFrame appFrame) {
//        this.appFrame = appFrame;
//        appFrame.setTitle("Welcome to the Inventory Management System"); // 设置标题
//        appFrame.setSize(400, 300);
//        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 默认关闭操作
//
//        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // 应用于 WelcomePanel
//
//        initializeWelcomeScreen();
//    }
//
//
//    private void initializeWelcomeScreen() {
//        JButton openFileButton = new JButton("Open File Store");
//        JButton createFileButton = new JButton("Create New File Store");
//
//        openFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                openFileStore();
//            }
//        });
//        //添加事件监听器
//        createFileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                createFileStore();
//            }
//        });
//        // 将按钮添加到面板中
//        add(openFileButton);
//        add(createFileButton);
//    }
//
//    // 打开文件存储的方法
//    private void openFileStore() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 允许选择文件和目录
//        int result = fileChooser.showOpenDialog(this);
//
//        if (result == JFileChooser.APPROVE_OPTION) {
//            currentFileStore = fileChooser.getSelectedFile();
//            appFrame.getInventoryManager().updateFile(currentFileStore);
//            displayOptions();
////            System.out.println("File selected: " + currentFileStore.getAbsolutePath()); // 显示选中文件的绝对路径
//        } else {
//            System.out.println("File selection cancelled");
//        }
//    }
//
//    private void createFileStore() {
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Specify a file to save");
//        int result = fileChooser.showSaveDialog(this);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            currentFileStore = fileChooser.getSelectedFile();
//            displayOptions();
//        }
//    }
//    // 显示选项的方法
//    private void displayOptions() {
//        this.removeAll();// 移除所有组件
//
//        JButton closeFileStoreButton = new JButton("Close File Store");
//        //添加事件监听器
//        closeFileStoreButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                closeFileStore();
//            }
//        });
//
//        JButton openInventoryManagerButton = new JButton("Open Inventory Manager");
//        openInventoryManagerButton.addActionListener(e -> appFrame.showInventoryManager());
//
//        JButton openPointOfSaleButton = new JButton("Open Point of Sale");
//        openPointOfSaleButton.addActionListener(e -> appFrame.showPointOfSale());
//        // 将按钮添加到面板中
//        add(closeFileStoreButton);
//        add(openInventoryManagerButton);
//        add(openPointOfSaleButton);
//
//        // 刷新和重绘界面
//        revalidate();
//        repaint();
//        System.out.println("Displaying options"); // 调试信息
//    }
//
//    // 关闭文件存储并重置界面的方法
//    private void closeFileStore() {
//        currentFileStore = null;// 重置文件存储
//
//        this.removeAll();// 移除所有组件
//        initializeWelcomeScreen();
//        // 通知布局管理器和重绘界面
//        revalidate();
//        repaint();
//
//        System.out.println("Closing file store and resetting the screen"); // 调试信息
//
//    }
//
//
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                AppFrame appFrame = new AppFrame();
//                new WelcomePanel(appFrame).setVisible(true);
//            }
//        });
//    }
//}
import javax.swing.*;
import java.awt.*;
import java.io.File;
import ictgradschool.industry.final_project.Controllers.FileController;

public class WelcomePanel extends JPanel {
    private AppFrame appFrame;
    private FileController fileController;

    public WelcomePanel(AppFrame appFrame, FileController fileController) {
        this.appFrame = appFrame;
        setLayout(new GridLayout(3, 1));

        JButton openFileStoreButton = new JButton("Open File Store");
        JButton createFileStoreButton = new JButton("Create New File Store");

        openFileStoreButton.addActionListener(e -> chooseFile(true));
        createFileStoreButton.addActionListener(e -> chooseFile(false));

        add(openFileStoreButton);
        add(createFileStoreButton);
    }

    private void chooseFile(boolean isOpen) {
        JFileChooser fileChooser = new JFileChooser();
        int result = isOpen ? fileChooser.showOpenDialog(appFrame) : fileChooser.showSaveDialog(appFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // 调用AppFrame的fileSelected方法，传递选择的文件
            appFrame.fileSelected(selectedFile);
        }
    }

}


