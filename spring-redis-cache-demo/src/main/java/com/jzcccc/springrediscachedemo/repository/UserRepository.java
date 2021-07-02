package com.jzcccc.springrediscachedemo.repository;

import com.jzcccc.springrediscachedemo.model.User;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findAddrById(Integer id);


    @Cacheable(key = "#id",unless = "#result==null")
    User findNameById(Integer id);

    @CacheEvict(key="#id")
    void deleteById(Integer id);

}
