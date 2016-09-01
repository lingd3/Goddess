package com.imooc.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
		String previous = null;
		int step = 1;
		int get_step = 1;
		while (scan.hasNext()) {
			String in = scan.next().toString();
			if (OPERATION_EXIT.equals(in.toUpperCase())
					|| OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功推出女神禁区。");
				break;
			}
			else if (OPERATION_UPDATE.equals(in.toUpperCase())
					|| OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase())) {
				
			}
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
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (OPERATION_GET.equals(previous)) {
					get_step++;
				}
			}
			else if (OPERATION_ADD.equals(in.toUpperCase())
					|| OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_ADD.equals(previous)) {
				previous = OPERATION_ADD;
				//新增女神
				if (1 == step) {
					System.out.println("请输入女神的[姓名]");
				} else if (2 == step) {
					goddess.setUser_name(in);
					System.out.println("请输入女神的[年龄]");
				} else if (3 == step) {
					goddess.setAge(Integer.valueOf(in));
					System.out.println("请输入女神的[生日]，格式如：yyyy-MM-dd");
				} else if (4 == step) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sf.parse(in);
						goddess.setBirthday(birthday);
						System.out.println("请输入女神的[邮箱]");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("您输入的格式有误，请重新输入");
						step = 3;
					}
				} else if (5 == step) {
					goddess.setEmail(in);
					System.out.println("请输入女神的[手机号]");
				} else if (6 == step) {
					goddess.setMobile(in);
					try {
						action.add(goddess);
						System.out.println("新增女神成功");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("新增女神失败");
					}
				}
				if (OPERATION_ADD.equals(previous)) {
					step++;
				}
				
			}
			else {
				System.out.println("请输入操作:");
			}
		}
	}
	
}























