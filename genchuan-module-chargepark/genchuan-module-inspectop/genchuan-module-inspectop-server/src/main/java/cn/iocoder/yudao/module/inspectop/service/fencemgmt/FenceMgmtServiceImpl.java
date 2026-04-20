package cn.iocoder.yudao.module.inspectop.service.fencemgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.fencemgmt.FenceMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.fencemgmt.FenceMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 电子围栏 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class FenceMgmtServiceImpl implements FenceMgmtService {

    @Resource
    private FenceMgmtMapper fenceMgmtMapper;

    @Override
    public Long createFenceMgmt(FenceMgmtSaveReqVO createReqVO) {
        // 插入
        FenceMgmtDO fenceMgmt = BeanUtils.toBean(createReqVO, FenceMgmtDO.class);
        fenceMgmtMapper.insert(fenceMgmt);

        // 返回
        return fenceMgmt.getId();
    }

    @Override
    public void updateFenceMgmt(FenceMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateFenceMgmtExists(updateReqVO.getId());
        // 更新
        FenceMgmtDO updateObj = BeanUtils.toBean(updateReqVO, FenceMgmtDO.class);
        fenceMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteFenceMgmt(Long id) {
        // 校验存在
        validateFenceMgmtExists(id);
        // 删除
        fenceMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteFenceMgmtListByIds(List<Long> ids) {
        // 删除
        fenceMgmtMapper.deleteByIds(ids);
        }


    private void validateFenceMgmtExists(Long id) {
        if (fenceMgmtMapper.selectById(id) == null) {
            throw exception(FENCE_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public FenceMgmtDO getFenceMgmt(Long id) {
        return fenceMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<FenceMgmtDO> getFenceMgmtPage(FenceMgmtPageReqVO pageReqVO) {
        return fenceMgmtMapper.selectPage(pageReqVO);
    }

}