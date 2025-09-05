package edu.com.catolica.products.services;


import edu.com.catolica.products.domain.Product;
import edu.com.catolica.products.dtos.ProductDto;
import edu.com.catolica.products.exceptions.ProductNotFoundException;
import edu.com.catolica.products.mapper.ProductMapper;
import edu.com.catolica.products.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;
    private final IProductRepository repository;


    public Product save(ProductDto dto) {
        var existingProduct = repository.findByNameAndMaker(dto.getName(), dto.getMaker());
        if (existingProduct.isPresent()) {
            throw new ProductNotFoundException("A product with this name already exists for this maker!");
        }
        var entity = mapper.dtoToEntity(dto);
        return repository.save(entity);
    }


    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    public Page<ProductDto> findAll(Pageable pageable) {
        var products = repository.findAll(pageable);
        return products.map(p -> ProductDto.builder()
                .name(p.getName())
                .description(p.getDescription())
                .price(p.getPrice())
                .maker(p.getMaker())
                .build());
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    public Product updateProduct(Long id, ProductDto productUpdated) {
        Product existing = findById(id);

        if (productUpdated.getName() != null) {
            existing.setName(productUpdated.getName());
        }
        if (productUpdated.getDescription() != null) {
            existing.setDescription(productUpdated.getDescription());
        }
        if (productUpdated.getPrice() != null) {
            existing.setPrice(productUpdated.getPrice());
        }
        if (productUpdated.getMaker() != null) {
            existing.setMaker(productUpdated.getMaker());
        }

        return repository.save(existing);
    }
}
