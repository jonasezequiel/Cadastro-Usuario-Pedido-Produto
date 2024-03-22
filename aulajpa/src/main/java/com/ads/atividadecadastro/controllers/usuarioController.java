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

import com.ads.atividadecadastro.entities.Usuario;

import com.ads.atividadecadastro.repesitory.usuarioRepository;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    @Autowired

    usuarioRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(Usuario usuario) {

        repository.save(usuario);

        return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK);

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios = repository.findAll();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);

    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") int id) {

        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }

        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") int id) {
        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario != null) {
            repository.delete(usuario);
            return new ResponseEntity<>("Tabela excluída", HttpStatus.OK);
        }

        return new ResponseEntity<>("Registro não encontrado", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> editar(Usuario newUsuario, @PathVariable("id") int id) {
        Usuario usuario = repository.findById(id).orElse(null);

        usuario.setNome(newUsuario.getNome());
        usuario.setUsuario(newUsuario.getUsuario());
        usuario.setSenha(newUsuario.getSenha());

        repository.save(usuario);

        return new ResponseEntity<>("Editado com sucesso", HttpStatus.OK);

    }

}
