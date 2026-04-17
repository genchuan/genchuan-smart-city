package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充停地图 Mapper
 *
 * @author carservice
 */
@Mapper
public interface ChargeParkMapMapper extends BaseMapperX<ChargeParkMapDO> {

    default PageResult<ChargeParkMapDO> selectPage(ChargeParkMapPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChargeParkMapDO>()
                .eqIfPresent(ChargeParkMapDO::getUserId, reqVO.getUserId())
                .likeIfPresent(ChargeParkMapDO::getQueryLocation, reqVO.getQueryLocation())
                .betweenIfPresent(ChargeParkMapDO::getQueryTime, reqVO.getQueryTime())
                .orderByDesc(ChargeParkMapDO::getId));
    }

}
