package com.vincent.personal.controller.admin;


import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import com.vincent.personal.controller.AbstractController;
import com.vincent.personal.controller.helper.ExceptionHelper;
import com.vincent.personal.exception.TipException;
import com.vincent.personal.modal.bo.RestResponseBo;
import com.vincent.personal.modal.vo.CommentVo;
import com.vincent.personal.modal.vo.CommentVoExample;
import com.vincent.personal.modal.vo.UserVo;
import com.vincent.personal.service.ICommentService;
import com.vincent.personal.util.MyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 评论模块
 *
 * Created with IDEA
 * author:vincent
 * Date:2018/11/14
 */
@Controller
@RequestMapping("admin/comments")
public class CommentController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Resource
    private ICommentService commentServcie;

    @GetMapping(value = "")
    public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                        @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
        UserVo users = this.user(request);
        CommentVoExample commentVoExample = new CommentVoExample();
        commentVoExample.setOrderByClause("coid desc");
        commentVoExample.createCriteria().andAuthorIdNotEqualTo(users.getUid());
        PageInfo<CommentVo> commentPaginator = commentServcie.getCommentsWithPage(commentVoExample, page, limit);
        request.setAttribute("comments", commentPaginator);
        return "admin/comment_list";
    }

    @PostMapping(value = "delete")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo delete(@RequestParam Integer coid) {
        try {

            CommentVo comments = commentServcie.getCommentById(coid);
            if (null == comments) {
                return RestResponseBo.fail("不存在该评论");
            }
            commentServcie.delete(coid, comments.getCid());
        } catch (Exception e) {
            String msg = "评论删除失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

    @PostMapping(value = "status")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo updateStatus(@RequestParam Integer coid, @RequestParam String status) {
        try {
            CommentVo comments = new CommentVo();
            comments.setCoid(coid);
            comments.setStatus(status);
            commentServcie.update(comments);
        } catch (Exception e) {
            String msg = "操作失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
        return RestResponseBo.ok();
    }

    @PostMapping(value = "")
    @ResponseBody
    @Transactional(rollbackFor = TipException.class)
    public RestResponseBo reply(@RequestParam Integer coid, @RequestParam String content, HttpServletRequest request) {
        if (null == coid || StringUtils.isBlank(content)) {
            return RestResponseBo.fail("请输入完整后评论");
        }

        if (content.length() > 2000) {
            return RestResponseBo.fail("请输入2000个字符以内的回复");
        }

        CommentVo temp = commentServcie.getCommentById(coid);
        if (null == temp) {
            return RestResponseBo.fail("不存在该评论");
        }

        UserVo users = this.user(request);
        content = MyUtils.cleanXSS(content);
        content = EmojiParser.parseToAliases(content);

        CommentVo comments = new CommentVo();
        comments.setAuthor(users.getUsername());
        comments.setAuthorId(users.getUid());
        comments.setCid(temp.getCid());
        comments.setIp(request.getRemoteAddr());
        comments.setUrl(users.getHomeUrl());
        comments.setContent(content);
        comments.setMail(users.getEmail());
        comments.setParent(coid);
        try {
            commentServcie.insertComment(comments);
            return RestResponseBo.ok();
        } catch (Exception e) {
            String msg = "回复失败";
            return ExceptionHelper.handlerException(logger, msg, e);
        }
    }
}

