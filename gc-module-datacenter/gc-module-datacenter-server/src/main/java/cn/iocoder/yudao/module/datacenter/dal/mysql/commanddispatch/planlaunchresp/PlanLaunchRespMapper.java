package cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.planlaunchresp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.planlaunchresp.PlanLaunchRespDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预案启动响应 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PlanLaunchRespMapper extends BaseMapperX<PlanLaunchRespDO> {

    default PageResult<PlanLaunchRespDO> selectPage(PlanLaunchRespPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlanLaunchRespDO>()
                .eqIfPresent(PlanLaunchRespDO::getRespId, reqVO.getRespId())
                .eqIfPresent(PlanLaunchRespDO::getRespNo, reqVO.getRespNo())
                .eqIfPresent(PlanLaunchRespDO::getPlanId, reqVO.getPlanId())
                .likeIfPresent(PlanLaunchRespDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(PlanLaunchRespDO::getPlanVersion, reqVO.getPlanVersion())
                .eqIfPresent(PlanLaunchRespDO::getEvtCode, reqVO.getEvtCode())
                .likeIfPresent(PlanLaunchRespDO::getEvtName, reqVO.getEvtName())
                .eqIfPresent(PlanLaunchRespDO::getLaunchReason, reqVO.getLaunchReason())
                .eqIfPresent(PlanLaunchRespDO::getRespLevel, reqVO.getRespLevel())
                .eqIfPresent(PlanLaunchRespDO::getLaunchUserId, reqVO.getLaunchUserId())
                .likeIfPresent(PlanLaunchRespDO::getLaunchUserName, reqVO.getLaunchUserName())
                .betweenIfPresent(PlanLaunchRespDO::getLaunchTime, reqVO.getLaunchTime())
                .eqIfPresent(PlanLaunchRespDO::getRespStatus, reqVO.getRespStatus())
                .eqIfPresent(PlanLaunchRespDO::getTerminateReason, reqVO.getTerminateReason())
                .betweenIfPresent(PlanLaunchRespDO::getTerminateTime, reqVO.getTerminateTime())
                .eqIfPresent(PlanLaunchRespDO::getSyncUnitIds, reqVO.getSyncUnitIds())
                .eqIfPresent(PlanLaunchRespDO::getSyncUnitNames, reqVO.getSyncUnitNames())
                .eqIfPresent(PlanLaunchRespDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PlanLaunchRespDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PlanLaunchRespDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PlanLaunchRespDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(PlanLaunchRespDO::getId));
    }

}