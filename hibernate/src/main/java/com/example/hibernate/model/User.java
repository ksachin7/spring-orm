package com.example.hibernate.model;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "userCache")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @Column(name = "unique_code", nullable = false, unique = true)
    @NotNull
    private String uniqueCode;

    @Column(name = "name")
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotEmpty(message = "Gender is required")
    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    @Lob
    private String description;

    @Lob
    @Column(name = "profile_img")
    private byte[] profileImg;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Transient
    private int nonPersistentField;

    @Formula("birth_date + interval '1 year'")
    private Date nextYearBirthday;

    @OptimisticLock(excluded = true)
    private String someUntrackedField;

    @Embedded
    private Address address;

    public enum Status {
        ACTIVE,
        INACTIVE,
        DELETED
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "cart_id")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserOrders> orders= new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    // constructors, getters, and setters
    public User() {}

    public User(Long id, String uniqueCode, String name, String gender, String description, byte[] profileImg, Status status, Date birthDate, int nonPersistentField, Date nextYearBirthday, String someUntrackedField, Address address) {
        this.id = id;
        this.uniqueCode = uniqueCode;
        this.name = name;
        this.gender = gender;
        this.description = description;
        this.profileImg = profileImg;
        this.status = status;
        this.birthDate = birthDate;
        this.nonPersistentField = nonPersistentField;
        this.nextYearBirthday = nextYearBirthday;
        this.someUntrackedField = someUntrackedField;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<UserOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrders> orders) {
        this.orders = orders;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public @NotNull String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(@NotNull String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public @NotNull @Size(min = 2, max = 100) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 2, max = 100) String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Gender is required") String getGender() {
        return gender;
    }

    public void setGender(@NotEmpty(message = "Gender is required") String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getNonPersistentField() {
        return nonPersistentField;
    }

    public void setNonPersistentField(int nonPersistentField) {
        this.nonPersistentField = nonPersistentField;
    }

    public Date getNextYearBirthday() {
        return nextYearBirthday;
    }

    public void setNextYearBirthday(Date nextYearBirthday) {
        this.nextYearBirthday = nextYearBirthday;
    }

    public String getSomeUntrackedField() {
        return someUntrackedField;
    }

    public void setSomeUntrackedField(String someUntrackedField) {
        this.someUntrackedField = someUntrackedField;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "\n, roles=" + roles +
                "\n, products=" + products +
                "\n, cart=" + cart +
                "\n, orders=" + orders +
                "\n, wishlist=" + wishlist +
                "\n, uniqueCode='" + uniqueCode + '\'' +
                "\n, name='" + name + '\'' +
                "\n, gender='" + gender + '\'' +
                "\n, description='" + description + '\'' +
                "\n, profileImg=" + Arrays.toString(profileImg) +
                "\n, status=" + status +
                "\n, birthDate=" + birthDate +
                "\n, nonPersistentField=" + nonPersistentField +
                "\n, nextYearBirthday=" + nextYearBirthday +
                "\n, someUntrackedField='" + someUntrackedField + '\'' +
                "\n, address=" + address +
                '}';
    }
}
