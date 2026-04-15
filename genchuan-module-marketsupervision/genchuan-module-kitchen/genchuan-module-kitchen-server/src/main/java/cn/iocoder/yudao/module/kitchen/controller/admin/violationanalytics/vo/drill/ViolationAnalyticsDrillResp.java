package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.kitchen.controller.admin.aialertmessage.vo.AiAlertMessageRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.RectifyReviewLedgerRespVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.aialertmessage.AiAlertMessageDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice.SysDeviceDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "企业违规数据分析 - 统计报表 Response VO")
public class ViolationAnalyticsDrillResp {

    // ====================== 统计维度（日/周/月） ======================
    @ExcelProperty(value = "统计维度", index = 1)
    @Schema(description = "统计维度：日、周、月",hidden = true)
    private String statisticPeriod;

    @ExcelProperty(value = "统计开始时间", index = 2)
    @Schema(description = "统计开始时间")
    private LocalDateTime beginTime;

    @ExcelProperty(value = "统计结束时间", index = 3)
    @Schema(description = "统计结束时间")
    private LocalDateTime endTime;

    @ExcelProperty(value = "统计时间标识", index = 11)
    @Schema(description = "用于图表展示：yyyy-MM-dd / yyyy-第W周 / yyyy-MM   /第几年第几月第几日-第几年第几月第几日")
    private String timeLabel;
    // ====================== 企业信息 ======================
    @ExcelProperty(value = "企业ID", index = 4)
    @Schema(description = "企业ID")
    private Long entId;

    @ExcelProperty(value = "企业名称", index = 5)
    @Schema(description = "企业名称")
    private String entName;

    // ====================== 核心统计指标 ======================

    @ExcelProperty(value = "钻取的告警详情列表", index = 6)
    @Schema(description = "钻取的告警详情列表（AI预警按企业分组统计）")
    private List<AiAlertMessageRespVO> alarmList;

    @ExcelProperty(value = "钻取的违规次数", index = 7)
    @Schema(description = "钻取的违规次数（整改复审计数）")
    private List<RectifyReviewLedgerRespVO> rectifyReviewDOList;

    @ExcelProperty(value = "设备正常列表", index = 8)
    @Schema(description = "设备正常列表（去重设备计算）")
    private List<SysDeviceDO> deviceNormalList;

    @ExcelProperty(value = "整改完成列表", index = 9)
    @Schema(description = "整改完成列表（整改复审计算）")
    private List<RectifyReviewDO> rectifyFinishList;

    // ====================== 导出/排名/图表通用字段 ======================

    @ExcelProperty(value = "企业违规排名", index = 10)
    @Schema(description = "企业违规频次排名（降序）")
    private Integer rank;


}
