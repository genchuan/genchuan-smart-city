package cn.iocoder.yudao.module.envirhealth.service.urbanvillage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillageDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillagePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillageSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.detail.UrbanVillageDetailDO;
import jakarta.validation.Valid;

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
     * @param pageReqVO 分页查询
     * @return 城中村分页
     */
    PageResult<UrbanVillageDO> getUrbanVillagePage(UrbanVillagePageReqVO pageReqVO);


    /**
     * 获得城中村分页
     * @param pageReqVO 分页查询
     * @return 城中村分页
     */
    PageResult<UrbanVillageDetailDO> getUrbanVillageDetailPage(UrbanVillagePageReqVO pageReqVO);

    /**
     * 获取城中村看板全量数据
     */
    UrbanVillageDashboardVO getUrbanVillageDashboard();
}