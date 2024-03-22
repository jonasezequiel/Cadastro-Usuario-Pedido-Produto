package com.ads.atividadecadastro.repesitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ads.atividadecadastro.entities.Usuario;

@Repository
public interface usuarioRepository extends JpaRepository<Usuario, Integer> {

}
