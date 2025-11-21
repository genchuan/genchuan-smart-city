package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.risktracepath;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risktracepath.vo.RiskTracePathPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risktracepath.RiskTracePathDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 风险溯源路径 Mapper
 *
 * @author zcq
 */
@Mapper
public interface RiskTracePathMapper extends BaseMapperX<RiskTracePathDO> {

    default PageResult<RiskTracePathDO> selectPage(RiskTracePathPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RiskTracePathDO>()
                .eqIfPresent(RiskTracePathDO::getTracePathId, reqVO.getTracePathId())
                .eqIfPresent(RiskTracePathDO::getEarlyWarnId, reqVO.getEarlyWarnId())
                .likeIfPresent(RiskTracePathDO::getEarlyWarnName, reqVO.getEarlyWarnName())
                .betweenIfPresent(RiskTracePathDO::getTraceTime, reqVO.getTraceTime())
                .eqIfPresent(RiskTracePathDO::getNode1Content, reqVO.getNode1Content())
                .eqIfPresent(RiskTracePathDO::getNode2Content, reqVO.getNode2Content())
                .eqIfPresent(RiskTracePathDO::getNode3Content, reqVO.getNode3Content())
                .eqIfPresent(RiskTracePathDO::getTraceConclusion, reqVO.getTraceConclusion())
                .eqIfPresent(RiskTracePathDO::getAnalystUserId, reqVO.getAnalystUserId())
                .likeIfPresent(RiskTracePathDO::getAnalystUserName, reqVO.getAnalystUserName())
                .eqIfPresent(RiskTracePathDO::getIsAutoAnalysis, reqVO.getIsAutoAnalysis())
                .eqIfPresent(RiskTracePathDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(RiskTracePathDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(RiskTracePathDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RiskTracePathDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(RiskTracePathDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RiskTracePathDO::getId));
    }

}