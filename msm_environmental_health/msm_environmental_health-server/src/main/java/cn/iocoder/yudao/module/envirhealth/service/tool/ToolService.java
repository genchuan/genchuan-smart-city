package cn.iocoder.yudao.module.envirhealth.service.tool;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.tool.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.tool.ToolDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 工具字典 Service 接口
 *
 * @author 芋道源码
 */
public interface ToolService {

    /**
     * 创建工具字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTool(@Valid ToolSaveReqVO createReqVO);

    /**
     * 更新工具字典
     *
     * @param updateReqVO 更新信息
     */
    void updateTool(@Valid ToolSaveReqVO updateReqVO);

    /**
     * 删除工具字典
     *
     * @param id 编号
     */
    void deleteTool(Long id);

    /**
     * 获得工具字典
     *
     * @param id 编号
     * @return 工具字典
     */
    ToolDO getTool(Long id);

    /**
     * 获得工具字典分页
     *
     * @param pageReqVO 分页查询
     * @return 工具字典分页
     */
    PageResult<ToolDO> getToolPage(ToolPageReqVO pageReqVO);

}