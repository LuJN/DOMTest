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
			// ����DocumentBuilder����
			DocumentBuilder db = dbf.newDocumentBuilder();
			// ͨ��DocumentBuilder�����parse��������books.xml�ļ�����ǰĿ¼��
			Document document = db.parse("books.xml");
			// ��ȡ����book�ڵ�ļ���
			NodeList bookList = document.getElementsByTagName("book");
			// ͨ��nodeList��getLength()���Ի�ȡbookList�ĳ���
			System.out.println("һ����" + bookList.getLength() + "����");
			// ����ÿһ��book�ڵ� 
			for(int i=0; i<bookList.getLength(); i++) {
				System.out.println("==============��ʼ������" + (i + 1) + "���������================");
				// ͨ��item(i)������ȡһ��book�ڵ㣬nodeList������ֵ��0��ʼ 
				Node book = bookList.item(i);
				// ��ȡbook�ڵ���������Լ���
				NamedNodeMap attrs = book.getAttributes();
				System.out.println("��" + (i + 1) + "����һ����" + attrs.getLength() + "������");
				// ����book������
				for(int j=0; j<attrs.getLength(); j++) {
					// ͨ��item()������ȡbook�ڵ��ĳһ������
					Node attr = attrs.item(j);
					// ��ȡ������
					System.out.print("��������" + attr.getNodeName());
					// ��ȡ����ֵ
					System.out.println("--����ֵ��" + attr.getNodeValue());
				}
//				// ǰ�᣺�Ѿ�֪��book�ڵ�����ֻ����1������
//				// ��book�ڵ����ǿ������ת����ת����Element����
//				Element book = (Element) bookList.item(i);
//				// ͨ��getAttribute()������ȡ����ֵ
//				String attrValue = book.getAttribute("id");
//				System.out.println("id���Ե�����ֵΪ" + attrValue);
				// ����book�ڵ���ӽڵ�
				NodeList childNodes = book.getChildNodes();
				// <>֮��Ŀհ�Ҳ����һ���ӽڵ㣨��text���͵��ӽڵ㣩�����Ի������9���ӽڵ�
				System.out.println("��" + (i + 1) + "����һ����" + childNodes.getLength() + "���ӽڵ�");
				// ����childNodes��ȡÿ���ڵ�Ľڵ����ͽڵ�ֵ
				for(int k=0; k<childNodes.getLength(); k++) {
					// ���ֳ�text���͵�node�Լ�element���͵�node
					if(childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						// ��ȡelement���ͽڵ�Ľڵ���
						System.out.print("��" + (k+ 1) + "���ڵ�Ľڵ�����" + childNodes.item(k).getNodeName());
						// ��ȡelement���ͽڵ�Ľڵ�ֵ
						// ���᷵��null ��Ϊelement���ͽڵ�Ľڵ�ֵ���Ǹýڵ���ӽڵ�
//						System.out.println(childNodes.item(k).getNodeValue());
						System.out.println("--�ڵ�ֵ�ǣ�" + childNodes.item(k).getFirstChild().getNodeValue());
						// �õ����ӽڵ��е������ı���Ϣ�����ӽڵ���ܰ����ӽڵ㣩
						// ����<name><a>aa</a>�����֮��</name>���������aa�����֮�衱
//						System.out.println("--�ڵ�ֵ�ǣ�" + childNodes.item(k).getTextContent());
					}
				}
				System.out.println("==============����������" + (i + 1) + "���������================");
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
