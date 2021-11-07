package com.example.Springboot.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    private @Id @GeneratedValue long id;
    private @NotBlank String item_name;
    private @NotBlank String category;
    private @NotBlank int price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item= (Item) o;
        return
//              Objects.equals(email, customer.email) &&
                Objects.equals(item_name, item.item_name) &&
                        Objects.equals(category, item.category) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item_name, category, price);
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
