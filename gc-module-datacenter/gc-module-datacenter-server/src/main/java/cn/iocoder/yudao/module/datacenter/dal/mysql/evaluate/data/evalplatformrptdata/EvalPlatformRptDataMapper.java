package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.data.evalplatformrptdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalplatformrptdata.EvalPlatformRptDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台上报数据 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalPlatformRptDataMapper extends BaseMapperX<EvalPlatformRptDataDO> {

    default PageResult<EvalPlatformRptDataDO> selectPage(EvalPlatformRptDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalPlatformRptDataDO>()
                .eqIfPresent(EvalPlatformRptDataDO::getPlatformRptDataId, reqVO.getPlatformRptDataId())
                .eqIfPresent(EvalPlatformRptDataDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalPlatformRptDataDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalPlatformRptDataDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalPlatformRptDataDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalPlatformRptDataDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalPlatformRptDataDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalPlatformRptDataDO::getDataValue, reqVO.getDataValue())
                .eqIfPresent(EvalPlatformRptDataDO::getDataUnit, reqVO.getDataUnit())
                .betweenIfPresent(EvalPlatformRptDataDO::getRptTime, reqVO.getRptTime())
                .eqIfPresent(EvalPlatformRptDataDO::getRptUserId, reqVO.getRptUserId())
                .likeIfPresent(EvalPlatformRptDataDO::getRptUserName, reqVO.getRptUserName())
                .eqIfPresent(EvalPlatformRptDataDO::getDataStatus, reqVO.getDataStatus())
                .eqIfPresent(EvalPlatformRptDataDO::getVerifyFailReason, reqVO.getVerifyFailReason())
                .eqIfPresent(EvalPlatformRptDataDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalPlatformRptDataDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalPlatformRptDataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalPlatformRptDataDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalPlatformRptDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalPlatformRptDataDO::getId));
    }

}