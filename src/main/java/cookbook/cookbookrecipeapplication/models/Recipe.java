package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;


import java.util.Date;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long spoonacular_id;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, columnDefinition="TEXT")
    private String summary;

}
