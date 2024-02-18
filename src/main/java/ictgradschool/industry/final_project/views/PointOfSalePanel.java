package ictgradschool.industry.final_project.views;

import ictgradschool.industry.final_project.Controllers.FileController;
import ictgradschool.industry.final_project.Controllers.SaleController;
import ictgradschool.industry.final_project.Models.InventoryModel;
import ictgradschool.industry.final_project.Models.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//package ictgradschool.industry.final_project.views;
//
//import ictgradschool.industry.final_project.Models.InventoryModel;
//import ictgradschool.industry.final_project.Models.Product;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PointOfSale extends JPanel {
//    private JList<Product> productList;
//    private DefaultListModel<Product> productListModel;
//    private JList<String> cartList;
//    private DefaultListModel<String> cartListModel;
//    private List<Product> cartItems = new ArrayList<>();
//    private JButton checkoutButton;
//    private InventoryModel inventoryModel;
//
//    public PointOfSale() {
//        this.inventoryModel = inventoryModel;
//        setLayout(new BorderLayout());
//
//        productListModel = new DefaultListModel<>();
//        productList = new JList<>(productListModel);
//        loadProducts();
//
//        cartListModel = new DefaultListModel<>();
//        cartList = new JList<>(cartListModel);
//
//        checkoutButton = new JButton("Checkout");
//        checkoutButton.addActionListener(this::performCheckout);
//
//        JPanel listPanel = new JPanel(new GridLayout(1, 2));
//        listPanel.add(new JScrollPane(productList));
//        listPanel.add(new JScrollPane(cartList));
//
//        add(listPanel, BorderLayout.CENTER);
//        add(checkoutButton, BorderLayout.SOUTH);
//    }
//
//    private void loadProducts() {
//        // 获取所有产品
//        List<Product> products = inventoryModel.getAllProducts();
//        for (Product product : products) {
//            productListModel.addElement(product);
//        }
//    }
//
//    private void addToCart(Product product) {
//        cartItems.add(product);
//        cartListModel.addElement(product.getName() + " - $" + product.getPrice());
//        // 可能需要更新库存显示，这取决于你的实现细节
//    }
//
//    private void performCheckout(ActionEvent e) {
//        // 在这里实现结账逻辑，包括更新库存和生成收据
//        // 这可能涉及到调用InventoryModel和Receipt类的方法
//        JOptionPane.showMessageDialog(this, "Checkout completed!");
//        cartItems.clear();
//        cartListModel.clear();
//        // 重新加载产品列表，以反映库存的变化
//        loadProducts();
//    }
//
//    // 其他必要的方法，例如处理用户将产品添加到购物车的事件
//}
//public class PointOfSalePanel extends JPanel {
//    private SaleController saleController;
//    private FileController fileController;
//    private JTable inventoryTable;
//    private JTable cartTable;
//    private DefaultTableModel inventoryModel;
//    private DefaultTableModel cartModel;
//    private AppFrame appFrame;
//    private Label cartTotalLabel;
//
//    public PointOfSalePanel(AppFrame appFrame, FileController fileController, InventoryModel inventoryModel) {
//        this.appFrame = appFrame;
//        this.fileController = fileController;
//        this.saleController = new SaleController(inventoryModel, fileController);
//        initializeUI();
//        loadProducts();
//    }
//
//    private void initializeUI() {
//        setLayout(new BorderLayout());
//
//        // 初始化库存商品表格模型
//        String[] inventoryColumns = {"ID", "Name", "Description", "Price", "Stock"};
//        inventoryModel = new DefaultTableModel(inventoryColumns, 0);
//        inventoryTable = new JTable(inventoryModel);
//
//        // 初始化购物车表格模型
//        String[] cartColumns = {"ID", "Name", "Description", "Price", "Quantity"};
//        cartModel = new DefaultTableModel(cartColumns, 0);
//        cartTable = new JTable(cartModel);
//
//        // 添加滚动面板
//        JScrollPane inventoryScrollPane = new JScrollPane(inventoryTable);
//        JScrollPane cartScrollPane = new JScrollPane(cartTable);
//        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inventoryScrollPane, cartScrollPane);
//        add(splitPane, BorderLayout.CENTER);
//
//        // 添加按钮
//        JButton addButton = new JButton("Add to Cart");
//        addButton.addActionListener(e -> addToCart());
//        JButton checkoutButton = new JButton("Check Out");
//        checkoutButton.addActionListener(e -> checkout());
//        JButton returnButton = new JButton("Return to Welcome Screen");
//        returnButton.addActionListener(e -> returnToWelcomeScreen());
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.add(addButton);
//        buttonPanel.add(checkoutButton);
//        buttonPanel.add(returnButton);
//        add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//
//    private void loadProducts() {
//        inventoryModel.setRowCount(0); // 清除现有数据
//        List<Product> products = fileController.loadData();
//        for (Product product : products) {
//            if (product.getStockQuantity() > 0) {
//                inventoryModel.addRow(new Object[]{product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStockQuantity()});
//            }
//        }
//    }
//
//
//    // 添加到购物车的方法现在看起来像这样
//    private void addToCart() {
//        int selectedRow = inventoryTable.getSelectedRow();
//        if (selectedRow != -1) {
//            Object id = inventoryModel.getValueAt(selectedRow, 0);
//            Object name = inventoryModel.getValueAt(selectedRow, 1);
//            Object description = inventoryModel.getValueAt(selectedRow, 2);
//            Object price = inventoryModel.getValueAt(selectedRow, 3);
//            Object stock = inventoryModel.getValueAt(selectedRow, 4);
//
//            // 更新库存数量
//            int newStock = Integer.parseInt(stock.toString()) - 1;
//            inventoryModel.setValueAt(newStock, selectedRow, 4);
//
//            // 如果库存变为0，从表格中移除
//            if (newStock <= 0) {
//                inventoryModel.removeRow(selectedRow);
//            }
//
//            // 添加到购物车表格
//            cartModel.addRow(new Object[]{id, name, description, price, 1});
//        }
//    }
//
//    private void refreshInventoryAndCartDisplay() {
//        // 清空并重新填充库存表格
//        DefaultTableModel inventoryTableModel = (DefaultTableModel) inventoryTable.getModel();
//        inventoryTableModel.setRowCount(0);
//        for (Product product : saleController.getInventoryModel().getProducts()) {
//            if (product.getStockQuantity() > 0) {
//                inventoryTableModel.addRow(new Object[]{product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStockQuantity()});
//            }
//        }
//
//        // 清空并重新填充购物车表格
//        DefaultTableModel cartTableModel = (DefaultTableModel) cartTable.getModel();
//        cartTableModel.setRowCount(0);
//        for (Map.Entry<Product, Integer> entry : saleController.getCartModel().getCartItems().entrySet()) {
//            Product product = entry.getKey();
//            Integer quantity = entry.getValue();
//            cartTableModel.addRow(new Object[]{product.getId(), product.getName(), product.getDescription(), product.getPrice(), quantity});
//        }
//
//        // 更新购物车总成本显示
//        // 假设有个JLabel cartTotalLabel用于展示总成本
//        cartTotalLabel.setText(String.format("Total: $%.2f", saleController.getCartTotalCost()));
//    }
//
//
//
//    // 结账的方法
//    private void checkout() {
//        // 此处简化处理，实际操作应更新库存，并处理结账逻辑
//        JOptionPane.showMessageDialog(this, "Checkout completed.");
//        cartModel.setRowCount(0); // 清空购物车
//        loadProducts(); // 重新加载产品以更新库存显示
//    }
//
//
//    private void returnToWelcomeScreen() {
//        appFrame.showWelcomeScreen();
//    }
//}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class PointOfSalePanel extends JPanel {
    private SaleController saleController;
    private JTable inventoryTable;
    private JTable cartTable;
    private DefaultTableModel inventoryModel;
    private DefaultTableModel cartModel;
    private JLabel cartTotalLabel;
    private AppFrame appFrame;

    public PointOfSalePanel(AppFrame appFrame, FileController fileController, InventoryModel inventoryModel) {
        this.appFrame = appFrame;
        this.saleController = new SaleController(inventoryModel);
        initializeUI();
        refreshInventoryAndCartDisplay();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // 初始化库存商品表格
        inventoryModel = new DefaultTableModel(new Object[]{"ID", "Name", "Description", "Price", "Stock"}, 0);
        inventoryTable = new JTable(inventoryModel);
        add(new JScrollPane(inventoryTable), BorderLayout.WEST);

        // 初始化购物车表格
        cartModel = new DefaultTableModel(new Object[]{"Name", "Quantity", "Price"}, 0);
        cartTable = new JTable(cartModel);
        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        // 购物车总计标签
        cartTotalLabel = new JLabel("Total: $0.00");
        add(cartTotalLabel, BorderLayout.NORTH);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(e -> addToCart());
        JButton removeButton = new JButton("Remove from Cart");
        removeButton.addActionListener(e -> removeFromCart());
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> checkout());
        JButton returnButton = new JButton("Return to Welcome Screen");
        returnButton.addActionListener(e -> appFrame.showWelcomeScreen());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addToCart() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Product product = saleController.getInventoryModel().getProducts().get(selectedRow);
                saleController.addToCart(product);
                refreshInventoryAndCartDisplay();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error adding product to cart.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeFromCart() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                String productName = (String) cartModel.getValueAt(selectedRow, 0);
                Product product = saleController.getInventoryModel().getProducts().stream()
                        .filter(p -> p.getName().equals(productName))
                        .findFirst().orElse(null);
                if (product != null) {
                    saleController.removeFromCart(product);
                    refreshInventoryAndCartDisplay();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error removing product from cart.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String generateReceiptContent() {
        StringBuilder receiptContent = new StringBuilder();
        receiptContent.append("Qty\tProduct\t\tPrice\tTotal\n");
        double totalCost = 0;
        for (Map.Entry<Product, Integer> entry : saleController.getCartModel().getCartItems().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            double price = product.getPrice();
            double total = price * quantity;
            receiptContent.append(String.format("%d\t%s\t$%.2f\t$%.2f\n", quantity, product.getName(), price, total));
            totalCost += total;
        }
        receiptContent.append(String.format("Total: $%.2f", totalCost));
        return receiptContent.toString();
    }


    private void checkout() {
        // 检查购物车是否为空
        if (saleController.getCartModel().getCartItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty. Add some products before checkout.", "Empty Cart", JOptionPane.WARNING_MESSAGE);
            return; // 直接返回，不进行后续结账操作
        }
        String receiptContent = generateReceiptContent();

        // 现在显示收据内容给用户看，这里简化处理，实际可以用更复杂的UI展示
        JOptionPane.showMessageDialog(this, receiptContent, "Receipt Preview", JOptionPane.INFORMATION_MESSAGE);

        // 弹出文件选择器让用户选择保存收据的位置
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Receipt");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            // 确保文件路径以".txt"结尾
            if (!fileToSave.getPath().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getPath() + ".txt");
            }

            try {
                FileWriter writer = new FileWriter(fileToSave);
                writer.write(receiptContent);
                writer.close();
                JOptionPane.showMessageDialog(this, "Receipt saved to: " + fileToSave.getAbsolutePath());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving receipt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // 清空购物车并刷新界面
            saleController.getCartModel().getCartItems().clear();
            refreshInventoryAndCartDisplay();
        } else {
            // 用户取消了文件选择器，结账过程继续（收据已经生成并展示给用户）
            JOptionPane.showMessageDialog(this, "Receipt not saved. Checkout completed.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }




    private void refreshInventoryAndCartDisplay() {
        inventoryModel.setRowCount(0);
        for (Product product : saleController.getInventoryModel().getProducts()) {
            if (product.getStockQuantity() > 0) {
                inventoryModel.addRow(new Object[]{product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStockQuantity()});
            }
        }

        cartModel.setRowCount(0);
        for (Map.Entry<Product, Integer> entry : saleController.getCartModel().getCartItems().entrySet()) {
            cartModel.addRow(new Object[]{entry.getKey().getName(), entry.getValue(), entry.getKey().getPrice()});
        }

        cartTotalLabel.setText("Total: $" + String.format("%.2f", saleController.getCartTotalCost()));
    }
}
