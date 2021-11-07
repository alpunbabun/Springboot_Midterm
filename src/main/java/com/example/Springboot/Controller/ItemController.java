package com.example.Springboot.Controller;

import com.example.Springboot.Entity.Item;
import com.example.Springboot.Service.ItemService;
import com.example.Springboot.ITEMS.Status;
import com.example.Springboot.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("items")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping(value = "/item/{id}")
    public Item getItem(@PathVariable("id") Long id) {
        return itemService.findById(id);
    }

    @PostMapping("item/add")
    public Status addNewItem(@Valid @RequestBody Item newItem) {
        List<Item> items = itemRepository.findAll();

        System.out.println("New customer: " + newItem.toString());

        for (Item item : items) {
            System.out.println("Registered customer: " + newItem.toString());

            if (item.equals(newItem)) {
                System.out.println("Customer Already exists!");
                return Status.ITEM_ALREADY_EXISTS;
            }
        }
        itemService.addNewItem(newItem);
        return Status.SUCCESSFULLY_ADDED;
    }

    @PutMapping(value = "/item/{id}")
    public Status updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
        this.itemService.updateItem(item, id);
//        return item;
        return Status.SUCCESSFULLY_UPDATED;
    }

    @DeleteMapping(value = "/item/{id}")
    public Status deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
        return Status.SUCCESSFULLY_DELETED;
    }

    @DeleteMapping("/item/all")
    public Status deleteItems() {
        itemRepository.deleteAll();
        return Status.SUCCESSFULLY_DELETED;
    }
}
