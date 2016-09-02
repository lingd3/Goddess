package com.imooc.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.imooc.action.GoddessAction;
import com.imooc.model.Goddess;

public class View {
	private static final String CONTEXT="欢迎来到女神禁区：\n" +
			"下面是女神禁区的功能列表：\n" +
			"[MAIN/M]:主菜单\n" +
			"[QUERY/Q]:查看全部女神的信息\n" +
			"[GET/G]:查看某位女神的详细信息\n" +
			"[ADD/A]:添加女神信息\n" +
			"[UPDATE/U]:更新女神信息\n" +
			"[DELETE/D]:删除女神信息\n" +
			"[SEARCH/S]:查询女神信息(根据姓名、手机号来查询)\n" +
			"[EXIT/E]:退出女神禁区\n" +
			"[BREAK/B]:退出当前功能，返回主菜单";

	
	private static final String OPERATION_MAIN="MAIN";
	private static final String OPERATION_QUERY="QUERY";
	private static final String OPERATION_GET="GET";
	private static final String OPERATION_ADD="ADD";
	private static final String OPERATION_UPDATE="UPDATE";
	private static final String OPERATION_DELETE="DELETE";
	private static final String OPERATION_SEARCH="SEARCH";
	private static final String OPERATION_EXIT="EXIT";
	private static final String OPERATION_BREAK="BREAK";
	
	public static void main(String[] args) {
		
		System.out.println(CONTEXT);
		
		Scanner scan = new Scanner(System.in);
		Goddess goddess = new Goddess();
		GoddessAction action = new GoddessAction();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> param = new HashMap<String, Object>();
		String previous = null;
		int add_step = 1;
		int get_step = 1;
		int delete_step = 1;
		int update_step = 1;
		int search_step = 1;
		while (scan.hasNext()) {
			String in = scan.next().toString();

			//exit退出女神禁区
			if (OPERATION_EXIT.equals(in.toUpperCase())
					|| OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功推出女神禁区。");
				break;
			}
			
			//break退出当前功能，返回主菜单
			else if (OPERATION_BREAK.equals(in.toUpperCase())
					|| OPERATION_BREAK.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("退出功能");
				previous = null;
				add_step = 1;
				get_step = 1;
				delete_step = 1;
				update_step = 1;
				search_step = 1;
			}
			
			//update更新女神信息
			else if (OPERATION_UPDATE.equals(in.toUpperCase())
					|| OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_UPDATE.equals(previous)) {
				previous = OPERATION_UPDATE;
				if (1 == update_step) {
					System.out.println("请输入要更新的女神编号");
					System.out.println("编号:");
				} else if (2 == update_step) {
					try {
						goddess = action.get(Integer.valueOf(in));
						System.out.println("不需要更新的字段，请输入null");
						System.out.println("姓名:");
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (3 == update_step) {
					if (!in.equals("null")) {
						goddess.setUser_name(in);
					}
					System.out.println("年龄:");
				} else if (4 == update_step) {
					if (!in.equals("null")) {
						goddess.setAge(Integer.valueOf(in));
					}
					System.out.println("性别(0:女; 1:男):");
				} else if (5 == update_step) {
					if (!in.equals("null")) {
						goddess.setSex(Integer.valueOf(in));
					}
					System.out.println("生日(如1990-01-01):");
				} else if (6 == update_step) {
					if (!in.equals("null")) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date;
						try {
							date = sdf.parse(in);
							goddess.setBirthday(date);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					System.out.println("邮箱:");
				} else if (7 == update_step) {
					if (!in.equals("null")) {
						goddess.setEmail(in);
					}
					System.out.println("手机号:");
				} else if (8 == update_step) {
					if (!in.equals("null")) {
						goddess.setMobile(in);
					}
					previous = null;
					update_step = 1;
					try {
						action.edit(goddess);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("女神更新成功");
					continue;
				}
				if (OPERATION_UPDATE.equals(previous)) {
					update_step++;
				}
			}
			
			//add添加女神信息
			else if (OPERATION_ADD.equals(in.toUpperCase())
					|| OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_ADD.equals(previous)) {
				previous = OPERATION_ADD;
				//新增女神
				if (1 == add_step) {
					System.out.println("请输入女神的[姓名]");
				} else if (2 == add_step) {
					goddess.setUser_name(in);
					System.out.println("请输入女神的[年龄]");
				} else if (3 == add_step) {
					goddess.setAge(Integer.valueOf(in));
					System.out.println("请输入女神的[生日]，格式如：yyyy-MM-dd");
				} else if (4 == add_step) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sf.parse(in);
						goddess.setBirthday(birthday);
						System.out.println("请输入女神的[邮箱]");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("您输入的格式有误，请重新输入");
						add_step = 3;
					}
				} else if (5 == add_step) {
					goddess.setEmail(in);
					System.out.println("请输入女神的[手机号]");
				} else if (6 == add_step) {
					goddess.setMobile(in);
					try {
						action.add(goddess);
						previous = null;
						add_step = 1;
						System.out.println("新增女神成功");
						continue;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("新增女神失败");
					}
				}
				if (OPERATION_ADD.equals(previous)) {
					add_step++;
				}
			}
			
			//delete删除女神信息
			else if (OPERATION_DELETE.equals(in.toUpperCase())
					|| OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_DELETE.equals(previous)) {
				previous = OPERATION_DELETE;
				if (1 == delete_step) {
					System.out.println("请输入要删除的女神的编号");
				} else if (2 == delete_step) {
					try {
						action.del(Integer.valueOf(in));
						previous = null;
						delete_step = 1;
						System.out.println("删除女神成功");
						continue;
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (OPERATION_DELETE.equals(previous)) {
					delete_step++;
				}
			}
			
			//query查看全部女神的信息
			else if (OPERATION_QUERY.equals(in.toUpperCase())
					|| OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())) {
				List<Goddess> list;
				try {
					list = action.query();
					System.out.println("下面是本禁区女神的基本信息");
					for (Goddess g : list) {
						System.out.println("编号：" + g.getId() + "，姓名：" + g.getUser_name());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//get查看某位女神的详细信息
			else if (OPERATION_GET.equals(in.toUpperCase())
					|| OPERATION_GET.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_GET.equals(previous)) {
				previous = OPERATION_GET;
				if (1 == get_step) {
					System.out.println("请输入女神的编号，来查看女神的详细信息");
				} else if (2 == get_step) {
					try {
						Goddess g = action.get(Integer.valueOf(in));
						System.out.println("编号：" + g.getId());
						System.out.println("姓名：" + g.getUser_name());
						System.out.println("年龄：" + g.getAge());
						System.out.println("性别：女");
						System.out.println("生日：" + g.getBirthday());
						System.out.println("邮箱：" + g.getEmail());
						System.out.println("手机号：" + g.getMobile());
						previous = null;
						get_step = 1;
						continue;
					} catch (Exception e) {
//						e.printStackTrace();
						System.out.println("sorry,没有找到对应编号的女神。请输入正确的女神编号");
						continue;
					}
				}
				if (OPERATION_GET.equals(previous)) {
					get_step++;
				}
			}
			
			//search查询女神信息(根据姓名、手机号来查询)
			else if (OPERATION_SEARCH.equals(in.toUpperCase())
					|| OPERATION_SEARCH.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_SEARCH.equals(previous)) {
				previous = OPERATION_SEARCH;
				if (1 == search_step) {
					System.out.println("请输入女神的姓名");
				} else if (2 == search_step) {
					param.put("name", "user_name");
					param.put("rela", "=");
					param.put("value", "'" + in + "'"); //注意值要加引号
					System.out.println("输入为：" + in);
					params.add(param);
					System.out.println("请输入女神的手机号");
				} else if (3 == search_step) {
					try {
						param = new HashMap<String, Object>();
						param.put("name", "mobile");
						param.put("rela", "=");
						param.put("value", "'" + in + "'");
						System.out.println("输入为：" + in);
						params.add(param);
						System.out.println("下面是查询到的女神的信息：");
						List<Goddess> result = action.query(params);
						for (int i = 0; i < result.size(); i++) {
							Goddess g  = action.get(result.get(i).getId());
							System.out.println("编号：" + g.getId());
							System.out.println("姓名：" + g.getUser_name());
							System.out.println("年龄：" + g.getAge());
							System.out.println("性别：女");
							System.out.println("生日：" + g.getBirthday());
							System.out.println("邮箱：" + g.getEmail());
							System.out.println("手机号：" + g.getMobile());
						}
						previous = null;
						search_step = 1;
						continue;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (OPERATION_SEARCH.equals(previous)) {
					search_step++;
				}
			}
			else {
				System.out.println("请输入操作:");
			}
		}
	}
	
}























