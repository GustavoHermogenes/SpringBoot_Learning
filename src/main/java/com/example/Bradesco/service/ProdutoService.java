package com.example.Bradesco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Bradesco.model.Produto;
import com.example.Bradesco.repository.ProdutoRepository;


// Usado para implementar a lógica de negócios relacionada aos produtos.

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository){
        
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> ListarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> BuscarPorId(Long id){
        return produtoRepository.findById(id);
    }

    public Produto SalvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void DeletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

}
