package cn.liuxiaokang.heroesapi.repository;

import cn.liuxiaokang.heroesapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
