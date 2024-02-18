package ictgradschool.industry.final_project.Models;

import ictgradschool.industry.final_project.Models.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class CartModel {
    private Map<Product, Integer> cartItems = new LinkedHashMap<>();

    public void addProduct(Product product) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + 1);
    }

    public void removeProduct(Product product) {
        Integer count = cartItems.get(product);
        if (count == null) {
            return;
        }
        if (count > 1) {
            cartItems.put(product, count - 1);
        } else {
            cartItems.remove(product);
        }
    }

    public Map<Product, Integer> getCartItems() {
        return new LinkedHashMap<>(cartItems);
    }

    public double getTotalCost() {
        double totalCost = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return totalCost;
    }
}
