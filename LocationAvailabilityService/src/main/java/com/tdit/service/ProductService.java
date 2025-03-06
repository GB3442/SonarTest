package com.tdit.service;
import com.tdit.SellerRepository.ProductRepo;
import com.tdit.SellerRepository.SellerRepo;
import com.tdit.model.Product;
import com.tdit.model.ProductRequestDTO;
import com.tdit.model.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepository;
    private final SellerRepo sellerRepository;

    @Transactional
    public Product addProduct(ProductRequestDTO productRequest) {
        Seller seller = sellerRepository.findById(productRequest.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setSeller(seller);

        return productRepository.save(product);
    }
}
