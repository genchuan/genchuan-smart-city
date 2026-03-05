package cn.iocoder.yudao.module.industry.service.park.through.inspectionevidence;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidencePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo.ParkInspectionEvidenceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.inspectionevidence.ParkInspectionEvidenceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.through.inspectionevidence.ParkInspectionEvidenceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 稽查证据 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkInspectionEvidenceServiceImpl implements ParkInspectionEvidenceService {

    @Resource
    private ParkInspectionEvidenceMapper parkInspectionEvidenceMapper;

    @Override
    public Long createParkInspectionEvidence(ParkInspectionEvidenceSaveReqVO createReqVO) {
        // 插入
        ParkInspectionEvidenceDO parkInspectionEvidence = BeanUtils.toBean(createReqVO, ParkInspectionEvidenceDO.class);
        parkInspectionEvidenceMapper.insert(parkInspectionEvidence);
        // 返回
        return parkInspectionEvidence.getId();
    }

    @Override
    public void updateParkInspectionEvidence(ParkInspectionEvidenceSaveReqVO updateReqVO) {
        // 校验存在
        validateParkInspectionEvidenceExists(updateReqVO.getId());
        // 更新
        ParkInspectionEvidenceDO updateObj = BeanUtils.toBean(updateReqVO, ParkInspectionEvidenceDO.class);
        parkInspectionEvidenceMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkInspectionEvidence(Long id) {
        // 校验存在
        validateParkInspectionEvidenceExists(id);
        // 删除
        parkInspectionEvidenceMapper.deleteById(id);
    }

    private void validateParkInspectionEvidenceExists(Long id) {
        if (parkInspectionEvidenceMapper.selectById(id) == null) {
            throw exception(PARK_INSPECTION_EVIDENCE_NOT_EXISTS);
        }
    }

    @Override
    public ParkInspectionEvidenceDO getParkInspectionEvidence(Long id) {
        return parkInspectionEvidenceMapper.selectById(id);
    }

    @Override
    public PageResult<ParkInspectionEvidenceDO> getParkInspectionEvidencePage(ParkInspectionEvidencePageReqVO pageReqVO) {
        return parkInspectionEvidenceMapper.selectPage(pageReqVO);
    }

}