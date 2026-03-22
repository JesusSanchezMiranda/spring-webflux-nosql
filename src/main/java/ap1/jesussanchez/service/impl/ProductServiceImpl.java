package ap1.jesussanchez.service.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import ap1.jesussanchez.model.Product;
import ap1.jesussanchez.repository.ProductRepository;
import ap1.jesussanchez.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found with id: " + id)));
    }

    @Override
    public Mono<Product> save(Product product) {
        product.setStatus(true);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(String id, Product product) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found with id: " + id)))
                .flatMap(existing -> {
                    existing.setName(product.getName());
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setCategory(product.getCategory());
                    existing.setStock(product.getStock());
                    existing.setUpdatedAt(Instant.now());
                    return productRepository.save(existing);
                });
    }

    @Override
    public Mono<Product> disable(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found with id: " + id)))
                .flatMap(existing -> {
                    existing.setStatus(false);
                    existing.setUpdatedAt(Instant.now());
                    return productRepository.save(existing);
                });
    }

    @Override
    public Mono<Product> enable(String id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found with id: " + id)))
                .flatMap(existing -> {
                    existing.setStatus(true);
                    existing.setUpdatedAt(Instant.now());
                    return productRepository.save(existing);
                });
    }
}
