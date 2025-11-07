package cn.iocoder.yudao.module.smartcity.service.workarea;

import java.util.*;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.smartcity.controller.admin.workarea.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.workarea.WorkAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 作业区域 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface WorkAreaService {

    /**
     * 创建作业区域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkArea(@Valid WorkAreaSaveReqVO createReqVO);

    /**
     * 更新作业区域
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkArea(@Valid WorkAreaSaveReqVO updateReqVO);

    /**
     * 删除作业区域
     *
     * @param id 编号
     */
    void deleteWorkArea(Long id);

    /**
     * 获得作业区域
     *
     * @param id 编号
     * @return 作业区域
     */
    WorkAreaDO getWorkArea(Long id);

    /**
     * 获得作业区域分页
     *
     * @param pageReqVO 分页查询
     * @return 作业区域分页
     */
    PageResult<WorkAreaDO> getWorkAreaPage(WorkAreaPageReqVO pageReqVO);

}