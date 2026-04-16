package cn.iocoder.yudao.module.studentmgmt.service.clubmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtAuditReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtPageReqVO;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.ClubMgmtSaveReqVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.dal.mysql.clubmgmt.ClubMgmtMapper;
import cn.iocoder.yudao.module.studentmgmt.enums.ClubStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.CLUB_MGMT_NOT_EXISTS;
import static cn.iocoder.yudao.module.studentmgmt.enums.LogRecordConstants.*;

/**
 * 社团管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ClubMgmtServiceImpl implements ClubMgmtService {

    @Resource
    private ClubMgmtMapper clubMgmtMapper;

    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_CREATE_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_CREATE_SUCCESS)
    public Long createClubMgmt(ClubMgmtSaveReqVO createReqVO) {
        // 插入
        ClubMgmtDO clubMgmt = BeanUtils.toBean(createReqVO, ClubMgmtDO.class);
        clubMgmt.setStatus(ClubStatusEnum.Club_STATUS_0.getStatus());
        clubMgmt.setApplyTime(LocalDateTime.now());
        String loginUserNickname = SecurityFrameworkUtils.getLoginUserNickname();
        clubMgmt.setCreator(loginUserNickname);
        clubMgmtMapper.insert(clubMgmt);

        // 记录操作日志上下文
        LogRecordContext.putVariable("club", clubMgmt);
        // 返回
        return clubMgmt.getId();
    }

    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_UPDATE_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_UPDATE_SUCCESS)
    public void updateClubMgmt(ClubMgmtSaveReqVO updateReqVO) {
        // 校验存在
        ClubMgmtDO clubMgmtDO = validateClubMgmtExists(updateReqVO.getId());

        // 更新
        ClubMgmtDO updateObj = BeanUtils.toBean(updateReqVO, ClubMgmtDO.class);
        clubMgmtMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable("club", clubMgmtDO);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(clubMgmtDO, ClubMgmtSaveReqVO.class));

    }

    @Override
    public void deleteClubMgmt(Long id) {
        // 校验存在
        validateClubMgmtExists(id);
        // 删除
        clubMgmtMapper.deleteById(id);
    }

    @Override
        public void deleteClubMgmtListByIds(List<Long> ids) {
        // 删除
        clubMgmtMapper.deleteByIds(ids);
        }


    private ClubMgmtDO validateClubMgmtExists(Long id) {
        ClubMgmtDO clubMgmtDO = clubMgmtMapper.selectById(id);
        if ( clubMgmtDO == null) {
            throw exception(CLUB_MGMT_NOT_EXISTS);
        }
        return clubMgmtDO;
    }

    @Override
    public ClubMgmtDO getClubMgmt(Long id) {
        return clubMgmtMapper.selectById(id);
    }

    @Override
    public PageResult<ClubMgmtDO> getClubMgmtPage(ClubMgmtPageReqVO pageReqVO) {
        return clubMgmtMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = CLUB_TYPE, subType = CLUB_AUDIT_SUB_TYPE, bizNo = "{{#club.id}}",
            success = CLUB_AUDIT_SUCCESS)
    public boolean audit(ClubMgmtAuditReqVO reqVO) {
        ClubMgmtDO clubMgmtDO = clubMgmtMapper.selectById(reqVO.getId());
        if ( clubMgmtDO == null) {
            throw exception(CLUB_MGMT_NOT_EXISTS);
        }
        clubMgmtDO.setAuditTime(LocalDateTime.now());
        // 获取当前用户
        String username = SecurityFrameworkUtils.getLoginUserNickname();
        clubMgmtDO.setAuditUser(username);
        clubMgmtDO.setStatus(reqVO.getStatus());

        int i = clubMgmtMapper.updateById(clubMgmtDO);
        if (i < 1 ) {
            return false;
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("club", clubMgmtDO);
        return true;

    }

}