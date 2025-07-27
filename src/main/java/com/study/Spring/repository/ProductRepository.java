//package com.study.Spring.repository;
//
//import com.study.Spring.entity.Product;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class ProductRepository {
//
//    private final Map<Long, Product> store = new HashMap<>();
//    private long sequence = 0L;
//
//    public List<Product> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public Long save(Product product) {
//        product.setId(++sequence);
//        store.put(product.getId(), product);
//        return product.getId();
//    }
//
//    public Optional<Product> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//    public Long update(Long id, Product updated) {
//        Product product = store.get(id);
//        if (product != null) {
//            product.setName(updated.getName());
//            product.setPrice(updated.getPrice());
//        }
//        return product.getId();
//    }
//
//    public Long delete(Long id) {
//        Long removed_Id = store.get(id).getId();
//        store.remove(id);
//        return removed_Id;
//    }
//}

package com.study.Spring.repository;

import com.study.Spring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Slice<Product> findByAll(Pageable pageable);
}