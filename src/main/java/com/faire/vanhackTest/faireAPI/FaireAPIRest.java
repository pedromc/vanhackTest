package com.faire.vanhackTest.faireAPI;

import com.faire.vanhackTest.Constants;
import com.faire.vanhackTest.dataObjects.Order;
import com.faire.vanhackTest.dataObjects.Product;
import com.faire.vanhackTest.dataObjects.ProductOptionUpdateValues;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FaireAPIRest implements FaireAPIInt {

    private class ProductsInfo {
        int page;
        int limit;
        List<Product> products;
    }

    private class OrdersInfo {
        int page;
        int limit;
        List<Order> orders;
    }

    private class Inventory {
        List<ProductOptionUpdateValues> inventories;
    }

    @Override
    public List<Product> getAllProducts(int page) {
        try {
            URL url = new URL(Constants.URL_PRODUCTS + "page=" + page);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty(Constants.HTTP_HEADER_ACCESS, Constants.HTTP_HEADER_ACCESS_KEY);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            //InputStreamReader reader = new InputStreamReader(con.getInputStream());
            InputStreamReader reader = new InputStreamReader(con.getInputStream());
            FaireAPIRest.ProductsInfo info = new Gson().fromJson(reader, FaireAPIRest.ProductsInfo.class);
            return info.products;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders(int page) {
        try {
            URL url = new URL(Constants.URL_ORDERS + "page=" + page);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty(Constants.HTTP_HEADER_ACCESS, Constants.HTTP_HEADER_ACCESS_KEY);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            //InputStreamReader reader = new InputStreamReader(con.getInputStream());
            InputStreamReader reader = new InputStreamReader(con.getInputStream());
            FaireAPIRest.OrdersInfo info = new Gson().fromJson(reader, FaireAPIRest.OrdersInfo.class);

            return info.orders;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void backorderingItems(String id) {

    }

    @Override
    public void acceptOrder(String id) {

    }

    @Override
    public void updateProdutOptions(List<ProductOptionUpdateValues> values) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        Inventory inv = new Inventory();
        inv.inventories = values;

        HttpPatch httpPatch = null;
        try {
            httpPatch = new HttpPatch(new URI(Constants.URL_INVENTORY));
            httpPatch.setHeader("Content-type", "application/json");
            httpPatch.setHeader(Constants.HTTP_HEADER_ACCESS, Constants.HTTP_HEADER_ACCESS_KEY);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            Gson gson = gsonBuilder.create();
            String json = gson.toJson(inv);

//            System.out.println("Jason to update: " + json);
//            final StringEntity stringData = new StringEntity(json);
//
//            httpPatch.setEntity(stringData);
//
//            System.out.println("alo " + json);
//            CloseableHttpResponse response = httpClient.execute(httpPatch);
//            System.out.println("mundo " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<ProductOptionUpdateValues> v = new ArrayList<>();
        ProductOptionUpdateValues pu = new ProductOptionUpdateValues();
        pu.sku = "alo";
        pu.current_quantity = 5;
        pu.discontinued = false;
        pu.backordered_until = null;
        v.add(pu);

        pu = new ProductOptionUpdateValues();
        pu.sku = "mundo";
        pu.current_quantity = 2;
        pu.discontinued = false;
        pu.backordered_until = null;
        v.add(pu);

        pu = new ProductOptionUpdateValues();
        pu.sku = "aloMundo";
        pu.current_quantity = 10;
        pu.discontinued = false;
        pu.backordered_until = null;
        v.add(pu);

        FaireAPIRest t = new FaireAPIRest();
        t.updateProdutOptions(v);
    }
}
