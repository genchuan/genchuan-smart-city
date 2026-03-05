package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/5 11:52
 */
@Data
public class ToiletConsumableDetailDO extends ToiletConsumableDO {
    /**
     * 耗材名称（关联 sys_consumable 表）
     */
    private String consumableName;

    /**
     * 负责人名称（关联 sys_user 表）
     */
    private String managerName;

    /**
     * 公厕名称（关联 public_toilet 表）
     */
    private String toiletName;

    /**
     * 公厕名称（关联 public_toilet 表）
     */
    private String areaName;
}