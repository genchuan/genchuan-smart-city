package cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 本地行业动态分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LocalDynamicPageReqVO extends PageParam {

    @Schema(description = "动态ID，唯一编码，UUID生成", example = "28896")
    private String localDynamicId;

    @Schema(description = "动态标题，如“XX市一网统管平台新增交通监测功能”")
    private String dynamicTitle;

    @Schema(description = "行政区划代码，符合GB/T 2260，行政区划表（sys_area）")
    private String regionCode;

    @Schema(description = "行政区划名称，与行政区划代码同步，行政区划表（sys_area）", example = "芋艿")
    private String regionName;

    @Schema(description = "动态来源，如“XX市政府官网”")
    private String dynamicSource;

    @Schema(description = "发布时间，格式：YYYYMMDD")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] releaseTime;

    @Schema(description = "动态详细内容（支持富文本）")
    private String dynamicDetail;

    @Schema(description = "封面图片ID，关联图片存储ID，文件存储表（sys_file_storage）", example = "9813")
    private String coverImgId;

    @Schema(description = "封面图片路径，封面图片存储路径，文件存储表（sys_file_storage）")
    private String coverImgPath;

    @Schema(description = "动态状态：已发布/草稿，标识动态是否公开", example = "2")
    private String dynamicStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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