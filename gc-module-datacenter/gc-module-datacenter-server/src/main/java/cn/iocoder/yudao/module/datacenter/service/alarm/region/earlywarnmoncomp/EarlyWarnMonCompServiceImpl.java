package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmoncomp.vo.EarlyWarnMonCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmoncomp.EarlyWarnMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmoncomp.EarlyWarnMonCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按监测部件预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnMonCompServiceImpl implements EarlyWarnMonCompService {

    @Resource
    private EarlyWarnMonCompMapper earlyWarnMonCompMapper;

    @Override
    public Long createEarlyWarnMonComp(EarlyWarnMonCompSaveReqVO createReqVO) {
        // 插入
        EarlyWarnMonCompDO earlyWarnMonComp = BeanUtils.toBean(createReqVO, EarlyWarnMonCompDO.class);
        earlyWarnMonCompMapper.insert(earlyWarnMonComp);
        // 返回
        return earlyWarnMonComp.getId();
    }

    @Override
    public void updateEarlyWarnMonComp(EarlyWarnMonCompSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnMonCompExists(updateReqVO.getId());
        // 更新
        EarlyWarnMonCompDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnMonCompDO.class);
        earlyWarnMonCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnMonComp(Long id) {
        // 校验存在
        validateEarlyWarnMonCompExists(id);
        // 删除
        earlyWarnMonCompMapper.deleteById(id);
    }

    private void validateEarlyWarnMonCompExists(Long id) {
        if (earlyWarnMonCompMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_MON_COMP_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnMonCompDO getEarlyWarnMonComp(Long id) {
        return earlyWarnMonCompMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnMonCompDO> getEarlyWarnMonCompPage(EarlyWarnMonCompPageReqVO pageReqVO) {
        return earlyWarnMonCompMapper.selectPage(pageReqVO);
    }

}