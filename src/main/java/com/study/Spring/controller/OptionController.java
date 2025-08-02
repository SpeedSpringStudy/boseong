package com.study.Spring.controller;

import com.study.Spring.dto.CreateOptionGroupRequest;
import com.study.Spring.dto.CreateOptionValueRequest;
import com.study.Spring.dto.OptionGroupResponse;
import com.study.Spring.dto.OptionValueResponse;
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
    public ResponseEntity<OptionGroupResponse> createGroup(@RequestBody CreateOptionGroupRequest request) {
        return ResponseEntity.ok(optionService.createOptionGroup(request.getProductId(), request.getName()));
    }

    @PostMapping("/values")
    public ResponseEntity<OptionValueResponse> createOptionValue(@RequestBody CreateOptionValueRequest request) {
        return ResponseEntity.ok(optionService.createOptionValue(request.getGroupId(), request.getValue()));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<OptionGroupResponse>> getOptionGroups(@RequestParam Long productId) {
        return ResponseEntity.ok(optionService.getOptionGroups(productId));
    }

    @GetMapping("/values")
    public ResponseEntity<List<OptionValueResponse>> getOptionValues(@RequestParam Long groupId) {
        return ResponseEntity.ok(optionService.getOptionValues(groupId));
    }
}