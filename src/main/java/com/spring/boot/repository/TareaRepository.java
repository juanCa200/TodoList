package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.entity.Tarea;

public interface TareaRepository extends JpaRepository<Tarea,Long>{
	
}
