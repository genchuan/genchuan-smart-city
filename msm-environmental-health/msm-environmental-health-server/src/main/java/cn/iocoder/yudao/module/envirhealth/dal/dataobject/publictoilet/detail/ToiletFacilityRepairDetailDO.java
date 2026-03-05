package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 14:10
 */
@Data
public class ToiletFacilityRepairDetailDO extends ToiletFacilityRepairDO {
    /**
     * 关联public_toilet.toilet_id
     */
    private String toiletName;
    /**
     * 关联sys_facility.id
     */
    private String facilityName;
    /**
     * 关联sys_user.id
     */
    private String reportName;
    /**
     * 关联sys_user.id
     */
    private String repairName;
}