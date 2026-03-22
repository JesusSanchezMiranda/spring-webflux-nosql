package ap1.jesussanchez.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ap1.jesussanchez.model.Product;
import ap1.jesussanchez.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin("*")
@RestController
@RequestMapping("v1/api/product")
public class ProductRest {

    private final ProductService productService;

    public ProductRest(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping("/save")
    public Mono<Product> save(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/update/{id}")
    public Mono<Product> update(@PathVariable String id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @PatchMapping("/disable/{id}")
    public Mono<Product> disable(@PathVariable String id) {
        return productService.disable(id);
    }

    @PatchMapping("/enable/{id}")
    public Mono<Product> enable(@PathVariable String id) {
        return productService.enable(id);
    }

}
