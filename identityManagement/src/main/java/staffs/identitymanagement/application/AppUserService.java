package staffs.identitymanagement.application;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import staffs.common.security.AppUserJpa;
import staffs.common.security.JwtTokenUtil;
import staffs.identitymanagement.infrastructure.AppUserRepository;
import staffs.identitymanagement.security.SecurityProperties;
import staffs.identitymanagement.ui.AppUserDetailsRequest;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@ComponentScan(basePackages = {"staffs.common.security"})  //needed to locate JwtTokenUtil
@EntityScan(basePackages = {"staffs.common.security"})     //needed to locate AppUser
@AllArgsConstructor
public class AppUserService {
    private AppUserRepository appUserRepository;
    private JwtTokenUtil jwtTokenUtil;
    private PasswordEncoder passwordEncoder;
    private final SecurityProperties securityProperties;

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public Optional<String> authenticate(AppUserDetailsRequest appUserDetailsRequest) {
        Random random = new SecureRandom();
        Optional<AppUserJpa> userOpt = appUserRepository.findByUsername(appUserDetailsRequest.getUsername());

        if (userOpt.isEmpty()) {
            //Dummy password check to prevent timing attacks
            List<String> hashes = securityProperties.getHashes();
            String dummyHash = hashes.get(random.nextInt(hashes.size()));
            passwordEncoder.matches(appUserDetailsRequest.getPassword(), dummyHash);
            return Optional.empty();
        }

        AppUserJpa user = userOpt.get();
        if (passwordEncoder.matches(appUserDetailsRequest.getPassword(), user.getPassword())) {
            String accessToken = jwtTokenUtil.generateToken(user);
            return Optional.of(accessToken);
        }

        LOG.info("Password {} does not match with username {}", appUserDetailsRequest.getPassword(), appUserDetailsRequest.getUsername());
        return Optional.empty();
    }
}