package com.example.Springboot.ITEMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public void addNewItem(Item item) {
        Optional<Item> item_nameOptional = itemRepository.findItemById(item.getId());
        if (item_nameOptional.isPresent()) {
            throw new IllegalStateException("Item Exists");
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long itemId) {
        boolean exists = itemRepository.existsById(itemId);
        if (!exists) {
            throw new IllegalStateException("item with id" + itemId + "does not exists");
        }
        itemRepository.deleteById(itemId);
    }

    @Transactional
    public void updateItem(Long itemId,
                           String item_name,
                           String category,
                           int price) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalStateException(
                        "item with id" + itemId + "does not exist"
                ));

        if (item_name != null &&
                item_name.length() > 0 &&
                !Objects.equals(item.getId(), item)) {
            item.setItem_name(item_name);
        }

        if (item_name != null &&
                item_name.length() > 0 &&
                !Objects.equals(item.getItem_name(), item_name)) {
            Optional<Item> itemOptional = itemRepository
                    .findItemById(itemId);
            if (itemOptional.isPresent()) {
                throw new IllegalStateException("Email taken");
            }
            item.setItem_name(item_name);
        }
    }
}