package com.usdev.infrastructure.postgres.repository;

import com.usdev.infrastructure.postgres.entity.PriceEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p where ?1 >= p.startDate AND ?1 <= p.endDate AND p.productId = ?2 AND p.brandId = ?3")     
    List<PriceEntity> findPrices(LocalDateTime data, Long productId, Long brandId, Sort sort);
}