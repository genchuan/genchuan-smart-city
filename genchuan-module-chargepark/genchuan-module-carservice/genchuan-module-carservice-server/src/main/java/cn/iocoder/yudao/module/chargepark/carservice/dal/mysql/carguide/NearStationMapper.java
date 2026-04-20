package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 周边场站 Mapper
 *
 * @author carservice
 */
@Mapper
public interface NearStationMapper extends BaseMapperX<NearStationDO> {

    default PageResult<NearStationDO> selectPage(NearStationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NearStationDO>()
                .eqIfPresent(NearStationDO::getUserId, reqVO.getUserId())
                .likeIfPresent(NearStationDO::getQueryLocation, reqVO.getQueryLocation())
                .betweenIfPresent(NearStationDO::getQueryTime, reqVO.getQueryTime())
                .orderByDesc(NearStationDO::getId));
    }

}
