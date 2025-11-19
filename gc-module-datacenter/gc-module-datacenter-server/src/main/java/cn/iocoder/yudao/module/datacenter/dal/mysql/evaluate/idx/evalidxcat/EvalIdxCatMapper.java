package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxcat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxcat.EvalIdxCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标分类管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalIdxCatMapper extends BaseMapperX<EvalIdxCatDO> {

    default PageResult<EvalIdxCatDO> selectPage(EvalIdxCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalIdxCatDO>()
                .eqIfPresent(EvalIdxCatDO::getIdxCatId, reqVO.getIdxCatId())
                .likeIfPresent(EvalIdxCatDO::getIdxCatName, reqVO.getIdxCatName())
                .eqIfPresent(EvalIdxCatDO::getCatCode, reqVO.getCatCode())
                .eqIfPresent(EvalIdxCatDO::getIdxSystemId, reqVO.getIdxSystemId())
                .likeIfPresent(EvalIdxCatDO::getIdxSystemName, reqVO.getIdxSystemName())
                .eqIfPresent(EvalIdxCatDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(EvalIdxCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EvalIdxCatDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalIdxCatDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalIdxCatDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalIdxCatDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalIdxCatDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalIdxCatDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalIdxCatDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalIdxCatDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalIdxCatDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalIdxCatDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalIdxCatDO::getId));
    }

}