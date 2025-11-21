package cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnarea;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnarea.EarlyWarnAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnarea.EarlyWarnAreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警区域维度统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnAreaServiceImpl implements EarlyWarnAreaService {

    @Resource
    private EarlyWarnAreaMapper earlyWarnAreaMapper;

    @Override
    public Long createEarlyWarnArea(EarlyWarnAreaSaveReqVO createReqVO) {
        // 插入
        EarlyWarnAreaDO earlyWarnArea = BeanUtils.toBean(createReqVO, EarlyWarnAreaDO.class);
        earlyWarnAreaMapper.insert(earlyWarnArea);
        // 返回
        return earlyWarnArea.getId();
    }

    @Override
    public void updateEarlyWarnArea(EarlyWarnAreaSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnAreaExists(updateReqVO.getId());
        // 更新
        EarlyWarnAreaDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnAreaDO.class);
        earlyWarnAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnArea(Long id) {
        // 校验存在
        validateEarlyWarnAreaExists(id);
        // 删除
        earlyWarnAreaMapper.deleteById(id);
    }

    private void validateEarlyWarnAreaExists(Long id) {
        if (earlyWarnAreaMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_AREA_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnAreaDO getEarlyWarnArea(Long id) {
        return earlyWarnAreaMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnAreaDO> getEarlyWarnAreaPage(EarlyWarnAreaPageReqVO pageReqVO) {
        return earlyWarnAreaMapper.selectPage(pageReqVO);
    }

}