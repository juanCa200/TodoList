package com.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.entity.Tarea;
import com.spring.boot.repository.TareaRepository;

@Service
public class TareaService {

	@Autowired
	public TareaRepository repositorio;
	
	public List<Tarea> listarTareas(){
		return repositorio.findAll();
	}
	
	public Tarea GuardarTarea(Tarea tareas){
		return repositorio.save(tareas);
	}
	
	public Tarea buscarPorId(Long id){
		return repositorio.findById(id).get();
	}
	
	public Tarea Actualizar(Tarea tarea){
		return repositorio.save(tarea);
	}
	
	public void Eliminar(Long id){
	    repositorio.deleteById(id);
	}
	
	
	
	
	
}
