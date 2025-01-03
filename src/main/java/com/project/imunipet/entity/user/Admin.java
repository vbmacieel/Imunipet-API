package com.project.imunipet.entity.user;

import com.project.imunipet.entity.role.Role;
import com.project.imunipet.entity.role.RoleName;
import com.project.imunipet.entity.user.base.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "admins")
@NoArgsConstructor
public class Admin extends User {

    public Admin(String name, String login, String password) {
        super(name, login, password, defaultAdminRoles());
    }

    private static List<Role> defaultAdminRoles() {
        return List.of(
            new Role(null, RoleName.ADMIN),
            new Role(null, RoleName.USER)
        );
    }
}