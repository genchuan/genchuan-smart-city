package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.GarbageTransferDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

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
    public PageResult<GarbageTransferDetailDO> getGarbageTransferDetailPage(GarbageTransferPageReqVO pageReqVO) {
        Long total = garbageTransferMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageTransferDetailDO> list = garbageTransferMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }


}