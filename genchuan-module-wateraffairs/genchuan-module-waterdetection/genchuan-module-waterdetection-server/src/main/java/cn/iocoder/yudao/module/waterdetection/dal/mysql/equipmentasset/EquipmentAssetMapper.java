package cn.iocoder.yudao.module.waterdetection.dal.mysql.equipmentasset;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentasset.EquipmentAssetDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset.vo.*;

/**
 * 设备资产台账管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EquipmentAssetMapper extends BaseMapperX<EquipmentAssetDO> {

    default PageResult<EquipmentAssetDO> selectPage(EquipmentAssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EquipmentAssetDO>()
                .eqIfPresent(EquipmentAssetDO::getEquipmentCode, reqVO.getEquipmentCode())
                .likeIfPresent(EquipmentAssetDO::getEquipmentName, reqVO.getEquipmentName())
                .eqIfPresent(EquipmentAssetDO::getModel, reqVO.getModel())
                .eqIfPresent(EquipmentAssetDO::getSpecification, reqVO.getSpecification())
                .eqIfPresent(EquipmentAssetDO::getInstallLocation, reqVO.getInstallLocation())
                .betweenIfPresent(EquipmentAssetDO::getInstallDate, reqVO.getInstallDate())
                .eqIfPresent(EquipmentAssetDO::getManufacturer, reqVO.getManufacturer())
                .eqIfPresent(EquipmentAssetDO::getMaintenanceRecord, reqVO.getMaintenanceRecord())
                .betweenIfPresent(EquipmentAssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EquipmentAssetDO::getId));
    }

}