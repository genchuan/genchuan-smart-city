package cn.iocoder.yudao.module.studentmgmt.service.accessapply;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.accessapply.AccessApplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.accessapply.AccessApplyMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 出入申请 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AccessApplyServiceImpl implements AccessApplyService {

    @Resource
    private AccessApplyMapper accessApplyMapper;

    @Override
    public Long createAccessApply(AccessApplySaveReqVO createReqVO) {
        // 插入
        AccessApplyDO accessApply = BeanUtils.toBean(createReqVO, AccessApplyDO.class);
        accessApplyMapper.insert(accessApply);

        // 返回
        return accessApply.getId();
    }

    @Override
    public void updateAccessApply(AccessApplySaveReqVO updateReqVO) {
        // 校验存在
        validateAccessApplyExists(updateReqVO.getId());
        // 更新
        AccessApplyDO updateObj = BeanUtils.toBean(updateReqVO, AccessApplyDO.class);
        accessApplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteAccessApply(Long id) {
        // 校验存在
        validateAccessApplyExists(id);
        // 删除
        accessApplyMapper.deleteById(id);
    }

    @Override
        public void deleteAccessApplyListByIds(List<Long> ids) {
        // 删除
        accessApplyMapper.deleteByIds(ids);
        }


    private void validateAccessApplyExists(Long id) {
        if (accessApplyMapper.selectById(id) == null) {
            throw exception(ACCESS_APPLY_NOT_EXISTS);
        }
    }

    @Override
    public AccessApplyDO getAccessApply(Long id) {
        return accessApplyMapper.selectById(id);
    }

    @Override
    public PageResult<AccessApplyDO> getAccessApplyPage(AccessApplyPageReqVO pageReqVO) {
        return accessApplyMapper.selectPage(pageReqVO);
    }

}