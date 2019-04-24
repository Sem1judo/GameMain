//package services;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class Services {
//
//    private ProductDao productDao = new ProductDao();
//
//    public List<Product> getProductList() {
//        return productDao.getAll();
//    }
//
//    public void addNewProduct(String productName, int quantityProduct, double price, LocalDateTime expiredDate) {
//        Product product = new Product();
//        product.setProductName(productName);
//        product.setQuantityProduct(quantityProduct);
//        product.setPrice(price);
//        product.setExpiredDate(expiredDate);
//        productDao.create(product);
//    }
//
//    public void deleteProduct(int id) {
//
//        productDao.delete(id);
//    }
//
//    public Product getProduct(int id) {
//        return productDao.read(id);
//    }
//
//    public void updateProduct(int id, String productName, int quantityProduct, double price, int expiredDate) {
//        Product product = productDao.read(id);
//        product.setProductName(productName);
//        product.setQuantityProduct(quantityProduct);
//        product.setPrice(price);
//        product.setExpired(expiredDate);
//        productDao.update(product);
//    }
//}
