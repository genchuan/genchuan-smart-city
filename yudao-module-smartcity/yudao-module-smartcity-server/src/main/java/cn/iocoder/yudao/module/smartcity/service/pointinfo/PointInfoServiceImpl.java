package cn.iocoder.yudao.module.smartcity.service.pointinfo;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.pointinfo.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.pointinfo.PointInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.pointinfo.PointInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 监测点位 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class PointInfoServiceImpl implements PointInfoService {

    @Resource
    private PointInfoMapper pointInfoMapper;

    @Override
    public Long createPointInfo(PointInfoSaveReqVO createReqVO) {
        // 插入
        PointInfoDO pointInfo = BeanUtils.toBean(createReqVO, PointInfoDO.class);
        pointInfoMapper.insert(pointInfo);
        // 返回
        return pointInfo.getId();
    }

    @Override
    public void updatePointInfo(PointInfoSaveReqVO updateReqVO) {
        // 校验存在
        validatePointInfoExists(updateReqVO.getId());
        // 更新
        PointInfoDO updateObj = BeanUtils.toBean(updateReqVO, PointInfoDO.class);
        pointInfoMapper.updateById(updateObj);
    }

    @Override
    public void deletePointInfo(Long id) {
        // 校验存在
        validatePointInfoExists(id);
        // 删除
        pointInfoMapper.deleteById(id);
    }

    private void validatePointInfoExists(Long id) {
        if (pointInfoMapper.selectById(id) == null) {
            throw exception(POINT_INFO_NOT_EXISTS);
        }
    }

    @Override
    public PointInfoDO getPointInfo(Long id) {
        return pointInfoMapper.selectById(id);
    }

    @Override
    public PageResult<PointInfoDO> getPointInfoPage(PointInfoPageReqVO pageReqVO) {
        return pointInfoMapper.selectPage(pageReqVO);
    }

}