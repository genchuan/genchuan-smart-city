package cn.iocoder.yudao.module.waterdetection.dal.mysql.invaliddata;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.invaliddata.InvalidDataDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.invaliddata.vo.*;

/**
 * 不合格数据处理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InvalidDataMapper extends BaseMapperX<InvalidDataDO> {

    default PageResult<InvalidDataDO> selectPage(InvalidDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InvalidDataDO>()
                .eqIfPresent(InvalidDataDO::getDataId, reqVO.getDataId())
                .eqIfPresent(InvalidDataDO::getInstrumentId, reqVO.getInstrumentId())
                .eqIfPresent(InvalidDataDO::getMonitorValue, reqVO.getMonitorValue())
                .betweenIfPresent(InvalidDataDO::getCollectionTime, reqVO.getCollectionTime())
                .eqIfPresent(InvalidDataDO::getDataStatus, reqVO.getDataStatus())
                .eqIfPresent(InvalidDataDO::getInvalidReason, reqVO.getInvalidReason())
                .eqIfPresent(InvalidDataDO::getIsExcluded, reqVO.getIsExcluded())
                .eqIfPresent(InvalidDataDO::getProcessorId, reqVO.getProcessorId())
                .betweenIfPresent(InvalidDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InvalidDataDO::getId));
    }

}