package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 医疗机构分布视图 Response VO")
@Data
public class MedInstRespVO {

    //1.左侧展示医疗机构分类数量卡片
    @Schema(description = "医疗机构分类数量统计对象")
    private InstSortCountVO instSortCountVO;
    @Data
    @Schema(description = "医疗机构分类数量统计对象")
    public static class InstSortCountVO {


        @Schema(description = "医疗机构总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
        @ExcelProperty("总数")
        private Integer totalInst;

        @Schema(description = "综合医院数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
        @ExcelProperty("综合医院数量")
        private Integer generalHospitalCount;

        @Schema(description = "专科医院数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
        @ExcelProperty("专科医院数量")
        private Integer specializedHospitalCount;


        @Schema(description = "社区卫生中心数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
        @ExcelProperty("社区卫生中心")
        private Integer communityHealthCount;
    }

    //2.主体展示区域医疗机构分布地图
    @Schema(description = "机构列表，用于地图标注及接诊信息展示")
    private List<InstDetailVO> instList;

    @Data
    @Schema(description = "医疗机构详细信息对象")
    public static class InstDetailVO {
        @Schema(description = "医疗机构id-orgId", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @ExcelProperty("医疗机构id-orgId")
        private Integer orgId;

        @Schema(description = "机构类型（0-综合医院 / 1-专科医院 / 2-社区卫生中心）", example = "0")
        private String type;

        @Schema(description = "机构床位数量", example = "500")
        private Integer bedCount;

        @Schema(description = "机构接诊量", example = "1200")
        private Integer treatVolume;

        @Schema(description = "机构X坐标", example = "116.397")
        private BigDecimal coordX;

        @Schema(description = "机构Y坐标", example = "39.908")
        private BigDecimal coordY;

        @Schema(description = "床位使用率（%）", example = "80")
        private BigDecimal bedUsageRate;

//        @Schema(description = "接诊趋势（近7天数据）")
//        private List<TreatTrendVO> treatTrend;

    }

//    @Data
//    @Schema(description = "接诊趋势对象")
//    public static class TreatTrendVO {
//
//        @Schema(description = "日期，格式 yyyy-MM-dd", example = "2025-11-01")
//        private String date;
//
//        @Schema(description = "接诊量", example = "150")
//        private Integer treatVolume;
//    }


    //3.底部展示热门接诊机构TOP5列表（按接诊量排序）
    @Schema(description = "机构列表，热门接诊机构TOP5列表")
    private List<InstDetailVO> instTop5List;


}
