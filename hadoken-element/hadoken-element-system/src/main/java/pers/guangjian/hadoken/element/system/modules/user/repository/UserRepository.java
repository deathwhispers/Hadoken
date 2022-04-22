package pers.guangjian.hadoken.element.system.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pers.guangjian.hadoken.element.system.modules.user.domain.User;

/**
 * @author yanggj
 * @version 1.0.0
 * @date 2022/4/22 9:32
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
