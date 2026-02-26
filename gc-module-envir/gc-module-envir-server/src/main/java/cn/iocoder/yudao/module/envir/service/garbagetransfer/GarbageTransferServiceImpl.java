package cn.iocoder.yudao.module.envir.service.garbagetransfer;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.garbagetransfer.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.garbagetransfer.GarbageTransferMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 垃圾转运站 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageTransferServiceImpl implements GarbageTransferService {

    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    @Override
    public Long createGarbageTransfer(GarbageTransferSaveReqVO createReqVO) {
        // 插入
        GarbageTransferDO garbageTransfer = BeanUtils.toBean(createReqVO, GarbageTransferDO.class);
        garbageTransferMapper.insert(garbageTransfer);
        // 返回
        return garbageTransfer.getId();
    }

    @Override
    public void updateGarbageTransfer(GarbageTransferSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageTransferExists(updateReqVO.getId());
        // 更新
        GarbageTransferDO updateObj = BeanUtils.toBean(updateReqVO, GarbageTransferDO.class);
        garbageTransferMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageTransfer(Long id) {
        // 校验存在
        validateGarbageTransferExists(id);
        // 删除
        garbageTransferMapper.deleteById(id);
    }

    private void validateGarbageTransferExists(Long id) {
        if (garbageTransferMapper.selectById(id) == null) {
            throw exception(GARBAGE_TRANSFER_NOT_EXISTS);
        }
    }

    @Override
    public GarbageTransferDO getGarbageTransfer(Long id) {
        return garbageTransferMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageTransferDO> getGarbageTransferPage(GarbageTransferPageReqVO pageReqVO) {
        return garbageTransferMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GarbageTransferDetailDO> getGarbageTransferListDetail() {
        return garbageTransferMapper.selectListDetail();
    }
}