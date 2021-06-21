package br.com.supera.game.store.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.repositories.ProductRepository;
import br.com.supera.game.store.services.exceptions.DatabaseIntegrityException;
import br.com.supera.game.store.services.exceptions.ResourceNotFoundException;

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

	public Product findById(Long id) {
		Optional<Product> opt = repository.findById(id);
		return opt.orElseThrow(() -> new ResourceNotFoundException(Product.class, id));
	}

	public Product insert(Product obj) {
		return repository.save(obj);
	}

	public Product update(Long id, Product obj) {
		try {
			Product obj2 = repository.getById(id);
			updateData(obj, obj2);
			return repository.save(obj2);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(Product.class, id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(Product.class, id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseIntegrityException(e.getMessage());
		}
	}

	private void updateData(Product objFrom, Product objTo) {
		objTo.setName(objFrom.getName());
		objTo.setPrice(objFrom.getPrice());
		objTo.setScore(objFrom.getScore());
		objTo.setImage(objFrom.getImage());
	}

}
