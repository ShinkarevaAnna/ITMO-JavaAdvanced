package com.example.car.model.db.repository;

import com.example.car.model.db.entity.User;
import com.example.car.model.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select c from User c where c.status <> :status")
    Page<User> findAllByStatusNot(Pageable request, UserStatus status);

    @Query("select c from User c where c.status <> :status and (lower(c.firstName) like %:filter%  or  lower(c.lastName) like %:filter% )")
    Page<User> findAllByStatusNotFiltered(Pageable request, UserStatus status, @Param("filter") String filter);

}

