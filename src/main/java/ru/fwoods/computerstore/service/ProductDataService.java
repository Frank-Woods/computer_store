package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.model.ProductDataCart;
import ru.fwoods.computerstore.repository.ProductDataRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductDataService {
    @Autowired
    private ProductDataRepository productDataRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AttributeCategoryService attributeCategoryService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ValueService valueService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PromotionProductService promotionProductService;

    @org.springframework.beans.factory.annotation.Value("${productData.upload.path}")
    private String productDataUploadPath;

    public List<ProductData> getAllProducts() {
        return productDataRepository.findAll();
    }

    private String saveProductDataImage(MultipartFile file) {
        return fileService.createFile(productDataUploadPath, file);
    }

    public void save(ru.fwoods.computerstore.model.ProductData productData, List<MultipartFile> images) {
        ProductData productDataDomain = new ProductData();

        productDataDomain.setName(productData.getName());
        productDataDomain.setDescription(productData.getDescription());
        productDataDomain.setCost(productData.getCost());
        productDataDomain.setCategory(productCategoryService.getCategoryById(productData.getCategory()));
        productDataDomain.setCountry(countryService.getCountryById(productData.getCountry()));
        productDataDomain.setManufacturer(manufacturerService.getManufacturerById(productData.getManufacturer()));

        ProductData productDataDomainSaved = productDataRepository.save(productDataDomain);

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> imageService.saveProductDataImage(image, productDataDomainSaved));
        }

        productDataRepository.save(productDataDomainSaved);
    }

    public Page<ProductData> getPageProducts(Pageable pageable) {
        return productDataRepository.findAll(pageable);
    }

    public Page<ru.fwoods.computerstore.model.rest.ProductData> getPageProductsSearch(Pageable pageable, String searchParam, List<Long> id) {
        Page<ProductData> productData;
        if (id == null || id.isEmpty()) {
            if (searchParam.isEmpty()) {
                productData = productDataRepository.findAll(pageable);
            } else {
                productData = productDataRepository.findAllByNameContainsIgnoreCase(searchParam, pageable);
            }
        } else {
            if (searchParam.isEmpty()) {
                productData = productDataRepository.getAllByIdNotIn(id, pageable);
            } else {
                productData = productDataRepository.findAllByNameContainsIgnoreCaseAndIdNotIn(searchParam, id, pageable);
            }
        }
        return productData.map(product -> {
            ru.fwoods.computerstore.model.rest.ProductData productDataRest = new ru.fwoods.computerstore.model.rest.ProductData();
            productDataRest.setId(product.getId());
            productDataRest.setName(product.getName());
            return productDataRest;
        });
    }

    public ProductData getProductDataByName(String name) {
        return productDataRepository.getProductDataByName(name);
    }

    public ProductData getProductDataById(Long id) {
        return productDataRepository.findById(id).orElse(null);
    }

    public void update(ru.fwoods.computerstore.model.ProductData productData, List<MultipartFile> images) {
        ProductData productDataDomain = productDataRepository.getOne(productData.getId());

        productDataDomain.setName(productData.getName());
        productDataDomain.setDescription(productData.getDescription());
        productDataDomain.setCost(productData.getCost());
        productDataDomain.setCategory(productCategoryService.getCategoryById(productData.getCategory()));
        productDataDomain.setCountry(countryService.getCountryById(productData.getCountry()));
        productDataDomain.setManufacturer(manufacturerService.getManufacturerById(productData.getManufacturer()));

        List<Image> productDataImages = imageService.getImagesByProductDataId(productDataDomain.getId());
        List<Long> imageIds = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> {
                if (!Objects.equals(image.getContentType(), "null")) {
                    imageService.saveProductDataImage(image, productDataDomain);
                } else {
                    try {
                        Long id = Long.parseLong(image.getOriginalFilename());
                        imageIds.add(id);
                    } catch (NullPointerException | NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        productDataImages.forEach(productDataImage -> {
            if (!imageIds.contains(productDataImage.getId())) {
                imageService.deleteProductDataImage(productDataImage.getId());
            }
        });

        productDataRepository.save(productDataDomain);
    }

    private void saveImage(List<MultipartFile> image, ProductData productDataDomain) {
        if (image != null && !image.isEmpty()) {
            image.forEach(banner -> {
                if (!Objects.equals(banner.getContentType(), "null")) {
                    Image newImage = new Image();
                    String bannerFilename = saveProductDataImage(banner);
                    newImage.setFilename(bannerFilename);
                    newImage.setProductData(productDataDomain);
                    imageService.save(newImage);
                }
            });
        }
    }

    private void saveProductData(ru.fwoods.computerstore.model.ProductData productData, ProductData productDataDomain) {
        productDataDomain.setName(productData.getName());
        productDataDomain.setDescription(productData.getDescription());
        productDataDomain.setCost(productData.getCost());

        Manufacturer manufacturer = manufacturerService.getManufacturerById(productData.getManufacturer());
        Country country = countryService.getCountryById(productData.getCountry());
        ProductCategory category = productCategoryService.getCategoryById(productData.getCategory());

        productDataDomain.setCountry(country);
        productDataDomain.setManufacturer(manufacturer);
        productDataDomain.setCategory(category);
    }

    public ru.fwoods.computerstore.model.ProductData getProductDataModelById(Long id) {
        ProductData productData = productDataRepository.getOne(id);

        ru.fwoods.computerstore.model.ProductData productDataModel = new ru.fwoods.computerstore.model.ProductData();

        productDataModel.setId(productData.getId());
        productDataModel.setName(productData.getName());
        productDataModel.setDescription(productData.getDescription());
        productDataModel.setCost(productData.getCost());
        productDataModel.setManufacturer(productData.getManufacturer().getId());
        productDataModel.setCountry(productData.getCountry().getId());
        productDataModel.setCategory(productData.getCategory().getId());

        return productDataModel;
    }

    public List<ru.fwoods.computerstore.model.ProductData> getProductDataSearch(String searchParam) {
        List<ProductData> productDataList;
        if (searchParam.length() > 0) productDataList = productDataRepository.findAllWithoutPromotionAndSearchParam(new Date(), searchParam);
        else productDataList = productDataRepository.findAllWithoutPromotion(new Date());

        return productDataList.stream().map(productData -> {
            ru.fwoods.computerstore.model.ProductData productDataModel = new ru.fwoods.computerstore.model.ProductData();
            productDataModel.setId(productData.getId());
            productDataModel.setName(productData.getName());
            return productDataModel;
        }).collect(Collectors.toList());
    }

    public ProductData saveWithAttributes(ProductData productData) {
        return productDataRepository.save(productData);
    }

    public List<ProductDataCart> getPopularProducts() {
        List<ProductData> productDataList = productDataRepository.findAll();
        List<ProductDataCart> productDataCarts = productDataList.stream().map(productData -> {
            ProductDataCart productDataCart = new ProductDataCart();

            productDataCart.setId(productData.getId());
            productDataCart.setName(productData.getName());
            productDataCart.setDescription(productData.getDescription());
            Image image = imageService.getFirstImageByProductDataId(productData.getId());
            if (image != null) {
                productDataCart.setImage(image.getFilename());
            }
            productDataCart.setCost(productData.getCost());

            if (productData.getReviews().size() > 0) {
                Integer ratingAll = productData.getReviews().stream().filter(review -> review.getStatusReview() == StatusReview.CONFIRMED).reduce(0, (integer, review) -> integer + review.getRating(), Integer::sum);
                productDataCart.setRating(((double)ratingAll / productData.getReviews().size()));
            } else {
                productDataCart.setRating(0.0);
            }

            PromotionProduct promotionProduct = promotionProductService.getPromotionProductByProductData(productData);
            Integer discountCost = productData.getCost();
            Integer discount = 0;
            if (promotionProduct != null) {
                if (new Date().getTime() < promotionProduct.getPromotion().getDateEnd().getTime()) {
                    discount = promotionProduct.getDiscount();
                    discountCost = productData.getCost() * promotionProduct.getDiscount() / 100;
                    productDataCart.setDiscount(promotionProduct.getDiscount());
                }
            }
            productDataCart.setDiscount(discount);
            productDataCart.setDiscountCost(discountCost);

            return productDataCart;
        }).collect(Collectors.toList());

        productDataCarts.sort(Comparator.comparingDouble(ProductDataCart::getRating));

        return productDataCarts.stream().limit(5).collect(Collectors.toList());
    }

    public Page<ProductDataCart> getProductDataCartsByPromotion(Pageable pageable, Long id) {
        Page<ProductData> productData = getPageProductsByPromotion(id, pageable);
        return getProductDataCartPage(productData);
    }

    public Page<ProductDataCart> getProductDataCartPage(Page<ProductData> page) {
        return page.map(pd -> {
            ProductDataCart productDataCart = new ProductDataCart();

            productDataCart.setId(pd.getId());
            productDataCart.setName(pd.getName());
            productDataCart.setDescription(pd.getDescription());
            Image image = imageService.getFirstImageByProductDataId(pd.getId());
            if (image != null) {
                productDataCart.setImage(image.getFilename());
            }
            productDataCart.setCost(pd.getCost());

            if (pd.getReviews().size() > 0) {
                Integer ratingAll = pd.getReviews().stream()
                        .filter(review -> review.getStatusReview() == StatusReview.CONFIRMED)
                        .reduce(0, (integer, review) -> integer + review.getRating(), Integer::sum);
                productDataCart.setRating(((double)ratingAll / pd.getReviews().size()));
            } else {
                productDataCart.setRating(0.0);
            }

            PromotionProduct promotionProduct = promotionProductService.getPromotionProductByProductData(pd);
            Integer discountCost = pd.getCost();
            Integer discount = 0;
            if (promotionProduct != null) {
                if (new Date().getTime() < promotionProduct.getPromotion().getDateEnd().getTime()) {
                    discount = promotionProduct.getDiscount();
                    discountCost = pd.getCost() * promotionProduct.getDiscount() / 100;
                    productDataCart.setDiscount(promotionProduct.getDiscount());
                }
            }
            productDataCart.setDiscount(discount);
            productDataCart.setDiscountCost(discountCost);

            return productDataCart;
        });
    }

    private Page<ProductData> getPageProductsByPromotion(Long id, Pageable pageable) {
        return productDataRepository.getAllByPromotion(id, pageable);
    }

    public Integer getDiscountCost(Long id) {
        ProductData pd = productDataRepository.getOne(id);

        PromotionProduct promotionProduct = promotionProductService.getPromotionProductByProductData(pd);
        Integer discountCost = pd.getCost();
        if (promotionProduct != null) {
            if (new Date().getTime() < promotionProduct.getPromotion().getDateEnd().getTime()) {
                discountCost = pd.getCost() * promotionProduct.getDiscount() / 100;
            }
        }
        return discountCost;
    }

    public Double getRating(Long id) {
        ProductData pd = productDataRepository.getOne(id);

        if (pd.getReviews().size() > 0) {
            Integer ratingAll = pd.getReviews().stream()
                    .filter(review -> review.getStatusReview() == StatusReview.CONFIRMED)
                    .reduce(0, (integer, review) -> integer + review.getRating(), Integer::sum);
            return (double)ratingAll / pd.getReviews().size();
        } else {
            return 0.0;
        }
    }

    public Page<ProductData> getPageProductsByCategory(Long category, List<Long> manufacturers, String cost, Pageable pageable) {
        List<ProductData> productDataList = productDataRepository.getAllByCategoryId(category);

        List<String> costs = new ArrayList<>();

        Pattern pattern = Pattern.compile("(?<=Р)\\d*");
        Matcher matcher = pattern.matcher(cost);
        while (matcher.find()) {
            costs.add(cost.substring(matcher.start(), matcher.end()));
        }

        Integer min = Integer.parseInt(costs.get(0));
        Integer max = Integer.parseInt(costs.get(1));

        productDataList = productDataList.stream().filter(productData ->
                manufacturers.contains(productData.getManufacturer().getId()) && productData.getCost() >= min && productData.getCost() <= max
        ).collect(Collectors.toList());

        return new PageImpl<>(productDataList, pageable, 0);
    }

    public Integer getMaxCostInCategory(Long category) {
        List<ProductData> productDataList = productDataRepository.getAllByCategoryId(category);
        if (productDataList != null) {
            productDataList.sort((o1, o2) -> o2.getCost() - o1.getCost());
            return productDataList.get(0).getCost();
        } else {
            return 0;
        }
    }

    public List<ProductData> getAllByCategoryId(Long category) {
        return productDataRepository.getAllByCategoryId(category);
    }
}
