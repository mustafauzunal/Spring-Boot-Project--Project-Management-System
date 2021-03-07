package com.mustafauzunal.springbootprojectcreator.repository;

import com.mustafauzunal.springbootprojectcreator.model.Projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    
}
