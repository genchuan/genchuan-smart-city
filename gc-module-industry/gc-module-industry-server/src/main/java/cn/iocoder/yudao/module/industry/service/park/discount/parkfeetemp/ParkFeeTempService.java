package cn.iocoder.yudao.module.industry.service.park.discount.parkfeetemp;

import java.util.*;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkfeetemp.vo.ParkFeeTempSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkfeetemp.ParkFeeTempDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 临停收费规则 Service 接口
 *
 * @author lxs
 */
public interface ParkFeeTempService {

    /**
     * 创建临停收费规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkFeeTemp(@Valid ParkFeeTempSaveReqVO createReqVO);

    /**
     * 更新临停收费规则
     *
     * @param updateReqVO 更新信息
     */
    void updateParkFeeTemp(@Valid ParkFeeTempSaveReqVO updateReqVO);

    /**
     * 删除临停收费规则
     *
     * @param id 编号
     */
    void deleteParkFeeTemp(Long id);

    /**
     * 获得临停收费规则
     *
     * @param id 编号
     * @return 临停收费规则
     */
    ParkFeeTempDO getParkFeeTemp(Long id);

    /**
     * 获得临停收费规则分页
     *
     * @param pageReqVO 分页查询
     * @return 临停收费规则分页
     */
    PageResult<ParkFeeTempDO> getParkFeeTempPage(ParkFeeTempPageReqVO pageReqVO);

}
