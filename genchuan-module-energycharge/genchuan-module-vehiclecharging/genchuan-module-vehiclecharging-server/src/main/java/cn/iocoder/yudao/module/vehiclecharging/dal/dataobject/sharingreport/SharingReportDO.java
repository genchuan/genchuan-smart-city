package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 分账报表 DO
 *
 * @author 亘川智城
 */
@TableName("sharing_report")
@KeySequence("sharing_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class SharingReportDO extends BaseDO {
}
