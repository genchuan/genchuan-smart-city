package cn.iocoder.yudao.module.park.service.park.resource.parkinglotinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.parkinglotinfo.ParkingLotInfoDO;
import jakarta.validation.Valid;

/**
 * 停车场信息管理 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkingLotInfoService {

    /**
     * 创建停车场信息管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createingLotInfo(@Valid ParkingLotInfoSaveReqVO createReqVO);

    /**
     * 更新停车场信息管理
     *
     * @param updateReqVO 更新信息
     */
    void updateingLotInfo(@Valid ParkingLotInfoSaveReqVO updateReqVO);

    /**
     * 删除停车场信息管理
     *
     * @param id 编号
     */
    void deleteingLotInfo(Long id);

    /**
     * 获得停车场信息管理
     *
     * @param id 编号
     * @return 停车场信息管理
     */
    ParkingLotInfoDO getingLotInfo(Long id);

    /**
     * 获得停车场信息管理分页
     *
     * @param pageReqVO 分页查询
     * @return 停车场信息管理分页
     */
    PageResult<ParkingLotInfoDO> getingLotInfoPage(ParkingLotInfoPageReqVO pageReqVO);

}