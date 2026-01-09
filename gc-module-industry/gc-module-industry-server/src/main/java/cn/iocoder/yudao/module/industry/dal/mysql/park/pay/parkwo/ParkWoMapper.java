package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkwo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkwo.ParkWoDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 停车订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkWoMapper extends BaseMapperX<ParkWoDO> {

    default PageResult<ParkWoDO> selectPage(ParkWoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkWoDO>()
//                .eqIfPresent(ParkWoDO::getWoId, reqVO.getWoId())
                .eqIfPresent(ParkWoDO::getWoNo, reqVO.getWoNo())
                .likeIfPresent(ParkWoDO::getParkName, reqVO.getParkName())
                .eqIfPresent(ParkWoDO::getParkEndure, reqVO.getParkEndure())
                .eqIfPresent(ParkWoDO::getReceivableAmount, reqVO.getReceivableAmount())
                .likeIfPresent(ParkWoDO::getArrearsReason, reqVO.getArrearsReason())
                .betweenIfPresent(ParkWoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkWoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkWoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkWoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkWoDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkWoDO::getId));
    }

}
