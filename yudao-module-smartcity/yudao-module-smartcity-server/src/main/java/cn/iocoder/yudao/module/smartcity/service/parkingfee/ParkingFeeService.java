package cn.iocoder.yudao.module.smartcity.service.parkingfee;

import java.util.*;
import jakarta.validation.Valid;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkingfee.ParkingFeeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车收费管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ParkingFeeService {

    /**
     * 创建停车收费管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkingFee(@Valid ParkingFeeSaveReqVO createReqVO);

    /**
     * 更新停车收费管理
     *
     * @param updateReqVO 更新信息
     */
    void updateParkingFee(@Valid ParkingFeeSaveReqVO updateReqVO);

    /**
     * 删除停车收费管理
     *
     * @param id 编号
     */
    void deleteParkingFee(Long id);

    /**
     * 获得停车收费管理
     *
     * @param id 编号
     * @return 停车收费管理
     */
    ParkingFeeDO getParkingFee(Long id);

    /**
     * 获得停车收费管理分页
     *
     * @param pageReqVO 分页查询
     * @return 停车收费管理分页
     */
    PageResult<ParkingFeeDO> getParkingFeePage(ParkingFeePageReqVO pageReqVO);

}