package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 收运频次字典 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CollectionFrequencyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9337")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "频次编码（如：uuid-frequency-001）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("频次编码（如：uuid-frequency-001）")
    private String frequencyCode;

    @Schema(description = "频次名称（如：每日/每周/每月/应急）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("频次名称（如：每日/每周/每月/应急）")
    private String frequencyName;

    @Schema(description = "排序号")
    @ExcelProperty("排序号")
    private Integer sort;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}