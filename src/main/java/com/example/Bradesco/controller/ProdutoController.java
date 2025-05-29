package com.example.Bradesco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bradesco.model.Produto;
import com.example.Bradesco.service.ProdutoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> ListarProdutos() {
        return produtoService.ListarProdutos();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity <Produto> BuscarProduto(@PathVariable Long id) {
        return produtoService.BuscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Produto criarProduto(@RequestBody Produto produto) {
        
        return produtoService.SalvarProduto(produto);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtoService.DeletarProduto(id);
        return ResponseEntity.noContent().build();
    }


}
