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

import com.ads.atividadecadastro.entities.Pedido;
import com.ads.atividadecadastro.repesitory.pedidoRepository;

@RestController
@RequestMapping("/pedido")
public class pedidoController {

    @Autowired

    pedidoRepository repository;

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(Pedido pedido) {

        repository.save(pedido);

        return new ResponseEntity<>("Cadastrado com sucesso", HttpStatus.OK);

    }

    @GetMapping("/lista")
    public ResponseEntity<List<Pedido>> listar() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos = repository.findAll();

        return new ResponseEntity<>(pedidos, HttpStatus.OK);

    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable("id") int id) {

        Pedido pedido = repository.findById(id).orElse(null);

        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK);
        }

        return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") int id) {
        Pedido pedido = repository.findById(id).orElse(null);

        if (pedido != null) {
            repository.delete(pedido);
            return new ResponseEntity<>("Tabela excluída", HttpStatus.OK);
        }

        return new ResponseEntity<>("Registro não encontrado", HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> editar(Pedido newPedido, @PathVariable("id") int id) {
        Pedido pedido = repository.findById(id).orElse(null);

        pedido.setData(newPedido.getData());
        pedido.setValorTotal(newPedido.getValorTotal());

        repository.save(pedido);

        return new ResponseEntity<>("Editado com sucesso", HttpStatus.OK);

    }

}
