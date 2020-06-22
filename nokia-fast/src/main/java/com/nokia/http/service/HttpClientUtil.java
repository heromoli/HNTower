package com.nokia.http.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by Administrator on 2020/6/10 0010.
 */
@Slf4j
public class HttpClientUtil {
    private String domain = "";

    private static String FILE_CODE = "";

    private static String FILE_EXPORT = "";

    static CookieStore cookieStore = null;

    public static Header[] headers = null;

    static HttpClientUtil httpClientUtil = null;

    static Map<Object, Boolean> tMap = new HashMap<>();

    public static HttpClientUtil getInstance(String domain) {
        if (httpClientUtil == null) {
            httpClientUtil = new HttpClientUtil(domain);
        } else {
            httpClientUtil.domain = domain;
        }
        return httpClientUtil;

    }

    private HttpClientUtil(String domain) {
        this.domain = domain;
    }

    public Map<String, Object> toPost(String url, Map<String, String> map) {
        return toPost(url, map, null);
    }

    public Map<String, Object> toPost(String url, Map<String, String> map, Header[] heads) {
        Map<String, Object> rMap = new HashMap<>();
        //创建默认客户端
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpClient closeableHttpClient = null;
//        closeableHttpClient = HttpClients.createDefault();
        if (cookieStore != null) {
            closeableHttpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore).build();
        } else {
            closeableHttpClient = HttpClients.createDefault();

        }
        //创建Post请求实例
        HttpPost httpPost = new HttpPost(url);
        //创建参数列表
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null)
            for (String key : map.keySet()) {
                nvps.add(new BasicNameValuePair(key, map.get(key)));
            }
        if (heads != null)
            httpPost.setHeaders(heads);
        //设置请求的报文头部的编码
        httpPost.setHeader(
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));

        //设置期望服务端返回的编码
//        httpPost.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        httpPost.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
        httpPost.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.21 Safari/537.36"));
        String cookie = "JSESSIONID=" + cookieStore.getCookies().get(0).getValue() + "";
        httpPost.setHeader(new BasicHeader("Cookie", cookie));
        //向对方服务器发送Post请求
        try {
            //将参数进行封装，提交到服务器端
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF8"));
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);

            System.out.println(httpResponse.getStatusLine().getStatusCode());
            //如果模拟登录成功
            Integer statusCode = httpResponse.getStatusLine().getStatusCode();
            rMap.put("statusCode", statusCode);
            Header[] headers = httpResponse.getAllHeaders();
            rMap.put("headers", headers);
            if (statusCode == 200) {
                String result = EntityUtils.toString(httpResponse.getEntity());
//                InputStream inputStream = httpResponse.getEntity().getContent();
//                String result = new BufferedReader(new InputStreamReader(inputStream))
//                        .lines().collect(Collectors.joining(System.lineSeparator()));
                result = gbkToUtf8(result);
                System.out.println(result);
                rMap.put("result", result);
            } else if (statusCode == 302) {
                Header header = httpResponse.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
                String newUri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
//                System.out.println("newuri=================>"+newUri);
//                Thread.sleep(500);// 有时存在跳转失败 可能因此服务还未存储登陆成功信息
                rMap.put("newUri", newUri);
                InputStream inputStream = httpResponse.getEntity().getContent();
                String result = new BufferedReader(new InputStreamReader(inputStream))
                        .lines().collect(Collectors.joining(System.lineSeparator()));
                rMap.put("result", result);
            }
            if (cookieStore == null) {
                setCookieStore(httpResponse);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();      //释放资源
        }
        return rMap;
    }


    public Map<String, Object> toPost2(String url, Map<String, String> map) {
        Map<String, Object> rMap = new HashMap<>();
        //创建默认客户端
        CloseableHttpClient closeableHttpClient = null;
        if (cookieStore != null) {
            closeableHttpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore).build();
        } else {
            closeableHttpClient = HttpClients.createDefault();

        }
        //创建Post请求实例
        HttpPost httpPost = new HttpPost(url);
        //创建参数列表
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null)
            for (String key : map.keySet()) {
                nvps.add(new BasicNameValuePair(key, map.get(key)));
            }
        //设置请求的报文头部的编码
        httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        httpPost.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        httpPost.setHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8"));
        httpPost.setHeader(new BasicHeader("Connection", "keep-alive"));
        httpPost.setHeader(new BasicHeader("Connection", "keep-alive"));

        //设置期望服务端返回的编码
        httpPost.setHeader(new BasicHeader("Accept", "*/*"));
//        httpPost.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
        httpPost.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.21 Safari/537.36"));
        String cookie = "JSESSIONID=" + cookieStore.getCookies().get(0).getValue() + "";
        httpPost.setHeader(new BasicHeader("Cookie", cookie));
        //向对方服务器发送Post请求
        try {
            //将参数进行封装，提交到服务器端
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF8"));
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);

            System.out.println(httpResponse.getStatusLine().getStatusCode());
            //如果模拟登录成功
            Integer statusCode = httpResponse.getStatusLine().getStatusCode();
            rMap.put("statusCode", statusCode);
            Header[] headers = httpResponse.getAllHeaders();
            rMap.put("headers", headers);
            if (statusCode == 200) {
                String result = EntityUtils.toString(httpResponse.getEntity());
//                InputStream inputStream = httpResponse.getEntity().getContent();
//                String result = new BufferedReader(new InputStreamReader(inputStream))
//                        .lines().collect(Collectors.joining(System.lineSeparator()));
                result = gbkToUtf8(result);
//                System.out.println(result);
                rMap.put("result", result);
            } else if (statusCode == 302) {
                Header header = httpResponse.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
                String newUri = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
//                System.out.println("newuri=================>"+newUri);
//                Thread.sleep(500);// 有时存在跳转失败 可能因此服务还未存储登陆成功信息
                rMap.put("newUri", newUri);
                InputStream inputStream = httpResponse.getEntity().getContent();
                String result = new BufferedReader(new InputStreamReader(inputStream))
                        .lines().collect(Collectors.joining(System.lineSeparator()));
                rMap.put("result", result);
            }
            if (cookieStore == null) {
                setCookieStore(httpResponse);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();      //释放资源
        }
        return rMap;
    }

    public Map<String, Object> toPostZip(String url, Map<String, String> map, Header[] heads, String fileName) {
        Map<String, Object> rMap = new HashMap<>();
        //创建默认客户端
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        CloseableHttpClient closeableHttpClient = null;
        if (cookieStore != null) {
            closeableHttpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore).build();
        } else {
            closeableHttpClient = HttpClients.createDefault();

        }
        Long l1 = System.currentTimeMillis();
        log.info("创建连接-----------------------------" + fileName);
        //创建Post请求实例
        HttpPost httpPost = new HttpPost(url);
        //创建参数列表
        List<NameValuePair> nvps = new ArrayList<>();
        if (map != null)
            for (String key : map.keySet()) {
                nvps.add(new BasicNameValuePair(key, map.get(key)));
            }
        //new UrlEncodedFormEntity(list, "UTF-8");
        if (heads != null)
            httpPost.setHeaders(heads);
        //设置请求的报文头部的编码
        httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        //设置期望服务端返回的编码
//        httpPost.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        httpPost.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));
        httpPost.setHeader(new BasicHeader("Accept-Encoding", "gzip, deflate"));
        httpPost.setHeader(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8"));
        httpPost.setHeader(new BasicHeader("Cache-Control", "max-age=0"));
        httpPost.setHeader(new BasicHeader("Connection", "keep-alive"));
        httpPost.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.21 Safari/537.36"));
        String cookie = "JSESSIONID=" + cookieStore.getCookies().get(0).getValue() + "";
        httpPost.setHeader(new BasicHeader("Cookie", cookie));
        //向对方服务器发送Post请求
        try {
            //将参数进行封装，提交到服务器端
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF8"));
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpPost);
            System.out.println(httpResponse.getStatusLine());
            HttpEntity httpEntity = httpResponse.getEntity();
            Long l2 = System.currentTimeMillis();
            log.info("连接成功准备下载-----------------------------" + fileName + ":" + (l2 - l1));
//            Header[] h =  httpResponse.getAllHeaders();
//            String result = EntityUtils.toString(httpEntity);
//            result = StringUtil.gbkToUtf8(result);
//            System.out.println(result);


//            long contentLength = httpEntity.getContentLength();
            InputStream is = httpEntity.getContent();
            // 根据InputStream 下载文件
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int r = 0;
            long totalRead = 0;
            while ((r = is.read(buffer)) > 0) {
                output.write(buffer, 0, r);
                totalRead += r;

            }
//            String fileName = "";
//            if(h.length>3){
//                String fn = h[2].toString();
//                if(fn.indexOf("filename")>-1){
//                    fileName = fn.substring(fn.indexOf("filename")+"filename=\"".length(),fn.length()-1);
//                }
//            }
            File file = new File(FILE_EXPORT + File.separator + fileName);
            if (!file.getParentFile().exists()) {//判断是否存在目录
                boolean res = file.getParentFile().mkdirs();
                if (!res) {
                    System.out.println("创建失败");
                }
            }
            FileOutputStream fos = new FileOutputStream(FILE_EXPORT + File.separator + fileName);
            output.writeTo(fos);
            output.flush();
            output.close();
            fos.close();
            EntityUtils.consume(httpEntity);
            log.info("下载完成-----------------------------" + fileName + ":" + (System.currentTimeMillis() - l2));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpPost.abort();      //释放资源
        }
        return rMap;
    }


    public Map<String, Object> toGet(String url) {
        return toGet(url, headers);
    }

    public Map<String, Object> toGet(String url, Header[] heads) {
        Map<String, Object> rMap = new HashMap<>();
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        CloseableHttpClient closeableHttpClient = HttpClients.custom()
//                .setDefaultCookieStore(cookieStore).build();
        CloseableHttpClient closeableHttpClient = null;
        if (cookieStore != null) {
            closeableHttpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore).build();
        } else {
            closeableHttpClient = HttpClients.createDefault();

        }
        //创建Post请求实例
        HttpGet httpGet = new HttpGet(url);
        //将参数进行封装，提交到服务器端
        if (heads != null)
            httpGet.setHeaders(heads);
        //设置请求的报文头部的编码
//        httpGet.setHeader(
//                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        httpGet.setHeader(
                new BasicHeader("Content-Type", "text/plain; charset=UTF-8"));
        //设置期望服务端返回的编码
        httpGet.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
        httpGet.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36"));
        httpGet.setHeader(new BasicHeader("Cache-Control", "keep-alive"));
        httpGet.setHeader(new BasicHeader("Connection", "no-cache"));
        try {
            CloseableHttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            Integer statusCode = httpResponse.getStatusLine().getStatusCode();
            rMap.put("statusCode", statusCode);
            if (statusCode == 200) {
                Header[] headers = httpResponse.getAllHeaders();
                rMap.put("headers", headers);
                String result = EntityUtils.toString(httpResponse.getEntity());
//                InputStream inputStream = httpResponse.getEntity().getContent();
//                String result = new BufferedReader(new InputStreamReader(inputStream))
//                        .lines().collect(Collectors.joining(System.lineSeparator()));
                //result = result.substring(result.indexOf("<html lang")).replaceAll("&nbsp;","");
                result = gbkToUtf8(result);
//                log.info("内容为：" + result) ;
                rMap.put("result", result);
//                analysisHtml(result);
                if (cookieStore == null) {
//                    setCookieStore(httpResponse);
                }
//                setCookieStore(httpResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.abort();      //释放资源
        }
        return rMap;
    }

    public void setCookieStore(HttpResponse httpResponse) {
        cookieStore = new BasicCookieStore();
        // JSESSIONID
        String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
        System.out.println(httpResponse.getLastHeader("Set-Cookie"));
        String setCookie1 = httpResponse.getLastHeader("Set-Cookie").getValue();
        String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
        addCookie("JSESSIONID", JSESSIONID, domain, "/");

    }

    private void addCookie(String name, String value, String domain, String path) {
        BasicClientCookie cookie = new BasicClientCookie(name, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
//        cookie.setVersion(0);
        cookieStore.addCookie(cookie);
    }

    /**
     * 下载文件
     *
     * @param url
     * @param filePath
     */
    public void httpDownloadFile(String url, String filePath,
                                 HttpClientDownLoadProgress progress) {
        CloseableHttpClient httpclient = null;
        if (cookieStore == null) {
            httpclient = HttpClients.createDefault();
        } else {
            httpclient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore).build();
        }
        try {
            //设置请求的报文头部的编码
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader(
                    new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
//            setGetHead(httpGet, headMap);
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            try {
                System.out.println(response1.getStatusLine());
                HttpEntity httpEntity = response1.getEntity();
                Header[] headers = response1.getAllHeaders();
//                String res = EntityUtils.toString(httpEntity);
//                res = StringUtil.gbkToUtf8(res);
//                System.out.println(res);


                long contentLength = httpEntity.getContentLength();
                InputStream is = httpEntity.getContent();
                // 根据InputStream 下载文件
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int r = 0;
                long totalRead = 0;
                while ((r = is.read(buffer)) > 0) {
                    output.write(buffer, 0, r);
                    totalRead += r;
                    if (progress != null) {// 回调进度
                        progress.onProgress((int) (totalRead * 100 / contentLength));
                    }
                }
                File file = new File(filePath);
                if (!file.getParentFile().exists()) {//判断是否存在目录
                    boolean result = file.getParentFile().mkdirs();
                    if (!result) {
                        System.out.println("创建失败");
                    }
                }
                FileOutputStream fos = new FileOutputStream(filePath);
                output.writeTo(fos);
                output.flush();
                output.close();
                fos.close();
                EntityUtils.consume(httpEntity);
            } finally {
                response1.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("下载失败." + e.getMessage());
        } finally {
            try {
                httpclient.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getYZM(String url) {
        CloseableHttpClient httpclient = httpclient = HttpClients.createDefault();
        String s = "";
        try {

            HttpGet ht = new HttpGet(url);
            ht.setHeader(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.21 Safari/537.36"));
            ht.setHeader(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3"));

            HttpResponse response = null;
            response = httpclient.execute(ht);
            HttpEntity entity = response.getEntity();
            InputStream inStream = entity.getContent();
            byte[] data = readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            String OriginalImg = FILE_CODE + File.separator + "yzm.jpg";
            File imageFile = new File(OriginalImg);
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();

            setCookieStore(response);

            //识别样本输出地址
            String ocrResult = FILE_CODE + File.separator + "or.jpg";
//            s = doOcrPicture(OriginalImg,ocrResult);
//            testAuthCodeDiscern(OriginalImg,ocrResult);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }


    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    public static String gbkToUtf8(String str) {
        String result = str;
        try {
            result = new String(str.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
        return result;
    }
}
