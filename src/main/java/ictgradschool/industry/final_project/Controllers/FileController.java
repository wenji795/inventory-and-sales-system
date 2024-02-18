package ictgradschool.industry.final_project.Controllers;

import ictgradschool.industry.final_project.Models.FileStore;
import ictgradschool.industry.final_project.Models.InventoryModel;
import ictgradschool.industry.final_project.Models.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileController {
    private FileStore fileStore; // 正确地引用FileStore
    private InventoryModel inventoryModel; // 正确地引用InventoryModel
    private File currentFile; // 当前操作的文件

    public FileController(FileStore fileStore, InventoryModel inventoryModel) {
        this.fileStore = fileStore;
        this.inventoryModel = inventoryModel;
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
        loadData();
    }

    public List<Product> loadData() {
        try {
            if (currentFile != null) {
                List<Product> products = fileStore.loadInventory(currentFile);
                if (products == null) {
                    products = new ArrayList<>(); // 确保products不为null
                }
                inventoryModel.setProducts(products); // 应确保InventoryModel类有此方法
                return products;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // 如果出现异常或文件为空，返回空列表而不是null
    }
    public void saveData() throws IOException {
        // 确保这里实现了将inventoryModel的产品列表保存到文件的逻辑
        if (currentFile != null) {
            List<Product> products = inventoryModel.getProducts();
            fileStore.saveInventory(products, currentFile);
        }
    }

    public void addProduct(Product product) {
        try {
            inventoryModel.addProduct(product); // 添加产品到模型
            saveData(); // 保存更新到文件
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeProduct(String productId) {
        inventoryModel.removeProduct(productId); // 从模型中删除产品
        try {
            saveData(); // 保存更新到文件
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editProduct(Product updatedProduct, Product newProductData) {
        try {
            inventoryModel.editProduct(updatedProduct, newProductData);
            saveData(); // 保存更新到文件
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e) { // 捕获editProduct可能抛出的其他异常
            e.printStackTrace(); // 处理异常，例如打印堆栈跟踪
            // 可以在这里添加更多的错误处理逻辑
        }
    }
}
