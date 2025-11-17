package cn.iocoder.yudao.module.datacenter.service.operationguide.generaloperguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.generaloperguide.vo.GeneralOperGuideSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.generaloperguide.GeneralOperGuideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 通用操作指南 Service 接口
 *
 * @author 亘川智城
 */
public interface GeneralOperGuideService {

    /**
     * 创建通用操作指南
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeneralOperGuide(@Valid GeneralOperGuideSaveReqVO createReqVO);

    /**
     * 更新通用操作指南
     *
     * @param updateReqVO 更新信息
     */
    void updateGeneralOperGuide(@Valid GeneralOperGuideSaveReqVO updateReqVO);

    /**
     * 删除通用操作指南
     *
     * @param id 编号
     */
    void deleteGeneralOperGuide(Long id);

    /**
     * 获得通用操作指南
     *
     * @param id 编号
     * @return 通用操作指南
     */
    GeneralOperGuideDO getGeneralOperGuide(Long id);

    /**
     * 获得通用操作指南分页
     *
     * @param pageReqVO 分页查询
     * @return 通用操作指南分页
     */
    PageResult<GeneralOperGuideDO> getGeneralOperGuidePage(GeneralOperGuidePageReqVO pageReqVO);

}