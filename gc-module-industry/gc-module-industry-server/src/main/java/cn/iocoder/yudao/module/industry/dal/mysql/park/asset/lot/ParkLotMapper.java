package cn.iocoder.yudao.module.industry.dal.mysql.park.asset.lot;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo.ParkLotPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.lot.ParkLotDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车场信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkLotMapper extends BaseMapperX<ParkLotDO> {

    default PageResult<ParkLotDO> selectPage(ParkLotPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkLotDO>()
                .eqIfPresent(ParkLotDO::getAssetExtendId, reqVO.getAssetExtendId())
                .eqIfPresent(ParkLotDO::getTotalSpace, reqVO.getTotalSpace())
                .eqIfPresent(ParkLotDO::getAvailableSpace, reqVO.getAvailableSpace())
                .eqIfPresent(ParkLotDO::getParkType, reqVO.getParkType())
                .betweenIfPresent(ParkLotDO::getOpenTime, reqVO.getOpenTime())
                .betweenIfPresent(ParkLotDO::getCloseTime, reqVO.getCloseTime())
                .eqIfPresent(ParkLotDO::getManagementMerchantId, reqVO.getManagementMerchantId())
                .eqIfPresent(ParkLotDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                .betweenIfPresent(ParkLotDO::getLotCreateTime, reqVO.getLotCreateTime())
                .betweenIfPresent(ParkLotDO::getLotUpdateTime, reqVO.getLotUpdateTime())
                .eqIfPresent(ParkLotDO::getLotRemark, reqVO.getLotRemark())
                .betweenIfPresent(ParkLotDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkLotDO::getId));
    }

}