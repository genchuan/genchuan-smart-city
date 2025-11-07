package cn.iocoder.yudao.module.smartcity.service.parkinglotinformation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo.ParkingLotInformationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkinglotinformation.vo.ParkingLotInformationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkinglotinformation.ParkingLotInformationDO;
import jakarta.validation.Valid;

/**
 * 停车场信息管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ParkingLotInformationService {

    /**
     * 创建停车场信息管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkingLotInformation(@Valid ParkingLotInformationSaveReqVO createReqVO);

    /**
     * 更新停车场信息管理
     *
     * @param updateReqVO 更新信息
     */
    void updateParkingLotInformation(@Valid ParkingLotInformationSaveReqVO updateReqVO);

    /**
     * 删除停车场信息管理
     *
     * @param id 编号
     */
    void deleteParkingLotInformation(Long id);

    /**
     * 获得停车场信息管理
     *
     * @param id 编号
     * @return 停车场信息管理
     */
    ParkingLotInformationDO getParkingLotInformation(Long id);

    /**
     * 获得停车场信息管理分页
     *
     * @param pageReqVO 分页查询
     * @return 停车场信息管理分页
     */
    PageResult<ParkingLotInformationDO> getParkingLotInformationPage(ParkingLotInformationPageReqVO pageReqVO);

}