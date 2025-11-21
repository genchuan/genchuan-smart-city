package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.data.evalrealtimedata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalrealtimedata.EvalRealTimeDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 实时监测数据接入 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalRealTimeDataMapper extends BaseMapperX<EvalRealTimeDataDO> {

    default PageResult<EvalRealTimeDataDO> selectPage(EvalRealTimeDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalRealTimeDataDO>()
                .eqIfPresent(EvalRealTimeDataDO::getRealTimeDataId, reqVO.getRealTimeDataId())
                .eqIfPresent(EvalRealTimeDataDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalRealTimeDataDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalRealTimeDataDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalRealTimeDataDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalRealTimeDataDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(EvalRealTimeDataDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(EvalRealTimeDataDO::getDataValue, reqVO.getDataValue())
                .betweenIfPresent(EvalRealTimeDataDO::getDataTime, reqVO.getDataTime())
                .eqIfPresent(EvalRealTimeDataDO::getAccessStatus, reqVO.getAccessStatus())
                .eqIfPresent(EvalRealTimeDataDO::getFailReason, reqVO.getFailReason())
                .eqIfPresent(EvalRealTimeDataDO::getSyncFrequency, reqVO.getSyncFrequency())
                .eqIfPresent(EvalRealTimeDataDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalRealTimeDataDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalRealTimeDataDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalRealTimeDataDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalRealTimeDataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalRealTimeDataDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalRealTimeDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalRealTimeDataDO::getId));
    }

}