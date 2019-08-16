package org.gjgr.datamining.association.fptree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * FPTree算法工具类
 * 😂
 * @author lyq
 * @modify gwd
 */
public class FPTreeTool {
	// 输入数据文件位置
	private String filePath;
	// 最小支持度阈值
	private int minSupportCount;
	// 所有事物ID记录
	private ArrayList<String[]> totalGoodsID;
	// 各个ID的统计数目映射表项，计数用于排序使用
	private HashMap<String, Integer> itemCountMap;

	public FPTreeTool(String filePath, int minSupportCount) {
		this.filePath = filePath;
		this.minSupportCount = minSupportCount;
		readDataFile();
	}

	/**
	 * 从文件中读取数据
	 */
	private void readDataFile() {
		File file = new File(filePath);
		ArrayList<String[]> dataArray = new ArrayList<String[]>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			String[] tempArray;
			while ((str = in.readLine()) != null) {
				tempArray = str.split(" ");
				dataArray.add(tempArray);
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}

		String[] temp;
		int count = 0;
		itemCountMap = new HashMap<>();
		totalGoodsID = new ArrayList<>();
		for (String[] a : dataArray) {
			temp = new String[a.length - 1];
			System.arraycopy(a, 1, temp, 0, a.length - 1);
			totalGoodsID.add(temp);
			for (String s : temp) {
				if (!itemCountMap.containsKey(s)) {
					count = 1;
				} else {
					count = ((int) itemCountMap.get(s));
					// 支持度计数加1
					count++;
				}
				// 更新表项
				itemCountMap.put(s, count);
			}
		}
	}

	/**
	 * 根据事物记录构造FP树
	 */
	private void buildFPTree(ArrayList<String> suffixPattern,
			ArrayList<ArrayList<TreeNode>> transctionList) {
		// 设置一个空根节点
		TreeNode rootNode = new TreeNode(null, 0);
		int count = 0;
		// 节点是否存在
		boolean isExist = false;
		ArrayList<TreeNode> childNodes;
		ArrayList<TreeNode> pathList;
		// 相同类型节点链表，用于构造的新的FP树
		HashMap<String, ArrayList<TreeNode>> linkedNode = new HashMap<>();
		HashMap<String, Integer> countNode = new HashMap<>();
		// 根据事物记录，一步步构建FP树
		for (ArrayList<TreeNode> array : transctionList) {
			TreeNode searchedNode;
			pathList = new ArrayList<>();
			for (TreeNode node : array) {
				pathList.add(node);
				nodeCounted(node, countNode);
				searchedNode = searchNode(rootNode, pathList);
				childNodes = searchedNode.getChildNodes();

				if (childNodes == null) {
					childNodes = new ArrayList<>();
					childNodes.add(node);
					searchedNode.setChildNodes(childNodes);
					node.setParentNode(searchedNode);
					nodeAddToLinkedList(node, linkedNode);
				} else {
					isExist = false;
					for (TreeNode node2 : childNodes) {
						// 如果找到名称相同，则更新支持度计数
						if (node.getName().equals(node2.getName())) {
							count = node2.getCount() + node.getCount();
							node2.setCount(count);
							// 标识已找到节点位置
							isExist = true;
							break;
						}
					}

					if (!isExist) {
						// 如果没有找到，需添加子节点
						childNodes.add(node);
						node.setParentNode(searchedNode);
						nodeAddToLinkedList(node, linkedNode);
					}
				}

			}
		}

		// 如果FP树已经是单条路径，则输出此时的频繁模式
		if (isSinglePath(rootNode)) {
			printFrequentPattern(suffixPattern, rootNode);
			System.out.println("-------");
		} else {
			ArrayList<ArrayList<TreeNode>> tList;
			ArrayList<String> sPattern;
			if (suffixPattern == null) {
				sPattern = new ArrayList<>();
			} else {
				// 进行一个拷贝，避免互相引用的影响
				sPattern = (ArrayList<String>) suffixPattern.clone();
			}

			// 利用节点链表构造新的事务
			for (Map.Entry entry : countNode.entrySet()) {
				// 添加到后缀模式中
				sPattern.add((String) entry.getKey());
				//获取到了条件模式机，作为新的事务
				tList = getTransactionList((String) entry.getKey(), linkedNode);
				
				System.out.print("[后缀模式]：{");
				for(String s: sPattern){
					System.out.print(s + ", ");
				}
				System.out.print("}, 此时的条件模式基：");
				for(ArrayList<TreeNode> tnList: tList){
					System.out.print("{");
					for(TreeNode n: tnList){
						System.out.print(n.getName() + ", ");
					}
					System.out.print("}, ");
				}
				System.out.println();
				// 递归构造FP树
				buildFPTree(sPattern, tList);
				// 再次移除此项，构造不同的后缀模式，防止对后面造成干扰
				sPattern.remove((String) entry.getKey());
			}
		}
	}

	/**
	 * 将节点加入到同类型节点的链表中
	 * 
	 * @param node
	 *            待加入节点
	 * @param linkedList
	 *            链表图
	 */
	private void nodeAddToLinkedList(TreeNode node,
			HashMap<String, ArrayList<TreeNode>> linkedList) {
		String name = node.getName();
		ArrayList<TreeNode> list;

		if (linkedList.containsKey(name)) {
			list = linkedList.get(name);
			// 将node添加到此队列中
			list.add(node);
		} else {
			list = new ArrayList<>();
			list.add(node);
			linkedList.put(name, list);
		}
	}

	/**
	 * 根据链表构造出新的事务
	 * 
	 * @param name
	 *            节点名称
	 * @param linkedList
	 *            链表
	 * @return
	 */
	private ArrayList<ArrayList<TreeNode>> getTransactionList(String name,
			HashMap<String, ArrayList<TreeNode>> linkedList) {
		ArrayList<ArrayList<TreeNode>> tList = new ArrayList<>();
		ArrayList<TreeNode> targetNode = linkedList.get(name);
		ArrayList<TreeNode> singleTansaction;
		TreeNode temp;

		for (TreeNode node : targetNode) {
			singleTansaction = new ArrayList<>();

			temp = node;
			while (temp.getParentNode().getName() != null) {
				temp = temp.getParentNode();
				singleTansaction.add(new TreeNode(temp.getName(), 1));
			}

			// 按照支持度计数得反转一下
			Collections.reverse(singleTansaction);

			for (TreeNode node2 : singleTansaction) {
				// 支持度计数调成与模式后缀一样
				node2.setCount(node.getCount());
			}

			if (singleTansaction.size() > 0) {
				tList.add(singleTansaction);
			}
		}

		return tList;
	}

	/**
	 * 节点计数
	 * 
	 * @param node
	 *            待加入节点
	 * @param nodeCount
	 *            计数映射图
	 */
	private void nodeCounted(TreeNode node, HashMap<String, Integer> nodeCount) {
		int count = 0;
		String name = node.getName();

		if (nodeCount.containsKey(name)) {
			count = nodeCount.get(name);
			count++;
		} else {
			count = 1;
		}

		nodeCount.put(name, count);
	}

	/**
	 * 显示决策树
	 * 
	 * @param node
	 *            待显示的节点
	 * @param blankNum
	 *            行空格符，用于显示树型结构
	 */
	private void showFPTree(TreeNode node, int blankNum) {
		System.out.println();
		for (int i = 0; i < blankNum; i++) {
			System.out.print("\t");
		}
		System.out.print("--");
		System.out.print("--");

		if (node.getChildNodes() == null) {
			System.out.print("[");
			System.out.print("I" + node.getName() + ":" + node.getCount());
			System.out.print("]");
		} else {
			// 递归显示子节点
			// System.out.print("【" + node.getName() + "】");
			for (TreeNode childNode : node.getChildNodes()) {
				showFPTree(childNode, 2 * blankNum);
			}
		}

	}

	/**
	 * 待插入节点的抵达位置节点，从根节点开始向下寻找待插入节点的位置
	 * 
	 * @param root
	 * @param list
	 * @return
	 */
	private TreeNode searchNode(TreeNode node, ArrayList<TreeNode> list) {
		ArrayList<TreeNode> pathList = new ArrayList<>();
		TreeNode tempNode = null;
		TreeNode firstNode = list.get(0);
		boolean isExist = false;
		// 重新转一遍，避免出现同一引用
		for (TreeNode node2 : list) {
			pathList.add(node2);
		}

		// 如果没有孩子节点，则直接返回，在此节点下添加子节点
		if (node.getChildNodes() == null) {
			return node;
		}

		for (TreeNode n : node.getChildNodes()) {
			if (n.getName().equals(firstNode.getName()) && list.size() == 1) {
				tempNode = node;
				isExist = true;
				break;
			} else if (n.getName().equals(firstNode.getName())) {
				// 还没有找到最后的位置，继续找
				pathList.remove(firstNode);
				tempNode = searchNode(n, pathList);
				return tempNode;
			}
		}

		// 如果没有找到，则新添加到孩子节点中
		if (!isExist) {
			tempNode = node;
		}

		return tempNode;
	}

	/**
	 * 判断目前构造的FP树是否是单条路径的
	 * 
	 * @param rootNode
	 *            当前FP树的根节点
	 * @return
	 */
	private boolean isSinglePath(TreeNode rootNode) {
		// 默认是单条路径
		boolean isSinglePath = true;
		ArrayList<TreeNode> childList;
		TreeNode node;
		node = rootNode;

		while (node.getChildNodes() != null) {
			childList = node.getChildNodes();
			if (childList.size() == 1) {
				node = childList.get(0);
			} else {
				isSinglePath = false;
				break;
			}
		}

		return isSinglePath;
	}

	/**
	 * 开始构建FP树
	 */
	public void startBuildingTree() {
		ArrayList<TreeNode> singleTransaction;
		ArrayList<ArrayList<TreeNode>> transactionList = new ArrayList<>();
		TreeNode tempNode;
		int count = 0;

		for (String[] idArray : totalGoodsID) {
			singleTransaction = new ArrayList<>();
			for (String id : idArray) {
				count = itemCountMap.get(id);
				tempNode = new TreeNode(id, count);
				singleTransaction.add(tempNode);
			}

			// 根据支持度数的多少进行排序
			Collections.sort(singleTransaction);
			for (TreeNode node : singleTransaction) {
				// 支持度计数重新归为1
				node.setCount(1);
			}
			transactionList.add(singleTransaction);
		}

		buildFPTree(null, transactionList);
	}

	/**
	 * 输出此单条路径下的频繁模式
	 * 
	 * @param suffixPattern
	 *            后缀模式
	 * @param rootNode
	 *            单条路径FP树根节点
	 */
	private void printFrequentPattern(ArrayList<String> suffixPattern,
			TreeNode rootNode) {
		ArrayList<String> idArray = new ArrayList<>();
		TreeNode temp;
		temp = rootNode;
		// 用于输出组合模式
		int length = 0;
		int num = 0;
		int[] binaryArray;

		while (temp.getChildNodes() != null) {
			temp = temp.getChildNodes().get(0);

			// 筛选支持度系数大于最小阈值的值
			if (temp.getCount() >= minSupportCount) {
				idArray.add(temp.getName());
			}
		}

		length = idArray.size();
		num = (int) Math.pow(2, length);
		for (int i = 0; i < num; i++) {
			binaryArray = new int[length];
			numToBinaryArray(binaryArray, i);

			// 如果后缀模式只有1个，不能输出自身
			if (suffixPattern.size() == 1 && i == 0) {
				continue;
			}

			System.out.print("频繁模式：{【后缀模式：");
			// 先输出固有的后缀模式
			if (suffixPattern.size() > 1
					|| (suffixPattern.size() == 1 && idArray.size() > 0)) {
				for (String s : suffixPattern) {
					System.out.print(s + ", ");
				}
			}
			System.out.print("】");
			// 输出路径上的组合模式
			for (int j = 0; j < length; j++) {
				if (binaryArray[j] == 1) {
					System.out.print(idArray.get(j) + ", ");
				}
			}
			System.out.println("}");
		}
	}

	/**
	 * 数字转为二进制形式
	 * 
	 * @param binaryArray
	 *            转化后的二进制数组形式
	 * @param num
	 *            待转化数字
	 */
	private void numToBinaryArray(int[] binaryArray, int num) {
		int index = 0;
		while (num != 0) {
			binaryArray[index] = num % 2;
			index++;
			num /= 2;
		}
	}

}
