package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.evalgridattr;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.evalgridattr.EvalGridAttrDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价网格属性 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EvalGridAttrMapper extends BaseMapperX<EvalGridAttrDO> {

    default PageResult<EvalGridAttrDO> selectPage(EvalGridAttrPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalGridAttrDO>()
                .eqIfPresent(EvalGridAttrDO::getEvalAttrId, reqVO.getEvalAttrId())
                .eqIfPresent(EvalGridAttrDO::getEvalGridId, reqVO.getEvalGridId())
                .eqIfPresent(EvalGridAttrDO::getEvalGridCode, reqVO.getEvalGridCode())
                .eqIfPresent(EvalGridAttrDO::getArea, reqVO.getArea())
                .eqIfPresent(EvalGridAttrDO::getGridType, reqVO.getGridType())
                .betweenIfPresent(EvalGridAttrDO::getInitTime, reqVO.getInitTime())
                .betweenIfPresent(EvalGridAttrDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(EvalGridAttrDO::getMgGridList, reqVO.getMgGridList())
                .eqIfPresent(EvalGridAttrDO::getIdxId, reqVO.getIdxId())
                .likeIfPresent(EvalGridAttrDO::getIdxName, reqVO.getIdxName())
                .eqIfPresent(EvalGridAttrDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EvalGridAttrDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalGridAttrDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalGridAttrDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalGridAttrDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalGridAttrDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalGridAttrDO::getId));
    }

}