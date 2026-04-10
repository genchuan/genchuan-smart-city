package cn.iocoder.yudao.module.studentmgmt.service.targetmgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt.TargetMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.targetmgmt.TargetMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 指标管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TargetMgmtServiceImpl implements TargetMgmtService {

    @Resource
    private TargetMgmtMapper targetMgmtMapper;

    @Override
    public Long createTargetMgmt(TargetMgmtSaveReqVO createReqVO) {
        // 插入
        TargetMgmtDO targetMgmt = BeanUtils.toBean(createReqVO, TargetMgmtDO.class);
        targetMgmtMapper.insert(targetMgmt);

        // 返回
        return targetMgmt.getId();
    }

    @Override
    public void updateTargetMgmt(TargetMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateTargetMgmtExists(updateReqVO.getId());
        // 更新
        TargetMgmtDO updateObj = BeanUtils.toBean(updateReqVO, TargetMgmtDO.class);
        targetMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteTargetMgmt(Long id) {
        // 校验存在
        validateTargetMgmtExists(id);
        // 删除
        targetMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteTargetMgmtListByIds(List<Long> ids) {
        // 删除
        targetMgmtMapper.deleteByIds(ids);
        }


    private void validateTargetMgmtExists(Long id) {
        if (targetMgmtMapper.selectById(id) == null) {
            throw exception(TARGET_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public TargetMgmtDO getTargetMgmt(Long id) {
        return targetMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<TargetMgmtDO> getTargetMgmtPage(TargetMgmtPageReqVO pageReqVO) {
        return targetMgmtMapper.selectPage(pageReqVO);
    }

}