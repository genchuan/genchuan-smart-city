package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("pay_app")
@Data
public class PayAppDO {

    @TableId
    private Long id;

    private String name;

    private Integer status;

    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
