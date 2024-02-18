package ictgradschool.industry.final_project.views;

import javax.swing.*;
import java.awt.*;

public class SelectionPanel extends JPanel {
    public SelectionPanel(AppFrame appFrame) {
        setLayout(new GridLayout(1, 2)); // 使用网格布局，两个按钮并排

        JButton inventoryButton = new JButton("Inventory Management");
        JButton pointOfSaleButton = new JButton("Point of Sale");

        inventoryButton.addActionListener(e -> appFrame.openInventoryManager());
        pointOfSaleButton.addActionListener(e -> appFrame.openPointOfSale());

        add(inventoryButton);
        add(pointOfSaleButton);
    }
}

