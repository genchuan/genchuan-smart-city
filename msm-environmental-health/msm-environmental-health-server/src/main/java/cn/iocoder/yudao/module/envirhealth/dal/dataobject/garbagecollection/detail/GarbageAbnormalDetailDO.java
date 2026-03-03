package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 9:55
 */
@Data
public class GarbageAbnormalDetailDO extends GarbageAbnormalDO {
    /**
     * 关联garbage_collection.collection_id
     */
    private String planNo;
    /**
     * 关联sys_abnormal_type.id
     */
    private String abnormalTypeName;
    /**
     * 关联sys_area.area_code
     */
    private String areaName;
    /**
     * 关联sys_user.id
     */
    private String reportName;
}