package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "dish_types")
public class DishType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String type;

    @ManyToMany(mappedBy = "dishTypes")
    Set<Recipe> recipes;

    public DishType() {
    }

    public DishType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
