package cn.iocoder.yudao.module.industry.service.park.cardriveoutrecord;

import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveoutrecord.CarDriveoutRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 车辆出场记录 Service 接口
 *
 * @author zhucongquan
 */
public interface CarDriveoutRecordService {

    /**
     * 创建车辆出场记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCarDriveoutRecord(@Valid CarDriveoutRecordSaveReqVO createReqVO);

    /**
     * 更新车辆出场记录
     *
     * @param updateReqVO 更新信息
     */
    void updateCarDriveoutRecord(@Valid CarDriveoutRecordSaveReqVO updateReqVO);

    /**
     * 删除车辆出场记录
     *
     * @param id 编号
     */
    void deleteCarDriveoutRecord(Long id);

    /**
     * 获得车辆出场记录
     *
     * @param id 编号
     * @return 车辆出场记录
     */
    CarDriveoutRecordDO getCarDriveoutRecord(Long id);

    /**
     * 获得车辆出场记录分页
     *
     * @param pageReqVO 分页查询
     * @return 车辆出场记录分页
     */
    PageResult<CarDriveoutRecordDO> getCarDriveoutRecordPage(CarDriveoutRecordPageReqVO pageReqVO);

}