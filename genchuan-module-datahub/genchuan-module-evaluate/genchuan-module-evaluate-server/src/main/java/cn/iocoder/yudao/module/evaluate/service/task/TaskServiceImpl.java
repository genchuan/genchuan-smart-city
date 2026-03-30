package cn.iocoder.yudao.module.evaluate.service.task;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.object.ObjectMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.task.TaskMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.TASK_NOT_EXISTS;

/**
 * 评价任务 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private ObjectMapper objectMapper; // 注入对象Mapper
    @Override
    public Long createTask(TaskSaveReqVO createReqVO) {
        // 插入
        TaskDO task = BeanUtils.toBean(createReqVO, TaskDO.class);
        taskMapper.insert(task);

        // 返回
        return task.getId();
    }

    @Override
    public void updateTask(TaskSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskExists(updateReqVO.getId());
        // 更新
        TaskDO updateObj = BeanUtils.toBean(updateReqVO, TaskDO.class);
        taskMapper.updateById(updateObj);
    }

    @Override
    public void deleteTask(Long id) {
        // 校验存在
        validateTaskExists(id);
        // 删除
        taskMapper.deleteById(id);
    }

    @Override
        public void deleteTaskListByIds(List<Long> ids) {
        // 删除
        taskMapper.deleteByIds(ids);
        }


    private void validateTaskExists(Long id) {
        if (taskMapper.selectById(id) == null) {
            throw exception(TASK_NOT_EXISTS);
        }
    }

    @Override
    public TaskDO getTask(Long id) {
        return taskMapper.selectById(id);
    }

    @Override
    public PageResult<TaskDO> getTaskPage(TaskPageReqVO pageReqVO) {
        return taskMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TaskRespVO> getTaskPagerelevant(TaskPageReqVO reqVO) {
        // 1. 先执行联表分页查询（只查主表和一对一关联表）
        PageResult<TaskRespVO> pageResult = taskMapper.selectTaskPage(reqVO);

        // 2. 处理一对多的自定义对象名称（从 Service 层调用 ObjectMapper）
        pageResult.getList().forEach(respVO -> {
            String objectIdsStr = respVO.getObjectId();
            if (StrUtil.isNotBlank(objectIdsStr)) { // 先判空，避免空指针
                List<String> idList = Arrays.asList(objectIdsStr.split(",")); // 拆分逗号分隔的ID
                List<String> objectNames = objectMapper.selectNamesByIds(idList); // 修正方法名
                respVO.setObjectName(objectNames);
            }
        });

        return pageResult;
    }
}