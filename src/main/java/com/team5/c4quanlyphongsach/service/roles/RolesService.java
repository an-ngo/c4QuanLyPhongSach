package com.team5.c4quanlyphongsach.service.roles;

import com.team5.c4quanlyphongsach.model.users.Roles;
import com.team5.c4quanlyphongsach.repository.roles.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesService implements IRolesService{
    @Autowired
    private IRolesRepository rolesRepository;
    @Override
    public Iterable<Roles> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public Optional<Roles> findById(Long id) {
        return rolesRepository.findById(id);
    }

    @Override
    public Roles save(Roles roles) {
        return rolesRepository.save(roles);
    }

    @Override
    public void remove(Long id) {
        rolesRepository.deleteById(id);
    }
}
