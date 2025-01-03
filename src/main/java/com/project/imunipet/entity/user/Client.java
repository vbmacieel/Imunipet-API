package com.project.imunipet.entity.user;

import java.util.ArrayList;
import java.util.List;

import com.project.imunipet.entity.pet.Pet;
import com.project.imunipet.entity.role.Role;
import com.project.imunipet.entity.role.RoleName;
import com.project.imunipet.entity.user.base.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<>();

    public Client(String name, String login, String password) {
        super(name, login, password, defaultClientRoles());
    }

    private static List<Role> defaultClientRoles() {
        return List.of(new Role(null, RoleName.USER));
    }
}
