package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;


@Entity
@Table(name = "instructions")
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int order;

    @Column(nullable = false, length = 150)
    private String content;

    @ManyToOne
    @JoinColumn (name = "custom_recipe_id")
    private Custom_recipe custom_recipe;


    public Instruction() {
    }

    public Instruction(int order, String content, Custom_recipe custom_recipe) {
        this.order = order;
        this.content = content;
        this.custom_recipe = custom_recipe;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Custom_recipe getCustom_recipe() {
        return custom_recipe;
    }

    public void setCustom_recipe(Custom_recipe custom_recipe) {
        this.custom_recipe = custom_recipe;
    }
}
