package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "巡查巡检 - 巡检人员导入 Request VO")
@Data
public class InspectUserImportReqVO {

    @Schema(description = "姓名")
    @ExcelProperty("姓名")
    private String name;

    @Schema(description = "手机号")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "所属片区")
    @ExcelProperty("所属片区")
    private String area;

    @Schema(description = "绑定设备ID")
    @ExcelProperty("绑定设备ID")
    private Long deviceId;

    @Schema(description = "人员状态")
    @ExcelProperty("人员状态")
    private String status;

    @Schema(description = "在线状态")
    @ExcelProperty("在线状态")
    private String onlineStatus;

    // 注意：最后登录时间、备用字段等，如果不需要通过Excel导入，则可以省略
    // 或者如果Excel中有对应列，可以加上
    // @ExcelProperty("最后登录时间")
    // private LocalDateTime lastLoginTime;
}