package com.example.hibernate.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "products")
    private List<User> users;

//    @ManyToMany(mappedBy = "products")
//    private List<Wishlist> wishlists;
    @ManyToMany
    @JoinTable(name = "wishlist_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "wishlist_id"))
    private List<Wishlist> wishlists;

    @Column(length= 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Product() {
        // Initialize createdAt and updatedAt with current date/time
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Product(Long id, List<User> users, List<Wishlist> wishlists, String name, double price, Date createdAt, Date updatedAt) {
        this.id = id;
        this.users = users;
        this.wishlists = wishlists;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Wishlist> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<Wishlist> wishlists) {
        this.wishlists = wishlists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
