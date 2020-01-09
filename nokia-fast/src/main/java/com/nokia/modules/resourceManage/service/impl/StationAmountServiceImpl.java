package com.nokia.modules.resourceManage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nokia.modules.resourceManage.dao.StationAmountDao;
import com.nokia.modules.resourceManage.entity.StationInfoAmount;
import com.nokia.modules.resourceManage.entity.VennEntity;
import com.nokia.modules.resourceManage.service.StationAmountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service("StationAmountService")
public class StationAmountServiceImpl extends ServiceImpl<StationAmountDao, StationInfoAmount> implements StationAmountService {

    private static final Logger logger = LoggerFactory.getLogger(StationAmountServiceImpl.class);

    @Override
    public List<StationInfoAmount> selectStationAmountData() {
        List<StationInfoAmount> stationInfoAmountList = new ArrayList<>();
        stationInfoAmountList.addAll(baseMapper.selectHaikouAmount());
        stationInfoAmountList.addAll(baseMapper.selectSanyaAmount());
        stationInfoAmountList.addAll(baseMapper.selectHainanAmount());
        stationInfoAmountList.addAll(baseMapper.selectQundaoAmount());
        return stationInfoAmountList;
    }

    @Override
    public List<StationInfoAmount> selectCountyAmountData() {
        return baseMapper.selectCountyAmount();
    }

    @Override
    public List<StationInfoAmount> selectChanquanAmount() {
        return baseMapper.selectChanquanAmount();
    }

    @Override
    public List<StationInfoAmount> selectCityStationAmount() {
        return baseMapper.selectCityStationAmount();
    }

    @Override
    public int[] selectStationShareAmount() {
        List<StationInfoAmount> stationShareAmountList = baseMapper.selectStationShareAmount();

        int[] stationShareAmount = new int[7];
        for (StationInfoAmount stationInfoAmount : stationShareAmountList) {
            if (stationInfoAmount.getName().equals("移动")) {
                stationShareAmount[0] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("联通")) {
                stationShareAmount[1] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("电信")) {
                stationShareAmount[2] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("移动，联通")) {
                stationShareAmount[3] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("移动，电信")) {
                stationShareAmount[4] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("联通，电信")) {
                stationShareAmount[5] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("移动，联通，电信")) {
                stationShareAmount[6] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("无")) {
//                stationShareAmount[7] = stationInfoAmount.getValue();
            }
        }


        return stationShareAmount;
    }

    @Override
    public VennEntity[] selectStationShareAmountVenn() {
        List<StationInfoAmount> stationShareAmountList = baseMapper.selectStationShareAmount();

        VennEntity[] vennList = new VennEntity[8];

        for (int i = 0; i < stationShareAmountList.size(); i++) {
            VennEntity vennEntity = new VennEntity();
            vennEntity.setName(stationShareAmountList.get(i).getName());
            vennEntity.setValue(stationShareAmountList.get(i).getValue());
            vennEntity.setSets(stationShareAmountList.get(i).getName().split("，"));
            vennList[i] = vennEntity;
        }
        return vennList;
    }

    @Override
    public int[] selectBizSceneAmount() {
        int[] bizSceneAmount = new int[16];
        List<StationInfoAmount> stationInfoAmountList = baseMapper.selectBizSceneAmount();
        for (StationInfoAmount stationInfoAmount : stationInfoAmountList) {
            if (stationInfoAmount.getName().equals("一般市区")) {
                bizSceneAmount[0] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("密集市区")) {
                bizSceneAmount[1] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("县城")) {
                bizSceneAmount[2] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("乡镇")) {
                bizSceneAmount[3] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("农村")) {
                bizSceneAmount[4] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("住宅小区")) {
                bizSceneAmount[5] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("3A以上景区")) {
                bizSceneAmount[6] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("其他景区")) {
                bizSceneAmount[7] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("校园")) {
                bizSceneAmount[8] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("工业园")) {
                bizSceneAmount[9] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("室分")) {
                bizSceneAmount[10] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("商业市场")) {
                bizSceneAmount[11] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("高速")) {
                bizSceneAmount[12] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("高铁线")) {
                bizSceneAmount[13] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("国道")) {
                bizSceneAmount[14] = stationInfoAmount.getValue();
            } else if (stationInfoAmount.getName().equals("其他交通干线")) {
                bizSceneAmount[15] = stationInfoAmount.getValue();
            }
        }
        return bizSceneAmount;
    }

    @Override
    public List<StationInfoAmount> selectStationAloneAmount() {
        return baseMapper.selectStationAloneAmount();
    }

    @Override
    public List<StationInfoAmount> selectStationAmountByYear() {
        List<StationInfoAmount> stationInfoAmountList = baseMapper.selectStationAmountByYear();
        return stationInfoAmountList;
    }

    @Override
    public List<StationInfoAmount> selectStationIncreaseByYear() {
        List<StationInfoAmount> stationInfoAmountList = baseMapper.selectStationIncreaseByYear();
        return stationInfoAmountList;
    }

    @Override
    public List<StationInfoAmount> selectStationAmountByMonth() {
        return baseMapper.selectStationAmountByMonth();
    }

    @Override
    public List<StationInfoAmount> selectStationSourceAmount() {
        return baseMapper.selectStationSourceAmount();
    }

    @Override
    public List<StationInfoAmount> calculateShareRate() {
        List<StationInfoAmount> yidongList = baseMapper.calculateShareRate("移动");

        return yidongList;
    }

    @Override
    public List<StationInfoAmount> selectOperatorStationAmount() {
        return baseMapper.selectOperatorStationAmount();
    }

    @Override
    public List<StationInfoAmount> selectCountyStation5GAmount() {
        return baseMapper.selectCountyStation5GAmount();
    }

    @Override
    public List<StationInfoAmount> selectStation5gAmountByMonth() {
        return baseMapper.selectMonthlyStation5GAmount();
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;

        for (long i = 1L; i <= 6L; i++) {
            LocalDate localDate = LocalDate.now().minusYears(i);
            String ss = localDate.toString().substring(0, 4);
            System.out.println(localDate);
            System.out.println(ss);
            System.out.println("==============");
        }
    }

}
