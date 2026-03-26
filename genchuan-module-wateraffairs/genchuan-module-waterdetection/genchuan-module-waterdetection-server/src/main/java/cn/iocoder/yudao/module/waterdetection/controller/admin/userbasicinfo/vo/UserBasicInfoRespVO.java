package cn.iocoder.yudao.module.waterdetection.controller.admin.userbasicinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 用户基础信息登记 Response VO")
@Data
@ExcelIgnoreUnannotated
public class UserBasicInfoRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("用户编号")
    private String userCode;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("姓名")
    private String userName;

    @Schema(description = "身份证号")
    @ExcelProperty("身份证号")
    private String idCardNo;

    @Schema(description = "家庭住址")
    @ExcelProperty("家庭住址")
    private String address;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "开户日期")
    @ExcelProperty("开户日期")
    private LocalDateTime openDate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}