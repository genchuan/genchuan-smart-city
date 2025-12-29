package cn.iocoder.yudao.module.industry.service.park.pay.parkwo;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoCreateReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkwo.vo.ParkWoSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkwo.ParkWoDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车订单 Service 接口
 *
 * @author lxs
 */
public interface ParkWoService {

    /**
     * 创建停车订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkWo(@Valid ParkWoCreateReqVO createReqVO);

    /**
     * 更新停车订单
     *
     * @param updateReqVO 更新信息
     */
    void updateParkWo(@Valid ParkWoSaveReqVO updateReqVO);

    /**
     * 删除停车订单
     *
     * @param id 编号
     */
    void deleteParkWo(Long id);

    /**
     * 获得停车订单
     *
     * @param id 编号
     * @return 停车订单
     */
    ParkWoDO getParkWo(Long id);

    /**
     * 获得停车订单分页
     *
     * @param pageReqVO 分页查询
     * @return 停车订单分页
     */
    PageResult<ParkWoDO> getParkWoPage(ParkWoPageReqVO pageReqVO);

}
