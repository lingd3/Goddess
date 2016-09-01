package com.imooc.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imooc.dao.GoddessDao;
import com.imooc.model.Goddess;

public class GoddessAction {
	
	public void add(Goddess goddess) throws Exception {
		GoddessDao dao = new GoddessDao();
		goddess.setSex(1);
		goddess.setCreate_user("ADMIN");
		goddess.setUpdate_user("ADMIN");
		goddess.setIsdel(0);
		dao.addGoddess(goddess);
	}
	
	public Goddess get(int id) throws Exception {
		GoddessDao dao = new GoddessDao();
		return dao.get(id);
	}
	
	public void edit(Goddess goddess) throws Exception {
		GoddessDao dao = new GoddessDao();
		goddess.setSex(1);
		goddess.setCreate_user("ADMIN");
		dao.updateGoddess(goddess);
	}
	
	public void del(int id) throws Exception {
		GoddessDao dao = new GoddessDao();
		dao.delGoddess(id);
	}
	
	public List<Goddess> query() throws Exception {
		GoddessDao dao = new GoddessDao();
		return dao.query();
	}
	
	public List<Goddess> query(List<Map<String, Object>> params) throws Exception {
		GoddessDao dao = new GoddessDao();
		return dao.query(params);
	}
	
/*
	public static void main(String[] args) throws Exception {
		GoddessDao g = new GoddessDao();
		
//		List<Goddess> result = g.query("小美");
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", "user_name");
		param.put("rela", "=");
		param.put("value", "'小美'");
		params.add(param);
		param = new HashMap<String, Object>();
		param.put("name", "mobile");
		param.put("rela", "like");
		param.put("value", "'%18%'");
		params.add(param);
		List<Goddess> result = g.query(params);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		
		
//		List<Goddess> gs = g.query();
//		for (Goddess goddess : gs) {
//			System.out.println(goddess.getUser_name() + "," + goddess.getAge());
//		}
		Goddess g1 = new Goddess();
		g1.setUser_name("小美");
		g1.setAge(21);
		g1.setSex(1);
		g1.setBirthday(new Date());
		g1.setEmail("xiaomei@imooc.com");
		g1.setMobile("18711115678");
		g1.setUpdate_user("ADMIN");
		g1.setIsdel(1);
		
//		Goddess g2 = g.get(7);
//		System.out.println(g2.toString());
//		g1.setId(5);
//		g.delGoddess(5);
//		g.updateGoddess(g1);
//		g.addGoddess(g1);
	}
	*/
}
























