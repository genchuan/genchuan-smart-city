package cn.iocoder.yudao.module.enterprisesvc.service.enterprisefile;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo.*;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.enterprisefile.EnterpriseFileDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.enterprisesvc.framework.file.FileUploadService;

import cn.iocoder.yudao.module.enterprisesvc.dal.mysql.enterprisefile.EnterpriseFileMapper;
import lombok.extern.slf4j.Slf4j;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.enterprisesvc.enums.ErrorCodeConstants.*;

/**
 * 企业档案 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
@Slf4j
public class EnterpriseFileServiceImpl implements EnterpriseFileService {

    private static final String FILE_STATUS_PENDING_REVIEW = "待审核";

    @Resource
    private EnterpriseFileMapper enterpriseFileMapper;

    @Resource
    private FileUploadService fileUploadService;

    @Override
    public Long createEnterpriseFile(EnterpriseFileCreateReqVO createReqVO) {
        validateCreditCodeUnique(createReqVO.getCreditCode());

        EnterpriseFileDO enterpriseFile = BeanUtils.toBean(createReqVO, EnterpriseFileDO.class);
        enterpriseFile.setFileStatus(FILE_STATUS_PENDING_REVIEW);
        enterpriseFile.setStaffCount(createReqVO.getStaffCount() == null ? 0 : createReqVO.getStaffCount());
        enterpriseFileMapper.insert(enterpriseFile);

        return enterpriseFile.getId();
    }

    @Override
    public String uploadEnterpriseFile(EnterpriseFileUploadReqVO uploadReqVO) {
        validateEnterpriseFileExists(uploadReqVO.getId());
        if (uploadReqVO.getFile().isEmpty()) {
            throw exception(ENTERPRISE_FILE_UPLOAD_FILE_EMPTY);
        }
        try {
            return fileUploadService.uploadEnterpriseFile(uploadReqVO.getFile());
        } catch (Exception e) {
            log.error("[uploadEnterpriseFile][id({}) 上传企业资质证明文件失败]", uploadReqVO.getId(), e);
            throw exception(ENTERPRISE_FILE_UPLOAD_FAIL);
        }
    }

    @Override
    public void updateEnterpriseFile(EnterpriseFileSaveReqVO updateReqVO) {
        // 校验存在
        validateEnterpriseFileExists(updateReqVO.getId());
        // 更新
        EnterpriseFileDO updateObj = BeanUtils.toBean(updateReqVO, EnterpriseFileDO.class);
        enterpriseFileMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnterpriseFile(Long id) {
        // 校验存在
        validateEnterpriseFileExists(id);
        // 删除
        enterpriseFileMapper.deleteById(id);
    }

    @Override
        public void deleteEnterpriseFileListByIds(List<Long> ids) {
        // 删除
        enterpriseFileMapper.deleteByIds(ids);
        }


    private void validateEnterpriseFileExists(Long id) {
        if (enterpriseFileMapper.selectById(id) == null) {
            throw exception(ENTERPRISE_FILE_NOT_EXISTS);
        }
    }

    private void validateCreditCodeUnique(String creditCode) {
        if (enterpriseFileMapper.selectByCreditCode(creditCode) != null) {
            throw exception(ENTERPRISE_FILE_CREDIT_CODE_EXISTS);
        }
    }

    @Override
    public EnterpriseFileDO getEnterpriseFile(Long id) {
        return enterpriseFileMapper.selectById(id);
    }

    @Override
    public PageResult<EnterpriseFileDO> getEnterpriseFilePage(EnterpriseFilePageReqVO pageReqVO) {
        return enterpriseFileMapper.selectPage(pageReqVO);
    }

    @Override
    public void auditEnterpriseFile(Long id) {
        // 校验存在
        validateEnterpriseFileExists(id);

        // 更新审核信息
        EnterpriseFileDO updateObj = new EnterpriseFileDO();
        String checkUser = SecurityFrameworkUtils.getLoginUserNickname();
        updateObj.setId(id);
        updateObj.setFileStatus("已通过");  // 档案状态改为已通过
        updateObj.setCheckUser(checkUser);  // 记录审核人账号
        updateObj.setHandleUser(checkUser);
        updateObj.setCheckRate(BigDecimal.valueOf(100.00));  // 更新审核通过率为100%

        enterpriseFileMapper.updateById(updateObj);
    }

    @Override
    public void rejectEnterpriseFile(EnterpriseFileRejectReqVO rejectReqVO) {
        // 1. 校验企业档案是否存在
        validateEnterpriseFileExists(rejectReqVO.getId());

        // 2. 构建更新对象：状态改为「已驳回」，驳回原因存入备用字段1
        EnterpriseFileDO updateObj = new EnterpriseFileDO();
        String checkUser = SecurityFrameworkUtils.getLoginUserNickname();
        updateObj.setId(rejectReqVO.getId());
        updateObj.setCheckUser(checkUser);  // 记录审核人账号
        updateObj.setHandleUser(checkUser);
        updateObj.setFileStatus("已驳回");          // 档案状态设为已驳回
        updateObj.setReserve1(rejectReqVO.getReserve1()); // 驳回原因存入备用字段1

        // 3. 执行更新
        enterpriseFileMapper.updateById(updateObj);
    }

    @Override
    public void resubmitEnterpriseFile(EnterpriseFileResubmitReqVO resubmitReqVO) {
        // 1. 校验企业档案是否存在（复用现有校验逻辑）
        validateEnterpriseFileExists(resubmitReqVO.getId());

        // 2. 构建更新对象：状态改为待审核，清空驳回原因
        EnterpriseFileDO updateObj = new EnterpriseFileDO();
        updateObj.setId(resubmitReqVO.getId());
        String checkUser = SecurityFrameworkUtils.getLoginUserNickname();
        updateObj.setCheckUser(checkUser);  // 记录审核人账号
        updateObj.setHandleUser(checkUser);
        updateObj.setFileStatus(FILE_STATUS_PENDING_REVIEW); // 复用常量，避免硬编码
        updateObj.setReserve1(null); // 清空驳回原因（原原因存储在reserve1中）

        // 3. 执行数据库更新
        enterpriseFileMapper.updateById(updateObj);
    }

    @Override
    public EnterpriseFileChartRespVO getEnterpriseFileChart() {
        // 1. 统计基础数据（总数、在园数、退园数、审核通过率）
        Map<String, Object> stats = enterpriseFileMapper.selectChartStats();
        Integer totalCount = ((Number) stats.get("totalCount")).intValue();
        Integer inParkCount = ((Number) stats.get("inParkCount")).intValue();
        Integer outParkCount = ((Number) stats.get("outParkCount")).intValue();
        BigDecimal checkPassRate = (BigDecimal) stats.get("checkPassRate");

        // 2. 统计企业类型占比
        List<EnterpriseFileChartRespVO.ChartItemVO> typeRatio = enterpriseFileMapper.selectTypeRatio();

        // 3. 统计企业规模占比
        List<EnterpriseFileChartRespVO.ChartItemVO> scaleRatio = enterpriseFileMapper.selectScaleRatio();

        // 4. 构建返回对象
        EnterpriseFileChartRespVO respVO = new EnterpriseFileChartRespVO();
        respVO.setTotalCount(totalCount);
        respVO.setInParkCount(inParkCount);
        respVO.setOutParkCount(outParkCount);
        respVO.setCheckPassRate(checkPassRate);
        respVO.setTypeRatio(typeRatio);
        respVO.setScaleRatio(scaleRatio);

        return respVO;
    }

}
