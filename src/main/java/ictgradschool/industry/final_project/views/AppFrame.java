package ictgradschool.industry.final_project.views;
//package ictgradschool.industry.final_project.views;
//
//import ictgradschool.industry.final_project.Models.InventoryModel;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class AppFrame extends JFrame {
//    private CardLayout cardLayout;
//    private JPanel mainPanel;
//    private InventoryManager inventoryManager;
////    private PointOfSale pointOfSale;
//
//    public AppFrame() {
//        setTitle("Inventory Management System");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//
//        cardLayout = new CardLayout();
//        mainPanel = new JPanel(cardLayout);
//        InventoryModel inventoryModel = new InventoryModel();
//        inventoryManager = new InventoryManager(this);
////        pointOfSale = new PointOfSale(inventoryModel);
//        WelcomePanel welcomeScreen = new WelcomePanel(this);
//
//        mainPanel.add(welcomeScreen, "WelcomeScreen");
//        mainPanel.add(inventoryManager, "InventoryManager");
////        mainPanel.add(pointOfSale, "PointOfSale");
//
//
//        add(mainPanel);
//    }
//
//    public void showWelcomeScreen() {
//        cardLayout.show(mainPanel, "WelcomeScreen");
//    }
//
//    public void showInventoryManager() {
//        cardLayout.show(mainPanel, "InventoryManager");
////        inventoryManager.updateFile();
//    }
//
//    public void showPointOfSale() {
//        cardLayout.show(mainPanel, "PointOfSale");
//    }
//
//    public InventoryManager getInventoryManager() {
//        return inventoryManager;
//    }
//
//    // ... 其他方法 ...
//}

import ictgradschool.industry.final_project.Controllers.FileController;
import ictgradschool.industry.final_project.Models.FileStore;
import ictgradschool.industry.final_project.Models.InventoryModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AppFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    // 创建 FileStore 实例
    private FileStore fileStore = new FileStore();

    // 创建 InventoryModel 实例
    private InventoryModel inventoryModel = new InventoryModel();
    // 使用 FileStore 和 InventoryModel 实例创建 FileController
    private FileController fileController = new FileController(fileStore, inventoryModel);

    public AppFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Inventory Management System");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        WelcomePanel welcomePanel = new WelcomePanel(this, fileController);
        mainPanel.add(welcomePanel, "WelcomePanel");

        getContentPane().add(mainPanel);
        cardLayout.show(mainPanel, "WelcomePanel");

        // 在初始化UI时，添加SelectionPanel到cardLayout中
        SelectionPanel selectionPanel = new SelectionPanel(this);
        mainPanel.add(selectionPanel, "SelectionPanel");
    }

    // 修改文件选择后的逻辑
    public void fileSelected(File selectedFile) {
        fileController.setCurrentFile(selectedFile);
        cardLayout.show(mainPanel, "SelectionPanel"); // 显示选择页面
    }

    public void showWelcomeScreen() {
        cardLayout.show(mainPanel, "WelcomePanel"); // 注意这里的字符串标识符要与添加面板时使用的相匹配
    }


    public void openInventoryManager() {
        InventoryManager inventoryManager = new InventoryManager(this, fileController);
        mainPanel.add(inventoryManager, "InventoryManager");
        cardLayout.show(mainPanel, "InventoryManager");
    }

    public void openPointOfSale() {
        PointOfSalePanel pointOfSalePanel = new PointOfSalePanel(this, fileController, inventoryModel); // 假设PointOfSalePanel已经适配
        mainPanel.add(pointOfSalePanel, "PointOfSale");
        cardLayout.show(mainPanel, "PointOfSale");
    }


    // 这里可以添加其他方法，如打开销售点界面等
}