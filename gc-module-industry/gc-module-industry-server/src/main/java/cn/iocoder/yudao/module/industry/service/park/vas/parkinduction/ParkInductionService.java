package cn.iocoder.yudao.module.industry.service.park.vas.parkinduction;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkinduction.vo.ParkInductionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkinduction.ParkInductionDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 停车诱导配置 Service 接口
 *
 * @author lxs
 */
public interface ParkInductionService {

    /**
     * 创建停车诱导配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkInduction(@Valid ParkInductionSaveReqVO createReqVO);

    /**
     * 更新停车诱导配置
     *
     * @param updateReqVO 更新信息
     */
    void updateParkInduction(@Valid ParkInductionSaveReqVO updateReqVO);

    /**
     * 删除停车诱导配置
     *
     * @param id 编号
     */
    void deleteParkInduction(Long id);

    /**
     * 获得停车诱导配置
     *
     * @param id 编号
     * @return 停车诱导配置
     */
    ParkInductionDO getParkInduction(Long id);

    /**
     * 获得停车诱导配置分页
     *
     * @param pageReqVO 分页查询
     * @return 停车诱导配置分页
     */
    PageResult<ParkInductionDO> getParkInductionPage(ParkInductionPageReqVO pageReqVO);

}
