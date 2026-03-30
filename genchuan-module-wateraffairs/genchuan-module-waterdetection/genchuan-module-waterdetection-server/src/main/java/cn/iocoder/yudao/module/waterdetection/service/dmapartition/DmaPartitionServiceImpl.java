package cn.iocoder.yudao.module.waterdetection.service.dmapartition;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.dmapartition.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.dmapartition.DmaPartitionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.dmapartition.DmaPartitionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * DMA分区划分与调整 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DmaPartitionServiceImpl implements DmaPartitionService {

    @Resource
    private DmaPartitionMapper dmaPartitionMapper;

    @Override
    public Long createDmaPartition(DmaPartitionSaveReqVO createReqVO) {
        // 插入
        DmaPartitionDO dmaPartition = BeanUtils.toBean(createReqVO, DmaPartitionDO.class);
        dmaPartitionMapper.insert(dmaPartition);
        // 返回
        return dmaPartition.getId();
    }

    @Override
    public void updateDmaPartition(DmaPartitionSaveReqVO updateReqVO) {
        // 校验存在
        validateDmaPartitionExists(updateReqVO.getId());
        // 更新
        DmaPartitionDO updateObj = BeanUtils.toBean(updateReqVO, DmaPartitionDO.class);
        dmaPartitionMapper.updateById(updateObj);
    }

    @Override
    public void deleteDmaPartition(Long id) {
        // 校验存在
        validateDmaPartitionExists(id);
        // 删除
        dmaPartitionMapper.deleteById(id);
    }

    private void validateDmaPartitionExists(Long id) {
        if (dmaPartitionMapper.selectById(id) == null) {
            throw exception(DMA_PARTITION_NOT_EXISTS);
        }
    }

    @Override
    public DmaPartitionDO getDmaPartition(Long id) {
        return dmaPartitionMapper.selectById(id);
    }

    @Override
    public PageResult<DmaPartitionDO> getDmaPartitionPage(DmaPartitionPageReqVO pageReqVO) {
        return dmaPartitionMapper.selectPage(pageReqVO);
    }

}