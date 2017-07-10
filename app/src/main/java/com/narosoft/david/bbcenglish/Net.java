package com.narosoft.david.bbcenglish;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by David on 2017/7/9.
 */

public class Net {

    public static List<BbctitleModel> getBBCData(String address){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(address);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            connection.setRequestProperty("Cookie", "AppName=" + URLEncoder.encode("english", "UTF-8"));
            connection.setRequestProperty("MyProperty", "this is me!");
            if(connection.getResponseCode() == 200){
                InputStream is = connection.getInputStream();
                List<BbctitleModel> result = XmlParse.parseXMLWithPull(is);
                is.close();
                return result;

            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null){
                connection.disconnect();
            }
        }
    }



}
