package cookbook.cookbookrecipeapplication.models;

import jakarta.persistence.*;

import java.io.Serializable;


@Entity
@Table(name = "instructions")
public class Instruction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int order_num;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn (name = "custom_recipe_id")
    private CustomRecipe custom_recipe;


    public Instruction() {
    }

    public Instruction(int order_num, String content, CustomRecipe custom_recipe) {
        this.order_num = order_num;
        this.content = content;
        this.custom_recipe = custom_recipe;
    }

    public Instruction(int order_num, String content) {
        this.order_num = order_num;
        this.content = content;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CustomRecipe getCustom_recipe() {
        return custom_recipe;
    }

    public void setCustom_recipe(CustomRecipe custom_recipe) {
        this.custom_recipe = custom_recipe;
    }
}
