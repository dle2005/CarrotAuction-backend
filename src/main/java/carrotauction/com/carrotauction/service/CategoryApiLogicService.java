package carrotauction.com.carrotauction.service;

import carrotauction.com.carrotauction.model.entity.Category;
import carrotauction.com.carrotauction.network.Header;
import carrotauction.com.carrotauction.network.request.CategoryApiRequest;
import carrotauction.com.carrotauction.network.response.AvgPriceApiResponse;
import carrotauction.com.carrotauction.network.response.CategoryApiResponse;
import carrotauction.com.carrotauction.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<List<CategoryApiResponse>> search(Pageable pageable) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();

        Category category = Category.builder()
                .category(categoryApiRequest.getCategory())
                .buy_year(categoryApiRequest.getBuy_year())
                .buy_price(categoryApiRequest.getBuy_price())
                .status(categoryApiRequest.getStatus())
                .build();

        Category newCategory = baseRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        Optional<Category> category = baseRepository.findById(id);

        return category.map(selectCategory -> response(selectCategory))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();

        Optional<Category> optional = baseRepository.findById(categoryApiRequest.getId());

        return optional.map(category -> {
            category.setCategory(categoryApiRequest.getCategory())
                    .setBuy_year(categoryApiRequest.getBuy_year())
                    .setBuy_price(categoryApiRequest.getBuy_price())
                    .setStatus(categoryApiRequest.getStatus());

            return category;
        })
                .map(category -> baseRepository.save(category))
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = baseRepository.findById(id);

        return optional.map(category -> {
            baseRepository.delete(category);
            return Header.OK();
            })
            .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .category(category.getCategory())
                .buy_year(category.getBuy_year())
                .buy_price(category.getBuy_price())
                .status(category.getStatus())
                .build();

        return Header.OK(categoryApiResponse);
    }

    public Header<AvgPriceApiResponse> getAvgPrice(Header<CategoryApiRequest> request) {
        CategoryApiRequest categoryApiRequest = request.getData();
        List<Category> categories = categoryRepository
                .findAllByCategoryAndStatus(categoryApiRequest.getCategory(), categoryApiRequest.getStatus());

        int sum = 0;
        System.out.println(categories.size());
        for (Category category : categories) {
            sum += category.getBuy_price();
        }

        AvgPriceApiResponse avgPriceApiResponse = AvgPriceApiResponse.builder()
                .price((long) (sum / categories.size()))
                .build();

        return Header.OK(avgPriceApiResponse);
    }
}
