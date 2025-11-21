package cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvtask;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtask.InspectSupvTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.heart.inspectsupvtask.InspectSupvTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检重点督办任务 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectSupvTaskServiceImpl implements InspectSupvTaskService {

    @Resource
    private InspectSupvTaskMapper inspectSupvTaskMapper;

    @Override
    public Long createInspectSupvTask(InspectSupvTaskSaveReqVO createReqVO) {
        // 插入
        InspectSupvTaskDO inspectSupvTask = BeanUtils.toBean(createReqVO, InspectSupvTaskDO.class);
        inspectSupvTaskMapper.insert(inspectSupvTask);
        // 返回
        return inspectSupvTask.getId();
    }

    @Override
    public void updateInspectSupvTask(InspectSupvTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectSupvTaskExists(updateReqVO.getId());
        // 更新
        InspectSupvTaskDO updateObj = BeanUtils.toBean(updateReqVO, InspectSupvTaskDO.class);
        inspectSupvTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectSupvTask(Long id) {
        // 校验存在
        validateInspectSupvTaskExists(id);
        // 删除
        inspectSupvTaskMapper.deleteById(id);
    }

    private void validateInspectSupvTaskExists(Long id) {
        if (inspectSupvTaskMapper.selectById(id) == null) {
            throw exception(INSPECT_SUPV_TASK_NOT_EXISTS);
        }
    }

    @Override
    public InspectSupvTaskDO getInspectSupvTask(Long id) {
        return inspectSupvTaskMapper.selectById(id);
    }

    @Override
    public PageResult<InspectSupvTaskDO> getInspectSupvTaskPage(InspectSupvTaskPageReqVO pageReqVO) {
        return inspectSupvTaskMapper.selectPage(pageReqVO);
    }

}