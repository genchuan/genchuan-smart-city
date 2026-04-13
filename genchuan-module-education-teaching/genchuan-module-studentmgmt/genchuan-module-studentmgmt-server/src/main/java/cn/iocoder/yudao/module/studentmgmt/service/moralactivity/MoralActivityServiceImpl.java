package cn.iocoder.yudao.module.studentmgmt.service.moralactivity;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralactivity.MoralActivityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.moralactivity.MoralActivityMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 德育活动 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MoralActivityServiceImpl implements MoralActivityService {

    @Resource
    private MoralActivityMapper moralActivityMapper;

    @Override
    public Long createMoralActivity(MoralActivitySaveReqVO createReqVO) {
        // 插入
        MoralActivityDO moralActivity = BeanUtils.toBean(createReqVO, MoralActivityDO.class);
        moralActivityMapper.insert(moralActivity);

        // 返回
        return moralActivity.getId();
    }

    @Override
    public void updateMoralActivity(MoralActivitySaveReqVO updateReqVO) {
        // 校验存在
        validateMoralActivityExists(updateReqVO.getId());
        // 更新
        MoralActivityDO updateObj = BeanUtils.toBean(updateReqVO, MoralActivityDO.class);
        moralActivityMapper.updateById(updateObj);
    }

    @Override
    public void deleteMoralActivity(Long id) {
        // 校验存在
        validateMoralActivityExists(id);
        // 删除
        moralActivityMapper.deleteById(id);
    }

    @Override
        public void deleteMoralActivityListByIds(List<Long> ids) {
        // 删除
        moralActivityMapper.deleteByIds(ids);
        }


    private void validateMoralActivityExists(Long id) {
        if (moralActivityMapper.selectById(id) == null) {
            throw exception(MORAL_ACTIVITY_NOT_EXISTS);
        }
    }

    @Override
    public MoralActivityDO getMoralActivity(Long id) {
        return moralActivityMapper.selectById(id);
    }

    @Override
    public PageResult<MoralActivityDO> getMoralActivityPage(MoralActivityPageReqVO pageReqVO) {
        return moralActivityMapper.selectPage(pageReqVO);
    }

}