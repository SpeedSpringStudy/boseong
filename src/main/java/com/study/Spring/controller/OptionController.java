package com.study.Spring.controller;

import com.study.Spring.entity.OptionGroup;
import com.study.Spring.entity.OptionValue;
import com.study.Spring.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    @PostMapping("/groups")
    public ResponseEntity<Long> createOptionGroup(@RequestParam Long productId, @RequestParam String name) {
        Long id = optionService.createOptionGroup(productId, name);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/values")
    public ResponseEntity<Long> createOptionValue(@RequestParam Long groupId, @RequestParam String value) {
        Long id = optionService.createOptionValue(groupId, value);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<OptionGroup>> getOptionGroups(@RequestParam Long productId) {
        return ResponseEntity.ok(optionService.getOptionGroups(productId));
    }

    @GetMapping("/values")
    public ResponseEntity<List<OptionValue>> getOptionValues(@RequestParam Long groupId) {
        return ResponseEntity.ok(optionService.getOptionValues(groupId));
    }
}