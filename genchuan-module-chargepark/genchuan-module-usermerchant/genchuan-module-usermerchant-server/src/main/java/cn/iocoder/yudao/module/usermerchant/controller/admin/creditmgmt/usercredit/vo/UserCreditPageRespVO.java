package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.usercredit.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户信用 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserCreditPageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1021")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31349")
    @ExcelProperty("用户ID")
    private Long userId;

    @Schema(description = "用户姓名", example = "张三")
    @ExcelProperty("用户姓名")
    private String nickname;

    @Schema(description = "信用分，默认100", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("信用分，默认100")
    private Integer creditScore;

    @Schema(description = "信用等级：优秀/良好/中等/较差/极差", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("信用等级：优秀/良好/中等/较差/极差")
    private String creditLevel;

    @Schema(description = "评分规则编码")
    @ExcelProperty("评分规则编码")
    private String ruleCode;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间（系统字段）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间（系统字段）")
    private LocalDateTime updateTime;

}