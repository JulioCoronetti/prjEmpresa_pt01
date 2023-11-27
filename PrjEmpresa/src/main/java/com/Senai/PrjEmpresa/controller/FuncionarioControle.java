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

import com.Senai.PrjEmpresa.entities.Funcionario;
import com.Senai.PrjEmpresa.services.FuncionarioServico;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Users", description = "API REST DE GERENCIAMENTO DE FUNCIONÁRIOS")
@RestController
@RequestMapping("/funcionario")
public class FuncionarioControle {
	
	private final FuncionarioServico funcionarioServico;

	@Autowired
	public FuncionarioControle(FuncionarioServico funcionarioServico) {
		this.funcionarioServico = funcionarioServico;
	}

	@GetMapping("/{id}")
	@Operation(summary = "localiza funcionário por ID")
	public ResponseEntity<Funcionario> findFuncionariobyId(@PathVariable Long id) {
		Funcionario User = funcionarioServico.findFuncionarioById(id);
		if (User != null) {
			return ResponseEntity.ok(User);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os funcionários ")
	public ResponseEntity<List<Funcionario>> findAllFuncionarioscontrol() {
		List<Funcionario> Users = funcionarioServico.findAllFuncionario();
		return ResponseEntity.ok(Users);
	}
	
	

	@PostMapping("/")
	@Operation(summary = "Cadastra um funcionário")
	public ResponseEntity<Funcionario> insertUsersControl(@RequestBody @Valid Funcionario User) {
		Funcionario novoFuncionario = funcionarioServico.insertFuncionario(User);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Altera um funcionário")
	public ResponseEntity<Funcionario> updateUserControl(@PathVariable Long id, @RequestBody @Valid Funcionario User) {
		Funcionario mudaFuncionario = funcionarioServico.updateFuncionario(id, User);
		if (mudaFuncionario != null) {
			return ResponseEntity.ok(User);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Exclui um funcionário")
	public ResponseEntity<String> deleteUserControl(@PathVariable Long id) {
		boolean remover = funcionarioServico.deleteFuncionario(id);
		if (remover) {
			return ResponseEntity.ok().body("Funcionário Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}