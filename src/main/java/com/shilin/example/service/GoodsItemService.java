package com.shilin.example.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import com.shilin.example.entity.GoodsItem;
import com.shilin.example.repository.GoodsItemRepository;

@Service
public class GoodsItemService {

	@Autowired
	private GoodsItemRepository goodsItemRepository;
	
	public GoodsItem insert() {
		GoodsItem goodsItem = new GoodsItem();
		goodsItem.setId(1L);
		goodsItem.setBrand("小米");
		goodsItem.setTitle("小米手机3");
		goodsItem.setPrice(200.00);
		goodsItem.setCategory("手机");
		goodsItem.setImages("http://image.baidu.com/13123.jpg");
		return goodsItemRepository.save(goodsItem);
	}
	
	public List<GoodsItem> insertAll() {
		List<GoodsItem> paramList = new ArrayList<GoodsItem>();
		GoodsItem goodsItem1 = new GoodsItem(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.baidu.com/13123.jpg");
		GoodsItem goodsItem2 = new GoodsItem(3L, "华为META10", "手机", "华为", 4499.00, "http://image.baidu.com/13123.jpg");
		paramList.add(goodsItem1);
		paramList.add(goodsItem2);
		return (List<GoodsItem>) goodsItemRepository.saveAll(paramList);
	}
	
	public GoodsItem update() {
		GoodsItem goodsItem = new GoodsItem();
		goodsItem.setId(1L);
		goodsItem.setBrand("小米");
		goodsItem.setTitle("小米手机5");
		goodsItem.setPrice(500.00);
		goodsItem.setCategory("手机");
		goodsItem.setImages("http://image.baidu.com/13123.jpg");
		return goodsItemRepository.save(goodsItem);
	}
	
	public List<GoodsItem> findAll(){
		List<GoodsItem> resultList = (List<GoodsItem>) goodsItemRepository.findByPriceBetween(80.00, 4000.00);
		return resultList;
	}
	
	public GoodsItem findById(Long id) {
		GoodsItem result = goodsItemRepository.findById(id).get();
		return result;
	}
	
	public void delete(Long id) {
		goodsItemRepository.deleteById(id);
	}
	
	// 自定义查询
	public void matchQuery() {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		queryBuilder.withQuery(QueryBuilders.matchQuery("title", "小米手机3"));
		Page<GoodsItem> goodsItemList = goodsItemRepository.search(queryBuilder.build());
		
		for(GoodsItem item : goodsItemList) {
			System.out.println(item.toString());
		}
	}
	
	// 自定义查询 分页
	public void matchQueryByPage() {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
		// 分页：
	    int page = 0;
	    int size = 2;
	    queryBuilder.withPageable(PageRequest.of(page,size));
		Page<GoodsItem> goodsItemList = goodsItemRepository.search(queryBuilder.build());
		
		for(GoodsItem item : goodsItemList) {
			System.out.println(item.toString());
		}
	}
	
	// 自定义查询 排序
	public void matchQueryBySort() {
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
		queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));
		Page<GoodsItem> goodsItemList = goodsItemRepository.search(queryBuilder.build());

		for(GoodsItem item : goodsItemList) {
			System.out.println(item.toString());
		}
	}
}
