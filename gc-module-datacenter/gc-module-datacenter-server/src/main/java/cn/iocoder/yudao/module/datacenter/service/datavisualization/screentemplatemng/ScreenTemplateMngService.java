package cn.iocoder.yudao.module.datacenter.service.datavisualization.screentemplatemng;

import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo.ScreenTemplateMngSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.screentemplatemng.ScreenTemplateMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 大屏模板管理 Service 接口
 *
 * @author 亘川智城
 */
public interface ScreenTemplateMngService {

    /**
     * 创建大屏模板管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScreenTemplateMng(@Valid ScreenTemplateMngSaveReqVO createReqVO);

    /**
     * 更新大屏模板管理
     *
     * @param updateReqVO 更新信息
     */
    void updateScreenTemplateMng(@Valid ScreenTemplateMngSaveReqVO updateReqVO);

    /**
     * 删除大屏模板管理
     *
     * @param id 编号
     */
    void deleteScreenTemplateMng(Long id);

    /**
     * 获得大屏模板管理
     *
     * @param id 编号
     * @return 大屏模板管理
     */
    ScreenTemplateMngDO getScreenTemplateMng(Long id);

    /**
     * 获得大屏模板管理分页
     *
     * @param pageReqVO 分页查询
     * @return 大屏模板管理分页
     */
    PageResult<ScreenTemplateMngDO> getScreenTemplateMngPage(ScreenTemplateMngPageReqVO pageReqVO);

}