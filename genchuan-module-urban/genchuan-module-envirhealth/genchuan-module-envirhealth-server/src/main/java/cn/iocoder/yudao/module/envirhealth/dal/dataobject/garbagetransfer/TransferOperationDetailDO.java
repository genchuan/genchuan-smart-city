package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.framework.util.json.StringSplitUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/14 17:23
 */
@Data
public class TransferOperationDetailDO extends TransferOperationDO {
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleName;
    /**
     * 关联sys_garbage_type.id
     */
    private String garbageTypeName;
    /**
     * 关联garbage_collection.collection_id
     */
    private String planNo;
    /**
     * 关联garbage_collection.collection_id
     */
    @JsonIgnore
    private String pointsNameStr;

    private List<String> pointsName;

    private String equipmentStatus;

    public void setPointsNameStr(String pointsNameStr) {
        this.pointsNameStr = pointsNameStr;
        this.pointsName = StringSplitUtils.splitToStringList(pointsNameStr);
    }

}