package ictgradschool.industry.final_project.Controllers;

import ictgradschool.industry.final_project.Models.CartModel;
import ictgradschool.industry.final_project.Models.InventoryModel;
import ictgradschool.industry.final_project.Models.Product;

public class SaleController {
    private InventoryModel inventoryModel;
    private CartModel cartModel;

    public SaleController(InventoryModel inventoryModel) {
        this.inventoryModel = inventoryModel;
        this.cartModel = new CartModel();
    }

    public void addToCart(Product product) {
        // 减少库存
        int newStock = product.getStockQuantity() - 1;
        if (newStock >= 0) {
            product.setStockQuantity(newStock);
            try {
                inventoryModel.updateProduct(product);
                cartModel.addProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeFromCart(Product product) {
        cartModel.removeProduct(product);
        // 增加库存
        product.setStockQuantity(product.getStockQuantity() + 1);
        try {
            inventoryModel.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CartModel getCartModel() {
        return cartModel;
    }

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    public double getCartTotalCost() {
        return cartModel.getTotalCost();
    }
}
