package com.study.Spring.config;

import com.study.Spring.entity.Category;
import com.study.Spring.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) { // 중복 실행 방지
            List<Category> defaultCategories = List.of(
                    new Category(null, "음료", "커피, 차, 음료류"),
                    new Category(null, "디저트", "케이크, 빵, 디저트류"),
                    new Category(null, "굿즈", "머그컵, 텀블러 등")
            );
            categoryRepository.saveAll(defaultCategories);
            System.out.println("기본 카테고리가 초기화되었습니다.");
        }
    }
}