package com.example.hibernate.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.*;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.Table;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hibernate_practice")
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

//    @Embedded
//    private Address address;

    public enum Status {
        ACTIVE,
        INACTIVE,
        DELETED
    }

    // constructors
    public User() {}

    public User(Long id, String uniqueCode, String name, String description, Status status, Date birthDate, int nonPersistentField, Date nextYearBirthday, String someUntrackedField) {
        this.id = id;
        this.uniqueCode = uniqueCode;
        this.name = name;
        this.description = description;
        this.status = status;
        this.birthDate = birthDate;
        this.nonPersistentField = nonPersistentField;
        this.nextYearBirthday = nextYearBirthday;
        this.someUntrackedField = someUntrackedField;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    @Column(name = "age")
    private int age;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public @NotEmpty(message = "Gender is required") String getGender() {
        return gender;
    }

    public void setGender(@NotEmpty(message = "Gender is required") String gender) {
        this.gender = gender;
    }

    public byte[] getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(byte[] profileImg) {
        this.profileImg = profileImg;
    }

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    public int getAge() {
        return age;
    }

    public void setAge(@Min(value = 18, message = "Age must be at least 18") @Max(value = 100, message = "Age must be less than or equal to 100") int age) {
        this.age = age;
    }
}
