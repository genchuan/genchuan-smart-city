package cn.iocoder.yudao.module.studentmgmt.service.behaviormgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.behaviormgmt.BehaviorMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.behaviormgmt.BehaviorMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 行为管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BehaviorMgmtServiceImpl implements BehaviorMgmtService {

    @Resource
    private BehaviorMgmtMapper behaviorMgmtMapper;

    @Override
    public Long createBehaviorMgmt(BehaviorMgmtSaveReqVO createReqVO) {
        // 插入
        BehaviorMgmtDO behaviorMgmt = BeanUtils.toBean(createReqVO, BehaviorMgmtDO.class);
        behaviorMgmtMapper.insert(behaviorMgmt);

        // 返回
        return behaviorMgmt.getId();
    }

    @Override
    public void updateBehaviorMgmt(BehaviorMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateBehaviorMgmtExists(updateReqVO.getId());
        // 更新
        BehaviorMgmtDO updateObj = BeanUtils.toBean(updateReqVO, BehaviorMgmtDO.class);
        behaviorMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBehaviorMgmt(Long id) {
        // 校验存在
        validateBehaviorMgmtExists(id);
        // 删除
        behaviorMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteBehaviorMgmtListByIds(List<Long> ids) {
        // 删除
        behaviorMgmtMapper.deleteByIds(ids);
        }


    private void validateBehaviorMgmtExists(Long id) {
        if (behaviorMgmtMapper.selectById(id) == null) {
            throw exception(BEHAVIOR_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public BehaviorMgmtDO getBehaviorMgmt(Long id) {
        return behaviorMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<BehaviorMgmtDO> getBehaviorMgmtPage(BehaviorMgmtPageReqVO pageReqVO) {
        return behaviorMgmtMapper.selectPage(pageReqVO);
    }

}