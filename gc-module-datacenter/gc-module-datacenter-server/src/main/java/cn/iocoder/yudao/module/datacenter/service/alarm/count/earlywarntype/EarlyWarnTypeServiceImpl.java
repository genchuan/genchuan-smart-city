package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarntype;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntype.EarlyWarnTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarntype.EarlyWarnTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警类型维度统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnTypeServiceImpl implements EarlyWarnTypeService {

    @Resource
    private EarlyWarnTypeMapper earlyWarnTypeMapper;

    @Override
    public Long createEarlyWarnType(EarlyWarnTypeSaveReqVO createReqVO) {
        // 插入
        EarlyWarnTypeDO earlyWarnType = BeanUtils.toBean(createReqVO, EarlyWarnTypeDO.class);
        earlyWarnTypeMapper.insert(earlyWarnType);
        // 返回
        return earlyWarnType.getId();
    }

    @Override
    public void updateEarlyWarnType(EarlyWarnTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnTypeExists(updateReqVO.getId());
        // 更新
        EarlyWarnTypeDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnTypeDO.class);
        earlyWarnTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnType(Long id) {
        // 校验存在
        validateEarlyWarnTypeExists(id);
        // 删除
        earlyWarnTypeMapper.deleteById(id);
    }

    private void validateEarlyWarnTypeExists(Long id) {
        if (earlyWarnTypeMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnTypeDO getEarlyWarnType(Long id) {
        return earlyWarnTypeMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnTypeDO> getEarlyWarnTypePage(EarlyWarnTypePageReqVO pageReqVO) {
        return earlyWarnTypeMapper.selectPage(pageReqVO);
    }

}