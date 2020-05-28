package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

//添加店铺的返回类型：比如添加店铺若失败将返回controller层
public class ShopExecution {
	
	//结果状态
	private int state;
	
	
	//状态标识
	private String stateInfo;
	
	//店铺数量
	private int count;
	
	
	//操作的shop（增删改店铺的时候用到）
	private Shop shop;
	
	//shop列表（查询店铺列表的时候使用）
	private List<Shop> shopList;
	
	//构造函数
	public ShopExecution(){
		
		
	}
	//构造器，枚举类型存放状态值,店铺操作失败时候使用的的构造器
	public ShopExecution(ShopStateEnum stateEnum){
		this.state=stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
		
	}
	//店铺操作成功时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum,Shop shop){
		this.state=stateEnum.getState();
		this.stateInfo=stateEnum.getStateInfo();
		this.shop=shop;
	}
	//店铺操作成功时候使用的构造器
		public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList){
			this.state=stateEnum.getState();
			this.stateInfo=stateEnum.getStateInfo();
			this.shopList=shopList;
		}
		public int getState() {
			return state;
		}
		public void setState(int state) {
			this.state = state;
		}
		public String getStateInfo() {
			return stateInfo;
		}
		public void setStateInfo(String stateInfo) {
			this.stateInfo = stateInfo;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public Shop getShop() {
			return shop;
		}
		public void setShop(Shop shop) {
			this.shop = shop;
		}
		public List<Shop> getShopList() {
			return shopList;
		}
		public void setShopList(List<Shop> shopList) {
			this.shopList = shopList;
		}
		
		
		
		
		
}








