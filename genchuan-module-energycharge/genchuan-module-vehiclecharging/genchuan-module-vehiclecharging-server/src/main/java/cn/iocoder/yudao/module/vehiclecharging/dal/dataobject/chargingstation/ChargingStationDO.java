package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation;


import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ChargingStationDO extends BaseDO {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String stationCode;    // 场站编号
  private String stationName;    // 场站名称
  private String address;        // 场站地址
  private String coopMode;       // 合作模式
  private String openTime;       // 开放时间
  private BigDecimal priceService; // 电价服务费
  private String manager;        // 负责人
  private String stationStatus;  // 场站状态
  private BigDecimal lon;        // 经度
  private BigDecimal lat;        // 纬度
  private String stopReason;     // 停用原因
  private String remark;         // 备注
  private String reserve1;       // 备用1
  private String reserve2;       // 备用2
}
