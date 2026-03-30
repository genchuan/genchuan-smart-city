package cn.iocoder.yudao.module.waterdetection.service.dmapartition;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.dmapartition.DmaPartitionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * DMA分区划分与调整 Service 接口
 *
 * @author zcq
 */
public interface DmaPartitionService {

    /**
     * 创建DMA分区划分与调整
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDmaPartition(@Valid DmaPartitionSaveReqVO createReqVO);

    /**
     * 更新DMA分区划分与调整
     *
     * @param updateReqVO 更新信息
     */
    void updateDmaPartition(@Valid DmaPartitionSaveReqVO updateReqVO);

    /**
     * 删除DMA分区划分与调整
     *
     * @param id 编号
     */
    void deleteDmaPartition(Long id);

    /**
     * 获得DMA分区划分与调整
     *
     * @param id 编号
     * @return DMA分区划分与调整
     */
    DmaPartitionDO getDmaPartition(Long id);

    /**
     * 获得DMA分区划分与调整分页
     *
     * @param pageReqVO 分页查询
     * @return DMA分区划分与调整分页
     */
    PageResult<DmaPartitionDO> getDmaPartitionPage(DmaPartitionPageReqVO pageReqVO);

}