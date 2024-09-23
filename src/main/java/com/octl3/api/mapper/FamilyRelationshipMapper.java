package com.octl3.api.mapper;

import com.octl3.api.dto.response.EmployeeResponse;
import com.octl3.api.dto.response.FamilyRelationshipResponse;
import com.octl3.api.entity.Employee;
import com.octl3.api.entity.FamilyRelationship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamilyRelationshipMapper {
    FamilyRelationshipResponse toFamilyRelationshipResponse(FamilyRelationship familyRelationship);
}
