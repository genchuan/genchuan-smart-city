package cn.iocoder.yudao.module.datacenter.service.emerplan.emerresstatus;

import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.emerplan.emerresstatus.vo.EmerResStatusSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.emerplan.emerresstatus.EmerResStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.emerplan.emerresstatus.EmerResStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.EMER_RES_STATUS_NOT_EXISTS;

/**
 * 资源状态统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerResStatusServiceImpl implements EmerResStatusService {

    @Resource
    private EmerResStatusMapper emerResStatusMapper;

    @Override
    public Long createEmerResStatus(EmerResStatusSaveReqVO createReqVO) {
        // 插入
        EmerResStatusDO emerResStatus = BeanUtils.toBean(createReqVO, EmerResStatusDO.class);
        emerResStatusMapper.insert(emerResStatus);
        // 返回
        return emerResStatus.getId();
    }

    @Override
    public void updateEmerResStatus(EmerResStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerResStatusExists(updateReqVO.getId());
        // 更新
        EmerResStatusDO updateObj = BeanUtils.toBean(updateReqVO, EmerResStatusDO.class);
        emerResStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerResStatus(Long id) {
        // 校验存在
        validateEmerResStatusExists(id);
        // 删除
        emerResStatusMapper.deleteById(id);
    }

    private void validateEmerResStatusExists(Long id) {
        if (emerResStatusMapper.selectById(id) == null) {
            throw exception(EMER_RES_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public EmerResStatusDO getEmerResStatus(Long id) {
        return emerResStatusMapper.selectById(id);
    }

    @Override
    public PageResult<EmerResStatusDO> getEmerResStatusPage(EmerResStatusPageReqVO pageReqVO) {
        return emerResStatusMapper.selectPage(pageReqVO);
    }

}