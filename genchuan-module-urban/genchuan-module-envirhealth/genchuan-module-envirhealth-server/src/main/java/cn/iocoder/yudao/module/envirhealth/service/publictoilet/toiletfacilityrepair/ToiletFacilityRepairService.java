package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletfacilityrepair;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPendingRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDetailDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * 批量删除公厕设施维修
     *
     * @param ids 编号列表
     */
    void deleteToiletFacilityRepairBatch(List<Long> ids);

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


    /**
     * 获得公厕设施维修分页(详情)
     *
     * @param pageReqVO 分页查询
     * @return 公厕设施维修分页
     */
    PageResult<ToiletFacilityRepairDetailDO> getToiletFacilityRepairDetailPage(ToiletFacilityRepairPageReqVO pageReqVO);


    /**
     * 上传多张图片
     *
     * @param id 维修ID
     * @param files 图片文件列表
     * @return 图片访问URL列表
     */
    List<String> uploadPhotos(Long id, List<MultipartFile> files);

    /**
     * 获取图片列表
     *
     * @param id 维修ID
     * @return 图片URL列表
     */
    List<String> getPhotos(Long id);

    /**
     * 删除图片
     *
     * @param id 维修ID
     * @param photoUrl 图片URL
     */
    void deletePhoto(Long id, String photoUrl);

    /**
     * 卡片/圆环图/柱状图统计(待维修)
     */
    ToiletFacilityRepairPendingRespVO getPendingData();

}