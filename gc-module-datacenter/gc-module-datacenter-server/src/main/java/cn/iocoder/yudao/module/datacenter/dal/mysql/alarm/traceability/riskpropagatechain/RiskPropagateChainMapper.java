package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.traceability.riskpropagatechain;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.riskpropagatechain.RiskPropagateChainDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 风险传播链 Mapper
 *
 * @author zcq
 */
@Mapper
public interface RiskPropagateChainMapper extends BaseMapperX<RiskPropagateChainDO> {

    default PageResult<RiskPropagateChainDO> selectPage(RiskPropagateChainPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RiskPropagateChainDO>()
                .eqIfPresent(RiskPropagateChainDO::getPropagateChainId, reqVO.getPropagateChainId())
                .eqIfPresent(RiskPropagateChainDO::getPropagateChainNo, reqVO.getPropagateChainNo())
                .eqIfPresent(RiskPropagateChainDO::getSourceWarnId, reqVO.getSourceWarnId())
                .likeIfPresent(RiskPropagateChainDO::getSourceWarnName, reqVO.getSourceWarnName())
                .eqIfPresent(RiskPropagateChainDO::getNodeSeq, reqVO.getNodeSeq())
                .eqIfPresent(RiskPropagateChainDO::getNodeContent, reqVO.getNodeContent())
                .eqIfPresent(RiskPropagateChainDO::getNodeType, reqVO.getNodeType())
                .betweenIfPresent(RiskPropagateChainDO::getOccurTime, reqVO.getOccurTime())
                .eqIfPresent(RiskPropagateChainDO::getImpactRange, reqVO.getImpactRange())
                .eqIfPresent(RiskPropagateChainDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(RiskPropagateChainDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(RiskPropagateChainDO::getRecUserId, reqVO.getRecUserId())
                .likeIfPresent(RiskPropagateChainDO::getRecUserName, reqVO.getRecUserName())
                .eqIfPresent(RiskPropagateChainDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(RiskPropagateChainDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(RiskPropagateChainDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RiskPropagateChainDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(RiskPropagateChainDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RiskPropagateChainDO::getId));
    }

}