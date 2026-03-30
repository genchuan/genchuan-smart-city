package cn.iocoder.yudao.module.waterdetection.dal.mysql.dmapartition;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.dmapartition.DmaPartitionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo.*;

/**
 * DMA分区划分与调整 Mapper
 *
 * @author zcq
 */
@Mapper
public interface DmaPartitionMapper extends BaseMapperX<DmaPartitionDO> {

    default PageResult<DmaPartitionDO> selectPage(DmaPartitionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DmaPartitionDO>()
                .eqIfPresent(DmaPartitionDO::getPartitionId, reqVO.getPartitionId())
                .likeIfPresent(DmaPartitionDO::getPartitionName, reqVO.getPartitionName())
                .eqIfPresent(DmaPartitionDO::getCoveredVillages, reqVO.getCoveredVillages())
                .eqIfPresent(DmaPartitionDO::getBoundaryCoordinates, reqVO.getBoundaryCoordinates())
                .eqIfPresent(DmaPartitionDO::getMonitorPointIds, reqVO.getMonitorPointIds())
                .betweenIfPresent(DmaPartitionDO::getDivisionDate, reqVO.getDivisionDate())
                .eqIfPresent(DmaPartitionDO::getAdjustmentRecords, reqVO.getAdjustmentRecords())
                .betweenIfPresent(DmaPartitionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DmaPartitionDO::getId));
    }

}