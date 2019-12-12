package com.geekbrains.marketdemoa.entites;

import com.geekbrains.marketdemoa.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {
    private List<Item> items;
    private ItemService itemService;

    @Autowired
    public void setProductService(ItemService itemService) {
        this.itemService = itemService;
    }

    public List<Item> getProducts() {
        return items;
    }

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addItemToCartById(Long id) {
        Item item = itemService.findById(id);
        items.add(item);
    }


    public void delItemToCartById(Long id) {
        Item item = itemService.findById(id);

        for (int i = 0; i < items.size(); i++) {
            Item oldName = items.get(i);
            if (oldName.getId().equals(item.getId())) {
                items.remove(i);
            }
        }

    }

}