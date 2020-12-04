package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Role;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public Page<User> getPageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean updateUser(ru.fwoods.computerstore.model.User user) {
        User userDomain = userRepository.getOne(user.getId());

        userDomain.setFirstName(user.getFirstName());
        userDomain.setLastName(user.getLastName());
        userDomain.setPatronymic(user.getPatronymic());
        userDomain.setEmail(user.getEmail());
        userDomain.setPhone(user.getPhone());
        userDomain.setCity(cityService.getCity(user.getCity()));

        if (user.getPassword() != null && user.getNewPassword() != null) {
            if (passwordEncoder.matches(user.getPassword(), userDomain.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getNewPassword()));
                userRepository.save(userDomain);
                securityService.updateAuthenticationToken();
                return true;
            }
            return false;
        }

        userRepository.save(userDomain);

        securityService.updateAuthenticationToken();

        return true;
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public void addAdmin(Long id) {
        User user = getUserById(id);
        user.getRoles().add(Role.ADMIN);
        userRepository.save(user);
    }

    public void deleteAdmin(Long id) {
        User user = getUserById(id);
        user.getRoles().clear();
        user.getRoles().add(Role.USER);
        userRepository.save(user);
    }

    public List<User> getAllOnlyUser() {
        List<User> users = userRepository.findAll();
        return users.stream().filter(user -> user.getRoles().size() == 1).collect(Collectors.toList());
    }
}
