package com.dinushka.internship_portal_api.service.auth;

import com.dinushka.internship_portal_api.dto.auth.*;
import com.dinushka.internship_portal_api.entity.CompanyProfile;
import com.dinushka.internship_portal_api.entity.StudentProfile;
import com.dinushka.internship_portal_api.entity.User;
import com.dinushka.internship_portal_api.enums.Role;
import com.dinushka.internship_portal_api.exception.BadRequestException;
import com.dinushka.internship_portal_api.repository.CompanyProfileRepository;
import com.dinushka.internship_portal_api.repository.StudentProfileRepository;
import com.dinushka.internship_portal_api.repository.UserRepository;
import com.dinushka.internship_portal_api.security.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentRepo;
    private final CompanyProfileRepository companyRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository,
                           StudentProfileRepository studentRepo,
                           CompanyProfileRepository companyRepo,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.studentRepo = studentRepo;
        this.companyRepo = companyRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse registerStudent(RegisterStudentRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.STUDENT);

        User savedUser = userRepository.save(user);

        StudentProfile sp = new StudentProfile();
        sp.setUser(savedUser);           // MapsId will set student_id
        sp.setUniversity(req.getUniversity());
        sp.setDegree(req.getDegree());
        sp.setGraduationYear(req.getGraduationYear());
        sp.setCvUrl(null);
        studentRepo.save(sp);

        String token = jwtService.generateToken(savedUser.getEmail(), savedUser.getRole().name());
        return new AuthResponse(token, savedUser.getRole().name(), savedUser.getUserId());
    }

    @Override
    public AuthResponse registerCompany(RegisterCompanyRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.COMPANY);

        User savedUser = userRepository.save(user);

        CompanyProfile cp = new CompanyProfile();
        cp.setUser(savedUser);
        cp.setCompanyName(req.getCompanyName());
        cp.setWebsite(req.getWebsite());
        cp.setLocation(req.getLocation());
        companyRepo.save(cp);

        String token = jwtService.generateToken(savedUser.getEmail(), savedUser.getRole().name());
        return new AuthResponse(token, savedUser.getRole().name(), savedUser.getUserId());
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new BadRequestException("Invalid email or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token, user.getRole().name(), user.getUserId());
    }
}
