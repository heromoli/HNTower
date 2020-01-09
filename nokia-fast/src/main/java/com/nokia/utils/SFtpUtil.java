package com.nokia.utils;

import com.jcraft.jsch.*;
import com.nokia.common.exception.RRException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

public class SFtpUtil {
    private static final Logger logger = LoggerFactory.getLogger(SFtpUtil.class);

    private ChannelSftp sftp;
    private Session session;

    private String account;
    private String pwd;
    private String privateKey;
    private String host;
    private int port;

    /**
     * 构造基于密码认证的sftp对象
     */
    public SFtpUtil(String account, String pwd, String host, int port) {
        this.account = account;
        this.pwd = pwd;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SFtpUtil(String account, String host, int port, String privateKey) {
        this.account = account;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    /**
     * 连接sftp服务器
     */
    public void connectSFtp() {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }
            session = jsch.getSession(account, host, port);

            if (pwd != null) {
                session.setPassword(pwd);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            logger.info("通过SFTP协议连接文件服务器成功。");
        } catch (JSchException e) {
            e.printStackTrace();
            logger.info("通过SFTP协议连接文件服务器失败。");
        }
    }

    /**
     * 关闭sftp连接
     */
    public void closeFtp() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    /**
     * 下载文件
     *
     * @param remotePath   远程路径
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public byte[] download(String remotePath, String downloadFile) throws SftpException, IOException {
        if (remotePath != null && !"".equals(remotePath)) {
            sftp.cd(remotePath);
        }
        InputStream is = sftp.get(downloadFile);
        byte[] fileData = IOUtils.toByteArray(is);
        return fileData;
    }

    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param localPath    本地路径
     * @param remotePath   远程路径
     * @param sftpFileName sftp端文件名
     * @param input        输入流
     */
    public void uploadFile(String localPath, String remotePath, String sftpFileName, InputStream input) throws SftpException {
        try {
            sftp.cd(localPath);
            sftp.cd(remotePath);
        } catch (SftpException e) {
            //目录不存在，则创建文件夹
            String[] dirs = remotePath.split("/");
            String tempPath = localPath;
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) continue;
                tempPath += "/" + dir;
                try {
                    sftp.cd(tempPath);
                } catch (SftpException ex) {
                    sftp.mkdir(tempPath);
                    sftp.cd(tempPath);
                }
            }
        }
        sftp.put(input, sftpFileName);  //上传文件
    }

    /**
     * 下载单个文件
     *
     * @param remotePath：远程下载目录(以路径符号结束)
     * @param remoteFileName：下载文件名
     * @param localPath：本地保存目录(以路径符号结束)
     * @param localFileName：保存文件名
     */
    public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileOutputStream fileOutput = null;
        try {
            mkdirs(localPath);
//            File file = new File(localPath + "/"+ localFileName);
            File file = new File(localPath + File.separator + localFileName);
            fileOutput = new FileOutputStream(file);
//            sftp.get(remotePath + "/" + remoteFileName, fileOutput);
            sftp.get(remotePath + File.separator + remoteFileName, fileOutput);
            if (logger.isInfoEnabled()) {
                logger.info("#### DownloadFile:" + remoteFileName + " success from sftp.");
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (null != fileOutput) {
                try {
                    fileOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }



    /**
     * 批量下载文件。
     *
     * @param remotePath   远程路径
     * @param localPath    本地的路径
     * @param downloadFile 下载的文件
     */
    public List<String> batchDownloadFiles(String remotePath, String localPath, String downloadFile, String fileFormat, String fileEndFormat, boolean del) throws SftpException {
//        if (remotePath != null && !"".equals(remotePath)) {
//            sftp.cd(remotePath);
//        }
        List<String> filenames = new ArrayList<String>();
        try {
            // connect();
            Vector vector = listFiles(remotePath);
            if (vector.size() > 0) {
                logger.info("目标路径下有多个目标，开始批量下载：" + vector.size());
                Iterator it = vector.iterator();
                while (it.hasNext()) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) it.next();
                    String filename = entry.getFilename();
                    SftpATTRS attrs = entry.getAttrs();
                    if (!attrs.isDir()) {
                        boolean flag = false;
                        String localFileName = localPath + filename;
                        fileFormat = fileFormat == null ? "" : fileFormat.trim();
                        fileEndFormat = fileEndFormat == null ? "" : fileEndFormat.trim();
                        // 三种情况
                        if (fileFormat.length() > 0 && fileEndFormat.length() > 0) {
                            if (filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileFormat.length() > 0 && "".equals(fileEndFormat)) {
                            if (filename.startsWith(fileFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else if (fileEndFormat.length() > 0 && "".equals(fileFormat)) {
                            if (filename.endsWith(fileEndFormat)) {
                                flag = downloadFile(remotePath, filename, localPath, filename);
                                if (flag) {
                                    filenames.add(localFileName);
                                    if (flag && del) {
                                        deleteSFTP(remotePath, filename);
                                    }
                                }
                            }
                        } else {
                            flag = downloadFile(remotePath, filename, localPath, filename);
                            if (flag) {
                                filenames.add(localFileName);
                                if (flag && del) {
                                    deleteSFTP(remotePath, filename);
                                }
                            }
                        }
                    }
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("download file is success:remotePath=" + remotePath
                        + "and localPath=" + localPath + ",file size is" + vector.size());
            }
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            // this.disconnect();
        }
        return filenames;
    }

    /**
     * 批量下载管理
     *
     * @param remotePath            服务器端地址
     * @param localPath             本地地址
     */
    public boolean batchDownloadMag(String remotePath, String localPath) {
        // 确保服务端地址是绝对地址
        if (!remotePath.startsWith("/")) {
            remotePath = "/" + remotePath;
        }
        // 目录标志符，若为有效地址，则为true，否则为false
        boolean flag = isDirExist(remotePath);
        if (flag) {
            try {
                Vector vv = sftp.ls(remotePath);
                if (vv == null && vv.size() == 0) {
                    return false;
                } else {
                    // 遍历当前目录所有文件及子文件夹
                    for (Object object : vv) {
                        ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) object;
                        // 得到当前项的名称（可能是文件名，也可能是文件夹名）
                        String filename = entry.getFilename();
                        // 去除无关项
                        if (".".equals(filename) || "..".equals(filename)) {
                            continue;
                        }
                        if (openDir(remotePath + File.separator + filename, sftp)) {
                            // 如果是目录，创建目录，进行递归
                            String localPathChild = localPath + filename;
                            File file = new File(localPathChild);
                            file.mkdirs();
                            batchDownloadMag(remotePath + File.separator + filename, localPathChild + File.separator);
                        } else {
                            // 如果是文件，进行下载。
                            logger.info("SFTP服务器端的路径为：" + remotePath + " ,本地路径为：" + localPath + " ,文件名为：" + filename);
                            downloadFile(remotePath, filename, localPath, filename);
                        }
                    }
                    return true;
                }
            } catch (SftpException e) {
                logger.error("遍历目录失败：" + remotePath, e);
            }
        } else {
            logger.info("对应的目录" + remotePath + "不存在！");
            return false;
        }
        return true;
    }

    /**
     * 上传单个文件
     *
     * @param remotePath：远程保存目录
     * @param remoteFileName：保存文件名
     * @param localPath：本地上传目录(以路径符号结束)
     * @param localFileName：上传的文件名
     */
    public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
        FileInputStream in = null;
        try {
            createDir(remotePath);
            File file = new File(localPath + localFileName);
            in = new FileInputStream(file);
            sftp.put(in, remoteFileName);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 批量上传文件
     *
     * @param remotePath：远程保存目录
     * @param localPath：本地上传目录(以路径符号结束)
     * @param del：上传后是否删除本地文件
     */
    public boolean bacthUploadFile(String remotePath, String localPath, boolean del) {
        try {
            connectSFtp();
            File file = new File(localPath);
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()
                        && files[i].getName().indexOf("bak") == -1) {
                    if (this.uploadFile(remotePath, files[i].getName(),
                            localPath, files[i].getName())
                            && del) {
                        deleteFile(localPath + files[i].getName());
                    }
                }
            }
            if (logger.isInfoEnabled()) {
                logger.info("upload file is success:remotePath=" + remotePath
                        + "and localPath=" + localPath + ",file size is "
                        + files.length);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeFtp();
        }
        return false;
    }

    /**
     * 创建远程目录
     *
     * @param createpath
     */
    public boolean createDir(String createpath) {
        try {
            if (isDirExist(createpath)) {
                this.sftp.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            this.sftp.cd(createpath);
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断远程目标是否为目录
     *
     * @param directory
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 打开或进入目录
     */
    private boolean openDir(String directory, ChannelSftp channelsftp) {
        try {
            channelsftp.cd(directory);
            return true;
        } catch (SftpException e) {
            logger.error("异常：目标为文件而不是路径：" + directory);
//            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除本地文件
     *
     * @param filePath
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        }
        if (!file.isFile()) {
            return false;
        }
        boolean rs = file.delete();
        if (rs && logger.isInfoEnabled()) {
            logger.info("delete file success from local.");
        }
        return rs;
    }

    /**
     * 删除stfp文件
     *
     * @param directory：要删除文件所在目录
     * @param deleteFile：要删除的文件
     */
    public void deleteSFTP(String directory, String deleteFile) {
        try {
            // sftp.cd(directory);
            sftp.rm(directory + deleteFile);
            if (logger.isInfoEnabled()) {
                logger.info("delete file success from sftp.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 如果目录不存在就创建目录
     *
     * @param path
     */
    public void mkdirs(String path) {
        File f = new File(path);
//        String fs = f.getParent();
//        f = new File(fs);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param remotePath 要列出的目录
     */
    public Vector listFiles(String remotePath) throws SftpException {
        return sftp.ls(remotePath);
    }


    /**
     * 导出文件时为Writer生成OutputStream
     */
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws RRException {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));

            return os;
        } catch (IOException e) {
            throw new RRException("创建文件失败！");
        }
    }
}
