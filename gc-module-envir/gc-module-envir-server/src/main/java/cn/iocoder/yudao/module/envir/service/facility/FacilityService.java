package cn.iocoder.yudao.module.envir.service.facility;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.facility.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.facility.FacilityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 设施字典 Service 接口
 *
 * @author 芋道源码
 */
public interface FacilityService {

    /**
     * 创建设施字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createFacility(@Valid FacilitySaveReqVO createReqVO);

    /**
     * 更新设施字典
     *
     * @param updateReqVO 更新信息
     */
    void updateFacility(@Valid FacilitySaveReqVO updateReqVO);

    /**
     * 删除设施字典
     *
     * @param id 编号
     */
    void deleteFacility(Long id);

    /**
     * 获得设施字典
     *
     * @param id 编号
     * @return 设施字典
     */
    FacilityDO getFacility(Long id);

    /**
     * 获得设施字典分页
     *
     * @param pageReqVO 分页查询
     * @return 设施字典分页
     */
    PageResult<FacilityDO> getFacilityPage(FacilityPageReqVO pageReqVO);

}