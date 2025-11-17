package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.record.inspectspecexecrec;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo.InspectSpecExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectspecexecrec.InspectSpecExecRecDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专项巡查执行记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectSpecExecRecMapper extends BaseMapperX<InspectSpecExecRecDO> {

    default PageResult<InspectSpecExecRecDO> selectPage(InspectSpecExecRecPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectSpecExecRecDO>()
                .eqIfPresent(InspectSpecExecRecDO::getSpecExecId, reqVO.getSpecExecId())
                .eqIfPresent(InspectSpecExecRecDO::getSpecExecCode, reqVO.getSpecExecCode())
                .eqIfPresent(InspectSpecExecRecDO::getSpecPlanId, reqVO.getSpecPlanId())
                .likeIfPresent(InspectSpecExecRecDO::getSpecPlanName, reqVO.getSpecPlanName())
                .eqIfPresent(InspectSpecExecRecDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectSpecExecRecDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(InspectSpecExecRecDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectSpecExecRecDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectSpecExecRecDO::getStdId, reqVO.getStdId())
                .likeIfPresent(InspectSpecExecRecDO::getStdName, reqVO.getStdName())
                .betweenIfPresent(InspectSpecExecRecDO::getExecStartTime, reqVO.getExecStartTime())
                .betweenIfPresent(InspectSpecExecRecDO::getExecCompleteTime, reqVO.getExecCompleteTime())
                .eqIfPresent(InspectSpecExecRecDO::getCkItemDetail, reqVO.getCkItemDetail())
                .eqIfPresent(InspectSpecExecRecDO::getUnqualifiedCount, reqVO.getUnqualifiedCount())
                .eqIfPresent(InspectSpecExecRecDO::getUnqualifiedDetail, reqVO.getUnqualifiedDetail())
                .eqIfPresent(InspectSpecExecRecDO::getExecScore, reqVO.getExecScore())
                .eqIfPresent(InspectSpecExecRecDO::getSpecExecRemark, reqVO.getSpecExecRemark())
                .eqIfPresent(InspectSpecExecRecDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(InspectSpecExecRecDO::getExecLocX, reqVO.getExecLocX())
                .eqIfPresent(InspectSpecExecRecDO::getExecLocY, reqVO.getExecLocY())
                .eqIfPresent(InspectSpecExecRecDO::getBizCreateUser, reqVO.getBizCreateUser())
                .betweenIfPresent(InspectSpecExecRecDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectSpecExecRecDO::getBizUpdateUser, reqVO.getBizUpdateUser())
                .betweenIfPresent(InspectSpecExecRecDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectSpecExecRecDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectSpecExecRecDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectSpecExecRecDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectSpecExecRecDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectSpecExecRecDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectSpecExecRecDO::getId));
    }

}