package Personal.project.product;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository

public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Product addProduct(Product product){
        String sql ="INSERT INTO product(name,price) VALUES(?,?)";
        jdbcTemplate.update(sql,product.getName(),product.getPrice());
        return product;
    }

    public Product findById(Long id){
        String sql = "SELECT * FROM product WHERE id =?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new ProductMapper());

    }

    public List<Product> findAll(){
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql,new ProductMapper());
    }
    public void updateProduct(Long id,Product updateParam){
        String sql = "UPDATE product SET name=?, price =? WHERE id =?";
        jdbcTemplate.update(sql,updateParam.getName(),updateParam.getPrice(),id);
    }
    public void deleteProduct(Long id){
        String sql = "DELETE FROM product WHERE id =?";
        jdbcTemplate.update(sql,id);
    }
}