package com.contactManager.repository;

import com.contactManager.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    Optional<ContactEntity> findByNameIgnoreCase(String name);
}
