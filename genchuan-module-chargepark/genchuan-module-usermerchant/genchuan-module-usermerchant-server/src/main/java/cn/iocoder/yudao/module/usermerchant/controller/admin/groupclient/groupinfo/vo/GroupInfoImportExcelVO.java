package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupinfo.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.usermerchant.framework.commom.utils.FlexibleTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=false)
public class GroupInfoImportExcelVO {

    @ExcelProperty("主键ID")
    private Long id;

    @ExcelProperty("集团名称，唯一")
    private String name;

    @ExcelProperty("联系人")
    private String contact;

    @ExcelProperty("联系手机号")
    private String phone;

    @ExcelProperty("集团类型：企业单位/事业单位/政府机构/其他")
    private String groupType;

    @ExcelProperty("集团地址")
    private String address;

    @ExcelProperty("注册时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime registerTime;

    @ExcelProperty("集团状态：待审核/正常/禁用/已驳回")
    private String status;

    @ExcelProperty("账户余额")
    private BigDecimal walletBalance;

    @ExcelProperty("审核人ID，关联system_user.id")
    private Long auditorId;

    @ExcelProperty("审核意见")
    private String auditRemark;

    @ExcelProperty("审核时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime auditTime;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("备用字段1")
    private String reserve1;

    @ExcelProperty("备用字段2")
    private String reserve2;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("更新者")
    private String updater;

    @ExcelProperty("创建时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime createTime;

    @ExcelProperty("更新时间")
    @JsonDeserialize(using = FlexibleTimestampDeserializer.class)
    private LocalDateTime updateTime;

}
