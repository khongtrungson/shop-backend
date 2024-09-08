package com.khongtrungson.shopapp.services.implement;

import com.khongtrungson.shopapp.dtos.requests.ProductDTO;
import com.khongtrungson.shopapp.dtos.responses.PageResponse;
import com.khongtrungson.shopapp.dtos.responses.ProductResponse;
import com.khongtrungson.shopapp.entities.Category;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.ProductImage;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.exceptions.InvalidParamException;
import com.khongtrungson.shopapp.mappers.ProductMapper;
import com.khongtrungson.shopapp.repositories.CategoryRepository;
import com.khongtrungson.shopapp.repositories.ProductImageRepository;
import com.khongtrungson.shopapp.repositories.ProductRepository;
import com.khongtrungson.shopapp.repositories.criteria.SearchProductRepository;
import com.khongtrungson.shopapp.services.IProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageRepository productImageRepository;
    private final SearchProductRepository searchProductRepository;
    @Override
    @Transactional
    public Product createProduct(ProductDTO productDTO )throws IOException {

        Category category = categoryRepository.findById(productDTO.getCategoryId()).
                orElseThrow(()->new DataNotFoundException("category is not exist"));
        Product product = ProductMapper.dtoToEntity(productDTO,category);
         var newProduct =productRepository.save(product);
            createProductImages(newProduct.getId(),productDTO.getFiles());
         return newProduct;
    }

    @Override
    public ProductResponse getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(()->new DataNotFoundException("product is not exist"));
        var productResponse = ProductMapper.entityToResponse(product);
        return productResponse;
    }




    @Override
    public Product updateProduct(long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).
                orElseThrow(()->new DataNotFoundException("product is not exist"));
        Category category = categoryRepository.findById(productDTO.getCategoryId()).
                orElseThrow(()->new DataNotFoundException("category is not exist"));
        Product updatedProduct = ProductMapper.updateEntity(productDTO,product,category);
        return  productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }



    @Override
    public List<ProductResponse> findProductsByIds(List<Long> productIds) {
        return productRepository.findByIdIsIn(productIds).stream().
                map((product)->ProductMapper.entityToResponse(product)).collect(Collectors.toList());
    }

    @Override
    public PageResponse<List<ProductResponse>> searchProduct(int pageNo, int pageSize, String sortBy, String[] search) {
        return searchProductRepository.searchByCriteria(pageNo,pageSize,sortBy,search);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream().
                map((product -> ProductMapper.entityToResponse(product))).collect(Collectors.toList());
    }


    @Override
    public boolean createProductImages(Long productId, List<MultipartFile> files) throws DataNotFoundException, IOException {
        Product product = productRepository.findById(productId).orElseThrow(()->new DataNotFoundException("product is not exist"));
        // tồn tại product
        if (files.isEmpty()) {
            throw new DataNotFoundException("file is not valid");
        }
        if (files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
            throw new DataNotFoundException("file'number must less than "+ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        if(product.getProductImages() != null) {
            if (product.getProductImages().size() + files.size() > ProductImage.MAXIMUM_IMAGES_PER_PRODUCT) {
                throw new DataNotFoundException("file'number must be less than " + ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
            }
        }
        for (MultipartFile file : files) {
            String fileName = validAndStoreFile(file);
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setImageUrl(fileName);
            productImageRepository.save(productImage);
        }
        return true;
    }
    private String validAndStoreFile(MultipartFile file) throws InvalidParamException, IOException {

        if (file.getSize() > 10 * 1024 * 1024) {
            throw new InvalidParamException("file'size is too large");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new InvalidParamException("file must be the image file");
        }
        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image file format");
        }
        String fileName = storeFile(file);
        return fileName;
    }

    private String storeFile(MultipartFile file) throws IOException {

        // Generate a unique filename using UUID
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Save the file to the "uploads" directory
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        Path destination = uploadDir.resolve(uniqueFileName);
        Files.copy(file.getInputStream(), destination);


        return uniqueFileName;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
