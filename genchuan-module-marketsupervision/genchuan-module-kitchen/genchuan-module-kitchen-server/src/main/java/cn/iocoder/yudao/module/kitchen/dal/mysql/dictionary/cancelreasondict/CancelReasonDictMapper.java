package cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.cancelreasondict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 撤销原因字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CancelReasonDictMapper extends BaseMapperX<CancelReasonDictDO> {

    default PageResult<CancelReasonDictDO> selectPage(CancelReasonDictPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CancelReasonDictDO>()
                .eqIfPresent(CancelReasonDictDO::getReasonCode, reqVO.getReasonCode())
                .likeIfPresent(CancelReasonDictDO::getReasonName, reqVO.getReasonName())
                .eqIfPresent(CancelReasonDictDO::getSort, reqVO.getSort())
                .betweenIfPresent(CancelReasonDictDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CancelReasonDictDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CancelReasonDictDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CancelReasonDictDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CancelReasonDictDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(CancelReasonDictDO::getId));
    }

}
