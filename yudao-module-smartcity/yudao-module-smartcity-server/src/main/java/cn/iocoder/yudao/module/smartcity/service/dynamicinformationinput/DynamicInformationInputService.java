package cn.iocoder.yudao.module.smartcity.service.dynamicinformationinput;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationinput.DynamicInformationInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 动态信息录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface DynamicInformationInputService {

    /**
     * 创建动态信息录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDynamicInformationInput(@Valid DynamicInformationInputSaveReqVO createReqVO);

    /**
     * 更新动态信息录入
     *
     * @param updateReqVO 更新信息
     */
    void updateDynamicInformationInput(@Valid DynamicInformationInputSaveReqVO updateReqVO);

    /**
     * 删除动态信息录入
     *
     * @param id 编号
     */
    void deleteDynamicInformationInput(Long id);

    /**
     * 获得动态信息录入
     *
     * @param id 编号
     * @return 动态信息录入
     */
    DynamicInformationInputDO getDynamicInformationInput(Long id);

    /**
     * 获得动态信息录入分页
     *
     * @param pageReqVO 分页查询
     * @return 动态信息录入分页
     */
    PageResult<DynamicInformationInputDO> getDynamicInformationInputPage(DynamicInformationInputPageReqVO pageReqVO);

}