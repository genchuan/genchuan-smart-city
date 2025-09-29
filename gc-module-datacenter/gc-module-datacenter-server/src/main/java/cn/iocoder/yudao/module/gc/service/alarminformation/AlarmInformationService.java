package cn.iocoder.yudao.module.gc.service.alarminformation;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo.*;
import cn.iocoder.yudao.module.gc.dal.dataobject.alarminformation.AlarmInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 预警信息 Service 接口
 *
 * @author 亘川智城
 */
public interface AlarmInformationService {

    /**
     * 创建预警信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createAlarmInformation(@Valid AlarmInformationSaveReqVO createReqVO);

    /**
     * 更新预警信息
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmInformation(@Valid AlarmInformationSaveReqVO updateReqVO);

    /**
     * 删除预警信息
     *
     * @param id 编号
     */
    void deleteAlarmInformation(String id);

    /**
     * 获得预警信息
     *
     * @param id 编号
     * @return 预警信息
     */
    AlarmInformationDO getAlarmInformation(String id);

    /**
     * 获得预警信息分页
     *
     * @param pageReqVO 分页查询
     * @return 预警信息分页
     */
    PageResult<AlarmInformationDO> getAlarmInformationPage(AlarmInformationPageReqVO pageReqVO);

}