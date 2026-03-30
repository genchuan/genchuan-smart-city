package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 垃圾收运导入专用VO - 与导出VO保持一致
 */
@Data
public class GarbageCollectionImportVO {

    @ExcelProperty(value = "收运计划主键", index = 0)
    private String collectionId;

    @ExcelProperty(value = "收运计划单编号", index = 1)
    private String planNo;

    @ExcelProperty(value = "区域编码", index = 2)
    private String areaCode;

    @ExcelProperty(value = "垃圾类型编码", index = 3)
    private String garbageTypeId;

    @ExcelProperty(value = "收运频次", index = 4)
    private String frequency;

    @ExcelProperty(value = "收运时段", index = 5)
    private String timePeriod;

    @ExcelProperty(value = "车牌号", index = 6)
    private String vehicleId;

    @ExcelProperty(value = "负责人员", index = 7)
    private String staffIds;

    @ExcelProperty(value = "收运点位", index = 8)
    private String pointIds;

    @ExcelProperty(value = "计划状态编码", index = 9)
    private String planStatusId;

    @ExcelProperty(value = "完成率", index = 10)
    private String completionRate;

    @ExcelProperty(value = "异常记录数", index = 11)
    private String abnormalCount;

    @ExcelProperty(value = "创建人", index = 12)
    private String createBy;

    @ExcelProperty(value = "当前进度", index = 13)
    private String progress;

    @ExcelProperty(value = "已收运量", index = 14)
    private String collectedVolume;

    @ExcelProperty(value = "打卡状态", index = 15)
    private String checkinStatus;

    @ExcelProperty(value = "轨迹覆盖情况", index = 16)
    private String trackCoverage;

    @ExcelProperty(value = "最新上报时间", index = 17)
    private String lastReportTime;

    @ExcelProperty(value = "异常", index = 18)
    private String isAbnormal;

    @ExcelProperty(value = "完成时间", index = 19)
    private String completeTime;

    @ExcelProperty(value = "总收运量", index = 20)
    private String totalVolume;

    @ExcelProperty(value = "异常处置结果", index = 21)
    private String abnormalResult;

    @ExcelProperty(value = "异常办结率", index = 22)
    private String abnormalCompleteRate;
}