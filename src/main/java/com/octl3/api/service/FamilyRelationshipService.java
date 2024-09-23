package com.octl3.api.service;

import com.octl3.api.dto.response.EmployeeResponse;
import com.octl3.api.dto.response.FamilyRelationshipResponse;

import java.util.List;

public interface FamilyRelationshipService {
    List<FamilyRelationshipResponse> getAll();
}
