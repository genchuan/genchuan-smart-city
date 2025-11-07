package cn.iocoder.yudao.module.datacenter.dal.mysql.grid.code.evalgridcode;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.code.evalgridcode.vo.EvalGridCodePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.code.evalgridcode.EvalGridCodeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价网格编码 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalGridCodeMapper extends BaseMapperX<EvalGridCodeDO> {

    default PageResult<EvalGridCodeDO> selectPage(EvalGridCodePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalGridCodeDO>()
                .eqIfPresent(EvalGridCodeDO::getEvalCodeId, reqVO.getEvalCodeId())
                .eqIfPresent(EvalGridCodeDO::getEvalGridCode, reqVO.getEvalGridCode())
                .eqIfPresent(EvalGridCodeDO::getEvalGridId, reqVO.getEvalGridId())
                .eqIfPresent(EvalGridCodeDO::getAreaFullCode, reqVO.getAreaFullCode())
                .eqIfPresent(EvalGridCodeDO::getSeqCode, reqVO.getSeqCode())
                .eqIfPresent(EvalGridCodeDO::getTypeCode, reqVO.getTypeCode())
                .betweenIfPresent(EvalGridCodeDO::getGenerateTime, reqVO.getGenerateTime())
                .eqIfPresent(EvalGridCodeDO::getCodeStatus, reqVO.getCodeStatus())
                .eqIfPresent(EvalGridCodeDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EvalGridCodeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalGridCodeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalGridCodeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalGridCodeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalGridCodeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalGridCodeDO::getId));
    }

}