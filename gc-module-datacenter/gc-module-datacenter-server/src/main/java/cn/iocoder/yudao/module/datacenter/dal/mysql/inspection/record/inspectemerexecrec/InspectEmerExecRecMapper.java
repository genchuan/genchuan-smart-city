package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.record.inspectemerexecrec;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectemerexecrec.vo.InspectEmerExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectemerexecrec.InspectEmerExecRecDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应急巡查执行记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectEmerExecRecMapper extends BaseMapperX<InspectEmerExecRecDO> {

    default PageResult<InspectEmerExecRecDO> selectPage(InspectEmerExecRecPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectEmerExecRecDO>()
                .eqIfPresent(InspectEmerExecRecDO::getEmerExecId, reqVO.getEmerExecId())
                .eqIfPresent(InspectEmerExecRecDO::getEmerExecCode, reqVO.getEmerExecCode())
                .eqIfPresent(InspectEmerExecRecDO::getEmerPlanId, reqVO.getEmerPlanId())
                .likeIfPresent(InspectEmerExecRecDO::getEmerPlanName, reqVO.getEmerPlanName())
                .eqIfPresent(InspectEmerExecRecDO::getEmerEvtId, reqVO.getEmerEvtId())
                .likeIfPresent(InspectEmerExecRecDO::getEmerEvtName, reqVO.getEmerEvtName())
                .eqIfPresent(InspectEmerExecRecDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectEmerExecRecDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(InspectEmerExecRecDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectEmerExecRecDO::getPersonName, reqVO.getPersonName())
                .betweenIfPresent(InspectEmerExecRecDO::getExecStartTime, reqVO.getExecStartTime())
                .betweenIfPresent(InspectEmerExecRecDO::getExecCompleteTime, reqVO.getExecCompleteTime())
                .eqIfPresent(InspectEmerExecRecDO::getEmerCkDetail, reqVO.getEmerCkDetail())
                .eqIfPresent(InspectEmerExecRecDO::getUrgencyLevel, reqVO.getUrgencyLevel())
                .eqIfPresent(InspectEmerExecRecDO::getEmerProblemDesc, reqVO.getEmerProblemDesc())
                .eqIfPresent(InspectEmerExecRecDO::getDisposalSugg, reqVO.getDisposalSugg())
                .eqIfPresent(InspectEmerExecRecDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(InspectEmerExecRecDO::getEmerLocX, reqVO.getEmerLocX())
                .eqIfPresent(InspectEmerExecRecDO::getEmerLocY, reqVO.getEmerLocY())
                .eqIfPresent(InspectEmerExecRecDO::getIsPushedCmd, reqVO.getIsPushedCmd())
                .betweenIfPresent(InspectEmerExecRecDO::getPushTime, reqVO.getPushTime())
                .eqIfPresent(InspectEmerExecRecDO::getBizCreateUser, reqVO.getBizCreateUser())
                .betweenIfPresent(InspectEmerExecRecDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectEmerExecRecDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectEmerExecRecDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectEmerExecRecDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectEmerExecRecDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectEmerExecRecDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectEmerExecRecDO::getId));
    }

}