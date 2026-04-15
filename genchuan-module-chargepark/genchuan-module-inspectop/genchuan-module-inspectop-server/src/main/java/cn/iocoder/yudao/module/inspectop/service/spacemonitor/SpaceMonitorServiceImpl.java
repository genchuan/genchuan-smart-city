package cn.iocoder.yudao.module.inspectop.service.spacemonitor;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.spacemonitor.SpaceMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.spacemonitor.SpaceMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 车位状态监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class SpaceMonitorServiceImpl implements SpaceMonitorService {

    @Resource
    private SpaceMonitorMapper spaceMonitorMapper;

    @Override
    public Long createSpaceMonitor(SpaceMonitorSaveReqVO createReqVO) {
        // 插入
        SpaceMonitorDO spaceMonitor = BeanUtils.toBean(createReqVO, SpaceMonitorDO.class);
        spaceMonitorMapper.insert(spaceMonitor);

        // 返回
        return spaceMonitor.getId();
    }

    @Override
    public void updateSpaceMonitor(SpaceMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateSpaceMonitorExists(updateReqVO.getId());
        // 更新
        SpaceMonitorDO updateObj = BeanUtils.toBean(updateReqVO, SpaceMonitorDO.class);
        spaceMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpaceMonitor(Long id) {
        // 校验存在
        validateSpaceMonitorExists(id);
        // 删除
        spaceMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteSpaceMonitorListByIds(List<Long> ids) {
        // 删除
        spaceMonitorMapper.deleteByIds(ids);
        }


    private void validateSpaceMonitorExists(Long id) {
        if (spaceMonitorMapper.selectById(id) == null) {
            throw exception(SPACE_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public SpaceMonitorDO getSpaceMonitor(Long id) {
        return spaceMonitorMapper.selectById(id);
    }

//    @Override
//    public PageResult<SpaceMonitorDO> getSpaceMonitorPage(SpaceMonitorPageReqVO pageReqVO) {
//        return spaceMonitorMapper.selectPage(pageReqVO);
//    }

    @Override
    public List<SpaceMonitorRespVO> getSpaceMonitorPage(SpaceMonitorPageReqVO pageReqVO) {
        // 创建MyBatis-Plus的分页对象
        Page<SpaceMonitorRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的自定义关联查询方法
        List<SpaceMonitorRespVO> pageResult = spaceMonitorMapper.selectPageWithJoin(mpPage, pageReqVO);

        return pageResult;
    }

}