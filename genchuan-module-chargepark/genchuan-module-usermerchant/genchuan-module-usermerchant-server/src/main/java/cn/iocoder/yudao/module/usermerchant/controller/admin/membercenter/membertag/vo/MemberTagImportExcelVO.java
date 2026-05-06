package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo;

import cn.idev.excel.annotation.ExcelProperty;
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
@Accessors(chain=false)// 设置 chain = false，避免用户导入有问题
public class MemberTagImportExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("标签名称")
    private String name;

    @ExcelProperty("标签描述")
    private String description;

    @ExcelProperty("状态：0-禁用，1-正常")
    private Integer status;

    @ExcelProperty("创建者")
    private String creator;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    private String updater;

    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
