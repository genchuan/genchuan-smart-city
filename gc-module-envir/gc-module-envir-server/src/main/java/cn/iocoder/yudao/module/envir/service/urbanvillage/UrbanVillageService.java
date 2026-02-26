package cn.iocoder.yudao.module.envir.service.urbanvillage;

import java.util.*;

import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.urbanvillage.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 城中村 Service 接口
 *
 * @author 芋道源码
 */
public interface UrbanVillageService {

    /**
     * 创建城中村
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUrbanVillage(@Valid UrbanVillageSaveReqVO createReqVO);

    /**
     * 更新城中村
     *
     * @param updateReqVO 更新信息
     */
    void updateUrbanVillage(@Valid UrbanVillageSaveReqVO updateReqVO);

    /**
     * 删除城中村
     *
     * @param id 编号
     */
    void deleteUrbanVillage(Long id);

    /**
     * 获得城中村
     *
     * @param id 编号
     * @return 城中村
     */
    UrbanVillageDO getUrbanVillage(Long id);

    /**
     * 获得城中村分页
     *
     * @param pageReqVO 分页查询
     * @return 城中村分页
     */
    PageResult<UrbanVillageDO> getUrbanVillagePage(UrbanVillagePageReqVO pageReqVO);

    /**
     * 获得城中村列表详情
     *
     * @return 详情列表
     */
    List<UrbanVillageDetailDO> getUrbanVillageListDetail();
}