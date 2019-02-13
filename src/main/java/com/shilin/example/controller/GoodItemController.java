package com.shilin.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shilin.example.common.RestResponse;
import com.shilin.example.entity.GoodsItem;
import com.shilin.example.service.GoodsItemService;

@RestController
@RequestMapping(value = "/api/es/goods",produces = {APPLICATION_JSON_VALUE})
public class GoodItemController {
	
	@Autowired
    private GoodsItemService goodsItemService;
	
	@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
	
    @RequestMapping(value = "/createIndex", method = RequestMethod.GET)
    public int createIndex() {
    	elasticsearchTemplate.createIndex(GoodsItem.class);
    	return 11;
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<RestResponse<GoodsItem>> insert(){
    	GoodsItem result = goodsItemService.insert();
    	return ResponseEntity.ok().body(new RestResponse<GoodsItem>("100", "插入成功", result));
    }
    
    @RequestMapping(value = "/insertAll", method = RequestMethod.POST)
    public ResponseEntity<RestResponse<List<GoodsItem>>> insertAll(){
    	List<GoodsItem> result = goodsItemService.insertAll();
    	return ResponseEntity.ok().body(new RestResponse<List<GoodsItem>>("100", "插入成功", result));
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<RestResponse<GoodsItem>> update(){
    	GoodsItem result = goodsItemService.update();
    	return ResponseEntity.ok().body(new RestResponse<GoodsItem>("100", "修改成功", result));
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<RestResponse<List<GoodsItem>>> findAll(){
    	List<GoodsItem> result = goodsItemService.findAll();
    	return ResponseEntity.ok().body(new RestResponse<List<GoodsItem>>("100", "获取数据成功", result));
    }
    
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ResponseEntity<RestResponse<GoodsItem>> find(@PathVariable("id") Long id){
    	GoodsItem result = goodsItemService.findById(id);
    	return ResponseEntity.ok().body(new RestResponse<GoodsItem>("100", "获取数据成功", result));
    }
}
