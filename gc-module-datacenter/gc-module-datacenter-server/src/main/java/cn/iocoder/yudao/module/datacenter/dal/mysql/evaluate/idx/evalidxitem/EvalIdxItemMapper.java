package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxitem.EvalIdxItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 指标项管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalIdxItemMapper extends BaseMapperX<EvalIdxItemDO> {

    default PageResult<EvalIdxItemDO> selectPage(EvalIdxItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalIdxItemDO>()
                .eqIfPresent(EvalIdxItemDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalIdxItemDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalIdxItemDO::getIdxItemCode, reqVO.getIdxItemCode())
                .eqIfPresent(EvalIdxItemDO::getIdxCatId, reqVO.getIdxCatId())
                .likeIfPresent(EvalIdxItemDO::getIdxCatName, reqVO.getIdxCatName())
                .eqIfPresent(EvalIdxItemDO::getIdxType, reqVO.getIdxType())
                .eqIfPresent(EvalIdxItemDO::getCalcMethod, reqVO.getCalcMethod())
                .eqIfPresent(EvalIdxItemDO::getDataSourceTable, reqVO.getDataSourceTable())
                .eqIfPresent(EvalIdxItemDO::getDataSourceField, reqVO.getDataSourceField())
                .eqIfPresent(EvalIdxItemDO::getQualifiedThreshold, reqVO.getQualifiedThreshold())
                .eqIfPresent(EvalIdxItemDO::getIdxDesc, reqVO.getIdxDesc())
                .eqIfPresent(EvalIdxItemDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalIdxItemDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalIdxItemDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalIdxItemDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalIdxItemDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalIdxItemDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalIdxItemDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalIdxItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalIdxItemDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalIdxItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalIdxItemDO::getId));
    }

}