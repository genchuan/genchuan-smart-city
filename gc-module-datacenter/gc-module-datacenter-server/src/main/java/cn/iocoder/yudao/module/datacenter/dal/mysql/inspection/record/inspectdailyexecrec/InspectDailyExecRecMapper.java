package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.record.inspectdailyexecrec;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo.InspectDailyExecRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.record.inspectdailyexecrec.InspectDailyExecRecDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日常巡查执行记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectDailyExecRecMapper extends BaseMapperX<InspectDailyExecRecDO> {

    default PageResult<InspectDailyExecRecDO> selectPage(InspectDailyExecRecPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectDailyExecRecDO>()
                .eqIfPresent(InspectDailyExecRecDO::getDailyExecId, reqVO.getDailyExecId())
                .eqIfPresent(InspectDailyExecRecDO::getDailyExecCode, reqVO.getDailyExecCode())
                .eqIfPresent(InspectDailyExecRecDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectDailyExecRecDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(InspectDailyExecRecDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectDailyExecRecDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectDailyExecRecDO::getStdId, reqVO.getStdId())
                .betweenIfPresent(InspectDailyExecRecDO::getExecStartTime, reqVO.getExecStartTime())
                .betweenIfPresent(InspectDailyExecRecDO::getExecCompleteTime, reqVO.getExecCompleteTime())
                .eqIfPresent(InspectDailyExecRecDO::getCkItemExec, reqVO.getCkItemExec())
                .eqIfPresent(InspectDailyExecRecDO::getExecScore, reqVO.getExecScore())
                .eqIfPresent(InspectDailyExecRecDO::getExecRemark, reqVO.getExecRemark())
                .eqIfPresent(InspectDailyExecRecDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(InspectDailyExecRecDO::getExecLocX, reqVO.getExecLocX())
                .eqIfPresent(InspectDailyExecRecDO::getExecLocY, reqVO.getExecLocY())
                .eqIfPresent(InspectDailyExecRecDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectDailyExecRecDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectDailyExecRecDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectDailyExecRecDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectDailyExecRecDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectDailyExecRecDO::getId));
    }

}