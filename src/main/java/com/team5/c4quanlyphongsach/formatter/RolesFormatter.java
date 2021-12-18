package com.team5.c4quanlyphongsach.formatter;

import com.team5.c4quanlyphongsach.model.users.Roles;
import com.team5.c4quanlyphongsach.service.roles.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class RolesFormatter implements Formatter<Roles> {

    private IRolesService rolesService;

    @Autowired
    public RolesFormatter(IRolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    public Roles parse(String text, Locale locale) throws ParseException {
        Optional<Roles> provinceOptional = rolesService.findById(Long.parseLong(text));
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Roles object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}