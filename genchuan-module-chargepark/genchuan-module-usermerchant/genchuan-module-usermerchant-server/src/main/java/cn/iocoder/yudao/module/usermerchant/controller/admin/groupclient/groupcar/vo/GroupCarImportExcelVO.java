package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)
public class GroupCarImportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("所属集团ID，关联group_info.id")
    private Long groupId;

    @ExcelProperty("车牌号码，唯一")
    private String plateNo;

    @ExcelProperty("车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌")
    private String plateColor;

    @ExcelProperty("车辆类型：小型车/大型车/新能源/其他")
    private String carType;

    @ExcelProperty("绑定时间")
    private LocalDateTime bindTime;

    @ExcelProperty("绑定状态：待审核/已绑定/已解绑")
    private String status;

    @ExcelProperty("审核人ID，关联system_user.id")
    private Long auditorId;

    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @ExcelProperty("审核备注")
    private String auditRemark;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("备用字段1")
    private String reserve1;

    @ExcelProperty("备用字段2")
    private String reserve2;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
