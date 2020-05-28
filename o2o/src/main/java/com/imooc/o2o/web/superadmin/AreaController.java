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
@RequestMapping("/superadmin")//表示调用controller方法必须在/superadmin路径下调用 
public class AreaController {
	org.slf4j.Logger logger=LoggerFactory.getLogger(AreaController.class);
	@Autowired	//当层序调用service层时会将service层注入
	private AreaService areaService;
	
	//加路由，告诉访问主目录下的哪个方法在浏览器输入http://localhost:8080/o2o/superadmin/listarea
	@RequestMapping(value="/listarea",method=RequestMethod.GET)
	//modelMap自动转换成json对象返回给前端
	@ResponseBody
	
	//modelMap用于存放方法返回值
	//list获取service层返回的区域列表
  private Map<String, Object> listArea(){
		logger.info("=====start======");
		long startTime=System.currentTimeMillis();
		
	  Map<String, Object> modelMap=new HashMap<String,Object>();
	  List<Area> list=new ArrayList<Area>();
	  //捕捉可能出现的异常
	  try{
		  list=areaService.getAreaList();
		  modelMap.put("rows",list);
		  modelMap.put("total", list.size());
	  }catch(Exception e){
		  e.printStackTrace();
		  //返回错误信息
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










