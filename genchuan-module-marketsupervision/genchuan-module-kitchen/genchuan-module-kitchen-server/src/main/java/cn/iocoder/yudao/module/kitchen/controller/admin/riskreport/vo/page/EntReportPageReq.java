package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "企业风险报表 Request VO")
public class EntReportPageReq extends PageParam {

    @Schema(description = "筛选的编号")
    private List<String> reportNoList;

    @Schema(description = "编号")
    private String reportNo;

    // ====================== 【必填：区分月报 / 自定义报表】 ======================
//    @Schema(description = "报表类型 1-企业月度评估报告 2-自定义报表", requiredMode = Schema.RequiredMode.REQUIRED)
//    private Integer reportType;

    // ====================== 【统计周期是月的】 ======================
    @Schema(description = "统计周期（yyyy-MM）")
    private String statisticPeriod;

    // ====================== 【自定义报表专用筛选】 ======================
    @Schema(description = "区域")
    private String area;

    @Schema(description = "企业类型")
    private String entType;

    // ====================== 【通用时间条件】 ======================
    @Schema(description = "统计开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime beginTime;

    @Schema(description = "统计结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    // ====================== 【企业维度】 ======================
//    @Schema(description = "企业ID")
//    private Long entId;

    @Schema(description = "企业名称（模糊查询）")
    private String entName;



}
