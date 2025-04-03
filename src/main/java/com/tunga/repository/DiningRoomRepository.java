package com.tunga.repository;

import com.tunga.model.DiningRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningRoomRepository extends JpaRepository<DiningRoom, Long> {
} 