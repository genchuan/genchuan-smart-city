package cn.iocoder.yudao.module.studentmgmt.service.communicatemgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt.CommunicateMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.communicatemgmt.CommunicateMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 沟通管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CommunicateMgmtServiceImpl implements CommunicateMgmtService {

    @Resource
    private CommunicateMgmtMapper communicateMgmtMapper;

    @Override
    public Long createCommunicateMgmt(CommunicateMgmtSaveReqVO createReqVO) {
        // 插入
        CommunicateMgmtDO communicateMgmt = BeanUtils.toBean(createReqVO, CommunicateMgmtDO.class);
        communicateMgmtMapper.insert(communicateMgmt);

        // 返回
        return communicateMgmt.getId();
    }

    @Override
    public void updateCommunicateMgmt(CommunicateMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateCommunicateMgmtExists(updateReqVO.getId());
        // 更新
        CommunicateMgmtDO updateObj = BeanUtils.toBean(updateReqVO, CommunicateMgmtDO.class);
        communicateMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommunicateMgmt(Long id) {
        // 校验存在
        validateCommunicateMgmtExists(id);
        // 删除
        communicateMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteCommunicateMgmtListByIds(List<Long> ids) {
        // 删除
        communicateMgmtMapper.deleteByIds(ids);
        }


    private void validateCommunicateMgmtExists(Long id) {
        if (communicateMgmtMapper.selectById(id) == null) {
            throw exception(COMMUNICATE_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public CommunicateMgmtDO getCommunicateMgmt(Long id) {
        return communicateMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<CommunicateMgmtDO> getCommunicateMgmtPage(CommunicateMgmtPageReqVO pageReqVO) {
        return communicateMgmtMapper.selectPage(pageReqVO);
    }

}