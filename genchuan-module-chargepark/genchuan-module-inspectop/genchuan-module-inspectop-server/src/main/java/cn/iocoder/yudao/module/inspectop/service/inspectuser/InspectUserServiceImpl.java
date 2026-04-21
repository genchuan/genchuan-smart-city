package cn.iocoder.yudao.module.inspectop.service.inspectuser;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectuser.InspectUserDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.inspectuser.InspectUserMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 巡检人员 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class InspectUserServiceImpl implements InspectUserService {

    @Resource
    private InspectUserMapper inspectUserMapper;

    @Override
    public Long createInspectUser(InspectUserSaveReqVO createReqVO) {
        // 插入
        InspectUserDO inspectUser = BeanUtils.toBean(createReqVO, InspectUserDO.class);
        inspectUserMapper.insert(inspectUser);

        // 返回
        return inspectUser.getId();
    }

    @Override
    public void updateInspectUser(InspectUserSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectUserExists(updateReqVO.getId());
        // 更新
        InspectUserDO updateObj = BeanUtils.toBean(updateReqVO, InspectUserDO.class);
        inspectUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectUser(Long id) {
        // 校验存在
        validateInspectUserExists(id);
        // 删除
        inspectUserMapper.deleteById(id);
    }

    @Override
        public void deleteInspectUserListByIds(List<Long> ids) {
        // 删除
        inspectUserMapper.deleteByIds(ids);
        }


    private void validateInspectUserExists(Long id) {
        if (inspectUserMapper.selectById(id) == null) {
            throw exception(INSPECT_USER_NOT_EXISTS);
        }
    }

    @Override
    public InspectUserDO getInspectUser(Long id) {
        return inspectUserMapper.selectById(id);
    }

    @Override
    public PageResult<InspectUserDO> getInspectUserPage(InspectUserPageReqVO pageReqVO) {
        return inspectUserMapper.selectPage(pageReqVO);
    }

}