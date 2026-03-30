package cn.iocoder.yudao.module.waterdetection.service.metercalibration;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.metercalibration.MeterCalibrationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 监测仪表校准管理 Service 接口
 *
 * @author zcq
 */
public interface MeterCalibrationService {

    /**
     * 创建监测仪表校准管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMeterCalibration(@Valid MeterCalibrationSaveReqVO createReqVO);

    /**
     * 更新监测仪表校准管理
     *
     * @param updateReqVO 更新信息
     */
    void updateMeterCalibration(@Valid MeterCalibrationSaveReqVO updateReqVO);

    /**
     * 删除监测仪表校准管理
     *
     * @param id 编号
     */
    void deleteMeterCalibration(Long id);

    /**
     * 获得监测仪表校准管理
     *
     * @param id 编号
     * @return 监测仪表校准管理
     */
    MeterCalibrationDO getMeterCalibration(Long id);

    /**
     * 获得监测仪表校准管理分页
     *
     * @param pageReqVO 分页查询
     * @return 监测仪表校准管理分页
     */
    PageResult<MeterCalibrationDO> getMeterCalibrationPage(MeterCalibrationPageReqVO pageReqVO);

}