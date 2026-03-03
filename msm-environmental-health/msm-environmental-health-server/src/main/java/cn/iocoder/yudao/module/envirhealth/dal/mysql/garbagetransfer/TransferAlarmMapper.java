package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferAlarmDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 转运站预警 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferAlarmMapper extends BaseMapperX<TransferAlarmDO> {

    default PageResult<TransferAlarmDO> selectPage(TransferAlarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferAlarmDO>()
                .eqIfPresent(TransferAlarmDO::getAlarmId, reqVO.getAlarmId())
                .eqIfPresent(TransferAlarmDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferAlarmDO::getAlarmTypeId, reqVO.getAlarmTypeId())
                .betweenIfPresent(TransferAlarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(TransferAlarmDO::getAlarmContent, reqVO.getAlarmContent())
                .eqIfPresent(TransferAlarmDO::getRelevantInfo, reqVO.getRelevantInfo())
                .eqIfPresent(TransferAlarmDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(TransferAlarmDO::getHandleBy, reqVO.getHandleBy())
                .eqIfPresent(TransferAlarmDO::getAbnormalIsTimeout, reqVO.getAbnormalIsTimeout())
                .eqIfPresent(TransferAlarmDO::getHandleProgress, reqVO.getHandleProgress())
                .eqIfPresent(TransferAlarmDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(TransferAlarmDO::getProofMaterial, reqVO.getProofMaterial())
                .eqIfPresent(TransferAlarmDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TransferAlarmDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TransferAlarmDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TransferAlarmDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TransferAlarmDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferAlarmDO::getId));
    }

    List<TransferAlarmDetailDO> selectDetailPage(@Param("reqVO") TransferAlarmPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferAlarmPageReqVO pageReqVO);
}