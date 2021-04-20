package qdu.life.utils;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import qdu.life.model.BO.User.AutoReplyPostDataBO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageUtil
 * @Description TODO
 * @Author uuorb
 * @Date 2021/4/1012:18 下午
 * @Version 0.1
 **/

public class MessageUtil {

  public static final String MESSAGE_TEXT = "text";
  public static final String MESSAGE_IMAGE = "image";
  public static final String MESSAGE_VOICE = "voice";
  public static final String MESSAGE_VIDEO = "video";
  public static final String MESSAGE_LINK = "link";
  public static final String MESSAGE_LOCATION = "location";
  public static final String MESSAGE_EVENT = "event";

  public static final String EVENT_SUB = "subscribe";
  public static final String EVENT_UNSUB = "unsubscribe";
  public static final String EVENT_CLICK = "CLICK";
  public static final String EVENT_VIEW = "VIEW";

  /**
    *  接收request对象，读取xml内容，转换成map对象
    * @param request
    * @return
    */
    public static Map<String, String> parseXmlToMap(HttpServletRequest request){
     Map<String, String> map = new HashMap<>();
     SAXReader reader = new SAXReader();
     InputStream ins = null;
     try {
           ins = request.getInputStream();
       } catch (IOException e1) {
           e1.printStackTrace();
       }
        Document doc = null;
        try {
              doc = reader.read(ins);
              Element root = doc.getRootElement();
              List<Element> list = root.elements();
              for (Element e : list) {
                    map.put(e.getName(), e.getText());
          }
        return map;
    } catch (DocumentException e1) {
        e1.printStackTrace();
    }finally{
        try {
              if (null != ins){
                    ins.close();
                }
          } catch (IOException e) {
                e.printStackTrace();
            }
      }
        return null;
    }

  /**
  * 将消息转换成xml格式的字符串
  * @param msg 各种信息类，如文本信息类，图片信息类，音频信息类等。（都是WxMessage的子类）
  * @param child 用来确定到底是哪一种子类
  * @return
  */
  public static String parseMsgToXml(AutoReplyPostDataBO msg, Class child){
        XStream xstream = new XStream();
        xstream.alias("xml", child);
        return xstream.toXML(msg);
    }



 }

