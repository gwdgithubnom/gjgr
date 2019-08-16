import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.kindlebird.core.entity.Color;
import com.kindlebird.core.entity.Node;
import com.kindlebird.core.entity.view.Message;
import com.kindlebird.core.entity.view.NodeList;
import com.kindlebird.core.tools.JSONTool;

import com.kindlebird.core.tools.SystemTools;
import org.json.JSONObject;
import org.junit.*;
import org.mortbay.util.ajax.JSON;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
/**
 * 文 件 名 :
 * CopyRright (c) 1949-xxxx:
 * 文件编号：
 * 创 建 人：龚文东
 * 日    期：2015年10月28日
 * 修 改 人：gwd
 * 日   期：
 * 修改备注：
 * 描   述：
 * 版 本 号：
 */

/**
 * This class is used for ...
 * ClassName: Test   202CB962AC59075B964B07152D234B70
 * @Description: TODO
 * @author 龚文东 gwd
 * @version 2015年10月28日 上午12:39:04
 */
public class MainTest {

	/**
	 * Description :       带参数构造函数,
	 *                       初始化模式名,名称和数据源类型
	 * <p>Description: </p>
	 */
	public MainTest() {
		// TODO Auto-generated constructor stub
	}
	private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

	@BeforeClass
	public static void beforeTest() {
		logger.debug("@BeforeClass");

	}

	@Before
	public void testBefore() {
		logger.info("@Before:");
	}

	@After
	public void testAfter() {
		logger.info("@After:");
	}

	@AfterClass
	public static void afterTest() {
		logger.info("@AfterClass");
	}

	@Ignore
	public void testCase(){
		/*JSONObject jsonObject=new JSONObject();
        Message message=Message.getInstance();
        List<Book> activities=new ArrayList<Book>();
		List<String> image=new ArrayList<String>();
		ArrayList list=new ArrayList();
		list.add("Denver"):
		list.add("Austin");
		list.add(new java.util.Date());
		String city=list.get(0);
		list.set(3."Dallas");
		System.out.println(list.get(3));
		image.add("http:/*//****.image");
		List<String> str=new ArrayList<String>();
		str.add("asinId1");
		str.add("asinId2");
		str.add("asinId3");
        for(int i=0;i<3;i++){
            Book activity=new Book();
		    activity.setId(i+"");
			activity.setName("The Vacant Throne: A Band of Four Novel");
			activity.setAuthor("Machiavelli, Niccolo");
			activity.setAliasName("The Vacant Throne: A Band of Four Novel");
			activity.setBriefDescription("<b>The Prince<br /><br /></b>Here is the world’s most</b>");
			activity.setCategory("图书/类别/励志与成功/心灵读物/幸福");
			activity.setCoverImg("http://ec4.images-amazon.com/images/I/51vaxVygY1L._SL160_.jpg");
			activity.setCpUpdateTime("2016-04-22");
			activities.add(activity);
       }
        message.setData(activities);
		 logger.debug(JSONTool.toJson(message));*/
	}


	@Test
	public void testMethod() {
		testSaveJson();
	}

    //@Test
    public void testArrayList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("Tom");
        logger.debug(arrayList.toString());
        arrayList.add("Jerry");
        logger.debug(arrayList.toString());
        ArrayList yourlist = new ArrayList();
        yourlist.addAll(arrayList);
        logger.debug(yourlist.toString() + "");
    }

	public void testNodeList() {
		List<NodeList> series = new ArrayList<NodeList>();
		series.add(0, getNodeList());
		series.add(0, getNodeList());
		logger.debug(JSONTool.toJson(series));
		try {
			FileOutputStream file = new FileOutputStream(new File(SystemTools.getWebrootpath() + File.separator + "cluster1.json"));
			try {
				file.write(JSONTool.toJson(series).getBytes());
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void testSaveJson() {
		List<NodeList> series = new ArrayList<NodeList>();
		BufferedReader dataInput = null;
		BufferedReader resultInput = null;
		logger.info("please choose the directory:");
		Scanner scanner = new Scanner(System.in);
		String s = null;
		//s=scanner.nextLine();
		String dir = null;
		if (s == null || s.equals("null") || s.endsWith("Null") || s.equals("0"))
            dir = "Spiral";
        else dir = s;
		try {
			FileReader data = new FileReader(new File(SystemTools.getWebrootpath() + File.separator + "data" + File.separator + dir + File.separator + "data.txt"));
			dataInput = new BufferedReader(data);
			data = new FileReader(new File(SystemTools.getWebrootpath() + File.separator + "data" + File.separator + dir + File.separator + "Result.txt"));
			resultInput = new BufferedReader(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		List<Node> exNodes = new ArrayList<Node>();
		List<Node> nodes = new ArrayList<Node>();
		String str = null;
		try {
			while ((str = dataInput.readLine()) != null) {
				String[] strings = str.split(" ");
				Node node = new Node(Double.parseDouble(strings[0]), Double.parseDouble(strings[1]));
				nodes.add(node);
				exNodes.add(node);
				//logger.debug(node.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i = 0;
        // j is a key to make the a origin map
        int j = 1;
        if (dir.equals("default"))
            j = 0;
        try {
            String[] symbols = {"circle", "rect", "roundRect", "triangle", "diamond", "pin", "arrow"};
            while ((str = resultInput.readLine()) != null) {
				NodeList t = getNodeList("cluster" + i++, str, nodes, exNodes);
                t.getItemStyle().getNormal().setColor(Color.get(i * j - 1) + "");
                t.setSymbol(symbols[i % 7 + 1]);
                t.setSymbolSize(15);
                series.add(0, t);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		NodeList exNodesList = new NodeList();
		exNodesList.setData(exNodes);
		exNodesList.setName("outliers");
		exNodesList.getItemStyle().getNormal().setColor(Color.Black + "");
		if (exNodes.size() > 0)
		series.add(0, exNodesList);

		//Scanner scanner=new Scanner();
/*		FileChannel fileChannel=dataInput.getChannel();
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		try {
			fileChannel.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		buffer.flip();
		while (buffer.hasRemaining())*/

		logger.debug(JSONTool.toJson(series));
		try {
            FileOutputStream file = new FileOutputStream(new File(SystemTools.getWebrootpath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "data" + File.separator + "asset" + File.separator + "cluster2.json"));
            try {
				file.write(JSONTool.toJson(series).getBytes());
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public NodeList getNodeList() {
		NodeList nodeList = new NodeList();
		for (int i = 0; i < 7; i++) {
			Node node = new Node();
			node.setX(Math.random());
			node.setY(Math.random());
			nodeList.add(node);
		}
		nodeList.setType("sin");
		//String s=JSONTool.toJson(nodeList);
		//logger.debug(s);
		return nodeList;
	}


	public NodeList getNodeList(String nodename, String idstr, List<Node> data, List<Node> exData) {
		NodeList nodeList = new NodeList();
		logger.debug("nodename:" + nodename + "\tbefore working, data include node:" + exData.size());
		String[] strs = idstr.split(" ");


		for (int i = 0; i < strs.length; i++) {
			int id = Integer.parseInt(strs[i]);
			nodeList.add(data.get(id));
			exData.remove(data.get(id));
		}


		/*List<Node> trueNode=nodeList.getData();
		for(int i=0;i<trueNode.size();i++){
			data.remove(trueNode.get(i));
		}
*/
		logger.debug("after working, data include node:" + exData.size());
		nodeList.setName(nodename);
		//String s=JSONTool.toJson(nodeList);
		//logger.debug(s);
		return nodeList;
	}
}
