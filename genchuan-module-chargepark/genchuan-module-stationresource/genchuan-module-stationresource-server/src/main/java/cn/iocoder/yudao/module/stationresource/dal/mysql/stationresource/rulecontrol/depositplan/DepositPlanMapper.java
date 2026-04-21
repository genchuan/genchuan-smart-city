package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.depositplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.DepositPlanPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.depositplan.vo.ops.DepositPlanChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.depositplan.DepositPlanDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 押金方案 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DepositPlanMapper extends BaseMapperX<DepositPlanDO> {

    default PageResult<DepositPlanDO> selectPage(DepositPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DepositPlanDO>()
                .eqIfPresent(DepositPlanDO::getStationId, reqVO.getStationId())
                .eqIfPresent(DepositPlanDO::getDepositAmount, reqVO.getDepositAmount())
                .eqIfPresent(DepositPlanDO::getScene, reqVO.getScene())
                .eqIfPresent(DepositPlanDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DepositPlanDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(DepositPlanDO::getAuditUserId, reqVO.getAuditUserId())
                .eqIfPresent(DepositPlanDO::getDepositOrderCount, reqVO.getDepositOrderCount())
                .eqIfPresent(DepositPlanDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DepositPlanDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(DepositPlanDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(DepositPlanDO::getCreator, reqVO.getCreator())
                .eqIfPresent(DepositPlanDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(DepositPlanDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(DepositPlanDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(DepositPlanDO::getId));
    }

    DepositPlanChartRespVO.CardDataVO selectCardData();

    List<DepositPlanChartRespVO.SceneBarVO> selectSceneBarList();
}
