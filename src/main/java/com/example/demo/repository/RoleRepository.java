package com.example.demo.repository;

import com.example.demo.enums.RoleName;
import com.example.demo.model.RoleModel;
import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(RoleName name);
}
