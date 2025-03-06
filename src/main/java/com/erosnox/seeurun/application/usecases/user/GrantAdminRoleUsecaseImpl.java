package com.erosnox.seeurun.application.usecases.user;

import com.erosnox.seeurun.application.contracts.gateways.UserRepository;
import com.erosnox.seeurun.application.contracts.usecases.user.GrantAdminRoleUsecase;
import com.erosnox.seeurun.application.enums.RolesEnum;
import com.erosnox.seeurun.application.exceptions.ConflictException;
import com.erosnox.seeurun.application.exceptions.NotFoundException;
import com.erosnox.seeurun.application.models.dto.UserDto;
import com.erosnox.seeurun.application.utils.UserUtils;

import java.util.UUID;

public class GrantAdminRoleUsecaseImpl implements GrantAdminRoleUsecase {
    private final UserRepository repository;

    public GrantAdminRoleUsecaseImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UUID id, UserDto currentUser) {
        UserUtils.isUserAllowed(id, currentUser);
        var entity = repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        if (RolesEnum.ADMIN.equals(entity.getRole())) {
            throw new ConflictException("User already granted admin role");
        }

        entity.setRole(RolesEnum.ADMIN);

        repository.save(entity);
    }
}
