package com.example.mymovies.repository;

import com.example.mymovies.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AwardBaseRepository<T extends Award> extends JpaRepository<T, Integer> {
}
