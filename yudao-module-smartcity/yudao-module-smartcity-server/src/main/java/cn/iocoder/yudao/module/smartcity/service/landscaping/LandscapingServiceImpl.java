package cn.iocoder.yudao.module.smartcity.service.landscaping;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.landscaping.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.landscaping.LandscapingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.landscaping.LandscapingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 园林绿化 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class LandscapingServiceImpl implements LandscapingService {

    @Resource
    private LandscapingMapper landscapingMapper;

    @Override
    public Long createLandscaping(LandscapingSaveReqVO createReqVO) {
        // 插入
        LandscapingDO landscaping = BeanUtils.toBean(createReqVO, LandscapingDO.class);
        landscapingMapper.insert(landscaping);
        // 返回
        return landscaping.getId();
    }

    @Override
    public void updateLandscaping(LandscapingSaveReqVO updateReqVO) {
        // 校验存在
        validateLandscapingExists(updateReqVO.getId());
        // 更新
        LandscapingDO updateObj = BeanUtils.toBean(updateReqVO, LandscapingDO.class);
        landscapingMapper.updateById(updateObj);
    }

    @Override
    public void deleteLandscaping(Long id) {
        // 校验存在
        validateLandscapingExists(id);
        // 删除
        landscapingMapper.deleteById(id);
    }

    private void validateLandscapingExists(Long id) {
        if (landscapingMapper.selectById(id) == null) {
            throw exception(LANDSCAPING_NOT_EXISTS);
        }
    }

    @Override
    public LandscapingDO getLandscaping(Long id) {
        return landscapingMapper.selectById(id);
    }

    @Override
    public PageResult<LandscapingDO> getLandscapingPage(LandscapingPageReqVO pageReqVO) {
        return landscapingMapper.selectPage(pageReqVO);
    }

}