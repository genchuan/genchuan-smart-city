package cn.iocoder.yudao.module.waterdetection.service.inspectiontask;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectiontask.InspectionTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.inspectiontask.InspectionTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 巡检任务派发与执行 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectionTaskServiceImpl implements InspectionTaskService {

    @Resource
    private InspectionTaskMapper inspectionTaskMapper;

    @Override
    public Long createInspectionTask(InspectionTaskSaveReqVO createReqVO) {
        // 插入
        InspectionTaskDO inspectionTask = BeanUtils.toBean(createReqVO, InspectionTaskDO.class);
        inspectionTaskMapper.insert(inspectionTask);
        // 返回
        return inspectionTask.getId();
    }

    @Override
    public void updateInspectionTask(InspectionTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectionTaskExists(updateReqVO.getId());
        // 更新
        InspectionTaskDO updateObj = BeanUtils.toBean(updateReqVO, InspectionTaskDO.class);
        inspectionTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectionTask(Long id) {
        // 校验存在
        validateInspectionTaskExists(id);
        // 删除
        inspectionTaskMapper.deleteById(id);
    }

    private void validateInspectionTaskExists(Long id) {
        if (inspectionTaskMapper.selectById(id) == null) {
            throw exception(INSPECTION_TASK_NOT_EXISTS);
        }
    }

    @Override
    public InspectionTaskDO getInspectionTask(Long id) {
        return inspectionTaskMapper.selectById(id);
    }

    @Override
    public PageResult<InspectionTaskDO> getInspectionTaskPage(InspectionTaskPageReqVO pageReqVO) {
        return inspectionTaskMapper.selectPage(pageReqVO);
    }

}