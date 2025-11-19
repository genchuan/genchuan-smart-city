package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.evalobject;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalobject.EvalObjectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价对象管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalObjectMapper extends BaseMapperX<EvalObjectDO> {

    default PageResult<EvalObjectDO> selectPage(EvalObjectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalObjectDO>()
                .eqIfPresent(EvalObjectDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalObjectDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalObjectDO::getObjectCode, reqVO.getObjectCode())
                .eqIfPresent(EvalObjectDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EvalObjectDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EvalObjectDO::getGridId, reqVO.getGridId())
                .likeIfPresent(EvalObjectDO::getGridName, reqVO.getGridName())
                .eqIfPresent(EvalObjectDO::getDeptId, reqVO.getDeptId())
                .likeIfPresent(EvalObjectDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(EvalObjectDO::getLeaderId, reqVO.getLeaderId())
                .likeIfPresent(EvalObjectDO::getLeaderName, reqVO.getLeaderName())
                .eqIfPresent(EvalObjectDO::getLeaderContact, reqVO.getLeaderContact())
                .eqIfPresent(EvalObjectDO::getObjectStatus, reqVO.getObjectStatus())
                .eqIfPresent(EvalObjectDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalObjectDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalObjectDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalObjectDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalObjectDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalObjectDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalObjectDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalObjectDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalObjectDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalObjectDO::getId));
    }

}