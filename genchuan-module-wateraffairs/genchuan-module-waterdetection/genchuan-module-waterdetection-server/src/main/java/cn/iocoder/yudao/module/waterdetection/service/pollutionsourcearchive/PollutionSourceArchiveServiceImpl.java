package cn.iocoder.yudao.module.waterdetection.service.pollutionsourcearchive;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.pollutionsourcearchive.PollutionSourceArchiveDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.pollutionsourcearchive.PollutionSourceArchiveMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 周边污染源档案管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class PollutionSourceArchiveServiceImpl implements PollutionSourceArchiveService {

    @Resource
    private PollutionSourceArchiveMapper pollutionSourceArchiveMapper;

    @Override
    public Long createPollutionSourceArchive(PollutionSourceArchiveSaveReqVO createReqVO) {
        // 插入
        PollutionSourceArchiveDO pollutionSourceArchive = BeanUtils.toBean(createReqVO, PollutionSourceArchiveDO.class);
        pollutionSourceArchiveMapper.insert(pollutionSourceArchive);
        // 返回
        return pollutionSourceArchive.getId();
    }

    @Override
    public void updatePollutionSourceArchive(PollutionSourceArchiveSaveReqVO updateReqVO) {
        // 校验存在
        validatePollutionSourceArchiveExists(updateReqVO.getId());
        // 更新
        PollutionSourceArchiveDO updateObj = BeanUtils.toBean(updateReqVO, PollutionSourceArchiveDO.class);
        pollutionSourceArchiveMapper.updateById(updateObj);
    }

    @Override
    public void deletePollutionSourceArchive(Long id) {
        // 校验存在
        validatePollutionSourceArchiveExists(id);
        // 删除
        pollutionSourceArchiveMapper.deleteById(id);
    }

    private void validatePollutionSourceArchiveExists(Long id) {
        if (pollutionSourceArchiveMapper.selectById(id) == null) {
            throw exception(POLLUTION_SOURCE_ARCHIVE_NOT_EXISTS);
        }
    }

    @Override
    public PollutionSourceArchiveDO getPollutionSourceArchive(Long id) {
        return pollutionSourceArchiveMapper.selectById(id);
    }

    @Override
    public PageResult<PollutionSourceArchiveDO> getPollutionSourceArchivePage(PollutionSourceArchivePageReqVO pageReqVO) {
        return pollutionSourceArchiveMapper.selectPage(pageReqVO);
    }

}