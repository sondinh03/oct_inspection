package com.octl3.api.service.impl;

import com.octl3.api.dto.response.FamilyRelationshipResponse;
import com.octl3.api.mapper.FamilyRelationshipMapper;
import com.octl3.api.repository.FamilyRelationShipRepository;
import com.octl3.api.service.FamilyRelationshipService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    FamilyRelationShipRepository familyRelationShipRepo;
    FamilyRelationshipMapper familyRelationshipMapper;

    @Override
    public List<FamilyRelationshipResponse> getAll() {
        return  familyRelationShipRepo.findAll().stream()
                .map(familyRelationshipMapper::toFamilyRelationshipResponse)
                .toList();
    }

}
