import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Dom {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// 创建DocumentBuilder对象
			DocumentBuilder db = dbf.newDocumentBuilder();
			// 通过DocumentBuilder对象的parse方法加载books.xml文件到当前目录下
			Document document = db.parse("books.xml");
			// 获取所有book节点的集合
			NodeList bookList = document.getElementsByTagName("book");
			// 通过nodeList的getLength()可以获取bookList的长度
			System.out.println("一共有" + bookList.getLength() + "本书");
			// 遍历每一个book节点 
			for(int i=0; i<bookList.getLength(); i++) {
				System.out.println("==============开始遍历第" + (i + 1) + "本书的内容================");
				// 通过item(i)方法获取一个book节点，nodeList的索引值从0开始 
				Node book = bookList.item(i);
				// 获取book节点的所有属性集合
				NamedNodeMap attrs = book.getAttributes();
				System.out.println("第" + (i + 1) + "本书一共有" + attrs.getLength() + "个属性");
				// 遍历book的属性
				for(int j=0; j<attrs.getLength(); j++) {
					// 通过item()方法获取book节点的某一个属性
					Node attr = attrs.item(j);
					// 获取属性名
					System.out.print("属性名：" + attr.getNodeName());
					// 获取属性值
					System.out.println("--属性值：" + attr.getNodeValue());
				}
//				// 前提：已经知道book节点有且只能有1个属性
//				// 将book节点进行强制类型转换，转换成Element类型
//				Element book = (Element) bookList.item(i);
//				// 通过getAttribute()方法获取属性值
//				String attrValue = book.getAttribute("id");
//				System.out.println("id属性的属性值为" + attrValue);
				// 解析book节点的子节点
				NodeList childNodes = book.getChildNodes();
				// <>之间的空白也算是一个子节点（是text类型的子节点），所以会输出有9个子节点
				System.out.println("第" + (i + 1) + "本书一共有" + childNodes.getLength() + "个子节点");
				// 遍历childNodes获取每个节点的节点名和节点值
				for(int k=0; k<childNodes.getLength(); k++) {
					// 区分出text类型的node以及element类型的node
					if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						// 获取element类型节点的节点名
						System.out.print("第" + (k+ 1) + "个节点的节点名：" + childNodes.item(k).getNodeName());
						// 获取element类型节点的节点值
						// 将会返回null 因为element类型节点的节点值算是该节点的子节点
//						System.out.println(childNodes.item(k).getNodeValue());
						System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
						// 得到该子节点中的所有文本信息（该子节点可能包含子节点）
						// 比如<name><a>aa</a>冰与火之歌</name>将会输出“aa冰与火之歌”
//						System.out.println("--节点值是：" + childNodes.item(k).getTextContent());
					}
				}
				System.out.println("==============结束遍历第" + (i + 1) + "本书的内容================");
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
