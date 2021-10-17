package com.example.Springboot.ITEMS;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "item")
public class Item {
    private @Id @GeneratedValue long id;
    private @NotBlank String item_name;
    private @NotBlank String category;
    private @NotBlank int price;

    public Item() {
    }

    public Item(@NotBlank String item_name,
                @NotBlank String category,
                @NotBlank int price) {
        this.item_name = item_name;
        this.category = category;
        this.price = price;
    }

    public Item(@NotBlank String item_name,
                @NotBlank String category,
                @NotBlank Integer price) {
        this.item_name = item_name;
        this.category = category;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", item_name='" + item_name + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
