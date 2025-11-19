package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.std.evalstdcat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstdcat.EvalStdCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标准分类管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalStdCatMapper extends BaseMapperX<EvalStdCatDO> {

    default PageResult<EvalStdCatDO> selectPage(EvalStdCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalStdCatDO>()
                .eqIfPresent(EvalStdCatDO::getStdCatId, reqVO.getStdCatId())
                .likeIfPresent(EvalStdCatDO::getStdCatName, reqVO.getStdCatName())
                .eqIfPresent(EvalStdCatDO::getStdCatCode, reqVO.getStdCatCode())
                .eqIfPresent(EvalStdCatDO::getIdxSystemId, reqVO.getIdxSystemId())
                .likeIfPresent(EvalStdCatDO::getIdxSystemName, reqVO.getIdxSystemName())
                .eqIfPresent(EvalStdCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EvalStdCatDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalStdCatDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalStdCatDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalStdCatDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalStdCatDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalStdCatDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalStdCatDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalStdCatDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalStdCatDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalStdCatDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalStdCatDO::getId));
    }

}