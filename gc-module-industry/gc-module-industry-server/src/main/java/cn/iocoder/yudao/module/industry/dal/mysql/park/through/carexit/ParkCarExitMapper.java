package cn.iocoder.yudao.module.industry.dal.mysql.park.through.carexit;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo.ParkCarExitPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carexit.ParkCarExitDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 离场记录 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkCarExitMapper extends BaseMapperX<ParkCarExitDO> {

    default PageResult<ParkCarExitDO> selectPage(ParkCarExitPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkCarExitDO>()
                .eqIfPresent(ParkCarExitDO::getExitId, reqVO.getExitId())
                .eqIfPresent(ParkCarExitDO::getEntryId, reqVO.getEntryId())
                .eqIfPresent(ParkCarExitDO::getCarNumber, reqVO.getCarNumber())
                .betweenIfPresent(ParkCarExitDO::getExitTime, reqVO.getExitTime())
                .eqIfPresent(ParkCarExitDO::getExitExitId, reqVO.getExitExitId())
                .eqIfPresent(ParkCarExitDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkCarExitDO::getParkingDuration, reqVO.getParkingDuration())
                .eqIfPresent(ParkCarExitDO::getFeeAmount, reqVO.getFeeAmount())
                .eqIfPresent(ParkCarExitDO::getActualPayAmount, reqVO.getActualPayAmount())
                .eqIfPresent(ParkCarExitDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(ParkCarExitDO::getPaymentId, reqVO.getPaymentId())
                .eqIfPresent(ParkCarExitDO::getExitType, reqVO.getExitType())
                .eqIfPresent(ParkCarExitDO::getAbnormalReason, reqVO.getAbnormalReason())
                .eqIfPresent(ParkCarExitDO::getDeviceCode, reqVO.getDeviceCode())
                .betweenIfPresent(ParkCarExitDO::getExitCreateTime, reqVO.getExitCreateTime())
                .betweenIfPresent(ParkCarExitDO::getExitUpdateTime, reqVO.getExitUpdateTime())
                .eqIfPresent(ParkCarExitDO::getExitRemark, reqVO.getExitRemark())
                .betweenIfPresent(ParkCarExitDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkCarExitDO::getId));
    }

}