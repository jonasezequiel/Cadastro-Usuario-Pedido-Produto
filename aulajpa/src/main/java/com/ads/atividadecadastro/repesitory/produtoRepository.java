package com.ads.atividadecadastro.repesitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ads.atividadecadastro.entities.Produto;

@Repository
public interface produtoRepository extends JpaRepository<Produto, Integer> {

}
