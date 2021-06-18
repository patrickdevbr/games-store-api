package br.com.supera.game.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public Page<Product> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<Product> findAll() {
		return repository.findAll();
	}
}
