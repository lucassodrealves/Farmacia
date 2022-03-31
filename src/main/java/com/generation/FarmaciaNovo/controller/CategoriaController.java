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

import com.generation.FarmaciaNovo.model.Categoria;
import com.generation.FarmaciaNovo.repository.CategoriaRepository;
import com.generation.FarmaciaNovo.repository.ProdutoRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins="*",allowedHeaders="*")
public class CategoriaController extends Categoria {
	
	@Autowired
	CategoriaRepository repositoryCategoria;
	
	@Autowired
	ProdutoRepository repositoryProduto;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getCategorias(){
		return ResponseEntity.ok(repositoryCategoria.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return repositoryCategoria.findById(id)
		.map(resultado -> ResponseEntity.ok(resultado))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("nome/{nome}")
	public ResponseEntity<List<Categoria>> getAllByNome(@PathVariable String nome){
		return ResponseEntity.ok(repositoryCategoria.findAllByNomeContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody @Valid Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryCategoria.save(categoria));
		
	}
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody @Valid Categoria categoria){
	      return repositoryCategoria.findById(categoria.getId())
		  .map(resultado -> ResponseEntity.ok(repositoryCategoria.save(resultado)))
		  .orElse(ResponseEntity.notFound().build());
		
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
		repositoryCategoria.deleteById(id);
		return repositoryCategoria.findById(id)
	    .map(resultado -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(null))
	    .orElse(ResponseEntity.notFound().build());
	}
	
	
	

}
