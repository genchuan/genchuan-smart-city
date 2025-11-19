package cn.iocoder.yudao.module.datacenter.service.evaluate.evalsubject;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo.EvalSubjectSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalsubject.EvalSubjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价主体管理 Service 接口
 *
 * @author zcq
 */
public interface EvalSubjectService {

    /**
     * 创建评价主体管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalSubject(@Valid EvalSubjectSaveReqVO createReqVO);

    /**
     * 更新评价主体管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalSubject(@Valid EvalSubjectSaveReqVO updateReqVO);

    /**
     * 删除评价主体管理
     *
     * @param id 编号
     */
    void deleteEvalSubject(Long id);

    /**
     * 获得评价主体管理
     *
     * @param id 编号
     * @return 评价主体管理
     */
    EvalSubjectDO getEvalSubject(Long id);

    /**
     * 获得评价主体管理分页
     *
     * @param pageReqVO 分页查询
     * @return 评价主体管理分页
     */
    PageResult<EvalSubjectDO> getEvalSubjectPage(EvalSubjectPageReqVO pageReqVO);

}