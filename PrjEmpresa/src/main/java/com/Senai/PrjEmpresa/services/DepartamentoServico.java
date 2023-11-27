package com.Senai.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Senai.PrjEmpresa.entities.Departamento;
import com.Senai.PrjEmpresa.repository.DepartamentoRepositorio;


@Service
public class DepartamentoServico {
	
private final DepartamentoRepositorio departamentoRepositorio;
	
	@Autowired
	public DepartamentoServico(DepartamentoRepositorio departamentoRepositorio) {
		this.departamentoRepositorio = departamentoRepositorio;
	}

	public Departamento getDepartamentoById(Long Depcodigo) {
		return departamentoRepositorio.findById(Depcodigo).orElse(null); 
	}

	public List<Departamento> getAllDepartamento() {
		return departamentoRepositorio.findAll();
	}

	public Departamento saveDepartamento(Departamento departamento) {
		return departamentoRepositorio.save(departamento);
	}

	public Departamento updateDepartamento(Long Depcodigo, Departamento novoDepartamento) {
		Optional<Departamento> departamentoOptional = departamentoRepositorio.findById(Depcodigo);
		if (departamentoOptional.isPresent()) {
			Departamento departamentoExistente = departamentoOptional.get();
			departamentoExistente.setDepnome(novoDepartamento.getDepnome());
			return departamentoRepositorio.save(departamentoExistente);
		} else {
			return null;
		}
	}

	public void deleteDepartamento(Long Depcodigo) {
		departamentoRepositorio.deleteById(Depcodigo);
	}
}
