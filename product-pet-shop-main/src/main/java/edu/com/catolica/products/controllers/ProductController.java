package edu.com.catolica.products.controllers;

import edu.com.catolica.products.domain.Product;
import edu.com.catolica.products.dtos.ProductDto;
import edu.com.catolica.products.dtos.ResponseDTO;
import edu.com.catolica.products.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static edu.com.catolica.products.constants.ProductConstants.CODE_STATUS_201;
import static edu.com.catolica.products.constants.ProductConstants.PRODUCT_MESSAGE_CREATED_201;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;


    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody ProductDto dto) {
        service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDTO.builder()
                        .statusCode(CODE_STATUS_201)
                        .message(PRODUCT_MESSAGE_CREATED_201)
                        .build());
    }

    @GetMapping("/findAll")
    public Page<ProductDto> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        return ResponseEntity.ok(service.updateProduct(id, dto));
    }
}
