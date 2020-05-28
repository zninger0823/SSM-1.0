package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.enums.ProductStateEnum;

public class ProductExecution {
	// ���״̬
		private int state;

		// ״̬��ʶ
		private String stateInfo;

		// ��������
		private int count;

		// ������product����ɾ����Ʒ��ʱ���ã�
		private Product product;

		// ��ȡ��product�б�(��ѯ��Ʒ�б��ʱ����)
		private List<Product> productList;

		public ProductExecution() {
		}

		// ʧ�ܵĹ�����
		public ProductExecution(ProductStateEnum stateEnum) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
		}

		// �ɹ��Ĺ�����
		public ProductExecution(ProductStateEnum stateEnum, Product product) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.product = product;
		}

		// �ɹ��Ĺ�����
		public ProductExecution(ProductStateEnum stateEnum,
				List<Product> productList) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.productList = productList;
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

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public List<Product> getProductList() {
			return productList;
		}

		public void setProductList(List<Product> productList) {
			this.productList = productList;
		}
}
