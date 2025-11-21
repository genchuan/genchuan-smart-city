package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;


import java.time.LocalDateTime;

@Schema(description = "管理后台 - 本地行业动态新增/修改 Request VO")
@Data
public class LocalDynamicSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2654")
    private Long id;

    @Schema(description = "动态ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "28896")
    @NotEmpty(message = "动态ID，唯一编码，UUID生成不能为空")
    private String localDynamicId;

    @Schema(description = "动态标题，如“XX市一网统管平台新增交通监测功能”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态标题，如“XX市一网统管平台新增交通监测功能”不能为空")
    private String dynamicTitle;

    @Schema(description = "行政区划代码，符合GB/T 2260，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政区划代码，符合GB/T 2260，行政区划表（sys_area）不能为空")
    private String regionCode;

    @Schema(description = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）不能为空")
    private String regionName;

    @Schema(description = "动态来源，如“XX市政府官网”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态来源，如“XX市政府官网”不能为空")
    private String dynamicSource;

    @Schema(description = "发布时间，格式：YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发布时间，格式：YYYYMMDD不能为空")
    private LocalDateTime releaseTime;

    @Schema(description = "动态详细内容（支持富文本）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "动态详细内容（支持富文本）不能为空")
    private String dynamicDetail;

    @Schema(description = "封面图片ID，关联图片存储ID，文件存储表（sys_file_storage）", example = "9813")
    private String coverImgId;

    @Schema(description = "封面图片路径，封面图片存储路径，文件存储表（sys_file_storage）")
    private String coverImgPath;

    @Schema(description = "动态状态：已发布/草稿，标识动态是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "动态状态：已发布/草稿，标识动态是否公开不能为空")
    private String dynamicStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“动态类型：功能更新/成效展示”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“关联业务：事件处置/设备监测”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“原文链接”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“阅读量”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}