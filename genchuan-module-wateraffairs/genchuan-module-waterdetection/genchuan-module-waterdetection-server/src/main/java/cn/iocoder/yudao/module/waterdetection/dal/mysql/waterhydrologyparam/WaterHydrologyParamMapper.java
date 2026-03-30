package cn.iocoder.yudao.module.waterdetection.dal.mysql.waterhydrologyparam;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterhydrologyparam.WaterHydrologyParamDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo.*;

/**
 * 水源水文参数管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WaterHydrologyParamMapper extends BaseMapperX<WaterHydrologyParamDO> {

    default PageResult<WaterHydrologyParamDO> selectPage(WaterHydrologyParamPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WaterHydrologyParamDO>()
                .betweenIfPresent(WaterHydrologyParamDO::getMonitorTime, reqVO.getMonitorTime())
                .eqIfPresent(WaterHydrologyParamDO::getWaterLevel, reqVO.getWaterLevel())
                .eqIfPresent(WaterHydrologyParamDO::getAquiferThickness, reqVO.getAquiferThickness())
                .eqIfPresent(WaterHydrologyParamDO::getPermeabilityCoefficient, reqVO.getPermeabilityCoefficient())
                .eqIfPresent(WaterHydrologyParamDO::getDataCollector, reqVO.getDataCollector())
                .betweenIfPresent(WaterHydrologyParamDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WaterHydrologyParamDO::getId));
    }

}