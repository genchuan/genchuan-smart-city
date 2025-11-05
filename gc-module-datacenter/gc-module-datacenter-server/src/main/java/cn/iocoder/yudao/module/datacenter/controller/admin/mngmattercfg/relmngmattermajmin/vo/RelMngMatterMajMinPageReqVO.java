package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.relmngmattermajmin.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项大小类关联分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RelMngMatterMajMinPageReqVO extends PageParam {

    @Schema(description = "关联ID，唯一编码，UUID生成", example = "20521")
    private String mngMatterMajorMinorId;

    @Schema(description = "关联管理事项大类ID，关联管理事项大类表(gc_biz_mng_matter_major)", example = "29534")
    private String majorId;

    @Schema(description = "关联管理事项大类名称，与大类ID同步，不可手动修改，关联管理事项大类表(gc_biz_mng_matter_major)", example = "张三")
    private String majorName;

    @Schema(description = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)", example = "26457")
    private String minorId;

    @Schema(description = "关联管理事项小类名称，与小类ID同步，不可手动修改，关联管理事项小类表(gc_biz_mng_matter_minor)", example = "芋艿")
    private String minorName;

    @Schema(description = "关联状态，1（有效）/0（无效），1表示正常关联，0表示已解除关联", example = "2")
    private String relStatus;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}