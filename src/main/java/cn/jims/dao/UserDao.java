package cn.jims.dao;

import cn.jims.entity.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * dom4j:解析xml文件
 * Created by Jims on 2017/1/17.
 */
public class UserDao {
    private String path = "e:/users.xml";//依赖的数据文件

    /*
    按用户名查询
     */
    public User findByUsername(String username) {
        /*
         * 1. 得到Document
		 * 2. xpath查询！
		 * 3. 校验查询结果是否为null，如果为null，返回null
		 * 4. 如果不为null，需要把Element封装到User对象中。
		 */
        /*
		 * 1. 得到Document
		 */
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            /*
            2.通过xpath查询得到Element
            //:无限深度查询
            user:元素
            username:属性
             */
            Element ele = (Element) doc.selectSingleNode("//user[@username='" + username + "']");

        /*
			 * 3. 校验ele是否为null，如果为null，返回null
			 */
            if (ele == null) return null;
            User user = new User();
            String attrUsername = ele.attributeValue("username");//获取该元素的名为username属性值
            String attrPassword = ele.attributeValue("password");//获取该元素的名为password属性值
            user.setUsername(attrUsername);
            user.setPassword(attrPassword);

            return user;
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    添加用户
     */
    public void add(User user) {
        /*
		 * 1. 得到Document
		 * 2. 通过Document得到root元素！<users>
		 * 3. 使用参数user，转发成Element对象
		 * 4. 把element添加到root元素中
		 * 5. 保存Document
		 */
        //得到Document
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(path);
            //得到根元素
            Element root = doc.getRootElement();//users
            //通过根元素创建新元素
            Element userEle = root.addElement("user");
            //为userEle设置属性
            userEle.addAttribute("username",user.getUsername());
            userEle.addAttribute("password",user.getPassword());
            /*
            保存文档
             */
            //创建输出格式化器
            OutputFormat format = new OutputFormat("\t",true);//\t:tab键,true:是否换行
            format.setTrimText(true);//清空原有的换行和缩进
            //创建XmlWriter
            XMLWriter writer;
            try {
                writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"),format);
                writer.write(doc);//保存document对象
                writer.close();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        } catch (DocumentException e){
            throw new RuntimeException(e);
        }
    }
}
