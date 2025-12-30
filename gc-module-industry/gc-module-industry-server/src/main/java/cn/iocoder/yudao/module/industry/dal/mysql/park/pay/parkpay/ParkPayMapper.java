package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkpay;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.ParkPayDrillReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.ParkPayPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkpay.ParkPayDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 停车缴费服务 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPayMapper extends BaseMapperX<ParkPayDO> {

    default PageResult<ParkPayDO> selectPage(ParkPayPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPayDO>()
                .eqIfPresent(ParkPayDO::getPayCode, reqVO.getPayCode())
                .eqIfPresent(ParkPayDO::getWoNo, reqVO.getWoNo())
                .eqIfPresent(ParkPayDO::getReservationId, reqVO.getReservationId())
                .eqIfPresent(ParkPayDO::getPayWoNo, reqVO.getPayWoNo())
                .eqIfPresent(ParkPayDO::getParkLotId, reqVO.getParkLotId())
                .likeIfPresent(ParkPayDO::getParkLotName, reqVO.getParkLotName())
                .eqIfPresent(ParkPayDO::getBerthId, reqVO.getBerthId())
                .eqIfPresent(ParkPayDO::getBerthNo, reqVO.getBerthNo())
                .eqIfPresent(ParkPayDO::getPlateNum, reqVO.getPlateNum())
                .betweenIfPresent(ParkPayDO::getEntryTime, reqVO.getEntryTime())
                .betweenIfPresent(ParkPayDO::getExitTime, reqVO.getExitTime())
                .eqIfPresent(ParkPayDO::getParkEndure, reqVO.getParkEndure())
                .betweenIfPresent(ParkPayDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(ParkPayDO::getReceivableAmount, reqVO.getReceivableAmount())
                .eqIfPresent(ParkPayDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(ParkPayDO::getActualPayAmount, reqVO.getActualPayAmount())
                .eqIfPresent(ParkPayDO::getPayMethod, reqVO.getPayMethod())
                .eqIfPresent(ParkPayDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(ParkPayDO::getReleaseStatus, reqVO.getReleaseStatus())
                .likeIfPresent(ParkPayDO::getActivityName, reqVO.getActivityName())
                .eqIfPresent(ParkPayDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ParkPayDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(ParkPayDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(ParkPayDO::getGridName, reqVO.getGridName())
                .eqIfPresent(ParkPayDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkPayDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkPayDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPayDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkPayDO::getId));
    }

//    PageResult<ParkPayDO> pageParkPay(ParkPayPageReqVO pageReqVO);

//    PageResult<ParkPayDO> selectDrillPage(ParkPayDrillReqVO drillReqVO);

    IPage<ParkPayDO> selectDrillPage(Page<ParkPayDO> page,
                                     @Param("reqVO") ParkPayDrillReqVO drillReqVO,
                                     @Param("regionFullCode") String regionFullCode);

}
