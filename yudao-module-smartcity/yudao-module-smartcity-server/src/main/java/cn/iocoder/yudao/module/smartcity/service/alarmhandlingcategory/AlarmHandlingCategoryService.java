package cn.iocoder.yudao.module.smartcity.service.alarmhandlingcategory;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 报警处置类 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface AlarmHandlingCategoryService {

    /**
     * 创建报警处置类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlarmHandlingCategory(@Valid AlarmHandlingCategorySaveReqVO createReqVO);

    /**
     * 更新报警处置类
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmHandlingCategory(@Valid AlarmHandlingCategorySaveReqVO updateReqVO);

    /**
     * 删除报警处置类
     *
     * @param id 编号
     */
    void deleteAlarmHandlingCategory(Long id);

    /**
     * 获得报警处置类
     *
     * @param id 编号
     * @return 报警处置类
     */
    AlarmHandlingCategoryDO getAlarmHandlingCategory(Long id);

    /**
     * 获得报警处置类分页
     *
     * @param pageReqVO 分页查询
     * @return 报警处置类分页
     */
    PageResult<AlarmHandlingCategoryDO> getAlarmHandlingCategoryPage(AlarmHandlingCategoryPageReqVO pageReqVO);

}