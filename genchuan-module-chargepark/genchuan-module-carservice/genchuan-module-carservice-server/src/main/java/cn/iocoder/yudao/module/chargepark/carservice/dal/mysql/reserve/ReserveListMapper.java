package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.reserve;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve.ReserveListDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预约列表 Mapper
 *
 * @author carservice
 */
@Mapper
public interface ReserveListMapper extends BaseMapperX<ReserveListDO> {

    default PageResult<ReserveListDO> selectPage(ReserveListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ReserveListDO>()
                .eqIfPresent(ReserveListDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ReserveListDO::getStationId, reqVO.getStationId())
                .eqIfPresent(ReserveListDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(ReserveListDO::getReserveType, reqVO.getReserveType())
                .eqIfPresent(ReserveListDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ReserveListDO::getReserveTime, reqVO.getReserveTime())
                .orderByDesc(ReserveListDO::getId));
    }

}
