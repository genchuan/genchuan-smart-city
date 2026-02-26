package cn.iocoder.yudao.module.envir.dal.mysql.commercialstreet;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet.CommercialStreetDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.commercialstreet.vo.*;

/**
 * 商业街 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CommercialStreetMapper extends BaseMapperX<CommercialStreetDO> {

    default PageResult<CommercialStreetDO> selectPage(CommercialStreetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommercialStreetDO>()
                .eqIfPresent(CommercialStreetDO::getCommercialStreetId, reqVO.getCommercialStreetId())
                .likeIfPresent(CommercialStreetDO::getName, reqVO.getName())
                .eqIfPresent(CommercialStreetDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CommercialStreetDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(CommercialStreetDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(CommercialStreetDO::getPatrolInterval, reqVO.getPatrolInterval())
                .eqIfPresent(CommercialStreetDO::getCollectionPoints, reqVO.getCollectionPoints())
                .eqIfPresent(CommercialStreetDO::getTransferInterval, reqVO.getTransferInterval())
                .eqIfPresent(CommercialStreetDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(CommercialStreetDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(CommercialStreetDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(CommercialStreetDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(CommercialStreetDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(CommercialStreetDO::getCleaningCoverage, reqVO.getCleaningCoverage())
                .eqIfPresent(CommercialStreetDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(CommercialStreetDO::getDisposalDuration, reqVO.getDisposalDuration())
                .eqIfPresent(CommercialStreetDO::getProblemPhotoUrl, reqVO.getProblemPhotoUrl())
                .eqIfPresent(CommercialStreetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CommercialStreetDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CommercialStreetDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CommercialStreetDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CommercialStreetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommercialStreetDO::getId));
    }

    /**
     * 联表查询商业街详情列表
     * @return 详情列表
     */
    List<CommercialStreetDetailDO> selectListDetail();
}