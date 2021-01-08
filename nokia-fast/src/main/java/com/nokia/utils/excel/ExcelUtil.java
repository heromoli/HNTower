package com.nokia.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.nokia.common.exception.RRException;
import com.nokia.common.listener.ExcelListener;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wow on 2019/7/15.
 */
public class ExcelUtil {

    /**
     * 私有化构造方法
     */
    private ExcelUtil() {
    }

    /**
     * 读取 Excel(多个 sheet)
     * 将多sheet合并成一个list数据集，通过自定义ExcelReader继承AnalysisEventListener
     * 重写invoke doAfterAllAnalysed方法
     * getExtendsBeanList 主要是做Bean的属性拷贝 ，可以通过ExcelReader中添加的数据集直接获取
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T> rowModel) throws RRException {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        for (Sheet sheet : reader.getSheets()) {
            sheet.setClazz(rowModel);
            reader.read(sheet);
        }
        return getExtendsBeanList(excelListener.getDataList(), rowModel);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel    文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo  sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T> rowModel, int sheetNo) throws RRException {
        return readExcel(excel, rowModel, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel       文件
     * @param rowModel    实体类映射，继承 BaseRowModel 类
     * @param sheetNo     sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <T extends BaseRowModel> List<T> readExcel(MultipartFile excel, Class<T> rowModel, int sheetNo,
                                                             int headLineNum) throws RRException {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return new ArrayList<>();
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel));
        return getExtendsBeanList(excelListener.getDataList(), rowModel);
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     * 自定义WriterHandler 可以定制行列数据进行灵活化操作
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     */
    public static <T extends BaseRowModel> void writeExcel(HttpServletResponse response, List<T> list,
                                                           String fileName, String sheetName, ExcelTypeEnum excelTypeEnum,
                                                           Class<T> classType) throws RRException {
        if (sheetName == null || "".equals(sheetName)) {
            sheetName = "sheet1";
        }
        if (excelTypeEnum == ExcelTypeEnum.XLSX) {
//            ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null,
//                    getOutputStream(fileName, response, excelTypeEnum), excelTypeEnum, true, new WriterHandler07<>(classType));
            ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(null,
                    getOutputStream(fileName, response, excelTypeEnum), excelTypeEnum, true);
            Sheet sheet = new Sheet(1, 0, classType);

            sheet.setSheetName(sheetName);
            try {
                writer.write(list, sheet);
            } finally {
                writer.finish();
            }
            //其实也可以专门调03版的样式，或者直接套用
        } else if (excelTypeEnum == ExcelTypeEnum.XLS) {
//            ExcelWriterFactory writer = new ExcelWriterFactory(getOutputStream(fileName, response,excelTypeEnum), excelTypeEnum);
            ExcelWriter writer = EasyExcelFactory.getWriter(getOutputStream(fileName, response, excelTypeEnum), excelTypeEnum, true);
            Sheet sheet = new Sheet(1, 0, classType);
            sheet.setSheetName(sheetName);
            try {
                writer.write(list, sheet);
            } finally {
                writer.finish();
//                writer.close();
            }
        }
    }

    /**
     * 导出 Excel ：多个 sheet，带表头
     */
    public static <T0 extends BaseRowModel, T1 extends BaseRowModel> void writeExcelWithSheets(HttpServletResponse response, ExcelTypeEnum excelTypeEnum, String fileName,
                                                                          List<T0> list0, String sheetName0, Class<T0> classType0, List<T1> list1, String sheetName1, Class<T1> classType1) throws RRException {
//        ExcelWriter excelWriter = null;
//        try {
//            excelWriter = EasyExcel.write(getOutputStream(fileName, response, excelTypeEnum)).build();
//            WriteSheet writeSheet0 = EasyExcel.writerSheet(0, sheetName0).head(classType0).build();
//            WriteSheet writeSheet1 = EasyExcel.writerSheet(1, sheetName1).head(classType1).build();
//
//            excelWriter.write(list0, writeSheet0);
//            excelWriter.write(list1, writeSheet1);
//        } finally {
//            if (excelWriter != null) {
//                excelWriter.finish();
//            }
//        }

        ExcelWriter writer = EasyExcelFactory.getWriterWithTemp(null,
                getOutputStream(fileName, response, excelTypeEnum), excelTypeEnum, true);
        Sheet sheet0 = new Sheet(1, 0, classType0);

        sheet0.setSheetName(sheetName0);

        Sheet sheet1 = new Sheet(2, 0, classType1);

        sheet1.setSheetName(sheetName1);
        try {
            writer.write(list0, sheet0);
            writer.write(list1, sheet1);
        } finally {
            writer.finish();
        }
    }

    /**
     * 导出文件时为Writer生成OutputStream
     */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response, ExcelTypeEnum excelTypeEnum) throws RRException {
        //创建本地文件
        String filePath = fileName + excelTypeEnum.getValue();
        try {
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "filename=" + fileName);
            return response.getOutputStream();
        } catch (IOException e) {
            throw new RRException("创建文件失败！");
        }
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel         需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel,
                                         ExcelListener excelListener) throws RRException {
        String fileName = excel.getOriginalFilename();
        if (fileName == null) {
            throw new RRException("文件格式错误！");
        }
        if (!fileName.toLowerCase().endsWith(ExcelTypeEnum.XLS.getValue()) && !fileName.toLowerCase().endsWith(ExcelTypeEnum.XLSX.getValue())) {
            throw new RRException("文件格式错误！");
        }
        InputStream is;
        try {
            is = excel.getInputStream();
            InputStream inputStream = new BufferedInputStream(is);  //添加这一行，否正导入XLS格式会报错
            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            //do something
        }
        return null;
    }

    /**
     * 利用BeanCopy转换list
     */
    public static <T extends BaseRowModel> List<T> getExtendsBeanList(List<?> list, Class<T> typeClazz) {
        return BeanCopyUtils.convert(list, typeClazz);
    }
}
