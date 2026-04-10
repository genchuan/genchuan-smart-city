package cn.iocoder.yudao.module.studentmgmt.service.comparemgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.comparemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.comparemgmt.CompareMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.comparemgmt.CompareMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 评比管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompareMgmtServiceImpl implements CompareMgmtService {

    @Resource
    private CompareMgmtMapper compareMgmtMapper;

    @Override
    public Long createCompareMgmt(CompareMgmtSaveReqVO createReqVO) {
        // 插入
        CompareMgmtDO compareMgmt = BeanUtils.toBean(createReqVO, CompareMgmtDO.class);
        compareMgmtMapper.insert(compareMgmt);

        // 返回
        return compareMgmt.getId();
    }

    @Override
    public void updateCompareMgmt(CompareMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateCompareMgmtExists(updateReqVO.getId());
        // 更新
        CompareMgmtDO updateObj = BeanUtils.toBean(updateReqVO, CompareMgmtDO.class);
        compareMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompareMgmt(Long id) {
        // 校验存在
        validateCompareMgmtExists(id);
        // 删除
        compareMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteCompareMgmtListByIds(List<Long> ids) {
        // 删除
        compareMgmtMapper.deleteByIds(ids);
        }


    private void validateCompareMgmtExists(Long id) {
        if (compareMgmtMapper.selectById(id) == null) {
            throw exception(COMPARE_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public CompareMgmtDO getCompareMgmt(Long id) {
        return compareMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<CompareMgmtDO> getCompareMgmtPage(CompareMgmtPageReqVO pageReqVO) {
        return compareMgmtMapper.selectPage(pageReqVO);
    }

}