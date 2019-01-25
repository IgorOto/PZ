package com.igi.kinoteka_api.repository;

import com.igi.kinoteka_api.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
