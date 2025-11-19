package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxweight;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxweight.EvalIdxWeightDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标权重管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalIdxWeightMapper extends BaseMapperX<EvalIdxWeightDO> {

    default PageResult<EvalIdxWeightDO> selectPage(EvalIdxWeightPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalIdxWeightDO>()
                .eqIfPresent(EvalIdxWeightDO::getIdxWeightId, reqVO.getIdxWeightId())
                .eqIfPresent(EvalIdxWeightDO::getIdxSystemId, reqVO.getIdxSystemId())
                .likeIfPresent(EvalIdxWeightDO::getIdxSystemName, reqVO.getIdxSystemName())
                .eqIfPresent(EvalIdxWeightDO::getWeightType, reqVO.getWeightType())
                .eqIfPresent(EvalIdxWeightDO::getRelateId, reqVO.getRelateId())
                .likeIfPresent(EvalIdxWeightDO::getRelateName, reqVO.getRelateName())
                .eqIfPresent(EvalIdxWeightDO::getWeightValue, reqVO.getWeightValue())
                .eqIfPresent(EvalIdxWeightDO::getWeightDesc, reqVO.getWeightDesc())
                .eqIfPresent(EvalIdxWeightDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalIdxWeightDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalIdxWeightDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalIdxWeightDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalIdxWeightDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalIdxWeightDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalIdxWeightDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalIdxWeightDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalIdxWeightDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalIdxWeightDO::getId));
    }

}