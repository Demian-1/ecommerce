package com.ipn.mx.ecommerce.service.interfaces;

import com.ipn.mx.ecommerce.model.ShopOrder;
import java.util.List;

public interface ShopOrderService {
    ShopOrder saveShopOrder(ShopOrder shopOrder);
    List<ShopOrder> getAllShopOrders();
    ShopOrder getShopOrderById(int id);
    ShopOrder updateShopOrder(ShopOrder shopOrder);
    void deleteShopOrder(int id);
}
