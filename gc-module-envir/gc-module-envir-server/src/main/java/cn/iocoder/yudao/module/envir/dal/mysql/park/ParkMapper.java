package cn.iocoder.yudao.module.envir.dal.mysql.park;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.park.ParkDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.park.vo.*;

/**
 * 公园 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ParkMapper extends BaseMapperX<ParkDO> {

    default PageResult<ParkDO> selectPage(ParkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkDO>()
                .eqIfPresent(ParkDO::getParkId, reqVO.getParkId())
                .likeIfPresent(ParkDO::getName, reqVO.getName())
                .eqIfPresent(ParkDO::getAddress, reqVO.getAddress())
                .eqIfPresent(ParkDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(ParkDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(ParkDO::getGreenTypeIds, reqVO.getGreenTypeIds())
                .eqIfPresent(ParkDO::getGreenMaintenanceCycle, reqVO.getGreenMaintenanceCycle())
                .eqIfPresent(ParkDO::getFacilityCheckCycle, reqVO.getFacilityCheckCycle())
                .eqIfPresent(ParkDO::getWasteTransferFrequency, reqVO.getWasteTransferFrequency())
                .eqIfPresent(ParkDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(ParkDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(ParkDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(ParkDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(ParkDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(ParkDO::getGreenSurvivalRate, reqVO.getGreenSurvivalRate())
                .eqIfPresent(ParkDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(ParkDO::getGreenPhotoUrl, reqVO.getGreenPhotoUrl())
                .eqIfPresent(ParkDO::getFacilityPhotoUrl, reqVO.getFacilityPhotoUrl())
                .eqIfPresent(ParkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ParkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkDO::getId));
    }

    /**
     * 联表查询公园详情列表
     * @return 详情列表
     */
    List<ParkDetailDO> selectListDetail();
}