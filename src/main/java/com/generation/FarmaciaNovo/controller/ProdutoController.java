package com.generation.FarmaciaNovo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.FarmaciaNovo.model.Produto;
import com.generation.FarmaciaNovo.repository.CategoriaRepository;
import com.generation.FarmaciaNovo.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins="*",allowedHeaders="*")
public class ProdutoController extends Produto {
	
	@Autowired
	CategoriaRepository repositoryCategoria;
	
	@Autowired
	ProdutoRepository repositoryProduto;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAllProduto(){
		return ResponseEntity.ok(repositoryProduto.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getByIdProduto(Long id){
		return repositoryProduto.findById(id)
		.map(resultado -> ResponseEntity.ok(resultado))
		.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getAllByNomeProduto(@PathVariable String nome){
		return ResponseEntity.ok(repositoryProduto.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody @Valid Produto produto){
		return repositoryProduto.findById(produto.getCategoria().getId())
		.map(resultado -> ResponseEntity.ok(repositoryProduto.save(resultado)))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		
		
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody @Valid Produto produto){
		return repositoryProduto.findById(produto.getId())
		.map(resultado -> ResponseEntity.ok(repositoryProduto.save(resultado)))
		.orElse(ResponseEntity.notFound().build());
		
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id){
		repositoryProduto.deleteById(id);
		return repositoryProduto.findById(id)
		.map(resultado -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null))
		.orElse(ResponseEntity.notFound().build());
		
	}
	

}
