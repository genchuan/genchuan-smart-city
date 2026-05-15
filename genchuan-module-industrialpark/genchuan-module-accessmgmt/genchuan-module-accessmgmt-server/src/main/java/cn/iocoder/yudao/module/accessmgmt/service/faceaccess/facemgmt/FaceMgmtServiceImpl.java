package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.facemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.faceaccess.facemgmt.FaceMgmtDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.faceaccess.facemgmt.FaceMgmtMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;


/**
 * 人脸信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FaceMgmtServiceImpl implements FaceMgmtService {

    @Resource
    private FaceMgmtMapper faceMgmtMapper;

    @Override
    public PageResult<FaceMgmtRespVO> getFaceMgmtPage(FaceMgmtPageReqVO pageReqVO) {
        PageResult<FaceMgmtDO> pageResult = faceMgmtMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, FaceMgmtRespVO.class);
    }

    @Override
    public FaceMgmtRespVO getFaceMgmt(Long id) {
        FaceMgmtDO entity = faceMgmtMapper.selectById(id);
        if (entity == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, FaceMgmtRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createFaceMgmt(FaceMgmtCreateReqVO createReqVO) {
        LambdaQueryWrapper<FaceMgmtDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FaceMgmtDO::getPhone, createReqVO.getPhone());
        FaceMgmtDO existByPhone = faceMgmtMapper.selectOne(wrapper);
        if (existByPhone != null) {
            throw exception(FACE_MGMT_PHONE_EXISTS);
        }

        FaceMgmtDO entity = BeanUtils.toBean(createReqVO, FaceMgmtDO.class);
        entity.setAuthStatus("未授权");
        faceMgmtMapper.insert(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFaceMgmt(FaceMgmtUpdateReqVO updateReqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(updateReqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }

        LambdaQueryWrapper<FaceMgmtDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FaceMgmtDO::getPhone, updateReqVO.getPhone())
               .ne(FaceMgmtDO::getId, updateReqVO.getId());
        FaceMgmtDO existByPhone = faceMgmtMapper.selectOne(wrapper);
        if (existByPhone != null) {
            throw exception(FACE_MGMT_PHONE_EXISTS);
        }

        FaceMgmtDO updateObj = BeanUtils.toBean(updateReqVO, FaceMgmtDO.class);
        faceMgmtMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFaceMgmt(List<Long> ids) {
        faceMgmtMapper.deleteByIds(ids);
    }

    @Override
    public List<FaceMgmtRespVO> getFaceMgmtList(FaceMgmtPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<FaceMgmtDO> pageResult = faceMgmtMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), FaceMgmtRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FaceMgmtCollectRespVO collectFaceMgmt(FaceMgmtCollectReqVO reqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }

        // 模拟人脸采集：实际应调用人脸识别算法评估图片质量，此处随机生成准确率
        BigDecimal verifyAccuracy = BigDecimal.valueOf(95.00 + Math.random() * 4.00)
                .setScale(2, RoundingMode.HALF_UP);

        // 更新数据库：保存采集结果及操作人
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setVerifyAccuracy(verifyAccuracy);
        updateObj.setHandleUser(SecurityFrameworkUtils.getLoginUserNickname());
        faceMgmtMapper.updateById(updateObj);

        FaceMgmtCollectRespVO respVO = new FaceMgmtCollectRespVO();
        respVO.setSuccess(true);
        respVO.setVerifyAccuracy(verifyAccuracy);
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean configFaceMgmt(FaceMgmtConfigReqVO reqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessArea(reqVO.getAccessArea());
        updateObj.setAuthValidity(reqVO.getAuthValidity() != null ?
                Instant.ofEpochSecond(reqVO.getAuthValidity()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null);
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    public FaceMgmtVerifyRespVO verifyFaceMgmt(FaceMgmtVerifyReqVO reqVO) {
        FaceMgmtVerifyRespVO respVO = new FaceMgmtVerifyRespVO();
        // 查找已授权的人脸信息进行匹配
        LambdaQueryWrapper<FaceMgmtDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FaceMgmtDO::getAuthStatus, "已授权");
        List<FaceMgmtDO> list = faceMgmtMapper.selectList(wrapper);

        if (list.isEmpty()) {
            respVO.setPass(false);
            respVO.setMsg("未找到匹配的人脸信息");
            return respVO;
        }

        // 模拟：取第一个匹配的记录
        FaceMgmtDO matched = list.get(0);
        respVO.setPass(true);
        respVO.setUserName(matched.getUserName());
        respVO.setAccessArea(matched.getAccessArea());
        respVO.setMsg("验证通过");
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean accessFaceMgmt(FaceMgmtAccessReqVO reqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessCount(exist.getAccessCount() != null ? exist.getAccessCount() + 1 : 1);
        updateObj.setLastAccessTime(LocalDateTime.now());
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disableFaceMgmt(FaceMgmtDisableReqVO reqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAuthStatus("已禁用");
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean authFaceMgmt(FaceMgmtAuthReqVO reqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAuthStatus("已授权");
        updateObj.setAuthValidity(reqVO.getAuthValidity() != null ?
                Instant.ofEpochSecond(reqVO.getAuthValidity()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null);
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean renewFaceMgmt(FaceMgmtRenewReqVO reqVO) {
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAuthStatus("已授权");
        updateObj.setAuthValidity(reqVO.getAuthValidity() != null ?
                Instant.ofEpochSecond(reqVO.getAuthValidity()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null);
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    public FaceMgmtChartRespVO getFaceMgmtChart(Long startTime, Long endTime) {
        FaceMgmtChartRespVO chartVO = faceMgmtMapper.selectChartStats(startTime, endTime);
        if (chartVO == null) {
            chartVO = new FaceMgmtChartRespVO();
            chartVO.setTotalCount(0);
            chartVO.setAuthCount(0);
            chartVO.setExpiredCount(0);
            chartVO.setUnAuthCount(0);
        }
        chartVO.setAreaAuthList(faceMgmtMapper.selectAreaAuthList(startTime, endTime));
        chartVO.setTimeAccessList(faceMgmtMapper.selectTimeAccessList(startTime, endTime));
        return chartVO;
    }

}
