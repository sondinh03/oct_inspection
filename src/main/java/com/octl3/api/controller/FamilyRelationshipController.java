package com.octl3.api.controller;

import com.octl3.api.commons.OctResponse;
import com.octl3.api.dto.response.FamilyRelationshipResponse;
import com.octl3.api.service.FamilyRelationshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family-relationship")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FamilyRelationshipController {
    FamilyRelationshipService familyRelationshipService;

    @GetMapping
    public OctResponse<List<FamilyRelationshipResponse>> getAll() {
        return OctResponse.build(familyRelationshipService.getAll());
    }
}
