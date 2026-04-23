package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 德育资源分页 Request VO")
@Data
public class MoralResourcePageReqVO extends PageParam {

    @Schema(description = "资源名称", example = "张三")
    private String resourceName;

    @Schema(description = "资源类型：课程/图书/专题包", example = "2")
    private String resourceType;

    @Schema(description = "资源地址", example = "https://www.iocoder.cn")
    private String resourceUrl;

    @Schema(description = "学习人数")
    private Integer learnNum;

    @Schema(description = "学习完成率")
    private BigDecimal learnRate;

    @Schema(description = "上架时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] publishTime;

    @Schema(description = "下架时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] offTime;

    @Schema(description = "状态：未上架/已上架", example = "1")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}