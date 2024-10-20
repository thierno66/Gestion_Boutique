package com.thierno.gestion_boutique.serviceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thierno.gestion_boutique.config.JwtUtils;
import com.thierno.gestion_boutique.dto.AddroleUser;
import com.thierno.gestion_boutique.dto.ConnexionRequest;
import com.thierno.gestion_boutique.dto.InscriptionWithRole;
import com.thierno.gestion_boutique.dto.UserRequest;
import com.thierno.gestion_boutique.dto.UserResponse;
import com.thierno.gestion_boutique.entite.Role;
import com.thierno.gestion_boutique.entite.RoleType;
import com.thierno.gestion_boutique.entite.User;
import com.thierno.gestion_boutique.mapper.UserMapper;
import com.thierno.gestion_boutique.repository.RoleRepository;
import com.thierno.gestion_boutique.repository.UserRepository;
import com.thierno.gestion_boutique.service.UserService;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Override
    public UserResponse createUser(InscriptionWithRole user) {
        List<Role> roles= user.roleName().stream().map(role->roleRepository.findByRoleName(RoleType.valueOf(role))).toList();
        System.out.println( RoleType.valueOf(user.roleName().get(0)));
       
        String password = passwordEncoder.encode(user.password());
        User user2 = userRepository.save(userMapper.toUser(user, roles,password));
        return userMapper.fromUser(user2);
    }

    @Override
    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream().map(user->userMapper.fromUser(user)).toList();
    }

    @Override
    public UserResponse getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new RuntimeException("l'utilisateur don l'id est "+id+" n'existe pas");
        }
        return userMapper.fromUser(user.get());
    }

    @Override
    public UserResponse updateUser(UserRequest user, Integer id) {
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new RuntimeException("L'utilisateur avec l'id "+id+" n'existe pas");
        }
        var userdata= userOptional.get();
        if(user.nom()!=null){
            userdata.setNom(user.nom());
        }
        if(user.prenom()!=null){
            userdata.setPrenom(user.prenom());
        }
        if(user.password()!=null){
            userdata.setPassword(user.password());
        }
        if(user.telephone()!=null){
            userdata.setTelephone(user.telephone());
        }
        if(user.email()!=null){
            userdata.setEmail(user.email());
        }
        if(user.roleNames()!=null){
            List<Role> roles=new ArrayList<>();
            user.roleNames().forEach(role->{
                Role roleValue=roleRepository.findByRoleName(RoleType.valueOf(role));
                roles.add(roleValue);
            });
            userdata.setRoles(roles);
        }
        System.out.println("modification reussi");
        return userMapper.fromUser(userRepository.save(userdata));
    }

    @Override 
    public Boolean deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new RuntimeException("L'utilisateur avec l'id "+id+" n'existe pas");
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Boolean addRoleToUser(Integer userId, AddroleUser roleName) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new RuntimeException("L'utilisateur avec l'id "+userId+" n'existe pas");
        }
        var user=userOptional.get();
        boolean roleDoesNotExist = user.getRoles().stream()
                                .map(Role::getRoleName)
                                .noneMatch(role -> role.equals(RoleType.valueOf(roleName.roleName())));

        if(!roleDoesNotExist){
            return false;
        }
        Role role = roleRepository.findByRoleName(RoleType.valueOf(roleName.roleName()));
        System.out.println("***********************"+role.getRoleName()+"***********************************");
        user.getRoles().add(role);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean deleteRoleToUser(Integer userId, AddroleUser roleName) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(!userOptional.isPresent()){
            throw new RuntimeException("L'utilisateur avec l'id "+userId+" n'existe pas");
        }
        var user=userOptional.get();
        boolean roleDoesNotExist = user.getRoles().stream()
                                .map(Role::getRoleName)
                                .noneMatch(role -> role.equals(RoleType.valueOf(roleName.roleName())));

        if(roleDoesNotExist){
            return false;
        }
        Role role = roleRepository.findByRoleName(RoleType.valueOf(roleName.roleName()));
        user.getRoles().remove(role);
        userRepository.save(user);
        return true;
    }

    @Override
    public String connexion(ConnexionRequest request) {
        System.out.println(request.username());
        Authentication authentication;
        try {
            authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (Exception e) {
            throw new RuntimeException("Identifiant ou Mot de passe incorrect ");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user= (UserDetails) authentication.getPrincipal();
        String token=jwtUtils.generateTokenFromUsername(user);
        return token;

    }
    
}
