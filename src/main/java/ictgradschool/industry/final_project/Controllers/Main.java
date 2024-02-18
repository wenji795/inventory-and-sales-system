package ictgradschool.industry.final_project.Controllers;

import ictgradschool.industry.final_project.Models.FileStore;
import ictgradschool.industry.final_project.Models.InventoryModel;
import ictgradschool.industry.final_project.views.AppFrame;
import ictgradschool.industry.final_project.views.InventoryManager;

import javax.swing.*;

//package ictgradschool.industry.final_project.Controllers;
//
//import ictgradschool.industry.final_project.Models.InventoryModel;
//import ictgradschool.industry.final_project.views.AppFrame;
//
//public class Main {//Main
//    public static void main(String[] args) {
//        // TODO: Your code here
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            // 初始化模型
//            InventoryModel inventoryModel = new InventoryModel();
//
//            // 创建控制器，并将模型传递给它
//            InventoryManagerController inventoryManagerController = new InventoryManagerController(inventoryModel);
//            WelcomePanelController welcomePanelController = new WelcomePanelController();
//
//            // 初始化视图并将控制器传递给它
//            AppFrame appFrame = new AppFrame(inventoryManagerController, welcomePanelController);
//            appFrame.setVisible(true);
//        });
//    }
//}
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            frame.setTitle("Inventory Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);

        });
    }

}
