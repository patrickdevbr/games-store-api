package br.com.supera.game.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supera.game.store.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
