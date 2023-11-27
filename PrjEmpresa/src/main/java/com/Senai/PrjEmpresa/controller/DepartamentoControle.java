package com.Senai.PrjEmpresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Senai.PrjEmpresa.entities.Departamento;
import com.Senai.PrjEmpresa.services.DepartamentoServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "API REST DE GERENCIAMENTO DE FUNCIONÁRIOS")
@RestController
@RequestMapping("/departamento")
public class DepartamentoControle {

	@GetMapping("/home")
	public String paginaInicial() {
		return "index";
	}
	
	
private final DepartamentoServico departamentoServico;
	
	@Autowired
	public DepartamentoControle(DepartamentoServico departamentoServico) {
		this.departamentoServico = departamentoServico;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um departamento por ID")
	public ResponseEntity<Departamento> getDepartamentobyId(@PathVariable Long Depcodigo) {
		Departamento departamento = departamentoServico.getDepartamentoById(Depcodigo);
		if (departamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	@Operation(summary = "Apresenta todos os departamentos")
	public ResponseEntity<List<Departamento>> getAllDepartamento(){
		List<Departamento> departamento = departamentoServico.getAllDepartamento();
		return ResponseEntity.ok(departamento);
	}

	@PostMapping
	@Operation(summary = "Cadastra um departamento")
	public ResponseEntity<Departamento> saveDepartamentoControl(@RequestBody @Valid Departamento departamento) {
		Departamento novoDepartamento = departamentoServico.saveDepartamento(departamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoDepartamento);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um departamento")
	public ResponseEntity<Departamento> updateDepartamentoControl(@PathVariable Long Depcodigo, @RequestBody @Valid Departamento departamento) {
		Departamento mudadepartamento= departamentoServico.updateDepartamento(Depcodigo, departamento);
		if (mudadepartamento != null) {
			return ResponseEntity.ok(departamento);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um departamento")
	public void deleteDepartamento(@PathVariable Long Depcodigo) {
		departamentoServico.deleteDepartamento(Depcodigo);
	}

}