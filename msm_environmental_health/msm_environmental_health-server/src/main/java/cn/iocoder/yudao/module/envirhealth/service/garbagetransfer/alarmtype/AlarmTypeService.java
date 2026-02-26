package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.alarmtype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype.AlarmTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype.AlarmTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.AlarmTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface AlarmTypeService {

    /**
     * 创建预警类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlarmType(@Valid AlarmTypeSaveReqVO createReqVO);

    /**
     * 更新预警类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmType(@Valid AlarmTypeSaveReqVO updateReqVO);

    /**
     * 删除预警类型字典
     *
     * @param id 编号
     */
    void deleteAlarmType(Long id);

    /**
     * 获得预警类型字典
     *
     * @param id 编号
     * @return 预警类型字典
     */
    AlarmTypeDO getAlarmType(Long id);

    /**
     * 获得预警类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 预警类型字典分页
     */
    PageResult<AlarmTypeDO> getAlarmTypePage(AlarmTypePageReqVO pageReqVO);

}