package ictgradschool.industry.final_project.Models;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryModel {
    private List<Product> products;

    public InventoryModel() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) throws Exception {
        // 检查标识符是否唯一
        for (Product p : products) {
            if (p.getId().equals(product.getId())) {
                throw new Exception("Product ID must be unique.");
            }
        }
        products.add(product);

    }

    public void removeProduct(String productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    public void editProduct(Product updatedProduct, Product newProductData) throws Exception {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(updatedProduct.getId())) {
                Product productToUpdate = products.get(i);
                productToUpdate.setName(newProductData.getName());
                productToUpdate.setDescription(newProductData.getDescription());
                productToUpdate.setPrice(newProductData.getPrice());
                productToUpdate.setStockQuantity(newProductData.getStockQuantity());
                return;
            }
        }
        throw new Exception("Product not found.");
    }

    public List<Product> searchProducts(String keyword) {
        return products.stream()
                .filter(product -> product.getId().contains(keyword) ||
                        product.getName().contains(keyword) ||
                        product.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Product> filterByStock(StockFilter filter) {
        return products.stream()
                .filter(product -> {
                    switch (filter) {
                        case IN_STOCK:
                            return product.getStockQuantity() > 0;
                        case OUT_OF_STOCK:
                            return product.getStockQuantity() == 0;
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());
    }


    public void sortProducts(Comparator<Product> comparator) {
        products.sort(comparator);
    }

    public void updateProduct(Product newProductData) throws Exception {
        // 查找与提供的产品ID匹配的产品
        for (Product product : products) {
            if (product.getId().equals(newProductData.getId())) {
                // 找到匹配的产品，更新信息
                product.setName(newProductData.getName());
                product.setDescription(newProductData.getDescription());
                product.setPrice(newProductData.getPrice());
                product.setStockQuantity(newProductData.getStockQuantity());
                return; // 更新完成，退出方法
            }
        }
        // 如果没有找到匹配的产品，可以选择抛出异常
        throw new Exception("Product with ID " + newProductData.getId() + " not found.");
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // 定义库存过滤器枚举
    public enum StockFilter {
        ALL, IN_STOCK, OUT_OF_STOCK
    }
}
