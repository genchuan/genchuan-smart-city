package cn.iocoder.yudao.module.envirhealth.service.handlestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.handlestatus.vo.HandleStatusOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.handlestatus.vo.HandleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.handlestatus.vo.HandleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.handlestatus.HandleStatusDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 处置状态字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface HandleStatusService {

    /**
     * 创建处置状态字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createHandleStatus(@Valid HandleStatusSaveReqVO createReqVO);

    /**
     * 更新处置状态字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateHandleStatus(@Valid HandleStatusSaveReqVO updateReqVO);

    /**
     * 删除处置状态字典表
     *
     * @param id 编号
     */
    void deleteHandleStatus(Long id);

    /**
     * 获得处置状态字典表
     *
     * @param id 编号
     * @return 处置状态字典表
     */
    HandleStatusDO getHandleStatus(Long id);

    /**
     * 获得处置状态字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 处置状态字典表分页
     */
    PageResult<HandleStatusDO> getHandleStatusPage(HandleStatusPageReqVO pageReqVO);

    /**
     * 获得处置状态下拉框选项
     * @return 下拉框选项列表
     */
    List<HandleStatusOptionVO> getHandleStatusOptions();
}