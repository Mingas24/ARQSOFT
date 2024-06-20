package com.example.UserManagement.service;

import com.example.UserManagement.domain.entities.Costumer;
import com.example.UserManagement.domain.entities.Manager;
import com.example.UserManagement.domain.entities.Role;
import com.example.UserManagement.domain.valueObjects.costumer.CostumerNIF;
import com.example.UserManagement.domain.valueObjects.user.Email;
import com.example.UserManagement.domain.valueObjects.user.Password;
import com.example.UserManagement.domain.valueObjects.user.Username;
import com.example.UserManagement.dto.costumer.CostumerDTO;
import com.example.UserManagement.dto.manager.ManagerDTO;
import com.example.UserManagement.dto.result.ResponseDTO;
import com.example.UserManagement.enums.StatusCode;
import com.example.UserManagement.mapper.CostumerMapper;
import com.example.UserManagement.mapper.ManagerMapper;
import com.example.UserManagement.repository.CostumerRepository;
import com.example.UserManagement.repository.ManagerRepository;
import com.example.UserManagement.repository.RoleRepository;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
public class UserService implements IUserService{

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private CostumerMapper costumerMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ManagerDTO createManager(ManagerDTO managerDTO) {
        try {
            Optional<Manager> managerOptional = this.managerRepository.findByUsername(new Username(managerDTO.getUsername()));
            if(managerOptional.isPresent()) {
                throw new GraphQLException("Username already exists");
            }

            if(this.managerRepository.findByEmail(new Email(managerDTO.getEmail())).isPresent()) {
                throw new GraphQLException("User email already exists");
            }

            Optional<Role> roleOptional = findRoleById(Long.parseLong(managerDTO.getRole()));
            if(roleOptional.isEmpty()) {
                throw new GraphQLException("Role not found");
            }

            Manager manager = this.managerMapper.toManager(managerDTO);
            manager.setRole(roleOptional.get());
            manager.setPassword(new Password(passwordEncoder.encode(managerDTO.getPassword())));
            this.managerRepository.save(manager);

            return this.managerMapper.toManagerDTO(manager, manager.getShopId().getShopId());

        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public ResponseDTO getUserByUsername(String username) {
        Optional<Manager> managerOptional = this.managerRepository.findByUsername(new Username(username));
        if(managerOptional.isPresent())
            return new ResponseDTO(StatusCode.OK.getCode(), this.managerMapper.toUserDTO(managerOptional.get()));
        Optional<Costumer> costumerOptional = this.costumerRepository.findByUsername(new Username(username));
        if(costumerOptional.isPresent())
            return new ResponseDTO(StatusCode.OK.getCode(), this.costumerMapper.toUserDTO(costumerOptional.get()));
        return new ResponseDTO(StatusCode.NOT_FOUND.getCode(), "User not found");
    }

    @Override
    public ManagerDTO changeManagerShop(Long managerId, Long shopId) {
        try {
            Optional<Manager> optionalManager = this.managerRepository.findById(managerId);
            if (!optionalManager.isPresent()) {
                throw new GraphQLException(String.format("There is no manager with the id: %s", managerId));
            }

            Manager manager = optionalManager.get();
            if (manager.getShopId().getShopId() == shopId) {
                throw new GraphQLException(String.format("The shop %s is already associated to the manager %s", shopId, manager.getName().getName()));
            }
            ManagerDTO managerDTO = this.managerMapper.toManagerDTO(manager, shopId);
            Manager managerToBeSaved = managerMapper.toManager(managerDTO);
            managerToBeSaved.setRole(manager.getRole());
            managerToBeSaved.setPassword(manager.getPassword());

            this.managerRepository.delete(manager);
            Manager savedManager = this.managerRepository.save(managerToBeSaved);
            return this.managerMapper.toManagerDTO(savedManager,shopId);
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public CostumerDTO createCostumer(CostumerDTO costumerDTO) {
        try {
            Optional<Costumer> costumerOptional = this.costumerRepository.findByUsername(new Username(costumerDTO.getUsername()));
            if(costumerOptional.isPresent()) {
                throw new GraphQLException("Username already exists");
            }

            if(this.costumerRepository.findByEmail(new Email(costumerDTO.getEmail())).isPresent()) {
                throw new GraphQLException("User email already exists");
            }

            Optional<Role> roleOptional = findRoleById(Long.parseLong(costumerDTO.getRole()));
            if(roleOptional.isEmpty()) {
                throw new GraphQLException("Role not found");
            }

            Costumer costumer = this.costumerMapper.toCostumer(costumerDTO);
            costumer.setRole(roleOptional.get());
            costumer.setPassword(new Password(passwordEncoder.encode(costumerDTO.getPassword())));
            this.costumerRepository.save(costumer);

            return this.costumerMapper.toCostumerDTO(costumer);

        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public CostumerDTO editCostumer(Long costumerId, CostumerDTO costumerDTO) {
        try {
            Optional<Costumer> optionalCostumer = this.costumerRepository.findById(costumerId);
            if (!optionalCostumer.isPresent()) {
                throw new GraphQLException(String.format("There is no costumer with the id: %s", costumerId));
            }

            String costumerEmail = costumerDTO.getEmail();
            if (costumerEmail != null) {
                Optional<Costumer> optionalEmailCostumer = this.costumerRepository.findByEmail(new Email(costumerEmail));
                if (optionalEmailCostumer.isPresent()) {
                    throw new GraphQLException(String.format("There is already a costumer with the email: %s", costumerEmail));
                }
            }

            String costumerNIF = costumerDTO.getCostumerNIF();
            if (costumerNIF != null) {
                Optional<Costumer> optionalNIFCostumer = this.costumerRepository.findCostumerByCostumerNIF(new CostumerNIF(costumerNIF));
                if (optionalNIFCostumer.isPresent()) {
                    throw new GraphQLException(String.format("There is already a costumer with the NIF: %s", costumerNIF));
                }
            }

            Costumer costumer = optionalCostumer.get();
            Costumer toBeSavedCostumer = this.costumerMapper.toCostumer(
                    CostumerDTO.builder()
                            .name(costumerDTO.getName() != null ? costumerDTO.getName() : costumer.getName().getName())
                            .email(costumerEmail != null ? costumerEmail : costumer.getEmail().getEmail())
                            .username(costumerDTO.getUsername() != null ? costumerDTO.getUsername() : costumer.getUsername().getUsername())
                            .costumerNIF(costumerNIF != null ? costumerNIF : costumer.getCostumerNIF().getCostumerNIF())
                            .address(costumerDTO.getAddress() != null ? costumerDTO.getAddress() : costumer.getCostumerAddress().getAddress())
                            .build());

            toBeSavedCostumer.setRole(costumer.getRole());
            toBeSavedCostumer.setPassword(costumerDTO.getPassword() != null ? new Password(passwordEncoder.encode(costumerDTO.getPassword())) : costumer.getPassword());

            this.costumerRepository.delete(costumer);
            Costumer savedCostumer = this.costumerRepository.save(toBeSavedCostumer);

            return this.costumerMapper.toCostumerDTO(savedCostumer);
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    @Override
    public CostumerDTO getCostumerByEmail(String email) {
        try {
            Optional<Costumer> optionalCostumer = this.costumerRepository.findByEmail(new Email(email));
            if (email != null && optionalCostumer.isPresent()) {
                return costumerMapper.toCostumerDTO(optionalCostumer.get());
            }
            throw new GraphQLException(String.format("The costumer with email %s does not exist.", email));
        } catch (Exception e) {
            throw new GraphQLException(e.getMessage());
        }
    }

    private Optional<Role> findRoleById(Long role){
        return this.roleRepository.findRoleById(role);
    }
}
