package com.example.test.service.base;



import com.example.test.model.Role;
import com.example.test.service.CommonService;

import java.util.Optional;

public interface RoleService extends CommonService<Role> {
    Optional<Role> findByName(String name);
}
