package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.ProductData;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, Long> {

    Page<ProductData> findAllByNameContainsIgnoreCase(String param, Pageable pageable);

    ProductData getProductDataByName(String name);

    Page<ProductData> findAllByNameContainsIgnoreCaseAndIdNotIn(String param, List<Long> id, Pageable pageable);

    Page<ProductData> getAllByIdNotIn(List<Long> id, Pageable pageable);

    @Query("select pd " +
            "from ProductData pd " +
            "where (pd.promotionProducts.size = 0 or pd.id in ( select pd.id from ProductData pd join pd.promotionProducts pp join pp.promotion ppp on ppp.dateEnd < ?1 )) and pd.name like (concat('%', ?2, '%'))")
    List<ProductData> findAllWithoutPromotionAndSearchParam(Date date, String searchParam);

    @Query("select pd " +
            "from ProductData pd " +
            "where pd.promotionProducts.size = 0 or pd.id in ( select pd.id from ProductData pd join pd.promotionProducts pp join pp.promotion ppp on ppp.dateEnd < ?1 )")
    List<ProductData> findAllWithoutPromotion(Date date);
}