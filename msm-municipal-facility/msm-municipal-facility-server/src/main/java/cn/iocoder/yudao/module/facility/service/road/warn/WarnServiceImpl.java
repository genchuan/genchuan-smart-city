package cn.iocoder.yudao.module.facility.service.road.warn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.road.warn.vo.WarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.warn.vo.WarnSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.warn.WarnDO;
import cn.iocoder.yudao.module.facility.dal.mysql.road.warn.WarnMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.WARN_NOT_EXISTS;

/**
 * 预警 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class WarnServiceImpl implements WarnService {

    @Resource
    private WarnMapper warnMapper;

    @Override
    public Long createWarn(WarnSaveReqVO createReqVO) {
        // 插入
        WarnDO warn = BeanUtils.toBean(createReqVO, WarnDO.class);
        warnMapper.insert(warn);
        // 返回
        return warn.getId();
    }

    @Override
    public void updateWarn(WarnSaveReqVO updateReqVO) {
        // 校验存在
        validateWarnExists(updateReqVO.getId());
        // 更新
        WarnDO updateObj = BeanUtils.toBean(updateReqVO, WarnDO.class);
        warnMapper.updateById(updateObj);
    }

    @Override
    public void deleteWarn(Long id) {
        // 校验存在
        validateWarnExists(id);
        // 删除
        warnMapper.deleteById(id);
    }

    private void validateWarnExists(Long id) {
        if (warnMapper.selectById(id) == null) {
            throw exception(WARN_NOT_EXISTS);
        }
    }

    @Override
    public WarnDO getWarn(Long id) {
        return warnMapper.selectById(id);
    }

    @Override
    public PageResult<WarnDO> getWarnPage(WarnPageReqVO pageReqVO) {
        return warnMapper.selectPage(pageReqVO);
    }

}
