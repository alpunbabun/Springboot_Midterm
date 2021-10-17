package com.example.Springboot.ITEMS;

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

    @GetMapping("item/get")
    public List<Item> getItems() {
        return itemService.getItems();
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

    @DeleteMapping(path = "{itemId}")
    public void deleteItem(
            @PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PutMapping(path = "{itemId}")
    public void updateItem(
            @PathVariable("itemId") Long itemId,
            @RequestParam(required = false) String item,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) int price) {
        itemService.updateItem(itemId, item, category, price);
    }
}
