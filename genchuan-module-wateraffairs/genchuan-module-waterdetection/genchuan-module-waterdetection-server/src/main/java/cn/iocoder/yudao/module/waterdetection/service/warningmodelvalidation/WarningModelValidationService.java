package cn.iocoder.yudao.module.waterdetection.service.warningmodelvalidation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningmodelvalidation.WarningModelValidationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 预警模型校验 Service 接口
 *
 * @author zcq
 */
public interface WarningModelValidationService {

    /**
     * 创建预警模型校验
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWarningModelValidation(@Valid WarningModelValidationSaveReqVO createReqVO);

    /**
     * 更新预警模型校验
     *
     * @param updateReqVO 更新信息
     */
    void updateWarningModelValidation(@Valid WarningModelValidationSaveReqVO updateReqVO);

    /**
     * 删除预警模型校验
     *
     * @param id 编号
     */
    void deleteWarningModelValidation(Long id);

    /**
     * 获得预警模型校验
     *
     * @param id 编号
     * @return 预警模型校验
     */
    WarningModelValidationDO getWarningModelValidation(Long id);

    /**
     * 获得预警模型校验分页
     *
     * @param pageReqVO 分页查询
     * @return 预警模型校验分页
     */
    PageResult<WarningModelValidationDO> getWarningModelValidationPage(WarningModelValidationPageReqVO pageReqVO);

}