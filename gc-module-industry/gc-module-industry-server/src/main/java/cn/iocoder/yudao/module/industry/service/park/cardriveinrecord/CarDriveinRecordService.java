package cn.iocoder.yudao.module.industry.service.park.cardriveinrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveinrecord.CarDriveinRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车辆入场记录 Service 接口
 *
 * @author zhucongquan
 */
public interface CarDriveinRecordService {

    /**
     * 创建车辆入场记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCarDriveinRecord(@Valid CarDriveinRecordSaveReqVO createReqVO);

    /**
     * 更新车辆入场记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCarDriveinRecord(@Valid CarDriveinRecordSaveReqVO updateReqVO);

    /**
     * 删除车辆入场记录
     *
     * @param id 编号
     */
    void deleteCarDriveinRecord(Long id);

    /**
     * 获得车辆入场记录
     *
     * @param id 编号
     * @return 车辆入场记录
     */
    CarDriveinRecordDO getCarDriveinRecord(Long id);

    /**
     * 获得车辆入场记录分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆入场记录分页
     */
    PageResult<CarDriveinRecordDO> getCarDriveinRecordPage(CarDriveinRecordPageReqVO pageReqVO);

}