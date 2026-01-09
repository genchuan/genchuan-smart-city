package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkarrears;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkarrears.ParkArrearsDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 欠费记录 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkArrearsMapper extends BaseMapperX<ParkArrearsDO> {

    default PageResult<ParkArrearsDO> selectPage(ParkArrearsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkArrearsDO>()
                .eqIfPresent(ParkArrearsDO::getArrearsNo, reqVO.getArrearsNo())
                .eqIfPresent(ParkArrearsDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkArrearsDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkArrearsDO::getOrderIds, reqVO.getOrderIds())
                .eqIfPresent(ParkArrearsDO::getTotalArrearsAmount, reqVO.getTotalArrearsAmount())
                .eqIfPresent(ParkArrearsDO::getArrearsStatus, reqVO.getArrearsStatus())
                .eqIfPresent(ParkArrearsDO::getTraceWay, reqVO.getTraceWay())
                .betweenIfPresent(ParkArrearsDO::getLastTraceTime, reqVO.getLastTraceTime())
                .eqIfPresent(ParkArrearsDO::getTraceRecord, reqVO.getTraceRecord())
                .eqIfPresent(ParkArrearsDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkArrearsDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkArrearsDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkArrearsDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkArrearsDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ParkArrearsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkArrearsDO::getId));
    }

}
