package cn.iocoder.yudao.module.envir.dal.mysql.garbagetransfer;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.garbagetransfer.vo.*;

/**
 * 垃圾转运站 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageTransferMapper extends BaseMapperX<GarbageTransferDO> {

    default PageResult<GarbageTransferDO> selectPage(GarbageTransferPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageTransferDO>()
                .eqIfPresent(GarbageTransferDO::getGarbageTransferId, reqVO.getGarbageTransferId())
                .likeIfPresent(GarbageTransferDO::getName, reqVO.getName())
                .eqIfPresent(GarbageTransferDO::getLocation, reqVO.getLocation())
                .eqIfPresent(GarbageTransferDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageTransferDO::getEquipmentIds, reqVO.getEquipmentIds())
                .eqIfPresent(GarbageTransferDO::getEnvironmentThreshold, reqVO.getEnvironmentThreshold())
                .eqIfPresent(GarbageTransferDO::getEnvironmentData, reqVO.getEnvironmentData())
                .eqIfPresent(GarbageTransferDO::getTransferDestination, reqVO.getTransferDestination())
                .eqIfPresent(GarbageTransferDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(GarbageTransferDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(GarbageTransferDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(GarbageTransferDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(GarbageTransferDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(GarbageTransferDO::getDailyTransferVolume, reqVO.getDailyTransferVolume())
                .eqIfPresent(GarbageTransferDO::getEquipmentRate, reqVO.getEquipmentRate())
                .eqIfPresent(GarbageTransferDO::getEnvironmentRate, reqVO.getEnvironmentRate())
                .eqIfPresent(GarbageTransferDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageTransferDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageTransferDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageTransferDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GarbageTransferDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageTransferDO::getId));
    }

    /**
     * 查询垃圾转运站详情列表
     * @return 详情列表
     */
    List<GarbageTransferDetailDO> selectListDetail();

}