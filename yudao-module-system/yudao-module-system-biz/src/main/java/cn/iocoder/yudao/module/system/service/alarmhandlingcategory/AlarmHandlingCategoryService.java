package cn.iocoder.yudao.module.system.service.alarmhandlingcategory;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.alarmhandlingcategory.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 警报处理类别 Service 接口
 *
 * @author zcq
 */
public interface AlarmHandlingCategoryService {

    /**
     * 创建警报处理类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlarmHandlingCategory(@Valid AlarmHandlingCategorySaveReqVO createReqVO);

    /**
     * 更新警报处理类别
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmHandlingCategory(@Valid AlarmHandlingCategorySaveReqVO updateReqVO);

    /**
     * 删除警报处理类别
     *
     * @param id 编号
     */
    void deleteAlarmHandlingCategory(Long id);

    /**
     * 获得警报处理类别
     *
     * @param id 编号
     * @return 警报处理类别
     */
    AlarmHandlingCategoryDO getAlarmHandlingCategory(Long id);

    /**
     * 获得警报处理类别分页
     *
     * @param pageReqVO 分页查询
     * @return 警报处理类别分页
     */
    PageResult<AlarmHandlingCategoryDO> getAlarmHandlingCategoryPage(AlarmHandlingCategoryPageReqVO pageReqVO);

}