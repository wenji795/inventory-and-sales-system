package ictgradschool.industry.final_project.views;

import ictgradschool.industry.final_project.Controllers.FileController;
import ictgradschool.industry.final_project.Models.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//package ictgradschool.industry.final_project.views;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableRowSorter;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.util.Arrays;
//
//public class InventoryManager extends JPanel {
//    private JTable table;
//    private DefaultTableModel tableModel;
//    private AppFrame appFrame; // 需要引用AppFrame来进行界面切换
//    private TableRowSorter<DefaultTableModel> sorter;
//    private File _dataFile = null;
//    private File dataFile = null;
//
//    public InventoryManager(AppFrame appFrame) {
//        this.appFrame = appFrame;
//        setLayout(new BorderLayout());
//        // 创建一个新的面板用于放置按钮
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//
//
//        // 初始化表格模型
//        String[] columnNames = {"Identifier", "Name", "Description", "Price", "Stock"};
//        // 初始化表格模型，只做一次
//        tableModel = new DefaultTableModel(columnNames, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false; // 防止编辑
//            }
//        };
//        table = new JTable(tableModel);
//
//        // 初始化 TableRowSorter
//        sorter = new TableRowSorter<>(tableModel);
//        table.setRowSorter(sorter);
//
//        // 添加滚动条
//        JScrollPane scrollPane = new JScrollPane(table);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // 返回欢迎屏幕的按钮
//        JButton returnButton = new JButton("Return to Welcome Screen");
//        returnButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                appFrame.showWelcomeScreen();
//            }
//        });
//
//
//        // 添加产品的按钮
//        JButton addProductButton = new JButton("Add Product");
//        addProductButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addNewProduct();
//            }
//        });
//        buttonPanel.add(addProductButton);
//
//        // 添加删除产品的按钮
//        JButton deleteProductButton = new JButton("Delete Product");
//        deleteProductButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                deleteSelectedProduct();
//            }
//        });
//        buttonPanel.add(deleteProductButton);
//        buttonPanel.add(returnButton);
//        // 将按钮面板添加到 InventoryManager 的顶部
//        add(buttonPanel, BorderLayout.NORTH);
//
////        buttonPanel.add(returnButton, BorderLayout.SOUTH);
//
//        // 加载数据
////        loadData();
//
//        JTextField searchField = new JTextField(20);
//        JButton searchButton = new JButton("Search");
//
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String searchText = searchField.getText();
//                searchTable(searchText);
//            }
//        });
//
//        // 添加到界面
//        buttonPanel.add(searchField);
//        buttonPanel.add(searchButton);
//
//        //提供一个方法来清除搜索，恢复显示所有项
//        JButton clearSearchButton = new JButton("Clear Search");
//        clearSearchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                searchField.setText("");
//                searchTable("");
//            }
//        });
//
//        buttonPanel.add(clearSearchButton);
//
//        //库存筛选
//        String[] stockOptions = {"All Items", "In Stock", "Out of Stock"};
//        JComboBox<String> stockFilterCombo = new JComboBox<>(stockOptions);
//        stockFilterCombo.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                filterByStock(stockFilterCombo.getSelectedItem().toString());
//            }
//        });
//
//        buttonPanel.add(stockFilterCombo);
//
//        //排序功能
//        String[] sortableColumns = {"Identifier", "Name", "Description", "Price", "Stock"};
//        JComboBox<String> sortColumnCombo = new JComboBox<>(sortableColumns);
//        buttonPanel.add(new JLabel("Sort by:"));
//        buttonPanel.add(sortColumnCombo);
//
//        // 排序方向选择
//        String[] sortDirections = {"Ascending", "Descending"};
//        JComboBox<String> sortDirectionCombo = new JComboBox<>(sortDirections);
//        buttonPanel.add(new JLabel("Direction:"));
//        buttonPanel.add(sortDirectionCombo);
//
//        // 添加排序按钮和其事件监听器
//        JButton sortButton = new JButton("Sort");
//        buttonPanel.add(sortButton);
//        sortButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                applySorting(sortColumnCombo.getSelectedItem().toString(),
//                        sortDirectionCombo.getSelectedItem().toString());
//            }
//        });
//    }
//
//    // 应用排序的方法
//    private void applySorting(String sortByColumn, String direction) {
//        int columnIndex = 0; // 默认为第一列
//        // 根据选择的列名确定索引
//        switch (sortByColumn) {
//            case "Name":
//                columnIndex = 1;
//                break;
//            case "Description":
//                columnIndex = 2;
//                break;
//            case "Price":
//                columnIndex = 3;
//                break;
//            case "Stock":
//                columnIndex = 4;
//                break;
//        }
//
//        // 排序方向
//        boolean ascending = "Ascending".equals(direction);
//
//        // 为选定的列创建排序器
//        sorter.setSortKeys(Arrays.asList(new RowSorter.SortKey(columnIndex, ascending ? SortOrder.ASCENDING : SortOrder.DESCENDING)));
//        sorter.sort();
//    }
//
//
//
//    private void addNewProduct() {
//        // 创建对话框或面板来收集产品信息
//        JTextField idField = new JTextField(10);
//        JTextField nameField = new JTextField(20);
//        JTextField descField = new JTextField(30);
//        JTextField priceField = new JTextField(10);
//        JTextField stockField = new JTextField(10);
//
//        JPanel panel = new JPanel(new GridLayout(0, 2));
//        panel.add(new JLabel("Identifier:"));
//        panel.add(idField);
//        panel.add(new JLabel("Name:"));
//        panel.add(nameField);
//        panel.add(new JLabel("Description:"));
//        panel.add(descField);
//        panel.add(new JLabel("Price:"));
//        panel.add(priceField);
//        panel.add(new JLabel("Stock:"));
//        panel.add(stockField);
//
//        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Product",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (result == JOptionPane.OK_OPTION) {
//            try{
//                // 获取并验证数据
//                String id = idField.getText();
//                String name = nameField.getText();
//                String desc = descField.getText();
//                double price = Double.parseDouble(priceField.getText()); // 需要错误处理
//                int stock = Integer.parseInt(stockField.getText()); // 需要错误处理
//
//                // 验证标识符是否唯一，价格和库存是否为有效数值
//
//                // 添加产品到表格
//                tableModel.addRow(new Object[]{id, name, desc, price, stock});
//
//                // 添加产品到后端存储
//                saveData(); // 保存更改到文件
//            }catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(this, "Invalid number format for price or stock.");
//            }
//
//        }
//    }
//
//
//    //删除产品
//    private void deleteSelectedProduct() {
//        int selectedRow = table.getSelectedRow();
//        if (selectedRow >= 0) {
//            // 从表格模型中移除选中的行
//            tableModel.removeRow(selectedRow);
//
//            saveData(); // 保存更改到文件
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a product to delete.");
//        }
//    }
//
//    //读取
//    private void loadData() {
//
//        String line;
//        String delimiter = ","; // 定义分隔符
//
////        tableModel.removeRow();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(delimiter); // 分割每一行
//                for (int i = 0; i < values.length; i++) {
//                    values[i] = values[i].replace("\"", ""); // 去除引号
//                }
//                System.out.println(Arrays.toString(values));
//                tableModel.addRow(values); // 将行添加到表格模型
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //保存
//    private void saveData() {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
//            for (int i = 0; i < tableModel.getRowCount(); i++) {
//                for (int j = 0; j < tableModel.getColumnCount(); j++) {
//                    bw.write(tableModel.getValueAt(i, j).toString());
//                    if (j < tableModel.getColumnCount() - 1) {
//                        bw.write(","); // 分隔符
//                    }
//                }
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //实现搜索逻辑
//    private void searchTable(String searchText) {
//        if (searchText.length() == 0) {
//            sorter.setRowFilter(null);
//        } else {
//            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
//                @Override
//                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
//                    // 检查id列是否完全匹配搜索文本
//                    if (entry.getStringValue(0).equalsIgnoreCase(searchText)) {
//                        return true;
//                    }
//                    // 检查name列是否完全匹配搜索文本
//                    if (entry.getStringValue(1).equalsIgnoreCase(searchText)) {
//                        return true;
//                    }
//                    // 检查description列是否完全匹配搜索文本
//                    return entry.getStringValue(2).equalsIgnoreCase(searchText);
//                }
//            });
//        }
//    }
//
//
//
//    //过滤
//    private void filterByStock(String stockOption) {
//        if ("In Stock".equals(stockOption)) {
//            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
//                @Override
//                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
//                    int stock = 0;
//                    try {
//                        stock = Integer.parseInt(entry.getStringValue(4)); // 假设库存在第 5 列，索引为 4
//                    } catch (NumberFormatException e) {
//                        // 处理解析错误
//                    }
//                    return stock > 0;
//                }
//            });
//        } else if ("Out of Stock".equals(stockOption)) {
//            sorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
//                @Override
//                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
//                    int stock = 0;
//                    try {
//                        stock = Integer.parseInt(entry.getStringValue(4));
//                    } catch (NumberFormatException e) {
//                        // 处理解析错误
//                    }
//                    return stock == 0;
//                }
//            });
//        } else {
//            sorter.setRowFilter(null); // 显示所有项
//        }
//    }
//
//    // -----
//
//    public void updateFile(File file) {
//        _dataFile = file;
//    }
//
//    public void updateFile() {
//        if (dataFile == null || !dataFile.equals(_dataFile)) {
//            dataFile = _dataFile;
//            loadData();
//        }
//    }
//
//
//}
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;


public class InventoryManager extends JPanel {
    private AppFrame appFrame;
    private FileController fileController;
    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    public InventoryManager(AppFrame appFrame, FileController fileController) {
        this.appFrame = appFrame;
        this.fileController = fileController;

        initializeUI();
        loadInventory();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        // 创建表格模型
        String[] columnNames = {"ID", "Name", "Description", "Price", "Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // 创建滚动面板包裹表格
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 创建顶部面板用于搜索和过滤
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JButton clearSearchButton = new JButton("Clear Search");
        JComboBox<String> stockFilterCombo = new JComboBox<>(new String[]{"All Items", "In Stock", "Out of Stock"});
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(clearSearchButton);
        topPanel.add(stockFilterCombo);

        // 创建底部面板用于操作按钮
        JPanel bottomPanel = new JPanel();
        JButton addButton = new JButton("Add Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton editButton = new JButton("Edit Product");
        JButton returnButton = new JButton("Return to Welcome Screen");
        JComboBox<String> columnComboBox = new JComboBox<>(new String[]{"ID", "Name", "Description", "Price", "Quantity"});
        JComboBox<String> directionComboBox = new JComboBox<>(new String[]{"Ascending", "Descending"});
        JButton sortButton = new JButton("Sort");

        topPanel.add(new JLabel("Sort by:"));
        topPanel.add(columnComboBox);
        topPanel.add(new JLabel("Direction:"));
        topPanel.add(directionComboBox);
        topPanel.add(sortButton);

        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(editButton);
        bottomPanel.add(returnButton);

        // 添加面板到主面板
        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // 事件监听器
        addButton.addActionListener(this::addProduct);
        deleteButton.addActionListener(this::deleteProduct);
        editButton.addActionListener(this::editProduct);
        searchButton.addActionListener(e -> searchTable(searchField.getText()));
        clearSearchButton.addActionListener(e -> {
            searchField.setText("");
            searchTable("");
        });
        stockFilterCombo.addActionListener(e -> filterByStock((String) stockFilterCombo.getSelectedItem()));
        returnButton.addActionListener(e -> returnToWelcomeScreen());
        sortButton.addActionListener(e -> {
            int selectedColumnIndex = columnComboBox.getSelectedIndex(); // 获取选中的列索引
            SortOrder sortOrder = directionComboBox.getSelectedItem().equals("Ascending") ? SortOrder.ASCENDING : SortOrder.DESCENDING;

            // 清除之前的排序键，设置新的排序键
            sorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(selectedColumnIndex, sortOrder)));
        });



    }

    private void loadInventory() {
        List<Product> products = fileController.loadData();
        for (Product product : products) {
            Vector<Object> row = new Vector<>();
            row.add(product.getId());
            row.add(product.getName());
            row.add(product.getDescription());
            row.add(product.getPrice());
            row.add(product.getStockQuantity());
            tableModel.addRow(row);
        }
    }

    private void addProduct(ActionEvent event) {
        // 弹出对话框获取新产品信息。这里仅演示，实际操作应收集用户输入。
        String id = JOptionPane.showInputDialog("Enter Product ID:");
        String name = JOptionPane.showInputDialog("Enter Product Name:");
        String description = JOptionPane.showInputDialog("Enter Description:");
        String priceStr = JOptionPane.showInputDialog("Enter Price:");
        String quantityStr = JOptionPane.showInputDialog("Enter Quantity:");

        try {
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);

            Product newProduct = new Product(id, name, description, price, quantity);
            fileController.addProduct(newProduct); // 假设FileController有一个添加产品的方法。
            tableModel.addRow(new Object[]{id, name, description, price, quantity});
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid format for price or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProduct(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String productId = tableModel.getValueAt(selectedRow, 0).toString();
            fileController.removeProduct(productId); // 假设FileController有一个删除产品的方法。
            tableModel.removeRow(selectedRow);
        }
    }

    private void editProduct(ActionEvent event) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // 提取当前选中行的产品信息
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            String name = (String) tableModel.getValueAt(selectedRow, 1);
            String description = (String) tableModel.getValueAt(selectedRow, 2);
            double price = (Double) tableModel.getValueAt(selectedRow, 3);
            int quantity = (Integer) tableModel.getValueAt(selectedRow, 4);

            // 弹出对话框让用户编辑产品信息
            String newName = JOptionPane.showInputDialog("Edit Product Name:", name);
            String newDescription = JOptionPane.showInputDialog("Edit Description:", description);
            String newPriceStr = JOptionPane.showInputDialog("Edit Price:", price);
            String newQuantityStr = JOptionPane.showInputDialog("Edit Quantity:", quantity);

            try {
                double newPrice = Double.parseDouble(newPriceStr);
                int newQuantity = Integer.parseInt(newQuantityStr);

                Product newProductData = new Product(id, newName, newDescription, newPrice, newQuantity);
                fileController.editProduct(new Product(id, name, description, price, quantity), newProductData); // 更新产品信息

                // 更新表格显示
                tableModel.setValueAt(newName, selectedRow, 1);
                tableModel.setValueAt(newDescription, selectedRow, 2);
                tableModel.setValueAt(newPrice, selectedRow, 3);
                tableModel.setValueAt(newQuantity, selectedRow, 4);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid format for price or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No product selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchTable(String searchText) {
        RowFilter<DefaultTableModel, Object> rf = null;
        // 如果搜索文本不为空，则创建一个RowFilter来过滤表格
        if (searchText.length() > 0) {
            try {
                // 使用正则表达式来匹配用户输入的文本（忽略大小写）
                rf = RowFilter.regexFilter("(?i)" + searchText, 0, 1, 2);
            } catch (java.util.regex.PatternSyntaxException e) {
                return;
            }
        }
        ((TableRowSorter<DefaultTableModel>) table.getRowSorter()).setRowFilter(rf);
    }

//    private void clearSearch() {
//        searchTable(""); // 或者直接设置 RowFilter 为 null
//    }

    private void filterByStock(String stockOption) {
        RowFilter<DefaultTableModel, Object> rf = null;
        // 根据选择的库存选项创建一个RowFilter
        if ("In Stock".equals(stockOption)) {
            rf = RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, 0, 4); // 假设库存数量在第5列
        } else if ("Out of Stock".equals(stockOption)) {
            rf = RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, 0, 4); // 假设库存数量在第5列
        }
        // 设置过滤器
        ((TableRowSorter<DefaultTableModel>) table.getRowSorter()).setRowFilter(rf);
    }

    private void applySorting(int columnIndex, SortOrder order) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(columnIndex, order));
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    private void returnToWelcomeScreen() {
        // 假设appFrame是您的主窗体实例，showWelcomeScreen是切换到欢迎屏幕的方法
        appFrame.showWelcomeScreen();
    }








}
