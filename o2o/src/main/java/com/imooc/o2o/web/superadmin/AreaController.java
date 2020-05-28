package com.imooc.o2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/superadmin")//��ʾ����controller����������/superadmin·���µ��� 
public class AreaController {
	org.slf4j.Logger logger=LoggerFactory.getLogger(AreaController.class);
	@Autowired	//���������service��ʱ�Ὣservice��ע��
	private AreaService areaService;
	
	//��·�ɣ����߷�����Ŀ¼�µ��ĸ����������������http://localhost:8080/o2o/superadmin/listarea
	@RequestMapping(value="/listarea",method=RequestMethod.GET)
	//modelMap�Զ�ת����json���󷵻ظ�ǰ��
	@ResponseBody
	
	//modelMap���ڴ�ŷ�������ֵ
	//list��ȡservice�㷵�ص������б�
  private Map<String, Object> listArea(){
		logger.info("=====start======");
		long startTime=System.currentTimeMillis();
		
	  Map<String, Object> modelMap=new HashMap<String,Object>();
	  List<Area> list=new ArrayList<Area>();
	  //��׽���ܳ��ֵ��쳣
	  try{
		  list=areaService.getAreaList();
		  modelMap.put("rows",list);
		  modelMap.put("total", list.size());
	  }catch(Exception e){
		  e.printStackTrace();
		  //���ش�����Ϣ
		  modelMap.put("success", false);
		  modelMap.put("errMsg",e.toString());
	  } 
	  logger.error("test error!!");
	  long endTime=System.currentTimeMillis();
	  logger.debug("costTime:[{}ms]",endTime-startTime);
	  logger.info("=====end========");
	return modelMap;
  }
}










