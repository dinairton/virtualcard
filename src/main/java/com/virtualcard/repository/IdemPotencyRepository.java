package com.virtualcard.repository;

import com.virtualcard.entity.IdemPotency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdemPotencyRepository extends JpaRepository <IdemPotency, Long>{

    Optional<IdemPotency> findByIdemPotencyKey(String idemPortencyKey);

}
