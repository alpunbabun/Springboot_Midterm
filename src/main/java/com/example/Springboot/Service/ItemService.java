package com.example.Springboot.Service;

import com.example.Springboot.Entity.Item;
import com.example.Springboot.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        boolean exists = itemRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("item with id " + id + " does not exists");
        }
        return item;
    }

    public void addNewItem(Item item) {
        Optional<Item> item_nameOptional = itemRepository.findItemById(item.getId());
        if (item_nameOptional.isPresent()) {
            throw new IllegalStateException("Item Exists");
        }
        itemRepository.save(item);
    }

    public void deleteItemById(Long id) {
        boolean exists = itemRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("item with id " + id + " does not exists");
        }
        itemRepository.deleteById(id);
    }

    public void updateItem(Item item, Long id) {
        itemRepository.save(item);
    }
}