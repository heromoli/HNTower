package com.nokia.modules.sys.service;

import com.nokia.utils.RData;

import javax.servlet.http.HttpServletResponse;

public interface SftpFileService {
    RData downloadSftpFile(String county,String plan_name, String plan_form_time, HttpServletResponse response);

    RData downloadFile(String county,String plan_name, String plan_form_time, HttpServletResponse response);
}
