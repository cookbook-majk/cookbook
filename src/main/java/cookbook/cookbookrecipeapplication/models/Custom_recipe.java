package cookbook.cookbookrecipeapplication.models;


import jakarta.persistence.*;

@Entity
@Table(name = "custom_recipes")
public class Custom_recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 250)
    private String image;

    @Column(nullable = false, length = 9)
    private String image_type;

    @Column(nullable = false)
    private int servings;

    @Column(nullable = false)
    private long readyInMinutes;

//    @ManyToOne
//    @JoinColumn (name = "user_id")
//    private User creator_id;
}
