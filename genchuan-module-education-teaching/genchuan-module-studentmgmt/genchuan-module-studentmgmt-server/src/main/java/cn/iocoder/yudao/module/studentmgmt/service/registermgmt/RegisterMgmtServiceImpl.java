package cn.iocoder.yudao.module.studentmgmt.service.registermgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.registermgmt.RegisterMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.registermgmt.RegisterMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 报名管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RegisterMgmtServiceImpl implements RegisterMgmtService {

    @Resource
    private RegisterMgmtMapper registerMgmtMapper;

    @Override
    public Long createRegisterMgmt(RegisterMgmtSaveReqVO createReqVO) {
        // 插入
        RegisterMgmtDO registerMgmt = BeanUtils.toBean(createReqVO, RegisterMgmtDO.class);
        registerMgmtMapper.insert(registerMgmt);

        // 返回
        return registerMgmt.getId();
    }

    @Override
    public void updateRegisterMgmt(RegisterMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateRegisterMgmtExists(updateReqVO.getId());
        // 更新
        RegisterMgmtDO updateObj = BeanUtils.toBean(updateReqVO, RegisterMgmtDO.class);
        registerMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteRegisterMgmt(Long id) {
        // 校验存在
        validateRegisterMgmtExists(id);
        // 删除
        registerMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteRegisterMgmtListByIds(List<Long> ids) {
        // 删除
        registerMgmtMapper.deleteByIds(ids);
        }


    private void validateRegisterMgmtExists(Long id) {
        if (registerMgmtMapper.selectById(id) == null) {
            throw exception(REGISTER_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public RegisterMgmtDO getRegisterMgmt(Long id) {
        return registerMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<RegisterMgmtDO> getRegisterMgmtPage(RegisterMgmtPageReqVO pageReqVO) {
        return registerMgmtMapper.selectPage(pageReqVO);
    }

}