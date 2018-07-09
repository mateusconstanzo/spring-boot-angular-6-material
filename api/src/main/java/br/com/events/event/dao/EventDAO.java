package br.com.events.event.dao;

import br.com.events.auth.entity.UserEntity;
import br.com.events.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<EventEntity, Integer> {

    List<EventEntity> findByUser(UserEntity entity, Sort sort);

}
