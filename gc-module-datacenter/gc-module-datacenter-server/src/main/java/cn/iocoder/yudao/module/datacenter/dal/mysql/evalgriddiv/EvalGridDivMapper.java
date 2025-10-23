package cn.iocoder.yudao.module.datacenter.dal.mysql.evalgriddiv;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evalgriddiv.EvalGridDivDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.evalgriddiv.vo.*;

/**
 * 评价网格划分 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalGridDivMapper extends BaseMapperX<EvalGridDivDO> {

    default PageResult<EvalGridDivDO> selectPage(EvalGridDivPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalGridDivDO>()
                .eqIfPresent(EvalGridDivDO::getEvalGridId, reqVO.getEvalGridId())
                .likeIfPresent(EvalGridDivDO::getEvalGridName, reqVO.getEvalGridName())
                .eqIfPresent(EvalGridDivDO::getTownStreetId, reqVO.getTownStreetId())
                .eqIfPresent(EvalGridDivDO::getGridType, reqVO.getGridType())
                .eqIfPresent(EvalGridDivDO::getIncludedMgIds, reqVO.getIncludedMgIds())
                .eqIfPresent(EvalGridDivDO::getArea, reqVO.getArea())
                .betweenIfPresent(EvalGridDivDO::getDivTime, reqVO.getDivTime())
                .betweenIfPresent(EvalGridDivDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(EvalGridDivDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EvalGridDivDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalGridDivDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalGridDivDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalGridDivDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalGridDivDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EvalGridDivDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(EvalGridDivDO::getId));
    }

}