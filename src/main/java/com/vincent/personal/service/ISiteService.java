package com.vincent.personal.service;

import com.vincent.personal.dto.MetaDto;
import com.vincent.personal.modal.bo.ArchiveBo;
import com.vincent.personal.modal.bo.BackResponseBo;
import com.vincent.personal.modal.bo.StaticticsBo;
import com.vincent.personal.modal.vo.CommentVo;
import com.vincent.personal.modal.vo.ContentVo;

import java.util.List;

/**
 *
 * 站点服务
 *
 * Created with IDEA
 * author:vincent
 * Date:2018/11/7
 */
public interface ISiteService {
    /**
     * 最新收到的评论
     *
     * @param limit
     * @return
     */
    List<CommentVo> recentComments(int limit);

    /**
     * 最新发表的文章
     *
     * @param limit
     * @return
     */
    List<ContentVo> recentContents(int limit);

    /**
     * 查询一条评论
     *
     * @param coid
     * @return
     */
    CommentVo getComment(Integer coid);

    /**
     * 系统备份
     *
     * @param bk_type
     * @param bk_path
     * @param fmt
     * @return
     * @throws Exception
     */
    BackResponseBo backup(String bk_type, String bk_path, String fmt) throws Exception;

    /**
     * 获取后台统计数据
     *
     * @return
     */
    StaticticsBo getStatistics();

    /**
     * 查看文章归档
     *
     * @return
     */
    List<ArchiveBo> getArchives();

    /**
     * 获取分类 标签列表
     *
     * @param type
     * @param orderBy
     * @param limit
     * @return
     */
    List<MetaDto> metas(String type, String orderBy, int limit);
}

