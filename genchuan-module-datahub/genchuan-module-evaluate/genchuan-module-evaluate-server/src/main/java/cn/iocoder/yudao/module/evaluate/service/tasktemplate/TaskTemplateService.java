package cn.iocoder.yudao.module.evaluate.service.tasktemplate;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.tasktemplate.TaskTemplateDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 评价任务模板 Service 接口
 *
 * @author 芋道源码
 */
public interface TaskTemplateService {

    /**
     * 创建评价任务模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTaskTemplate(@Valid TaskTemplateSaveReqVO createReqVO);

    /**
     * 更新评价任务模板
     *
     * @param updateReqVO 更新信息
     */
    void updateTaskTemplate(@Valid TaskTemplateSaveReqVO updateReqVO);

    /**
     * 删除评价任务模板
     *
     * @param id 编号
     */
    void deleteTaskTemplate(Long id);

    /**
    * 批量删除评价任务模板
    *
    * @param ids 编号
    */
    void deleteTaskTemplateListByIds(List<Long> ids);

    /**
     * 获得评价任务模板
     *
     * @param id 编号
     * @return 评价任务模板
     */
    TaskTemplateDO getTaskTemplate(Long id);

    /**
     * 获得评价任务模板分页
     *
     * @param pageReqVO 分页查询
     * @return 评价任务模板分页
     */
    PageResult<TaskTemplateDO> getTaskTemplatePage(TaskTemplatePageReqVO pageReqVO);

    //-----------------------------------xin
    PageResult<TaskTemplateRespVO> getTaskTemplatePagelian(TaskTemplatePageReqVO reqVO);
}