package cn.iocoder.yudao.module.studentmgmt.service.bedmgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.bedmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.bedmgmt.BedMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.bedmgmt.BedMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 床位管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BedMgmtServiceImpl implements BedMgmtService {

    @Resource
    private BedMgmtMapper bedMgmtMapper;

    @Override
    public Long createBedMgmt(BedMgmtSaveReqVO createReqVO) {
        // 插入
        BedMgmtDO bedMgmt = BeanUtils.toBean(createReqVO, BedMgmtDO.class);
        bedMgmtMapper.insert(bedMgmt);

        // 返回
        return bedMgmt.getId();
    }

    @Override
    public void updateBedMgmt(BedMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateBedMgmtExists(updateReqVO.getId());
        // 更新
        BedMgmtDO updateObj = BeanUtils.toBean(updateReqVO, BedMgmtDO.class);
        bedMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBedMgmt(Long id) {
        // 校验存在
        validateBedMgmtExists(id);
        // 删除
        bedMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteBedMgmtListByIds(List<Long> ids) {
        // 删除
        bedMgmtMapper.deleteByIds(ids);
        }


    private void validateBedMgmtExists(Long id) {
        if (bedMgmtMapper.selectById(id) == null) {
            throw exception(BED_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public BedMgmtDO getBedMgmt(Long id) {
        return bedMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<BedMgmtDO> getBedMgmtPage(BedMgmtPageReqVO pageReqVO) {
        return bedMgmtMapper.selectPage(pageReqVO);
    }

}