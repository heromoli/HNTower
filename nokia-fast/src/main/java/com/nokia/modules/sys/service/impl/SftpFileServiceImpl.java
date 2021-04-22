package com.nokia.modules.sys.service.impl;

import com.nokia.modules.sys.service.SftpFileService;
import com.nokia.utils.RData;
import com.nokia.utils.SFtpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Service("SftpFileService")
public class SftpFileServiceImpl implements SftpFileService {
    private static final Logger logger = Logger.getLogger(SftpFileServiceImpl.class);

    @Value("${spring.sftp.host}")
    private String sftpHost;

    @Value("${spring.sftp.port}")
    private String sftpPort;

    @Value("${spring.sftp.account}")
    private String sftpAccount;

    @Value("${spring.sftp.password}")
    private String sftpPassword;

    @Value("${spring.sftp.basePath}")
    private String sftpBasePath;

    @Override
    public RData downloadSftpFile(String county, String plan_name, String plan_form_time, HttpServletResponse response) {
        String fileName = "海口-海口绿地海德公馆-2019-11-12.pptx";

        try {
            SFtpUtil sftpUtil = new SFtpUtil(sftpAccount, sftpPassword, sftpHost, Integer.valueOf(sftpPort));
            sftpUtil.connectSFtp();
            sftpUtil.downloadFile(sftpBasePath + "HK", fileName, "E:\\TOWER_NO", fileName);
            sftpUtil.closeFtp();
        } catch (Exception e) {
            return RData.error("下载附件失败,error:" + e.getMessage());
        }

        return RData.ok();
    }

    @Override
    public RData downloadFile(String county, String plan_name, String plan_form_time, HttpServletResponse response) {

        String countyFolder = "";
        if (county.equals("海口")) {
            countyFolder = "HK";
        } else if (county.equals("三亚")) {
            countyFolder = "SY";
        } else if (county.equals("琼海")) {
            countyFolder = "QH";
        } else if (county.equals("儋州")) {
            countyFolder = "DZ";
        }
        String path = sftpBasePath + countyFolder + File.separator;
        String fileName = county + "-" + plan_name + "-" + plan_form_time + ".pptx";
        logger.info("文件名：" + path + fileName);

        SFtpUtil sftp = new SFtpUtil(sftpAccount, sftpPassword, sftpHost, Integer.valueOf(sftpPort));
        sftp.connectSFtp();
        String trueFileName = sftp.listTrueFileName(path, plan_name);
        sftp.closeFtp();

        logger.info("真实文件名：" + path + trueFileName);


        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        byte[] buff = new byte[1024];

        try {
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(trueFileName.getBytes("GBK"), "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            outputStream = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(path + trueFileName)));
            int i = bis.read(buff);
            while (i != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                i = bis.read(buff);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return RData.ok();
    }
}
