package com.Senai.PrjEmpresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Senai.PrjEmpresa.entities.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{

}