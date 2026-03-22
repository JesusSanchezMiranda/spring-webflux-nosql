package ap1.jesussanchez.service;

import ap1.jesussanchez.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> findById(String id);

    Mono<Product> save(Product product);

    Mono<Product> update(String id, Product product);

    Mono<Product> disable(String id);

    Mono<Product> enable(String id);
}
