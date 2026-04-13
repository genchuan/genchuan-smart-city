package cn.iocoder.yudao.module.studentmgmt.service.promotemgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.promotemgmt.PromoteMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.promotemgmt.PromoteMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 宣传管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PromoteMgmtServiceImpl implements PromoteMgmtService {

    @Resource
    private PromoteMgmtMapper promoteMgmtMapper;

    @Override
    public Long createPromoteMgmt(PromoteMgmtSaveReqVO createReqVO) {
        // 插入
        PromoteMgmtDO promoteMgmt = BeanUtils.toBean(createReqVO, PromoteMgmtDO.class);
        promoteMgmtMapper.insert(promoteMgmt);

        // 返回
        return promoteMgmt.getId();
    }

    @Override
    public void updatePromoteMgmt(PromoteMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validatePromoteMgmtExists(updateReqVO.getId());
        // 更新
        PromoteMgmtDO updateObj = BeanUtils.toBean(updateReqVO, PromoteMgmtDO.class);
        promoteMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deletePromoteMgmt(Long id) {
        // 校验存在
        validatePromoteMgmtExists(id);
        // 删除
        promoteMgmtMapper.deleteById(id);
    }

    @Override
        public void deletePromoteMgmtListByIds(List<Long> ids) {
        // 删除
        promoteMgmtMapper.deleteByIds(ids);
        }


    private void validatePromoteMgmtExists(Long id) {
        if (promoteMgmtMapper.selectById(id) == null) {
            throw exception(PROMOTE_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public PromoteMgmtDO getPromoteMgmt(Long id) {
        return promoteMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<PromoteMgmtDO> getPromoteMgmtPage(PromoteMgmtPageReqVO pageReqVO) {
        return promoteMgmtMapper.selectPage(pageReqVO);
    }

}