package ictgradschool.industry.final_project.Controllers;

import ictgradschool.industry.final_project.Models.InventoryModel;
import ictgradschool.industry.final_project.Models.Product;
import ictgradschool.industry.final_project.views.InventoryManager;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class InventoryController {
    private InventoryModel model;
    private InventoryManager view;
    private FileController fileController;

    public InventoryController(InventoryModel model, InventoryManager view, FileController fileController) {
        this.model = model;
        this.view = view;
        this.fileController = fileController;
    }


    public void loadProducts(File file) {
        fileController.setCurrentFile(file); // 确保已经有了 setCurrentFile 方法
        List<Product> products = fileController.loadData(); // 现在 loadData 不需要参数
        model.setProducts(products);
        // 更新视图等
    }


    // 添加更多方法来处理添加、删除和编辑产品
}