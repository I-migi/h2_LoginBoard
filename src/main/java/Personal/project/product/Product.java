package Personal.project.product;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Product {
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private int price;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }

    public Product() {
    }
}