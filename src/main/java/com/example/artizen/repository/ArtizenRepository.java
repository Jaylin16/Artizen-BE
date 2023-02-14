package com.example.artizen.repository;

import com.example.artizen.entity.Artizen;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtizenRepository extends JpaRepository<Artizen, String> {
    boolean existsById(String id);

    Slice<Artizen> findAllByCategoryContains(String genre, Pageable pageable);

    List<Artizen> findAllByCategoryContains(String keyword);

    List<Artizen> findAllByNameContains(String keyword);

    List<Artizen> findAllByPlaceContains(String keyword);

    List<Artizen> findAllByContentContains(String keyword);

    List<Artizen> findTop3ByOrderByCreatedAtDesc();

    List<Artizen> findTop4ByOrderByTotalHeartDesc();

    List<Artizen> findByPlaceAndCategoryContains(String place, String category);

}
