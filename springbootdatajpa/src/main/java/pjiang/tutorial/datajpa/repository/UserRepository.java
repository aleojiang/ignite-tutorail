package pjiang.tutorial.datajpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pjiang.tutorial.datajpa.repository.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * Created by: patrick.jiang@activenetwork.com
 * Created on:  13:20 2018/10/24.
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    @Override
    List<UserEntity> findAll();
    
    Optional<UserEntity> findDistinctByFirstName(String value);
}
