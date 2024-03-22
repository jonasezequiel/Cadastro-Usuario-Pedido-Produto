package com.ads.atividadecadastro.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.atividadecadastro.entities.Produto;

import com.ads.atividadecadastro.repesitory.produtoRepository;

@RestController
@RequestMapping("/produto")
public class produtoController {

    @Autowired

    produtoRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(Produto produto) {

        repository.save(produto);

        return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK);

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = new ArrayList<>();
        produtos = repository.findAll();

        return new ResponseEntity<>(produtos, HttpStatus.OK);

    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable("id") int id) {

        Produto produto = repository.findById(id).orElse(null);

        if (produto != null) {
            return new ResponseEntity<>(produto, HttpStatus.OK);
        }

        return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") int id) {
        Produto produto = repository.findById(id).orElse(null);

        if (produto != null) {
            repository.delete(produto);
            return new ResponseEntity<>("Tabela excluída", HttpStatus.OK);
        }

        return new ResponseEntity<>("Registro não encontrado", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> editar(Produto newEmpresa, @PathVariable("id") int id) {
        Produto produto = repository.findById(id).orElse(null);

        produto.setNome(newEmpresa.getNome());
        produto.setValor(newEmpresa.getValor());

        repository.save(produto);

        return new ResponseEntity<>("Editado com sucesso", HttpStatus.OK);

    }
}
