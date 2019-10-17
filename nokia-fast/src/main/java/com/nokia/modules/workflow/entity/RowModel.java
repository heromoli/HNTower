package com.nokia.modules.workflow.entity;

import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.TableField;
import org.apache.poi.ss.usermodel.CellStyle;

import java.util.HashMap;
import java.util.Map;

public class RowModel extends BaseRowModel {

    @TableField(exist=false)
    private Map<Integer,CellStyle> cellStyleMap = new HashMap<Integer,CellStyle>();

    public void addStyle(Integer row, CellStyle cellStyle){
        cellStyleMap.put(row,cellStyle);
    }

    public CellStyle getStyle(Integer row){
        return cellStyleMap.get(row);
    }

    public Map<Integer, CellStyle> getCellStyleMap() {
        return cellStyleMap;
    }

    public void setCellStyleMap(Map<Integer, CellStyle> cellStyleMap) {
        this.cellStyleMap = cellStyleMap;
    }

}
