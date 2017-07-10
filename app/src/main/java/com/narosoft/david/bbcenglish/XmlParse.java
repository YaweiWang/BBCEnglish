package com.narosoft.david.bbcenglish;

import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2017/7/9.
 */

public class XmlParse {

    public static List<BbctitleModel> parseXMLWithPull(InputStream is) {
        List<BbctitleModel> datas = new ArrayList<>();
        try {
            boolean flag = false;
            BbctitleModel data = null;
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(is, "UTF-8");
            int eventCode = parser.getEventType();
            while (eventCode != XmlPullParser.END_DOCUMENT) {
                switch (eventCode) {
                    case XmlPullParser.START_DOCUMENT:
                        datas = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        // 判断当前的元素是否是需要检索的元素
                        if ("Bbctitle".equals(parser.getName())) {
                            flag = true;
                            data = new BbctitleModel();
                        }
                        if (flag) {
                            if ("BbcId".equals(parser.getName())) {
                                data.setBbcId(parser.nextText());
                            }
                            else if ("Title".equals(parser.getName())) {
                                data.setTitle(parser.nextText());
                            } else if ("DescCn".equals(parser.getName())) {
                                data.setDescCn(parser.nextText());
                            } else if ("Title_cn".equals(parser.getName())) {
                                data.setTitle_cn(parser.nextText());
                            } else if ("Category".equals(parser.getName())) {
                                data.setCategory(parser.nextText());
                            } else if ("Sound".equals(parser.getName())) {
                                data.setSound(parser.nextText());
                            } else if ("Url".equals(parser.getName())) {
                                data.setUrl(parser.nextText());
                            } else if ("Pic".equals(parser.getName())) {
                                data.setPic(parser.nextText());
                            } else if ("CreatTime".equals(parser.getName())) {
                                data.setCreatTime(parser.nextText());
                            } else if ("PublishTime".equals(parser.getName())) {
                                data.setPublishTime(parser.nextText());
                            } else if ("ReadCount".equals(parser.getName())) {
                                data.setReadCount(parser.nextText());
                            } else if ("HotFlg".equals(parser.getName())) {
                                data.setHotFlg(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("Bbctitle".equals(parser.getName()) && data != null) {
                            flag = false;
                            datas.add(data);
                            data = null;
                        }
                        break;
                    default:
                        break;
                }
                eventCode = parser.next(); // 取下个标签
            }
        } catch (Exception e) {
            throw new RuntimeException("解析XML异常：" + e.getMessage());
        }

        return datas;
    }
   public static List<Object> parse(InputStream is, Class<?> clazz,
                                    List<String> fields, List<String> elements, String itemElement) {
       List<Object> list = new ArrayList<Object>();
       try {
           XmlPullParser xmlPullParser = Xml.newPullParser();
           xmlPullParser.setInput(is, "UTF-8");
           int event = xmlPullParser.getEventType();
           Object obj = null;
           while (event != XmlPullParser.END_DOCUMENT) {
               switch (event) {
                   case XmlPullParser.START_TAG:
                       if (itemElement.equals(xmlPullParser.getName())) {
                           obj = clazz.newInstance();
                       }
                       if (obj != null
                               && elements.contains(xmlPullParser.getName())) {
                           setFieldValue(obj, fields.get(elements
                                           .indexOf(xmlPullParser.getName())),
                                   xmlPullParser.nextText());
                       }
                       break;
                   case XmlPullParser.END_TAG:
                       if (itemElement.equals(xmlPullParser.getName())) {
                           list.add(obj);
                           obj = null;
                       }
                       break;
               }
               event = xmlPullParser.next();
           }
       } catch (Exception e) {
           throw new RuntimeException("解析XML异常：" + e.getMessage());
       }
       return list;
   }

    public static void setFieldValue(Object obj, String propertyName,
                                     Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
