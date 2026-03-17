package cn.iocoder.yudao.module.envirhealth.service.dictionary.maintainstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.maintainstatus.vo.MaintainStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.maintainstatus.vo.MaintainStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.maintainstatus.MaintainStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.maintainstatus.MaintainStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.MAINTAIN_STATUS_NOT_EXISTS;

/**
 * 维护状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MaintainStatusServiceImpl implements MaintainStatusService {

    @Resource
    private MaintainStatusMapper maintainStatusMapper;

    @Override
    public Long createMaintainStatus(MaintainStatusSaveReqVO createReqVO) {
        // 插入
        MaintainStatusDO maintainStatus = BeanUtils.toBean(createReqVO, MaintainStatusDO.class);
        maintainStatusMapper.insert(maintainStatus);
        // 返回
        return maintainStatus.getId();
    }

    @Override
    public void updateMaintainStatus(MaintainStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintainStatusExists(updateReqVO.getId());
        // 更新
        MaintainStatusDO updateObj = BeanUtils.toBean(updateReqVO, MaintainStatusDO.class);
        maintainStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintainStatus(Long id) {
        // 校验存在
        validateMaintainStatusExists(id);
        // 删除
        maintainStatusMapper.deleteById(id);
    }

    private void validateMaintainStatusExists(Long id) {
        if (maintainStatusMapper.selectById(id) == null) {
            throw exception(MAINTAIN_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public MaintainStatusDO getMaintainStatus(Long id) {
        return maintainStatusMapper.selectById(id);
    }

    @Override
    public PageResult<MaintainStatusDO> getMaintainStatusPage(MaintainStatusPageReqVO pageReqVO) {
        return maintainStatusMapper.selectPage(pageReqVO);
    }

}