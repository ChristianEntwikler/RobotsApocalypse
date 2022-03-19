/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robots.apocalypse.utils;

import com.robots.apocalypse.converters.JsonConverter;
import com.robots.apocalypse.dtos.RobotsDto;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ResourceBundle;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author cenebeli
 */
public class Util {
    private static SSLSocketFactory sslSocketFactory = null;
    
    public RobotsDto ListRobots()
    {
         RobotsDto respCode = new RobotsDto();
        try {     
            ResourceBundle rb=ResourceBundle.getBundle("config");
            String url = rb.getString("robotSys");

            URL uri = new URL(url);        
            
        HttpsURLConnection con = (HttpsURLConnection) uri.openConnection();
        setAcceptAllVerifier((HttpsURLConnection)con);
        con.setDoOutput(true);
        con.setInstanceFollowRedirects(false);
        con.setRequestMethod("GET");   
        con.setRequestProperty("authenticated", "true");
        con.setRequestProperty("accept", "application/json");
        con.setRequestProperty("Content-Type", "application/json");
            con.setHostnameVerifier(new HostnameVerifier()
            {
                public boolean verify(String hostname, SSLSession session)
                {
                    return true;
                }
            });
        con.connect();

            // reading the response
            InputStreamReader reader = new InputStreamReader(con.getInputStream());
            StringBuilder buf = new StringBuilder();
            char[] cbuf = new char[ 2048 ];
            int num;
            while ( -1 != (num=reader.read( cbuf )))
            {
                buf.append( cbuf, 0, num );
            }
            String result = buf.toString();
            
            int responseCode = con.getResponseCode();
            System.out.println("responseCode: " + responseCode);
            
            //System.out.println(result);

            result = result.replace("null", "\"\"");
            result = "{\"data\":" + result +"}";
            //System.out.println(result);
            
          respCode=JsonConverter.toObj(result, RobotsDto.class);         
 
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        
        return respCode;
    }
    
    protected static void setAcceptAllVerifier(HttpsURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {
        if( null == sslSocketFactory) {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new java.security.SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        }
        connection.setSSLSocketFactory(sslSocketFactory);
        connection.setHostnameVerifier(ALL_TRUSTING_HOSTNAME_VERIFIER);
    }

    private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER = new TrustManager[] {
        new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }
    };

    private static final HostnameVerifier ALL_TRUSTING_HOSTNAME_VERIFIER  = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    
}
