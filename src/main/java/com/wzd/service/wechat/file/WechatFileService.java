//package com.wzd.service.wechat.file;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.text.MessageFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.apache.http.Header;
//import org.apache.http.HeaderElement;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.entity.mime.content.ContentBody;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.log4j.Logger;
//
//import com.lifesense.framework.rest.exception.WebException;
//import com.lifesense.framework.rest.response.ResponseCode;
//import com.lifesense.healthcenter.service.wechat.WeixinAPI;
//import com.lifesense.healthcenter.service.wechat.file.enums.FileTypeEnum;
//import com.lifesense.healthcenter.service.wechat.token.TokenService;
//import com.lifesense.healthcenter.utils.DateUtils;
//import com.lifesense.healthcenter.utils.HttpClientManager;
//import com.lifesense.healthcenter.utils.StringUtil;
//
///**
// * 微信上传文件服务类
// * 
// * @author lyb
// *
// */
//public class WechatFileService {
//
//    private static Logger log = Logger.getLogger(WechatFileService.class);
//
//    private static String RESOURCE_URL = "/userfiles/wx_resource/";
//
//    public static final String BASE_PATH = System.getProperty("jetty.home") + "/webapps";
////    public static final String BASE_PATH = System.getProperty("user.dir") + "/src/main/webapp";//测试用
//
//    // 当语音消息上传到另一个公众号是，很有可能上传失败
//    private static final String UPLOAD_FAIL = "[upload fail]";
//
//    private static final String ERRCODE = "errcode";
//
//    private static final String MEDIA_ID = "media_id";
//
//    /**
//     * 根据文件serverId从微信下载文件
//     * 
//     * @param ownerId
//     * @param serverId
//     * @param fileType 
//     * 
//     * @return  文件存放服务的相对路径
//     * @throws IOException
//     */
//    public static String downloadWechatFile(String ownerId, String serverId, FileTypeEnum fileType) throws IOException {
//
//        if (StringUtil.isBlank(ownerId)) {
//            throw new WebException(ResponseCode.不允许为空, "ownerId为空");
//        }
//        if (StringUtil.isBlank(serverId)) {
//            throw new WebException(ResponseCode.不允许为空, "serverId为空");
//        }
//        if (fileType == null) {
//            throw new WebException(ResponseCode.不允许为空, "fileType为空");
//        }
//
//        String accessToken = TokenService.getAccessToken();
//
//        log.debug("====accessToken=" + accessToken);
//
//        String url = MessageFormat.format(WeixinAPI.GET_DOWNLOAD_URL, accessToken, serverId);
//
//        if(StringUtil.isNotBlank(fileType.getFileRaletivePath())){
//        	RESOURCE_URL = fileType.getFileRaletivePath();
//        }
//        
//        StringBuilder webPath = new StringBuilder();
//        webPath.append(RESOURCE_URL);
//        webPath.append(ownerId).append("/");
//        webPath.append(DateUtils.formatDate(new Date(), "yyyy/MM/dd"));
//        webPath.append("/").append(fileType.getType()).append("/");
//
//        String fileFolderPath = BASE_PATH + webPath.toString();
//
//        File file = download(url, fileFolderPath);
//
//        String fileName = file.getName();
//
//        return webPath + fileName;
//    }
//
//    /**
//     * 上传文件，并返回serverId
//     * 
//     * @return MEDIA_ID
//     * @throws UnsupportedEncodingException
//     */
//    @SuppressWarnings({ "deprecation" })
//    public static String uploadWechatFile(String fullFilePath, FileTypeEnum fileType, boolean refreshToken) throws UnsupportedEncodingException {
//
//        if (StringUtil.isBlank(fullFilePath)) {
//            throw new WebException(ResponseCode.不允许为空, "fullFilePath为空");
//        }
//        if (fileType == null) {
//            throw new WebException(ResponseCode.不允许为空, "fileType为空");
//        }
//
//        String accessToken = TokenService.getAccessToken();
//
//        String url = MessageFormat.format(WeixinAPI.GET_UPLOAD_URL, accessToken, fileType.getType());
//
//        File file = new File(fullFilePath);
//
//        ContentBody cbFile = new FileBody(file);
//        ContentBody filename = new StringBody(file.getName());
//        ContentBody filelength = new StringBody(String.valueOf(file.length()));
//
//        Map<String, ContentBody> contentBodyMap = new HashMap<String, ContentBody>();
//        contentBodyMap.put("file", cbFile);
//        contentBodyMap.put("filename", filename);
//        contentBodyMap.put("filelength", filelength);
//
//        String result = HttpClientManager.postMultipartFormData(url, contentBodyMap);
//
//        log.info("============上传语音result==========");
//        log.info("upload json=" + result);
//        log.info("============上传语音result==========");
//
//        JSONObject json = JSONObject.fromObject(result);
//
//        // 如果是accessToken （access_token expired）则重新上传
//        if (json.containsKey(ERRCODE) && json.getInt(ERRCODE) == 42001 && !refreshToken) {
//            result = uploadWechatFile(fullFilePath, fileType, true);
//        }
//
//        // {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
//        if (json.containsKey(MEDIA_ID)) {
//            log.debug("\nMEDIA_ID=" + json.getString(MEDIA_ID));
//            return json.getString(MEDIA_ID);
//        } else {
//            return UPLOAD_FAIL + fullFilePath;
//        }
//
//    }
//
//    /**
//     * 根据url下载文件，保存到filepath中
//     * 
//     * @param url
//     * @param filepath
//     * @return
//     */
//    private static File download(String url, String fileFolderPath) {
//        HttpClient client = HttpClientManager.getHttpClient();
//        HttpGet httpget = new HttpGet(url);
//        try {
//            HttpResponse response = client.execute(httpget);
//
//            HttpEntity entity = response.getEntity();
//            InputStream is = entity.getContent();
//
//            if (!(new File(fileFolderPath).exists())) { // 不存在，创建目录
//                new File(fileFolderPath).mkdirs();
//            }
//
//            String filepath = getFullFilePath(response, fileFolderPath);
//
//            File file = new File(filepath);
//
//            FileOutputStream fileout = new FileOutputStream(file);
//
//            /**
//             * 根据实际运行效果 设置缓冲区大小
//             */
//            byte[] buffer = new byte[1024];
//            int ch = 0;
//            while ((ch = is.read(buffer)) != -1) {
//                fileout.write(buffer, 0, ch);
//            }
//            is.close();
//            fileout.flush();
//            fileout.close();
//            return file;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭连接,释放资源
//            httpget.abort();
//            httpget.releaseConnection();
//        }
//        return null;
//    }
//
//    /**
//     * 获取response要下载的文件的默认路径
//     * 
//     * @param response
//     * @return
//     */
//    private static String getFullFilePath(HttpResponse response, String fileFolderPath) {
//        String filename = getFileName(response);
//
//        if (filename != null) {
//            fileFolderPath += filename;
//        } else {
//            fileFolderPath += getRandomFileName();
//        }
//        return fileFolderPath;
//    }
//
//    /**
//     * 获取随机文件名
//     * 
//     * @return
//     */
//    private static String getRandomFileName() {
//        return String.valueOf(System.currentTimeMillis());
//    }
//
//    /**
//     * 
//     * @param response
//     * @return
//     */
//    private static String getFileName(HttpResponse response) {
//        Header contentHeader = response.getFirstHeader("Content-Disposition");
//        String filename = null;
//        if (contentHeader != null) {
//            HeaderElement[] values = contentHeader.getElements();
//            if (values.length == 1) {
//                NameValuePair param = values[0].getParameterByName("filename");
//                if (param != null) {
//                    try {
//                        filename = new String(param.getValue().toString().getBytes(), "utf-8");
//                        // filename=URLDecoder.decode(param.getValue(),"utf-8");
//                        // filename = param.getValue();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return filename;
//    }
//
//}
