package com.faire.vanhackTest;

import com.faire.vanhackTest.dataObjects.Product;
import com.faire.vanhackTest.dataObjects.ProductOption;
import com.faire.vanhackTest.faireAPI.FaireAPIInt;
import com.faire.vanhackTest.faireAPI.FaireAPIRest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

    private Map<String, ProductOption> idProductOptionMap;
    private Map<ProductOption, Integer> quantities;
    private String brandId;
    private FaireAPIInt faireAPI;
    private List<ProductOption> productsConsumed;

    private Inventory(String brandId, FaireAPIInt faireAPI) {
        quantities = new HashMap<ProductOption, Integer>();
        idProductOptionMap = new HashMap<String, ProductOption>();
        productsConsumed = new ArrayList<>();
        this.brandId = brandId;
        this.faireAPI = faireAPI;
    }

    /**
     * Static factory to get an instance of the class
     * @return An instance of the class
     */
    public static Inventory getInstance(String brandId, FaireAPIInt faireAPI) {
        return new Inventory(brandId, faireAPI);
    }

    /**
     * Method to add information of a product option
     * @param productOption The product option to be added
     * @param availableQuantity The ordered quantity og the product option
     */
    public void addProductOptionInfo(ProductOption productOption, int availableQuantity) {
        quantities.put(productOption, availableQuantity);
        idProductOptionMap.put(productOption.id, productOption);
    }


    public void refreshInventory() {
        quantities.clear();
        int pageCount = 1;

        List<Product> pageData = faireAPI.getAllProducts(pageCount);

        while (pageData != null && !pageData.isEmpty()) {
            System.out.println("page ="+pageCount);

            for (Product itProd : pageData) {
                if (!itProd.brand_id.equals(this.brandId)) {
                    continue;
                }

                System.out.println(itProd.id + " " + itProd.name);
                for (ProductOption itProdItem : itProd.options) {
                    System.out.println("[" + itProdItem.sku + "] - " + itProdItem.product_id + " - " + itProdItem.available_quantity);
                    addProductOptionInfo(itProdItem, itProdItem.available_quantity);
                }
                System.out.println("---------------------------------");
            }
            pageCount++;
            pageData = faireAPI.getAllProducts(pageCount);
        }
    }

    public boolean checkItemInvetory(String productOptionId)
    {
        return idProductOptionMap.containsKey(productOptionId);
    }


    public int getProductOptionAvailableQuantity(String productOptionId) {
        ProductOption po = idProductOptionMap.get(productOptionId);
        if(po == null) {
            return 0;
        }
        return quantities.containsKey(po)? quantities.get(po) : 0;
    }


    public boolean consumeProductOption(String productOptionId, int quantity) {
        ProductOption po = idProductOptionMap.get(productOptionId);
        if (po == null || quantities.get(po) < quantity) {
            return false;
        }
        int currentQuantity = quantities.get(po);
        quantities.remove(po);
        quantities.put(po, currentQuantity - quantity);
        productsConsumed.add(po);
        return true;
    }

    void print() {
        System.out.println("Quantidades " + idProductOptionMap.size());
    }

    public static void main(String[] args) {
        Inventory inv = Inventory.getInstance("b_d2481b88", new FaireAPIRest());
        inv.refreshInventory();
        System.out.println(inv.quantities.size());
    }
}
