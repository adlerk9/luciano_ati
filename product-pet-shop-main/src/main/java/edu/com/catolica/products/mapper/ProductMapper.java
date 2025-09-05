package edu.com.catolica.products.mapper;


import edu.com.catolica.products.domain.Product;
import edu.com.catolica.products.dtos.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public Product dtoToEntity(ProductDto dto) {
        var product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setMaker(dto.getMaker());
        return product;
    }

}
