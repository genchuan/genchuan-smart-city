package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.std.evalstditem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo.EvalStdItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstditem.EvalStdItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标准项管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalStdItemMapper extends BaseMapperX<EvalStdItemDO> {

    default PageResult<EvalStdItemDO> selectPage(EvalStdItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalStdItemDO>()
                .eqIfPresent(EvalStdItemDO::getStdItemId, reqVO.getStdItemId())
                .likeIfPresent(EvalStdItemDO::getStdItemName, reqVO.getStdItemName())
                .eqIfPresent(EvalStdItemDO::getStdItemCode, reqVO.getStdItemCode())
                .eqIfPresent(EvalStdItemDO::getStdCatId, reqVO.getStdCatId())
                .likeIfPresent(EvalStdItemDO::getStdCatName, reqVO.getStdCatName())
                .eqIfPresent(EvalStdItemDO::getStdLevel, reqVO.getStdLevel())
                .eqIfPresent(EvalStdItemDO::getScoreMin, reqVO.getScoreMin())
                .eqIfPresent(EvalStdItemDO::getScoreMax, reqVO.getScoreMax())
                .eqIfPresent(EvalStdItemDO::getStdDesc, reqVO.getStdDesc())
                .eqIfPresent(EvalStdItemDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(EvalStdItemDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalStdItemDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalStdItemDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalStdItemDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalStdItemDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalStdItemDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalStdItemDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalStdItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalStdItemDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalStdItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalStdItemDO::getId));
    }

}