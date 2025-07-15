package com.study.Spring.dao;

import com.study.Spring.entity.Product;
import com.study.Spring.vo.Name;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> Product.builder()
            .id(rs.getLong("id"))
            .name(new Name(rs.getString("name")))
            .price(rs.getInt("price"))
            .build();

    public Long save(Product product) {
        jdbcTemplate.update(
                "INSERT INTO product (name, price) VALUES (?, ?)",
                product.getName().toString(),
                product.getPrice()
        );
        return jdbcTemplate.queryForObject("SELECT MAX(id) FROM product", Long.class);
    }

    public Optional<Product> findById(Long id) {
        List<Product> results = jdbcTemplate.query(
                "SELECT * FROM product WHERE id = ?", productRowMapper, id);
        return results.stream().findFirst();
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", productRowMapper);
    }

    public Long update(Long id, Product product) {
        jdbcTemplate.update(
                "UPDATE product SET name = ?, price = ? WHERE id = ?",
                product.getName().toString(),
                product.getPrice(),
                id
        );
        return id;
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}