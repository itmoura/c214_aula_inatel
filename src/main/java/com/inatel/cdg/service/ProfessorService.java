package com.inatel.cdg.service;

import com.inatel.cdg.entity.Professor;

import java.util.List;

public interface ProfessorService {

    Professor cadastraProfessor(Professor professor);
    Professor buscaProfessorId(Long id, List<Professor> professores);

    Professor buscaProfessorNome(String nome, List<Professor> professores);
}
