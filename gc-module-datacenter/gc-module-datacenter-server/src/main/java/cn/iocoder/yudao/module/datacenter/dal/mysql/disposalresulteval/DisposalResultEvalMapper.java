package cn.iocoder.yudao.module.datacenter.dal.mysql.disposalresulteval;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.disposalresulteval.DisposalResultEvalDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 处置结果评估 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DisposalResultEvalMapper extends BaseMapperX<DisposalResultEvalDO> {

    default PageResult<DisposalResultEvalDO> selectPage(DisposalResultEvalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DisposalResultEvalDO>()
                .eqIfPresent(DisposalResultEvalDO::getEvalId, reqVO.getEvalId())
                .eqIfPresent(DisposalResultEvalDO::getTraceId, reqVO.getTraceId())
                .eqIfPresent(DisposalResultEvalDO::getEvtCode, reqVO.getEvtCode())
                .likeIfPresent(DisposalResultEvalDO::getEvtName, reqVO.getEvtName())
                .eqIfPresent(DisposalResultEvalDO::getEfficacyScore, reqVO.getEfficacyScore())
                .eqIfPresent(DisposalResultEvalDO::getEfficacyDesc, reqVO.getEfficacyDesc())
                .eqIfPresent(DisposalResultEvalDO::getResScore, reqVO.getResScore())
                .eqIfPresent(DisposalResultEvalDO::getResDesc, reqVO.getResDesc())
                .eqIfPresent(DisposalResultEvalDO::getEffectScore, reqVO.getEffectScore())
                .eqIfPresent(DisposalResultEvalDO::getEffectDesc, reqVO.getEffectDesc())
                .eqIfPresent(DisposalResultEvalDO::getComprehensiveScore, reqVO.getComprehensiveScore())
                .eqIfPresent(DisposalResultEvalDO::getImproveSugg, reqVO.getImproveSugg())
                .eqIfPresent(DisposalResultEvalDO::getEvalUserId, reqVO.getEvalUserId())
                .likeIfPresent(DisposalResultEvalDO::getEvalUserName, reqVO.getEvalUserName())
                .betweenIfPresent(DisposalResultEvalDO::getEvalTime, reqVO.getEvalTime())
                .eqIfPresent(DisposalResultEvalDO::getRptPath, reqVO.getRptPath())
                .eqIfPresent(DisposalResultEvalDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DisposalResultEvalDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DisposalResultEvalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DisposalResultEvalDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DisposalResultEvalDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DisposalResultEvalDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(DisposalResultEvalDO::getId));
    }

}