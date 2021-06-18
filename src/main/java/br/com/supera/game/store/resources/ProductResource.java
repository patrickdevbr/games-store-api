package br.com.supera.game.store.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.supera.game.store.entities.Product;
import br.com.supera.game.store.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping
	public ResponseEntity<Page<Product>> findAll(
			@RequestParam(value = "sortby", required = false, defaultValue = "name") String sortby,
			@RequestParam(value = "descending", required = false, defaultValue = "false") Boolean descending,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

		PageRequest pgRequest = descending
				? PageRequest.of(page, limit, Sort.by(sortby).descending())
				: PageRequest.of(page, limit, Sort.by(sortby.toLowerCase()));

		Page<Product> products = service.findAll(pgRequest);
		return ResponseEntity.ok().body(products);
	}

}
