package cn.iocoder.yudao.module.studentmgmt.service.staymgmt;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.staymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.staymgmt.StayMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.staymgmt.StayMgmtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 留宿管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StayMgmtServiceImpl implements StayMgmtService {

    @Resource
    private StayMgmtMapper stayMgmtMapper;

    @Override
    public Long createStayMgmt(StayMgmtSaveReqVO createReqVO) {
        // 插入
        StayMgmtDO stayMgmt = BeanUtils.toBean(createReqVO, StayMgmtDO.class);
        stayMgmtMapper.insert(stayMgmt);

        // 返回
        return stayMgmt.getId();
    }

    @Override
    public void updateStayMgmt(StayMgmtSaveReqVO updateReqVO) {
        // 校验存在
        validateStayMgmtExists(updateReqVO.getId());
        // 更新
        StayMgmtDO updateObj = BeanUtils.toBean(updateReqVO, StayMgmtDO.class);
        stayMgmtMapper.updateById(updateObj);
    }

    @Override
    public void deleteStayMgmt(Long id) {
        // 校验存在
        validateStayMgmtExists(id);
        // 删除
        stayMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteStayMgmtListByIds(List<Long> ids) {
        // 删除
        stayMgmtMapper.deleteByIds(ids);
        }


    private void validateStayMgmtExists(Long id) {
        if (stayMgmtMapper.selectById(id) == null) {
            throw exception(STAY_MGMT_NOT_EXISTS);
        }
    }

    @Override
    public StayMgmtDO getStayMgmt(Long id) {
        return stayMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<StayMgmtDO> getStayMgmtPage(StayMgmtPageReqVO pageReqVO) {
        return stayMgmtMapper.selectPage(pageReqVO);
    }

}