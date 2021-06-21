package br.com.supera.game.store.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = service.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
		product = service.update(id, product);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<Product>> findAll(
			@RequestParam(value = "sortby", required = false, defaultValue = "name") String sortby,
			@RequestParam(value = "descending", required = false, defaultValue = "false") Boolean descending,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

		PageRequest pgRequest = descending ? PageRequest.of(page, limit, Sort.by(sortby).descending())
				: PageRequest.of(page, limit, Sort.by(sortby.toLowerCase()));

		Page<Product> products = service.findAll(pgRequest);
		return ResponseEntity.ok().body(products);
	}

}
