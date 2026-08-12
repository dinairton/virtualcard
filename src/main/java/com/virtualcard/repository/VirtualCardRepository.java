package com.virtualcard.repository;

import com.virtualcard.entity.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualCardRepository extends JpaRepository <VirtualCard, Long>{

}
