package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.result.evalresultsystemdock;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultsystemdock.EvalResultSystemDockDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 结果系统对接 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalResultSystemDockMapper extends BaseMapperX<EvalResultSystemDockDO> {

    default PageResult<EvalResultSystemDockDO> selectPage(EvalResultSystemDockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalResultSystemDockDO>()
                .eqIfPresent(EvalResultSystemDockDO::getResultDockId, reqVO.getResultDockId())
                .eqIfPresent(EvalResultSystemDockDO::getResultDockCode, reqVO.getResultDockCode())
                .eqIfPresent(EvalResultSystemDockDO::getResultArchId, reqVO.getResultArchId())
                .eqIfPresent(EvalResultSystemDockDO::getExternalSystemId, reqVO.getExternalSystemId())
                .likeIfPresent(EvalResultSystemDockDO::getExternalSystemName, reqVO.getExternalSystemName())
                .eqIfPresent(EvalResultSystemDockDO::getDockMethod, reqVO.getDockMethod())
                .eqIfPresent(EvalResultSystemDockDO::getDockFrequency, reqVO.getDockFrequency())
                .eqIfPresent(EvalResultSystemDockDO::getMapRuleId, reqVO.getMapRuleId())
                .eqIfPresent(EvalResultSystemDockDO::getDockStatus, reqVO.getDockStatus())
                .betweenIfPresent(EvalResultSystemDockDO::getDockTime, reqVO.getDockTime())
                .eqIfPresent(EvalResultSystemDockDO::getDockData, reqVO.getDockData())
                .eqIfPresent(EvalResultSystemDockDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(EvalResultSystemDockDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalResultSystemDockDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalResultSystemDockDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalResultSystemDockDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalResultSystemDockDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalResultSystemDockDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalResultSystemDockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalResultSystemDockDO::getId));
    }

}