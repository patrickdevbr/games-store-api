package br.com.supera.game.store.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.supera.game.store.entities.Product;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService service;

	@Test
	void productListByNameTest() {
		Page<Product> page = service.findAll(PageRequest.of(0, 10, Sort.by("name")));
		Page<Product> pageDescending = service.findAll(PageRequest.of(0, 10, Sort.by("name").descending()));

		Comparator<Product> comp = (p1, p2) -> p1.getName().compareTo(p2.getName());
		boolean isSorted = listIsSorted(page.getContent(), comp, false);
		boolean isSortedDescending = listIsSorted(pageDescending.getContent(), comp, true);

		assertThat(isSorted).isEqualTo(true);
		assertThat(isSortedDescending).isEqualTo(true);
	}

	@Test
	void productListByPriceTest() {
		Page<Product> page = service.findAll(PageRequest.of(0, 10, Sort.by("price")));
		Page<Product> pageDescending = service.findAll(PageRequest.of(0, 10, Sort.by("price").descending()));

		Comparator<Product> comp = (p1, p2) -> p1.getPrice().compareTo(p2.getPrice());
		boolean isSorted = listIsSorted(page.getContent(), comp, false);
		boolean isSortedDescending = listIsSorted(pageDescending.getContent(), comp, true);

		assertThat(isSorted).isEqualTo(true);
		assertThat(isSortedDescending).isEqualTo(true);
	}

	@Test
	void productListByScoreTest() {
		Page<Product> page = service.findAll(PageRequest.of(0, 10, Sort.by("score")));
		Page<Product> pageDescending = service.findAll(PageRequest.of(0, 10, Sort.by("score").descending()));

		Comparator<Product> comp = (p1, p2) -> p1.getScore().compareTo(p2.getScore());
		boolean isSorted = listIsSorted(page.getContent(), comp, false);
		boolean isSortedDescending = listIsSorted(pageDescending.getContent(), comp, true);

		assertThat(isSorted).isEqualTo(true);
		assertThat(isSortedDescending).isEqualTo(true);
	}

	private boolean listIsSorted(List<Product> list, Comparator<Product> comp, boolean descending) {
		BiPredicate<Product, Product> predicate = descending ? (p1, p2) -> comp.compare(p1, p2) > 0
				: (p1, p2) -> comp.compare(p1, p2) < 0;

		for (int i = 0; i < list.size() - 1; i++) {
			if (!predicate.test(list.get(i), list.get(i + 1)))
				return false;
		}
		return true;
	}
}
