package com.shilin.example.repository;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.shilin.example.entity.GoodsItem;

public interface GoodsItemRepository extends ElasticsearchRepository<GoodsItem,Long>{
	
	List<GoodsItem> findByBrand(String brandName);
	
	List<GoodsItem> findByBrandLike(String brandName);
	
	List<GoodsItem> findByBrandAndPrice(String brandName, double price);
	
	List<GoodsItem> findByPriceBetween(double price1, double price2);
	
	List<GoodsItem> findByPriceLessThan(double maxPrice);
	
	List<GoodsItem> findByPriceGreaterThan(double minPrice);
	
	List<GoodsItem> findByTitleIn(List<String> titleList);
	
	List<GoodsItem> findByTitleNotIn(List<String> titleList);
}
