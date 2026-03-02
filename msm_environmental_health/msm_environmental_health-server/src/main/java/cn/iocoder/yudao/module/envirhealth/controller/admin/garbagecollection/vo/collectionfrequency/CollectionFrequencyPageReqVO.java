package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.collectionfrequency;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 收运频次字典分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CollectionFrequencyPageReqVO extends PageParam {

    @Schema(description = "频次编码（如：uuid-frequency-001）")
    private String frequencyCode;

    @Schema(description = "频次名称（如：每日/每周/每月/应急）", example = "张三")
    private String frequencyName;

    @Schema(description = "排序号")
    private Integer sort;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}