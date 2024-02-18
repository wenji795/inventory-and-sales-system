package ictgradschool.industry.final_project.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStore {
    public List<Product> loadInventory(File file) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true; // 添加一个标志来检查是否是第一行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // 标记已经处理过第一行，后续的行不再跳过
                    continue; // 跳过第一行
                }
                String[] values = line.split(",");
                // 假设CSV文件的列顺序是：ID, Name, Description, Price, Quantity
                Product product = new Product(values[0], values[1], values[2],
                        Double.parseDouble(values[3]),
                        Integer.parseInt(values[4]));
                products.add(product);
            }
        }
        return products;
    }

    public void saveInventory(List<Product> products, File currentFile) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile))) {
            // 写入CSV文件头
            bw.write("ID,Name,Description,Price,Quantity");
            bw.newLine();

            // 遍历产品列表，每个产品转换为CSV行
            for (Product product : products) {
                String line = String.join(",",
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        String.valueOf(product.getPrice()),
                        String.valueOf(product.getStockQuantity()));
                bw.write(line);
                bw.newLine();
            }
        }
    }
}
