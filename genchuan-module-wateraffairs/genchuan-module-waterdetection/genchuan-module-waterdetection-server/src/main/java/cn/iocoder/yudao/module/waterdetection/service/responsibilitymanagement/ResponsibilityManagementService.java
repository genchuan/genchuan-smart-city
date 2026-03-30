package cn.iocoder.yudao.module.waterdetection.service.responsibilitymanagement;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.responsibilitymanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.responsibilitymanagement.ResponsibilityManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 责任单位及责任人管理 Service 接口
 *
 * @author zcq
 */
public interface ResponsibilityManagementService {

    /**
     * 创建责任单位及责任人管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createResponsibilityManagement(@Valid ResponsibilityManagementSaveReqVO createReqVO);

    /**
     * 更新责任单位及责任人管理
     *
     * @param updateReqVO 更新信息
     */
    void updateResponsibilityManagement(@Valid ResponsibilityManagementSaveReqVO updateReqVO);

    /**
     * 删除责任单位及责任人管理
     *
     * @param id 编号
     */
    void deleteResponsibilityManagement(Long id);

    /**
     * 获得责任单位及责任人管理
     *
     * @param id 编号
     * @return 责任单位及责任人管理
     */
    ResponsibilityManagementDO getResponsibilityManagement(Long id);

    /**
     * 获得责任单位及责任人管理分页
     *
     * @param pageReqVO 分页查询
     * @return 责任单位及责任人管理分页
     */
    PageResult<ResponsibilityManagementDO> getResponsibilityManagementPage(ResponsibilityManagementPageReqVO pageReqVO);

}