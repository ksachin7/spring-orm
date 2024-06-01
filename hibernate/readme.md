# Hibernate Practice
Hibernate a powerful object-relational mapping framework for Java applications. 
With this practice project, I learned how to map Java classes to database tables, establish relationships between entities, and utilize various Hibernate features such as entity lifecycle callbacks and data validation.

**Status**: In progress...

#### `hibernate.hbm2ddl.auto` 

It's a Hibernate configuration property that controls how Hibernate manages database schema changes.
- **Values**:
   - `none`: Hibernate does not perform any schema management operations.
   - `create`: Hibernate drops existing tables, creates new ones, and initializes the schema from entity mappings. Existing data may be lost.
   - `update`: Hibernate updates the schema based on the entity mappings, adding new tables or columns if necessary. Existing data is preserved.
   - `validate`: Hibernate validates the schema against entity mappings, but does not make any changes. It detects schema inconsistencies without modifying the database.
   - `create-drop`: Similar to `create`, but also drops the schema at the end of the session. Useful for development or testing purposes.
- **Considerations**:
   - Use with caution in production environments, especially with `create` or `create-drop`, as they may lead to data loss or unexpected schema changes.
   - `update` is often used during development to automatically synchronize the database schema with entity mappings.
   - `validate` is useful for detecting schema inconsistencies without altering the database.

### Explanation of the Hibernate annotations
- `@Entity` and @Table: Marks the class as an entity and maps it to the table example_entity.
- `@Id` and @GeneratedValue: Specifies the primary key and its generation strategy.
- `@NaturalId:` Indicates a natural identifier for the entity.
- `@NotNull`, `@Size`: Validates that name is not null and has a size between 2 and 100.
- `@Lob`: Specifies that description should be persisted as a large object like binary files, images, or large text fields.
- `@Enumerated`: Maps the status enum to a string in the database.
- `@Temporal`: Specifies that birthDate should be persisted as a date.
- `@Transient`: Indicates that nonPersistentField should not be persisted.
- `@Formula`: Defines a derived property nextYearBirthday.
- `@OptimisticLock`: Excludes someUntrackedField from optimistic locking checks.
[//]: # ( Lifecycle callbacks)
- `@PreUpdate` annotated method updates the updatedAt field before the entity is updated.
- `@PostPersist` annotated method prints a message after the entity has been persisted.
- `@PostUpdate` annotated method prints a message after the entity has been updated.
- `@PreRemove` annotated method prints a message before the entity is removed.
- `@PostRemove` annotated method prints a message after the entity has been removed.
- `@PostLoad` annotated method prints a message after the entity has been loaded.

### Decision Criteria for DAOImpl:

Whether to use DAO implementations or not depends on your project requirements and preferences. Here are some considerations:
- Complexity: If your data access logic is straightforward, using DAO interfaces directly within services might suffice. However, for complex data access logic, implementing separate DAO classes can improve maintainability and separation of concerns.
- Flexibility: Using DAO implementations gives you more flexibility in terms of customization and optimization of data access logic.
- Testing: Separating DAO implementations can make unit testing easier, as you can mock DAO interfaces in your service tests.
- Code Organization: Using separate DAO implementations can lead to a cleaner code organization, especially in larger projects.

### Entity Mappings
- In the User entity, a `@OneToMany` annotation is utilized to establish a one-to-many relationship with the Product entity. This annotation designates the User entity as the owning side of the relationship, and it is mapped by the user attribute in the Product entity.
- Conversely, within the Product entity, a `@ManyToOne` annotation is employed to establish a many-to-one relationship with the User entity. This annotation specifies the foreign key column (user_id) in the Product table that references the primary key of the User table.
- Moreover, to facilitate wishlist functionality, an additional `@OneToMany` annotation is included in the User entity. This establishes a one-to-many relationship with the Wishlist entity, enabling each user to possess multiple wishlists. Correspondingly, within the Wishlist entity, a `@ManyToOne` annotation is utilized to establish a many-to-one relationship with the User entity, indicating that each wishlist belongs to a single user.

<img src="er-diagram.png" alt="ER-diagram" width="1080" height="auto" />

### Fetching Strategies

**Eager Fetching:**

- Eager fetching means that related entities are loaded immediately with the main entity.
- This can lead to performance issues if you have large or complex relationships.

**Lazy Fetching:**

- Lazy fetching means that related entities are loaded only when they are accessed.

### Lifecycle Annotations

In JPA (Java Persistence API), lifecycle methods are used to hook into the lifecycle events of an entity. These methods can be annotated with specific annotations to indicate when they should be called during the lifecycle of the entity. Here are the main lifecycle annotations and their corresponding methods:
Certainly!

I used these JPA lifecycle annotations in the `Product` entity, to automate operations at different stages:

1. **`@PrePersist`**: Sets timestamps before the entity is first persisted.

2. **`@PreUpdate`**: Updates timestamps before any updates to the entity.

3. **`@PostLoad`**: Executes after the entity is loaded from the database, enabling post-loading operations.

4. **`@PreRemove`**: Performs operations before the entity is removed from the database.

5. **`@PostRemove`**: Executes after the entity has been removed, facilitating post-removal tasks.

These annotations streamline entity management, ensuring consistency and enabling automated actions without explicit method calls.


### Explanation of Caching Annotations:

Hibernate provides caching annotations that allow you to cache entity data for improved performance. Here are some commonly used caching annotations:

- `@Cacheable`: Indicates that entities should be cached. This annotation is applied at the entity level.
- `@Cache`: Specifies caching configuration options such as the cache region and expiration policy. This annotation is applied at the entity level.
- `@CacheConcurrencyStrategy`: Specifies the concurrency strategy for caching. This annotation is applied at the entity level and defines how the entity is cached and managed in a multi-threaded environment.

### Integration of Caching into the Project:

1. **Add Caching Annotations to Entities**:
   Apply `@Cacheable` and `@Cache` annotations to the entities you want to cache.

2. **Configure Caching Provider**:
   Configure the caching provider in your Hibernate configuration file (e.g., `hibernate.cfg.xml`).

    ```xml
      <property name="hibernate.cache.use_second_level_cache">true</property>
      <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
    ```

3. **Enable Caching**:
   Ensure that caching is enabled in your Hibernate configuration.

4. **Additional Considerations**:
    - Adjust caching settings such as expiration policies and cache regions based on your application's requirements.
    - Monitor cache usage and performance to optimize caching configurations.
