package com.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.entity.Tarea;
import com.spring.boot.service.TareaService;



@Controller
@RequestMapping("/")
public class Controlador {
	
	@Autowired
	public TareaService servicio;
	
	public List<Tarea> taskCompleted = new ArrayList<>();
	
	@GetMapping
	public String ListarTareas(Model modelo){
		modelo.addAttribute("tareas", servicio.listarTareas());
		modelo.addAttribute("object", new Tarea());
		modelo.addAttribute("totalTask", servicio.listarTareas().size());
		modelo.addAttribute("taskCompleted", taskCompleted.size());

		return "index";
	}
	
	@PostMapping("/guardar")
	public String GuardarTarea(@Valid Tarea tarea, BindingResult result){
		if(result.hasErrors()){
	        System.out.println("Error en formulario");
	        return "redirect:/";		
		}
		tarea.setCompleted(false);
		servicio.GuardarTarea(tarea);
		return "redirect:/";
	}
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id")Long id){
		servicio.Eliminar(id);
	    for(int i = 0; i<taskCompleted.size(); i++){
	    	Long id_entrada = taskCompleted.get(i).getId();
	    	if(id==id_entrada){
	    		taskCompleted.remove(i);
	    	}
	    }	
		
		return "redirect:/";
	}
	@GetMapping("/completed/{id}")
	public String completado(@PathVariable("id")Long id){
		Tarea tarea = servicio.buscarPorId(id);
		tarea.setCompleted(true);
		taskCompleted.add(tarea);
		servicio.GuardarTarea(tarea);
		return "redirect:/";
	}
	
}
