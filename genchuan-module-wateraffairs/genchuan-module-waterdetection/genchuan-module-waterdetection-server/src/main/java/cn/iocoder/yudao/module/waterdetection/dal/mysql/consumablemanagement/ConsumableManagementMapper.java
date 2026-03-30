package cn.iocoder.yudao.module.waterdetection.dal.mysql.consumablemanagement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.consumablemanagement.ConsumableManagementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo.*;

/**
 * 耗材库存与更换管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface ConsumableManagementMapper extends BaseMapperX<ConsumableManagementDO> {

    default PageResult<ConsumableManagementDO> selectPage(ConsumableManagementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ConsumableManagementDO>()
                .eqIfPresent(ConsumableManagementDO::getConsumableId, reqVO.getConsumableId())
                .eqIfPresent(ConsumableManagementDO::getConsumableType, reqVO.getConsumableType())
                .eqIfPresent(ConsumableManagementDO::getStockQuantity, reqVO.getStockQuantity())
                .eqIfPresent(ConsumableManagementDO::getWarningThreshold, reqVO.getWarningThreshold())
                .betweenIfPresent(ConsumableManagementDO::getLastReplacementDate, reqVO.getLastReplacementDate())
                .betweenIfPresent(ConsumableManagementDO::getNextReplacementDate, reqVO.getNextReplacementDate())
                .eqIfPresent(ConsumableManagementDO::getReplacementQuantity, reqVO.getReplacementQuantity())
                .eqIfPresent(ConsumableManagementDO::getRelatedEquipmentId, reqVO.getRelatedEquipmentId())
                .betweenIfPresent(ConsumableManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ConsumableManagementDO::getId));
    }

}