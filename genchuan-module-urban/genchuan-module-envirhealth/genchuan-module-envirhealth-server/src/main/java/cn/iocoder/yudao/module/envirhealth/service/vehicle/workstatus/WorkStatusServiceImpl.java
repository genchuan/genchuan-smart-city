package cn.iocoder.yudao.module.envirhealth.service.vehicle.workstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.WorkStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.WorkStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.WORK_STATUS_NOT_EXISTS;

/**
 * 作业状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class WorkStatusServiceImpl implements WorkStatusService {

    @Resource
    private WorkStatusMapper workStatusMapper;

    @Override
    public Long createWorkStatus(WorkStatusSaveReqVO createReqVO) {
        // 插入
        WorkStatusDO workStatus = BeanUtils.toBean(createReqVO, WorkStatusDO.class);
        workStatusMapper.insert(workStatus);
        // 返回
        return workStatus.getId();
    }

    @Override
    public void updateWorkStatus(WorkStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateWorkStatusExists(updateReqVO.getId());
        // 更新
        WorkStatusDO updateObj = BeanUtils.toBean(updateReqVO, WorkStatusDO.class);
        workStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorkStatus(Long id) {
        // 校验存在
        validateWorkStatusExists(id);
        // 删除
        workStatusMapper.deleteById(id);
    }

    private void validateWorkStatusExists(Long id) {
        if (workStatusMapper.selectById(id) == null) {
            throw exception(WORK_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public WorkStatusDO getWorkStatus(Long id) {
        return workStatusMapper.selectById(id);
    }

    @Override
    public PageResult<WorkStatusDO> getWorkStatusPage(WorkStatusPageReqVO pageReqVO) {
        return workStatusMapper.selectPage(pageReqVO);
    }

}