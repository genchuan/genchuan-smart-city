package cn.iocoder.yudao.module.accessmgmt.service.faceaccess.facemgmt;

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

    // ==================== 基础 CRUD ====================

    @Override
    public PageResult<FaceMgmtRespVO> getFaceMgmtPage(FaceMgmtPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 userName/phone/company/accessArea/authStatus 动态条件筛选，按主键倒序
        PageResult<FaceMgmtDO> pageResult = faceMgmtMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, FaceMgmtRespVO.class);
    }

    @Override
    public FaceMgmtRespVO getFaceMgmt(Long id) {
        // 按主键查单条，不存在抛 FACE_MGMT_NOT_EXISTS 业务异常
        FaceMgmtDO entity = faceMgmtMapper.selectById(id);
        if (entity == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, FaceMgmtRespVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createFaceMgmt(FaceMgmtCreateReqVO createReqVO) {
        // 1. 手机号唯一性校验：查是否存在同手机号记录
        LambdaQueryWrapper<FaceMgmtDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FaceMgmtDO::getPhone, createReqVO.getPhone());
        FaceMgmtDO existByPhone = faceMgmtMapper.selectOne(wrapper);
        if (existByPhone != null) {
            throw exception(FACE_MGMT_PHONE_EXISTS);
        }
        // 2. VO → DO，初始 authStatus = "未授权"
        FaceMgmtDO entity = BeanUtils.toBean(createReqVO, FaceMgmtDO.class);
        entity.setAuthStatus("未授权");
        // 3. 插入数据库，createTime/creator 由 DefaultDBFieldHandler 自动填充
        faceMgmtMapper.insert(entity);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFaceMgmt(FaceMgmtUpdateReqVO reqVO) {
        // 1. 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // 2. 手机号唯一性校验：eq(phone) + ne(id)，排除当前记录自身
        LambdaQueryWrapper<FaceMgmtDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FaceMgmtDO::getPhone, reqVO.getPhone())
               .ne(FaceMgmtDO::getId, reqVO.getId());
        FaceMgmtDO existByPhone = faceMgmtMapper.selectOne(wrapper);
        if (existByPhone != null) {
            throw exception(FACE_MGMT_PHONE_EXISTS);
        }
        // 3. VO → DO，仅更新非空字段，updateTime/updater 由 DefaultDBFieldHandler 自动填充
        FaceMgmtDO updateObj = BeanUtils.toBean(reqVO, FaceMgmtDO.class);
        faceMgmtMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFaceMgmt(List<Long> ids) {
        // 批量删除（BaseDO 标记了 @TableLogic，实际执行 UPDATE deleted = 1）
        faceMgmtMapper.deleteByIds(ids);
    }

    // ==================== 人脸采集 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public FaceMgmtCollectRespVO collectFaceMgmt(FaceMgmtCollectReqVO reqVO) {
        // 1. 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // 2. 模拟人脸采集：随机生成 95.00 ~ 99.00 的验证准确率，保留两位小数
        BigDecimal verifyAccuracy = BigDecimal.valueOf(95.00 + Math.random() * 4.00)
                .setScale(2, RoundingMode.HALF_UP);
        // 3. 更新 verifyAccuracy 和 handleUser（当前登录用户昵称）
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setVerifyAccuracy(verifyAccuracy);
        updateObj.setHandleUser(SecurityFrameworkUtils.getLoginUserNickname());
        faceMgmtMapper.updateById(updateObj);
        // 4. 返回采集成功及准确率
        FaceMgmtCollectRespVO respVO = new FaceMgmtCollectRespVO();
        respVO.setSuccess(true);
        respVO.setVerifyAccuracy(verifyAccuracy);
        return respVO;
    }

    // ==================== 权限管理 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean configFaceMgmt(FaceMgmtConfigReqVO reqVO) {
        // 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // 仅更新 accessArea 和 authValidity，不修改 authStatus
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessArea(reqVO.getAccessArea());
        // 前端传入毫秒时间戳 → Instant → ZonedDateTime(东八区) → LocalDateTime
        updateObj.setAuthValidity(reqVO.getAuthValidity() != null ?
                Instant.ofEpochMilli(reqVO.getAuthValidity()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null);
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean authFaceMgmt(FaceMgmtAuthReqVO reqVO) {
        // 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // authStatus → "已授权"，同时设置 authValidity
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAuthStatus("已授权");
        updateObj.setAuthValidity(reqVO.getAuthValidity() != null ?
                Instant.ofEpochMilli(reqVO.getAuthValidity()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null);
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean renewFaceMgmt(FaceMgmtRenewReqVO reqVO) {
        // 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // authStatus → "已授权"，同时延长 authValidity
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAuthStatus("已授权");
        updateObj.setAuthValidity(reqVO.getAuthValidity() != null ?
                Instant.ofEpochMilli(reqVO.getAuthValidity()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null);
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean disableFaceMgmt(FaceMgmtDisableReqVO reqVO) {
        // 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // authStatus → "已禁用"
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAuthStatus("已禁用");
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    // ==================== 通行验证与通行 ====================

    @Override
    public FaceMgmtVerifyRespVO verifyFaceMgmt(FaceMgmtVerifyReqVO reqVO) {
        // 查询所有 authStatus = "已授权" 的记录作为候选集
        LambdaQueryWrapper<FaceMgmtDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FaceMgmtDO::getAuthStatus, "已授权");
        List<FaceMgmtDO> list = faceMgmtMapper.selectList(wrapper);

        // 候选集为空 → 验证不通过
        if (list.isEmpty()) {
            FaceMgmtVerifyRespVO respVO = new FaceMgmtVerifyRespVO();
            respVO.setPass(false);
            respVO.setMsg("未找到匹配的人脸信息");
            return respVO;
        }

        // 取第一条记录模拟匹配成功，返回人员姓名和通行区域
        FaceMgmtDO matched = list.get(0);
        FaceMgmtVerifyRespVO respVO = new FaceMgmtVerifyRespVO();
        respVO.setPass(true);
        respVO.setUserName(matched.getUserName());
        respVO.setAccessArea(matched.getAccessArea());
        respVO.setMsg("验证通过");
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean accessFaceMgmt(FaceMgmtAccessReqVO reqVO) {
        // 校验记录是否存在
        FaceMgmtDO exist = faceMgmtMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(FACE_MGMT_NOT_EXISTS);
        }
        // accessCount + 1（null 时从 1 开始），更新 lastAccessTime 为当前时间
        FaceMgmtDO updateObj = new FaceMgmtDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessCount(exist.getAccessCount() != null ? exist.getAccessCount() + 1 : 1);
        updateObj.setLastAccessTime(LocalDateTime.now());
        faceMgmtMapper.updateById(updateObj);
        return true;
    }

    // ==================== 统计态势 ====================

    @Override
    public FaceMgmtChartRespVO getFaceMgmtChart(Long startTime, Long endTime) {
        // 汇总统计：总数 / 授权数 / 过期数 / 未授权数
        FaceMgmtChartRespVO chartVO = faceMgmtMapper.selectChartStats(startTime, endTime);
        if (chartVO == null) {
            // 无数据时各统计量默认填 0
            chartVO = new FaceMgmtChartRespVO();
            chartVO.setTotalCount(0);
            chartVO.setAuthCount(0);
            chartVO.setExpiredCount(0);
            chartVO.setUnAuthCount(0);
        }
        // 区域授权分布
        chartVO.setAreaAuthList(faceMgmtMapper.selectAreaAuthList(startTime, endTime));
        // 时段通行趋势
        chartVO.setTimeAccessList(faceMgmtMapper.selectTimeAccessList(startTime, endTime));
        return chartVO;
    }

}
