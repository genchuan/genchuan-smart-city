package cn.iocoder.yudao.module.industry.service.park.order.parkarrears;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkarrears.vo.ParkArrearsSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkarrears.ParkArrearsDO;
import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 欠费记录 Service 接口
 *
 * @author lxs
 */
public interface ParkArrearsService {

    /**
     * 创建欠费记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkArrears(@Valid ParkArrearsSaveReqVO createReqVO);

    /**
     * 更新欠费记录
     *
     * @param updateReqVO 更新信息
     */
    void updateParkArrears(@Valid ParkArrearsSaveReqVO updateReqVO);

    /**
     * 删除欠费记录
     *
     * @param id 编号
     */
    void deleteParkArrears(Long id);

    /**
     * 获得欠费记录
     *
     * @param id 编号
     * @return 欠费记录
     */
    ParkArrearsDO getParkArrears(Long id);

    /**
     * 获得欠费记录分页
     *
     * @param pageReqVO 分页查询
     * @return 欠费记录分页
     */
    PageResult<ParkArrearsDO> getParkArrearsPage(ParkArrearsPageReqVO pageReqVO);

}
