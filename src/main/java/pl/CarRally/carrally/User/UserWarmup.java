package pl.CarRally.carrally.User;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component("userWarmup")
class UserWarmup implements ApplicationListener<ContextRefreshedEvent> {
    private final UserRoleRepository userRoleRepository;

    UserWarmup(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(!userRoleRepository.existsByType(UserRoleType.USER)){
            userRoleRepository.save(new UserRole(UserRoleType.USER));
        }
        if(!userRoleRepository.existsByType(UserRoleType.ADMIN)){
            userRoleRepository.save(new UserRole(UserRoleType.ADMIN));
        }
    }
}
