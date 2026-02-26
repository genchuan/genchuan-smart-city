package cn.iocoder.yudao.module.park.service.park.pricing.feetemp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feetemp.FeeTempDO;
import jakarta.validation.Valid;

/**
 * 临停收费规则 Service 接口
 *
 * @author 亘川智城
 */
public interface FeeTempService {

    /**
     * 创建临停收费规则
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFeeTemp(@Valid FeeTempSaveReqVO createReqVO);

    /**
     * 更新临停收费规则
     *
     * @param updateReqVO 更新信息
     */
    void updateFeeTemp(@Valid FeeTempSaveReqVO updateReqVO);

    /**
     * 删除临停收费规则
     *
     * @param id 编号
     */
    void deleteFeeTemp(Long id);

    /**
     * 获得临停收费规则
     *
     * @param id 编号
     * @return 临停收费规则
     */
    FeeTempDO getFeeTemp(Long id);

    /**
     * 获得临停收费规则分页
     *
     * @param pageReqVO 分页查询
     * @return 临停收费规则分页
     */
    PageResult<FeeTempDO> getFeeTempPage(FeeTempPageReqVO pageReqVO);

}
