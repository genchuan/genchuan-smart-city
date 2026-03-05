package cn.iocoder.yudao.module.industry.service.park.through.inspectionevidence;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidencePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidenceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.inspectionevidence.ParkInspectionEvidenceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 稽查证据 Service 接口
 *
 * @author zhucongquan
 */
public interface ParkInspectionEvidenceService {

    /**
     * 创建稽查证据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createParkInspectionEvidence(@Valid ParkInspectionEvidenceSaveReqVO createReqVO);

    /**
     * 更新稽查证据
     *
     * @param updateReqVO 更新信息
     */
    void updateParkInspectionEvidence(@Valid ParkInspectionEvidenceSaveReqVO updateReqVO);

    /**
     * 删除稽查证据
     *
     * @param id 编号
     */
    void deleteParkInspectionEvidence(Long id);

    /**
     * 获得稽查证据
     *
     * @param id 编号
     * @return 稽查证据
     */
    ParkInspectionEvidenceDO getParkInspectionEvidence(Long id);

    /**
     * 获得稽查证据分页
     *
     * @param pageReqVO 分页查询
     * @return 稽查证据分页
     */
    PageResult<ParkInspectionEvidenceDO> getParkInspectionEvidencePage(ParkInspectionEvidencePageReqVO pageReqVO);

}