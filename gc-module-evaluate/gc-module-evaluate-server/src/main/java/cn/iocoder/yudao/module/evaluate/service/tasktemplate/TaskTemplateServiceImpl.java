package cn.iocoder.yudao.module.evaluate.service.tasktemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.tasktemplate.TaskTemplateDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.tasktemplate.TaskTemplateMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.TASK_TEMPLATE_NOT_EXISTS;

/**
 * 评价任务模板 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Slf4j
@Validated
public class TaskTemplateServiceImpl implements TaskTemplateService {

    @Resource
    private TaskTemplateMapper taskTemplateMapper;

    @Override
    public Long createTaskTemplate(TaskTemplateSaveReqVO createReqVO) {
        // 插入
        TaskTemplateDO taskTemplate = BeanUtils.toBean(createReqVO, TaskTemplateDO.class);
        taskTemplateMapper.insert(taskTemplate);

        // 返回
        return taskTemplate.getId();
    }

    @Override
    public void updateTaskTemplate(TaskTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateTaskTemplateExists(updateReqVO.getId());
        // 更新
        TaskTemplateDO updateObj = BeanUtils.toBean(updateReqVO, TaskTemplateDO.class);
        taskTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteTaskTemplate(Long id) {
        // 校验存在
        validateTaskTemplateExists(id);
        // 删除
        taskTemplateMapper.deleteById(id);
    }

    @Override
        public void deleteTaskTemplateListByIds(List<Long> ids) {
        // 删除
        taskTemplateMapper.deleteByIds(ids);
        }


    private void validateTaskTemplateExists(Long id) {
        if (taskTemplateMapper.selectById(id) == null) {
            throw exception(TASK_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public TaskTemplateDO getTaskTemplate(Long id) {
        return taskTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<TaskTemplateDO> getTaskTemplatePage(TaskTemplatePageReqVO pageReqVO) {
        return taskTemplateMapper.selectPage(pageReqVO);
    }
//-----------------------------------xin
@Override
public PageResult<TaskTemplateRespVO> getTaskTemplatePagelian(TaskTemplatePageReqVO reqVO) {
    // 直接调用Mapper层的联表分页查询方法，返回分页结果
    log.info("开始分页查询评价任务模板，查询条件：{}", reqVO);
    PageResult<TaskTemplateRespVO> pageResult = taskTemplateMapper.selectTaskTemplateJoinPage(reqVO);
    log.info("分页查询评价任务模板完成，共查询到 {} 条数据", pageResult.getTotal());
    return pageResult;
}
}