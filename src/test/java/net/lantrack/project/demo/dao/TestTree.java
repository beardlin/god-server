package net.lantrack.project.demo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;




public class TestTree {

	public static void main(String[] args) {
		List<Tree> list = new ArrayList<>();
		
		Tree t0 = new Tree("0", "", "0");
		list.add(t0);
		
		Tree t1 = new Tree("1", "0", "1");
		Tree t2 = new Tree("2", "0", "2");
		Tree t3 = new Tree("3", "0", "3");
		
		Tree t11 = new Tree("11", "1", "1.1");
		Tree t12 = new Tree("12", "1", "1.2");
		Tree t13 = new Tree("13", "1", "1.3");
		Tree t21 = new Tree("21", "2", "2.1");
		Tree t22 = new Tree("22", "2", "2.2");
		Tree t23 = new Tree("23", "2", "2.3");
		Tree t24 = new Tree("24", "2", "2.4");
		Tree t31 = new Tree("31", "3", "3.1");
		Tree t32 = new Tree("32", "3", "3.2");
		Tree t33 = new Tree("33", "3", "3.3");
		
		Tree t111 = new Tree("111", "11", "1.1.1");
		Tree t112 = new Tree("112", "11", "1.1.2");
		Tree t121 = new Tree("121", "12", "1.2.1");
		Tree t122 = new Tree("122", "12", "1.2.2");
		Tree t123 = new Tree("123", "12", "1.2.3");
		Tree t131 = new Tree("131", "13", "1.3.1");
		Tree t211 = new Tree("211", "21", "2.1.1");
		Tree t212 = new Tree("212", "21", "2.1.2");
		Tree t241 = new Tree("241", "24", "2.4.1");
		Tree t242 = new Tree("242", "24", "2.4.2");
		Tree t243 = new Tree("243", "24", "2.4.3");
		Tree t244 = new Tree("244", "24", "2.4.4");
		
		
		list.add(t1);
		list.add(t11);
		list.add(t111);
		list.add(t112);
		list.add(t12);
		list.add(t121);
		list.add(t122);
		list.add(t123);
		list.add(t13);
		list.add(t131);
		list.add(t2);
		list.add(t21);
		list.add(t211);
		list.add(t212);
		list.add(t22);
		list.add(t23);
		list.add(t24);
		list.add(t241);
		list.add(t242);
		list.add(t243);
		list.add(t244);
		list.add(t3);
		list.add(t31);
		list.add(t32);
		list.add(t33);
		List<Tree> list2 = converhandle(list);
		System.out.println(list2.size());
	}
	
	private static List<Tree> converhandle(List<Tree> list){
		Map<String, List<Tree>> treeMap = new HashMap<>();
		Map<String, Tree> map = new HashMap<>();
		for(Tree t:list) {
			map.put(t.getId(), t);
			String pid = t.getPid();
			if(treeMap.containsKey(pid)) {
				treeMap.get(pid).add(t);
			}else {
				List<Tree> tList = new ArrayList<>();
				tList.add(t);
				treeMap.put(pid,tList);
			}
		}
		Set<String> keySet = treeMap.keySet();
		if(keySet.size()==1) {
			for(String k:keySet) {
				return treeMap.get(k);
			}
		}
		List<Tree> pList = new ArrayList<>(keySet.size());
		for(String key:keySet) {
			Tree tree = map.get(key);
			if(tree==null) {
				continue;
			}
			List<Tree> child = treeMap.get(key);
			if(child!=null&&child.size()>0) {
				List<Tree> child1 = tree.getChilds();
				if(child1!=null && child1.size()>0) {
					Map<String, Tree> cMap = new HashMap<>(child1.size());
					for(Tree t:child1) {
						cMap.put(t.getId(), t);
					}
					for(Tree c:child) {
						if(cMap.containsKey(c.getId())) {
							cMap.put(c.getId(), c);
						}
					}
					child.clear();
					for(Map.Entry<String, Tree> entry:cMap.entrySet()) {
						child.add(entry.getValue());
					}
				}
				tree.setChilds(child);
				pList.add(tree);
			}
		}
		return converhandle(pList);
	}
	
	//树形结构
	private static class Tree{
		
		private String id;
		private String pid;
		private String name;
		private List<Tree> childs;
		
		
		
		public Tree(String id, String pid, String name) {
			super();
			this.id = id;
			this.pid = pid;
			this.name = name;
		}
		public Tree() {
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPid() {
			return pid;
		}
		public void setPid(String pid) {
			this.pid = pid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Tree> getChilds() {
			return childs;
		}
		public void setChilds(List<Tree> childs) {
			this.childs = childs;
		}
		
	}
}
