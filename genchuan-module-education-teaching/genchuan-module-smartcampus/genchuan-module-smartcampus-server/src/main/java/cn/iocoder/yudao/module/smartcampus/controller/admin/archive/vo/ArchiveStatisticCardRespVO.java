package cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "学生学籍卡片统计数据")
@Data
public class ArchiveStatisticCardRespVO {

    @Schema(description = "学生总数")
    private Integer studentTotal;

    @Schema(description = "在籍人数")
    private Integer inStudentTotal;

    @Schema(description = "休学人数")
    private Integer suspendStudentTotal;

    @Schema(description = "退学人数")
    private Integer quitStudentTotal;

    @Schema(description = "异动人数")
    private Integer changeStudentTotal;

    @Schema(description = "学生总数同比增长率(%)")
    private BigDecimal studentTotalYoy;

    @Schema(description = "在籍人数同比增长率(%)")
    private BigDecimal inStudentTotalYoy;
}