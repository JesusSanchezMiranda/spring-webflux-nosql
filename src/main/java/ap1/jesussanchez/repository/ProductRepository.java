package ap1.jesussanchez.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ap1.jesussanchez.model.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String>{
    
}
