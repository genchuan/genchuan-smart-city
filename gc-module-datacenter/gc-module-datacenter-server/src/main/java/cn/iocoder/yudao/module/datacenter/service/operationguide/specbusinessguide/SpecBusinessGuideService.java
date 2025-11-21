package cn.iocoder.yudao.module.datacenter.service.operationguide.specbusinessguide;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuidePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.specbusinessguide.vo.SpecBusinessGuideSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.specbusinessguide.SpecBusinessGuideDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 专项业务指南 Service 接口
 *
 * @author 亘川智城
 */
public interface SpecBusinessGuideService {

    /**
     * 创建专项业务指南
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSpecBusinessGuide(@Valid SpecBusinessGuideSaveReqVO createReqVO);

    /**
     * 更新专项业务指南
     *
     * @param updateReqVO 更新信息
     */
    void updateSpecBusinessGuide(@Valid SpecBusinessGuideSaveReqVO updateReqVO);

    /**
     * 删除专项业务指南
     *
     * @param id 编号
     */
    void deleteSpecBusinessGuide(Long id);

    /**
     * 获得专项业务指南
     *
     * @param id 编号
     * @return 专项业务指南
     */
    SpecBusinessGuideDO getSpecBusinessGuide(Long id);

    /**
     * 获得专项业务指南分页
     *
     * @param pageReqVO 分页查询
     * @return 专项业务指南分页
     */
    PageResult<SpecBusinessGuideDO> getSpecBusinessGuidePage(SpecBusinessGuidePageReqVO pageReqVO);

}