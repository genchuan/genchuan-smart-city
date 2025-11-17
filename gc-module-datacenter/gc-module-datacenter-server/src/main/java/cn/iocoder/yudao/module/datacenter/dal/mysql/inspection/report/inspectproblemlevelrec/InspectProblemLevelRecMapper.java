package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.report.inspectproblemlevelrec;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemlevelrec.InspectProblemLevelRecDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检问题分级记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectProblemLevelRecMapper extends BaseMapperX<InspectProblemLevelRecDO> {

    default PageResult<InspectProblemLevelRecDO> selectPage(InspectProblemLevelRecPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectProblemLevelRecDO>()
                .eqIfPresent(InspectProblemLevelRecDO::getProblemLevelId, reqVO.getProblemLevelId())
                .eqIfPresent(InspectProblemLevelRecDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InspectProblemLevelRecDO::getProblemCode, reqVO.getProblemCode())
                .likeIfPresent(InspectProblemLevelRecDO::getProblemName, reqVO.getProblemName())
                .eqIfPresent(InspectProblemLevelRecDO::getProblemLevel, reqVO.getProblemLevel())
                .eqIfPresent(InspectProblemLevelRecDO::getLevelDesc, reqVO.getLevelDesc())
                .eqIfPresent(InspectProblemLevelRecDO::getHandleDeadline, reqVO.getHandleDeadline())
                .eqIfPresent(InspectProblemLevelRecDO::getLevelBasis, reqVO.getLevelBasis())
                .eqIfPresent(InspectProblemLevelRecDO::getLevelUser, reqVO.getLevelUser())
                .betweenIfPresent(InspectProblemLevelRecDO::getLevelTime, reqVO.getLevelTime())
                .eqIfPresent(InspectProblemLevelRecDO::getIsPushRemind, reqVO.getIsPushRemind())
                .betweenIfPresent(InspectProblemLevelRecDO::getPushRemindTime, reqVO.getPushRemindTime())
                .eqIfPresent(InspectProblemLevelRecDO::getProblemStatusChange, reqVO.getProblemStatusChange())
                .eqIfPresent(InspectProblemLevelRecDO::getBizCreateUser, reqVO.getBizCreateUser())
                .betweenIfPresent(InspectProblemLevelRecDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectProblemLevelRecDO::getBizUpdateUser, reqVO.getBizUpdateUser())
                .betweenIfPresent(InspectProblemLevelRecDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectProblemLevelRecDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectProblemLevelRecDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectProblemLevelRecDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectProblemLevelRecDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectProblemLevelRecDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectProblemLevelRecDO::getId));
    }

}