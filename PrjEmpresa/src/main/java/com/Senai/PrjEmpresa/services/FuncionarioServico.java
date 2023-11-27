package com.Senai.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Senai.PrjEmpresa.entities.Funcionario;
import com.Senai.PrjEmpresa.repository.FuncionarioRepositorio;

@Service
public class FuncionarioServico {
	
	private final FuncionarioRepositorio funcionarioRepositorio;

	@Autowired
	public FuncionarioServico (FuncionarioRepositorio funcionarioRepositorio) {
		this.funcionarioRepositorio = funcionarioRepositorio;
	}

	public Funcionario findFuncionarioById(Long id) {
		Optional<Funcionario> Funcionario = funcionarioRepositorio.findById(id);
		return Funcionario.orElse(null);
	}

	public List<Funcionario> findAllFuncionario() {
		return funcionarioRepositorio.findAll();
	}

	public Funcionario insertFuncionario(Funcionario Funcionario) {
		return funcionarioRepositorio.save(Funcionario);
	}

	public Funcionario updateFuncionario(Long id, Funcionario novoFuncionario) {
		Optional<Funcionario> FuncionarioOptional = funcionarioRepositorio.findById(id);
		if (FuncionarioOptional.isPresent()) {
			Funcionario FuncionarioExistente = FuncionarioOptional.get();
			FuncionarioExistente.setFunnome(novoFuncionario.getFunnome());
			FuncionarioExistente.setFunnascimento(novoFuncionario.getFunnascimento());
			FuncionarioExistente.setFunsalario(novoFuncionario.getFunsalario());
			return funcionarioRepositorio.save(FuncionarioExistente);
		} else {
			return null;
		}
	}

	public boolean deleteFuncionario(Long id) {
		Optional<Funcionario> FuncionarioExistente = funcionarioRepositorio.findById(id);
		if (FuncionarioExistente.isPresent()) {
			funcionarioRepositorio.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}