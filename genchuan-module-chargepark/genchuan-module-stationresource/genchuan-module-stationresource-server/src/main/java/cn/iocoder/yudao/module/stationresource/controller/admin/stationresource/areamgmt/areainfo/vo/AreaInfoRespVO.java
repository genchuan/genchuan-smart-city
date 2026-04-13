package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 片区信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AreaInfoRespVO {

    @Schema(description = "[主键ID] 主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16304")
    @ExcelProperty("[主键ID] 主键ID")
    private Long id;

    @Schema(description = "[片区编号] 唯一标识片区编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[片区编号] 唯一标识片区编号")
    private String areaNo;

    @Schema(description = "[片区名称] 片区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("[片区名称] 片区名称")
    private String name;

    @Schema(description = "[上级片区ID] 上级片区ID", example = "6415")
    @ExcelProperty("[上级片区ID] 上级片区ID")
    private Long parentId;

    @Schema(description = "[省份] 省份", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[省份] 省份")
    private String province;

    @Schema(description = "[城市] 城市", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[城市] 城市")
    private String city;

    @Schema(description = "[区县] 区县", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[区县] 区县")
    private String district;

    @Schema(description = "[详细地址] 详细地址")
    @ExcelProperty("[详细地址] 详细地址")
    private String address;

    @Schema(description = "[负责人ID] 关联芋道用户表system_user", example = "18361")
    @ExcelProperty("[负责人ID] 关联芋道用户表system_user")
    private Long leaderId;

    @Schema(description = "[联系电话] 联系电话")
    @ExcelProperty("[联系电话] 联系电话")
    private String phone;

    @Schema(description = "[关联场站数] 关联场站数", example = "3066")
    @ExcelProperty("[关联场站数] 关联场站数")
    private Integer stationCount;

    @Schema(description = "[状态] 如:未生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[状态] 如:未生效/已生效/已禁用")
    private String status;

    @Schema(description = "[备注] 备注", example = "你说的对")
    @ExcelProperty("[备注] 备注")
    private String remark;

    @Schema(description = "[备用字段1] 备用字段1")
    @ExcelProperty("[备用字段1] 备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] 备用字段2")
    @ExcelProperty("[备用字段2] 备用字段2")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;


    //===============================新增的=================================================
    @Schema(description = "[创建人id] ")
    @ExcelProperty("[创建人id] ")
    private String creator;

    @Schema(description = "[更新时间] ")
    @ExcelProperty("[更新时间] ")
    private LocalDateTime updateTime;
}
