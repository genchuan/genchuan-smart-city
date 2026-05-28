package cn.iocoder.yudao.module.smartcampus.service.archive;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smartcampus.enums.ArchiveProcessStatusEnum;
import cn.iocoder.yudao.module.smartcampus.enums.ArchiveStatusEnum;
import cn.iocoder.yudao.module.smartcampus.enums.SmartCampusDictTypeEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.smartcampus.controller.admin.archive.vo.*;
import cn.iocoder.yudao.module.smartcampus.dal.dataobject.archive.ArchiveDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcampus.dal.mysql.archive.ArchiveMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils.dictDataApi;
import static cn.iocoder.yudao.module.smartcampus.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.smartcampus.enums.LogRecordConstants.*;

/**
 * 学生学籍档案 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ArchiveServiceImpl implements ArchiveService {

    @Resource
    private ArchiveMapper archiveMapper;

    @Override
    public Long createArchive(ArchiveSaveReqVO createReqVO) {
        // 插入
        ArchiveDO archive = BeanUtils.toBean(createReqVO, ArchiveDO.class);
        archiveMapper.insert(archive);

        // 返回
        return archive.getId();
    }

    @Override
    public void updateArchive(ArchiveSaveReqVO updateReqVO) {
        // 校验存在
        validateArchiveExists(updateReqVO.getId());
        // 更新
        ArchiveDO updateObj = BeanUtils.toBean(updateReqVO, ArchiveDO.class);
        archiveMapper.updateById(updateObj);
    }

    @Override
    public void deleteArchive(Long id) {
        // 校验存在
        validateArchiveExists(id);
        // 删除
        archiveMapper.deleteById(id);
    }

    @Override
        public void deleteArchiveListByIds(List<Long> ids) {
        // 删除
        archiveMapper.deleteByIds(ids);
        }


    private void validateArchiveExists(Long id) {
        if (archiveMapper.selectById(id) == null) {
            throw exception(ARCHIVE_NOT_EXISTS);
        }
    }

    @Override
    public ArchiveDO getArchive(Long id) {
        return archiveMapper.selectById(id);
    }

    @Override
    public PageResult<ArchiveDO> getArchivePage(ArchivePageReqVO pageReqVO) {
        return archiveMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(
            type = STUDENT_ARCHIVE_TYPE,
            subType = STUDENT_ARCHIVE_UPDATE_AUDIT_STATUS_SUB_TYPE,
            bizNo = "{{#archive.id}}",
            success = STUDENT_ARCHIVE_UPDATE_AUDIT_STATUS_SUCCESS
    )
    public boolean audit(ArchiveAuditReqVO reqVO, LoginUser loginUser) {

        List<Long> ids = reqVO.getIds();
        if (CollUtil.isEmpty(ids)) {
            throw exception(ARCHIVE_PROCESS_STATUS_NOT_NULL);
        }

        // 1. 校验并转换审核状态
        String processStatusCode = reqVO.getProcessStatus();
        ArchiveProcessStatusEnum statusEnum =
                Arrays.stream(ArchiveProcessStatusEnum.values())
                        .filter(e -> e.getProcessStatus().equals(processStatusCode))
                        .findFirst()
                        .orElseThrow(() ->
                                exception(ARCHIVE_PROCESS_STATUS_NOT_NULL));

        String updater = loginUser.getId().toString();

        // 2. 逐条校验 + 更新（保证幂等 & 可追踪）
        for (Long id : ids) {
            ArchiveDO archive = archiveMapper.selectById(id);
            if (archive == null) {
                throw exception(ARCHIVE_NOT_EXISTS);
            }

            ArchiveDO updateObj = new ArchiveDO();
            updateObj.setId(id);
            updateObj.setProcessStatus(statusEnum.getProcessStatus());
            updateObj.setRejectReason(reqVO.getRejectReason());
            updateObj.setUpdater(updater);
            updateObj.setUpdateTime(LocalDateTime.now());

            archiveMapper.updateById(updateObj);

            // 3. 日志上下文（只记第一条）
            if (LogRecordContext.getVariables().isEmpty()) {
                LogRecordContext.putVariable("archive", archive);

                String dictLabel = ArchiveProcessStatusEnum
                        .getNameByKey(statusEnum.getProcessStatus());
                LogRecordContext.putVariable("processStatus", dictLabel);
            }
        }

        return true;
    }

    @Override
    @LogRecord(
            type = STUDENT_ARCHIVE_TYPE,
            subType = STUDENT_ARCHIVE_MAINTAIN_SUB_TYPE,
            bizNo = "{{#archive.id}}",
            success = STUDENT_ARCHIVE_MAINTAIN_SUCCESS
    )
    public boolean maintain(ArchiveMaintainReqVO reqVO, LoginUser loginUser) {

        // 1. 校验档案是否存在
        ArchiveDO archive = archiveMapper.selectById(reqVO.getId());
        if (archive == null) {
            throw exception(ARCHIVE_NOT_EXISTS);
        }

        // 2. 校验并转换学籍状态（核心）
        String statusCode = reqVO.getStatus();
        ArchiveStatusEnum statusEnum = Arrays.stream(ArchiveStatusEnum.values())
                .filter(e -> e.getStatus().equals(statusCode))
                .findFirst()
                .orElseThrow(() -> exception(ARCHIVE_STATUS_NOT_NULL));

        // 3. 更新字段
        ArchiveDO updateObj = new ArchiveDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus(statusEnum.getStatus());
        updateObj.setChangeReason(reqVO.getChangeReason());
        updateObj.setEvidenceUrl(reqVO.getEvidenceUrl());
        updateObj.setUpdater(loginUser.getId().toString());
        updateObj.setUpdateTime(LocalDateTime.now());

        archiveMapper.updateById(updateObj);

        // 4. 日志上下文
        LogRecordContext.putVariable("archive", archive);
        LogRecordContext.putVariable("status", statusEnum.getName());
        LogRecordContext.putVariable("changeReason", reqVO.getChangeReason());

        return true;
    }

}