package cn.iocoder.yudao.module.waterdetection.service.instrumentcalibration;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.instrumentcalibration.InstrumentCalibrationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 仪器零点/量程漂移校验 Service 接口
 *
 * @author zcq
 */
public interface InstrumentCalibrationService {

    /**
     * 创建仪器零点/量程漂移校验
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInstrumentCalibration(@Valid InstrumentCalibrationSaveReqVO createReqVO);

    /**
     * 更新仪器零点/量程漂移校验
     *
     * @param updateReqVO 更新信息
     */
    void updateInstrumentCalibration(@Valid InstrumentCalibrationSaveReqVO updateReqVO);

    /**
     * 删除仪器零点/量程漂移校验
     *
     * @param id 编号
     */
    void deleteInstrumentCalibration(Long id);

    /**
     * 获得仪器零点/量程漂移校验
     *
     * @param id 编号
     * @return 仪器零点/量程漂移校验
     */
    InstrumentCalibrationDO getInstrumentCalibration(Long id);

    /**
     * 获得仪器零点/量程漂移校验分页
     *
     * @param pageReqVO 分页查询
     * @return 仪器零点/量程漂移校验分页
     */
    PageResult<InstrumentCalibrationDO> getInstrumentCalibrationPage(InstrumentCalibrationPageReqVO pageReqVO);

}