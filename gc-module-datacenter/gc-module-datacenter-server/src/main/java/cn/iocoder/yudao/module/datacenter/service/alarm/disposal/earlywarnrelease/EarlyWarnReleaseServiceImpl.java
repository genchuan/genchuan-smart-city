package cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnrelease;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleasePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleaseSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnrelease.EarlyWarnReleaseMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnrelease.EarlyWarnReleaseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警解除 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnReleaseServiceImpl implements EarlyWarnReleaseService {

    @Resource
    private EarlyWarnReleaseMapper earlyWarnReleaseMapper;

    @Override
    public Long createEarlyWarnRelease(EarlyWarnReleaseSaveReqVO createReqVO) {
        // 插入
        EarlyWarnReleaseDO earlyWarnRelease = BeanUtils.toBean(createReqVO, EarlyWarnReleaseDO.class);
        earlyWarnReleaseMapper.insert(earlyWarnRelease);
        // 返回
        return earlyWarnRelease.getId();
    }

    @Override
    public void updateEarlyWarnRelease(EarlyWarnReleaseSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnReleaseExists(updateReqVO.getId());
        // 更新
        EarlyWarnReleaseDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnReleaseDO.class);
        earlyWarnReleaseMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnRelease(Long id) {
        // 校验存在
        validateEarlyWarnReleaseExists(id);
        // 删除
        earlyWarnReleaseMapper.deleteById(id);
    }

    private void validateEarlyWarnReleaseExists(Long id) {
        if (earlyWarnReleaseMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_RELEASE_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnReleaseDO getEarlyWarnRelease(Long id) {
        return earlyWarnReleaseMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnReleaseDO> getEarlyWarnReleasePage(EarlyWarnReleasePageReqVO pageReqVO) {
        return earlyWarnReleaseMapper.selectPage(pageReqVO);
    }

}