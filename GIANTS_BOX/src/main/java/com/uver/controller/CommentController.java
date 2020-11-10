package com.uver.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.uver.cmn.Message;
import com.uver.service.CommentService;
import com.uver.vo.CommentVO;

@Controller("CommentController")
public class CommentController {
	private static final Logger LOG = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	CommentService commentservice;

	public CommentController() {
	}

	public CommentController(CommentService commentservice) {
		this.commentservice = commentservice;
	}

	@RequestMapping(value = "comment/comment_view.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String comment_view() {
		LOG.debug("comment_view");
		return "comment/comment_view";
	}

	@RequestMapping(value = "comment/doInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(CommentVO commentVO) throws ClassNotFoundException, SQLException {
		LOG.debug("==================");
		LOG.debug("=commentVO=" + commentVO);
		LOG.debug("==================");

		int flag = this.commentservice.doInsert(commentVO);
		LOG.debug("==================");
		LOG.debug("=flag=" + flag);
		LOG.debug("==================");

		// 메시지 처리 합니다
		Message message = new Message();
		message.setMsgId(flag + "");

		if (flag == 1) {
			message.setMsgContents(commentVO.getContent() + " 이 등록 되었습니다.");
		} else {
			message.setMsgContents(commentVO.getContent() + " 이 등록 실패되었습니다.");
		}

		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	@RequestMapping(value = "comment/doDelete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(CommentVO commentVO) throws ClassNotFoundException, SQLException {
		LOG.debug("==================");
		LOG.debug("=commentVO=" + commentVO);
		LOG.debug("==================");

		int flag = this.commentservice.doDelete(commentVO);
		LOG.debug("==================");
		LOG.debug("=flag=" + flag);
		LOG.debug("==================");

		// 메시지 처리 합니다
		Message message = new Message();
		message.setMsgId(flag + "");

		if (flag == 1) {
			message.setMsgContents(commentVO.getContent() + " 이 삭제 되었습니다.");
		} else {
			message.setMsgContents(commentVO.getContent() + " 이 삭제 실패되었습니다.");
		}

		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	@RequestMapping(value = "comment/doUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(CommentVO commentVO) throws ClassNotFoundException, SQLException {
		LOG.debug("==================");
		LOG.debug("=commentVO=" + commentVO);
		LOG.debug("==================");

		int flag = this.commentservice.doUpdate(commentVO);
		LOG.debug("==================");
		LOG.debug("=flag=" + flag);
		LOG.debug("==================");

		// 메시지 처리 합니다
		Message message = new Message();
		message.setMsgId(flag + "");

		if (flag == 1) {
			message.setMsgContents(commentVO.getContent() + " 이 수정 되었습니다.");
		} else {
			message.setMsgContents(commentVO.getContent() + " 이 수정 실패되었습니다.");
		}

		Gson gson = new Gson();
		String json = gson.toJson(message);
		LOG.debug("==================");
		LOG.debug("=json=" + json);
		LOG.debug("==================");

		return json;
	}

	@RequestMapping(value = "comment/doSelectList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentVO> doSelectList(CommentVO commentVO) throws ClassNotFoundException, SQLException {

		return commentservice.doSelectList(commentVO);
	}

	// 업데이트 테스트 못짜겠고 리스트는 컨트롤러 맞게 짯는지 모르겠음
}
