package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 新生宿舍分配看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DormAssignChartRespVO {

    @Schema(description = "总新生人数")
    private Integer totalCount;
    @Schema(description = "已分配人数")
    private Integer finishedCount;
    @Schema(description = "未分配人数")
    private Integer waitAssignCount;
    @Schema(description = "分配完成进度")
    private BigDecimal progress;
    @Schema(description = "楼栋列表")
    private List<String> buildingList;
    @Schema(description = "各楼栋已分配人数列表")
    private List<Integer> buildingAssignCountList;
    @Schema(description = "各楼栋总床位数列表")
    private List<Integer> buildingBedCountList;

}
