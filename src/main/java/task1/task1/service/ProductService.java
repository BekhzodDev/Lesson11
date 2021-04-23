package task1.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task1.task1.entity.Attachment;
import task1.task1.entity.Category;
import task1.task1.entity.Measurement;
import task1.task1.entity.Product;
import task1.task1.payload.ProductDto;
import task1.task1.payload.Result;
import task1.task1.repository.AttachmentRepository;
import task1.task1.repository.CategoryRepository;
import task1.task1.repository.MeasurementRepository;
import task1.task1.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    public Result addProduct(ProductDto productDto) {
        boolean existsByNameAndCategory = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategory)
            return new Result("Bunday maxsulot ushbu categoriyada mavjud", false);

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday category ID mavjud emas", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday measurement ID mavjud emas", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday photo ID mavjud emas", false);

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode("1");
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachment(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Maxsulot saqlandi", true);
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public Product getProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return null;
        return optionalProduct.get();
    }

    public Result editProduct(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday maxsulot mavjud emas", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("Bunday category ID mavjud emas", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("Bunday measurement ID mavjud emas", false);
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getAttachmentId());
        if (!optionalAttachment.isPresent())
            return new Result("Bunday photo ID mavjud emas", false);
        boolean existsByNameAndCategory = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategory)
            return new Result("Bunday maxsulot ushbu categoriyada mavjud", false);
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setAttachment(optionalAttachment.get());
        productRepository.save(product);
        return new Result("Maxsulot taxrirlandi", true);
    }

    public Result deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new Result("Bunday maxsulot mavjud emas", false);
        productRepository.deleteById(id);
        return new Result("Maxsulot o'chirildi", true);
    }


}
