package cn.iocoder.yudao.module.inspectop.service.inspecttask;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttask.InspectTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 巡检任务 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectTaskServiceImpl implements InspectTaskService {

    @Resource
    private InspectTaskMapper inspectTaskMapper;

    @Override
    public Long createInspectTask(InspectTaskSaveReqVO createReqVO) {
        // 插入
        InspectTaskDO inspectTask = BeanUtils.toBean(createReqVO, InspectTaskDO.class);
        inspectTaskMapper.insert(inspectTask);

        // 返回
        return inspectTask.getId();
    }

    @Override
    public void updateInspectTask(InspectTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectTaskExists(updateReqVO.getId());
        // 更新
        InspectTaskDO updateObj = BeanUtils.toBean(updateReqVO, InspectTaskDO.class);
        inspectTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectTask(Long id) {
        // 校验存在
        validateInspectTaskExists(id);
        // 删除
        inspectTaskMapper.deleteById(id);
    }

    @Override
        public void deleteInspectTaskListByIds(List<Long> ids) {
        // 删除
        inspectTaskMapper.deleteByIds(ids);
        }


    private void validateInspectTaskExists(Long id) {
        if (inspectTaskMapper.selectById(id) == null) {
            throw exception(INSPECT_TASK_NOT_EXISTS);
        }
    }

    @Override
    public InspectTaskDO getInspectTask(Long id) {
        return inspectTaskMapper.selectById(id);
    }

    @Override
    public PageResult<InspectTaskDO> getInspectTaskPage(InspectTaskPageReqVO pageReqVO) {
        return inspectTaskMapper.selectPage(pageReqVO);
    }

}