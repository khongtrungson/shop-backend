package com.khongtrungson.shopapp.controllers;

import com.khongtrungson.shopapp.dtos.requests.ProductDTO;
import com.khongtrungson.shopapp.dtos.responses.CategoryResponse;
import com.khongtrungson.shopapp.dtos.responses.PageResponse;
import com.khongtrungson.shopapp.dtos.responses.ProductResponse;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.services.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Tag(
        name = "CRUD REST APIs for Products "
)
@RestController
@RequestMapping(value = "/products",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final IProductService productService;

    @Operation(summary = "create a product api")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // tại 1 lần gửi request thì có thẻ gửi nhiều kiểu data không như multipart + Json không
    public ResponseData<String> createProduct(
           @Valid @ModelAttribute  ProductDTO productDTO
    ) throws IOException {
        productService.createProduct(productDTO);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("create product")
                .data("create product successfully")
                .build();
    }

    @Operation(summary = "upload list images of product has id")
    @PostMapping(value = "/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseData<String> uploadImages(
            @RequestPart(name = "files") List<MultipartFile> files ,
            @PathVariable(name = "id") @Positive Long id
    ) throws IOException {
        productService.createProductImages(id,files);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("create images")
                .data("create images successfully")
                .build();
    }

    @Operation(summary = "get image has the name")
    @GetMapping(value = "/images/{imageName}",produces = "image/png")
    public UrlResource viewImage(
            @PathVariable String imageName
    ) throws DataNotFoundException, MalformedURLException {
        Path imagePath = Paths.get("uploads/" + imageName);
        UrlResource resource = new UrlResource(imagePath.toUri());
        if (!resource.exists()) {
            throw new DataNotFoundException("image not found");
        }
        return resource;
    }

    @Operation(summary = "search a page of product api")
    @GetMapping("/page")
    public ResponseData<PageResponse<List<ProductResponse>>> searchProducts(
            @RequestParam(defaultValue = "1",required = false) int pageNo,
            @RequestParam(defaultValue = "7",required = false) int pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String... search) {
       var pages =  productService.searchProduct(pageNo,pageSize,sortBy, search);

        return ResponseData.<PageResponse<List<ProductResponse>>>builder()
                .status(HttpStatus.OK.value())
                .message("the products with specific conditions")
                .data(pages)
                .build();
    }


    @Operation(summary = "get all products api")
    @GetMapping("/all")
    public ResponseData<List<ProductResponse>> getAllProducts() {
        var products = productService.findAllProducts();
        return ResponseData.<List<ProductResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("list all product")
                .data(products)
                .build();
    }

    @Operation(summary = "get a product has id")
    @GetMapping("/{id}")
    public ResponseData<ProductResponse> getProductById(@PathVariable("id") @Positive Long productId) throws Exception {
       var product =  productService.getProductById(productId);
      return ResponseData.<ProductResponse>builder()
              .status(HttpStatus.OK.value())
              .message("product has id : "+productId)
              .data(product)
              .build();
    }

    @Operation(summary = "delete a product has id")
    @DeleteMapping("/{id}")
    public ResponseData<String> deleteProduct(@PathVariable @Positive Long id) {
     productService.deleteProduct(id);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("delete product action")
                .data("delete product successfully")
                .build();
    }

    @PostMapping("/generateFakeProducts")
    private ResponseData<?> generateFakeProducts() {
        return null;
    }

    @GetMapping("/by-ids")
    public ResponseData<?> getProductsByIds(@RequestParam("ids") String ids) {
        return null;
    }


    @Operation(summary = "update a product has id")
    @PutMapping("/{id}")
    public ResponseData<String> updateProduct(
            @PathVariable("id") @Positive Long id,
            @Valid @RequestBody ProductDTO productDTO
    ) {
        productService.updateProduct(id,productDTO);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("update product action")
                .data("update product successfully")
                .build();
    }
}
