package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletfacilityrepair;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletFacilityRepairDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 公厕设施维修 Service 接口
 *
 * @author 芋道源码
 */
public interface ToiletFacilityRepairService {

    /**
     * 创建公厕设施维修
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createToiletFacilityRepair(@Valid ToiletFacilityRepairSaveReqVO createReqVO);

    /**
     * 更新公厕设施维修
     *
     * @param updateReqVO 更新信息
     */
    void updateToiletFacilityRepair(@Valid ToiletFacilityRepairSaveReqVO updateReqVO);

    /**
     * 删除公厕设施维修
     *
     * @param id 编号
     */
    void deleteToiletFacilityRepair(Long id);

    /**
     * 获得公厕设施维修
     *
     * @param id 编号
     * @return 公厕设施维修
     */
    ToiletFacilityRepairDO getToiletFacilityRepair(Long id);

    /**
     * 获得公厕设施维修分页
     *
     * @param pageReqVO 分页查询
     * @return 公厕设施维修分页
     */
    PageResult<ToiletFacilityRepairDO> getToiletFacilityRepairPage(ToiletFacilityRepairPageReqVO pageReqVO);

    PageResult<ToiletFacilityRepairDetailDO> getToiletFacilityRepairDetailPage(ToiletFacilityRepairPageReqVO pageReqVO);

}