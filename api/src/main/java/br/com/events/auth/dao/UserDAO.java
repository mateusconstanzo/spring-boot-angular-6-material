package br.com.events.auth.dao;

import br.com.events.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsernameAndPassword(String username, String password);

}
