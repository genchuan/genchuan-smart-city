package cn.iocoder.yudao.module.evaluate.service.cycletype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.cycletype.vo.CycleTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.cycletype.CycleTypeDO;
import jakarta.validation.Valid;

/**
 * 周期类型字典 Service 接口
 *
 * @author 亘川智城
 */
public interface CycleTypeService {

    /**
     * 创建周期类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCycleType(@Valid CycleTypeSaveReqVO createReqVO);

    /**
     * 更新周期类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateCycleType(@Valid CycleTypeSaveReqVO updateReqVO);

    /**
     * 删除周期类型字典
     *
     * @param id 编号
     */
    void deleteCycleType(Long id);

    /**
     * 获得周期类型字典
     *
     * @param id 编号
     * @return 周期类型字典
     */
    CycleTypeDO getCycleType(Long id);

    /**
     * 获得周期类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 周期类型字典分页
     */
    PageResult<CycleTypeDO> getCycleTypePage(CycleTypePageReqVO pageReqVO);

}