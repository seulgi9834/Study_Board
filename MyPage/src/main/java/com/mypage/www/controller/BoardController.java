package com.mypage.www.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypage.www.service.BoardService;
import com.mypage.www.service.ReplyService;
import com.mypage.www.vo.BoardVo;
import com.mypage.www.vo.PageVo;
import com.mypage.www.vo.ReplyVo;
import com.mypage.www.vo.UserVo;

@Controller
@RequestMapping(value = "board")
public class BoardController {

	@Autowired
	BoardService service;

	@Autowired
	ReplyService replyService;

	@Resource(name = "uploadPath")
	private String uploadPath;

	/**
	 * 게시판 리스트 읽기
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	String boardList(Model model) {
		model.addAttribute("list", service.selectBoard());
		return "board/list";
	}


	/*
	 * @RequestMapping(value = "/page", method=RequestMethod.GET) void
	 * getListPage(Model model, @RequestParam("num") int num) {
	 *
	 * int count = service.countBoard();
	 * int postNum = 10;
	 * int pageNum =(int)Math.ceil((double)count/postNum);
	 * int displayPost = (num-1)*postNum;
	 * int pageNum_cnt = 10;
	 * int endPageNum = (int)(Math.ceil((double)num /(double)pageNum_cnt)*pageNum_cnt);
	 * int startPageNum = endPageNum -(pageNum_cnt -1);
	 * int endPageNum_tmp = (int)(Math.ceil((double)count /(double)pageNum_cnt));
	 * if(endPageNum>endPageNum_tmp) { endPageNum =endPageNum_tmp; }
	 * boolean prev = startPageNum == 1? false : true;
	 * boolean next = endPageNum *pageNum_cnt >= count ? false : true;
	 * List list = null;
	 * list = service.pageBoard(displayPost, postNum);
	 * model.addAttribute("list", list);
	 * model.addAttribute("pageNum", pageNum);
	 * model.addAttribute("startPageNum", startPageNum);
	 * model.addAttribute("endPageNum", endPageNum);
	 * model.addAttribute("prev", prev);
	 * model.addAttribute("next", next);
	 * model.addAttribute("select",num); }
	 */

	@RequestMapping(value="/page", method = RequestMethod.GET)
	public void getListSearchPage(Model model, @RequestParam("num") int num,
			@RequestParam(value= "searchType", required = false, defaultValue="TITLE") String searchType,
			@RequestParam(value="keyword", required = false, defaultValue ="") String keyword) {
		PageVo page = new PageVo();

		page.setNum(num);
		//page.setCount(service.countBoard());
		page.setCount(service.searchCount(searchType, keyword));

		// 검색 타입과 검색어
		page.setSearchType(searchType);
		page.setKeyword(keyword);

		List<BoardVo> list = null;
		list = service.searchBoard(page.getDisplayPost(), page.getPostNum(), searchType, keyword);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);
//		model.addAttribute("searchType", searchType);
//		model.addAttribute("keyword", keyword);
	}

	/**
	 * 게시글 작성 페이지
	 *
	 * @return
	 */
	@RequestMapping(value = "write")
	String boardWrite() {
		return "board/write";
	}

	/**
	 * 작성된 게시글 등록
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "regist", method = RequestMethod.POST)
	String boardRegist(BoardVo vo, MultipartFile files) throws IOException, Exception {

//		String imgUploadPath = uploadPath + File.separator + "ckUpload";
//		String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
//		String fileName = null;
//
//		if (files != null) {
//			fileName = UploadFileUtils.fileUpload(imgUploadPath, files.getOriginalFilename(), files.getBytes(), ymdPath);
//		} else {
//			fileName = uploadPath + File.separator + "images" + File.separator + "none.png";
//		}
//
//		vo.setFileName(fileName);
//		vo.setThumFileName(
//				File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
		service.insertBoard(vo);
		return "redirect:/board";
	}

	/**
	 * 원하는 게시물 읽기
	 *
	 * @param boardNo
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/read", method=RequestMethod.GET)
	String read(@RequestParam("boardNo") int boardNo,  @RequestParam("page") int page,Model model, BoardVo vo, @ModelAttribute("search") PageVo search) {
		model.addAttribute("list", service.selectBoardReader(vo));
		model.addAttribute("search", search);
		model.addAttribute("page", page);
//		List<ReplyVo> reply = replyService.selectReply(boardNo);
//		model.addAttribute("reply", reply);
		return "board/read";
	}

	@ResponseBody
	@RequestMapping(value="/read/replyList", method =RequestMethod.GET)
	public List<ReplyVo> getReplyList(@RequestParam("boardNo") int boardNo){
	List<ReplyVo> reply = replyService.selectReply(boardNo);
	return reply;
	}

	/**
	 * 게시물 수정 페이지
	 *
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	String boardModify(@RequestParam("boardNo") String boardNo, BoardVo vo, Model model) {
		model.addAttribute("list", service.selectBoardReader(vo));

		return "board/modify";
	}

	/**
	 * 수정된 게시물 갱신
	 *
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	String boardUpdate(BoardVo vo, @ModelAttribute("search") PageVo search, RedirectAttributes rttr) {
		service.modifyBoard(vo);
		rttr.addAttribute("page",search.getNum());
		rttr.addAttribute("searchType", search.getSearchType());
		rttr.addAttribute("keyword",search.getKeyword());
		return "redirect:/board/page";
	}

	/**
	 * 게시물 삭제
	 *
	 * @param boardNo
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	String boardDelete(@RequestParam("boardNo") String boardNo, BoardVo vo, @ModelAttribute("search") PageVo search, RedirectAttributes rttr) {
		service.deleteBoard(vo);
		rttr.addAttribute("page",search.getNum());
		rttr.addAttribute("searchType", search.getSearchType());
		rttr.addAttribute("keyword",search.getKeyword());
		return "redirect:/board/page";
	}

//	/**
//	 * 댓글 작성
//	 * @param vo
//	 * @param rttr
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value="/replyWrite", method=RequestMethod.POST)
//	String replyWrite(ReplyVo vo, RedirectAttributes rttr) throws Exception{
//		replyService.insertReply(vo);
//		return "redirect:/board/read?boardNo="+vo.getBoardNo();
//	}

	@ResponseBody
	@RequestMapping(value="/read/replyRegist", method = RequestMethod.POST)
	public void replyRegist(ReplyVo replyVo, HttpSession session) {
//		UserVo user = (UserVo)session.getAttribute("user");
		System.out.println(replyVo.toString());
		replyService.insertReply(replyVo);
	}

//	/**
//	 * 댓글 수정
//	 * @param vo
//	 * @param rttr
//	 * @return
//	 */
//	@RequestMapping(value="/updateReply", method = RequestMethod.POST)
//	String updateReply(ReplyVo vo) {
//		replyService.updateReply(vo);
//		return "redirect:/board/read?boardNo="+vo.getBoardNo();
//	}

//	/**
//	 * 댓글 삭제
//	 * @param replyNo
//	 * @param vo
//	 * @return
//	 */
//	@RequestMapping(value="/read/deleteReply", method = RequestMethod.POST)
//	String deleteReply(@RequestParam("replyNo") String replyNo, ReplyVo vo) {
//		System.out.println("READ");
//		replyService.deleteReply(vo);
//		return "redirect:/board/read?boardNo="+vo.getBoardNo();
//	}

	// 상품 소감(댓글) 삭제
	@ResponseBody
	@RequestMapping(value = "/read/deleteReply", method = RequestMethod.POST)
	public int getReplyList(ReplyVo replyVo, HttpSession session) throws Exception {

	 int result = 0;
	 replyService.deleteReply(replyVo);

	  result = 1;

	 return result;
	}

	// 댓글 수정
	@ResponseBody
	@RequestMapping(value="/read/updateReply", method = RequestMethod.POST)
	public int updateReply(ReplyVo replyVo, HttpSession session) throws Exception{
		int result = 0;
		replyService.updateReply(replyVo);
		System.out.println(replyVo.getReplyComment());
		System.out.println(replyVo.getReplyNo());
		result = 1;
		return result;
	}
	/*
	 * // ck 에디터에서 파일 업로드
	 *
	 * @RequestMapping(value = "/ckUpload", method = RequestMethod.POST) public void
	 * postCKEditorImgUpload(HttpServletRequest req, HttpServletResponse res,
	 *
	 * @RequestParam MultipartFile upload) throws Exception {
	 *
	 * // 랜덤 문자 생성 UUID uid = UUID.randomUUID();
	 *
	 * OutputStream out = null; PrintWriter printWriter = null;
	 *
	 * // 인코딩 res.setCharacterEncoding("utf-8");
	 * res.setContentType("text/html;charset=utf-8");
	 *
	 * try {
	 *
	 * String fileName = upload.getOriginalFilename(); // 파일 이름 가져오기 byte[] bytes =
	 * upload.getBytes();
	 *
	 * // 업로드 경로 String ckUploadPath = uploadPath + File.separator + "ckUpload" +
	 * File.separator + uid + "_" + fileName;
	 *
	 * out = new FileOutputStream(new File(ckUploadPath)); out.write(bytes);
	 * out.flush(); // out에 저장된 데이터를 전송하고 초기화
	 *
	 * String callback = req.getParameter("CKEditorFuncNum"); printWriter =
	 * res.getWriter(); String fileUrl = "/resources/ckUpload/" + uid + "_" +
	 * fileName; // 업로드시 메시지 출력 printWriter.println("{\"filename\" : \""
	 * +fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
	 * printWriter.flush();
	 *
	 * } catch (IOException e) { e.printStackTrace(); } finally { try { if(out !=
	 * null) { out.close(); } if(printWriter != null) { printWriter.close(); } }
	 * catch(IOException e) { e.printStackTrace(); } }
	 *
	 * return; }
	 */

	/**
	 * * @param multiFile
	 * * @param request
	 * * @return
	 * * @throws Exception
	 * https://mine-it-record.tistory.com/277 따라서 진행함
	 * */
	@RequestMapping(value="/imageUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile , @RequestParam MultipartFile upload)
			throws Exception{
		// 랜덤 문자 생성
		UUID uid = UUID.randomUUID();
		OutputStream out = null;
		PrintWriter printWriter = null;
		//인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try{
			//파일 이름 가져오기
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			//이미지 경로 생성
			String path = "C:\\Users\\cspi\\git\\Study_Board\\MyPage\\src\\main\\webapp\\resources\\" + "ckUpload/";
			// fileDir는 전역 변수라 그냥 이미지 경로 설정해주면 된다.
			String ckUploadPath = path + uid + "_" + fileName;
			File folder = new File(path);
			//해당 디렉토리 확인
			if(!folder.exists()){ try{ folder.mkdirs();
			// 폴더 생성
			}catch(Exception e){ e.getStackTrace(); } }
			out = new FileOutputStream(new File(ckUploadPath));
			out.write(bytes);
			out.flush(); // outputStram에 저장된 데이터를 전송하고 초기화
			String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			String fileUrl = "/board/ckImgSubmit?uid=" + uid + "&fileName=" + fileName;
			// 작성화면
			// 업로드시 메시지 출력
			printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
			printWriter.flush();
			}catch(IOException e){ e.printStackTrace(); } finally {
				try {
					if(out != null) { out.close(); }
					if(printWriter != null) { printWriter.close(); } }
				catch(IOException e) { e.printStackTrace(); } }
		return; }


	@RequestMapping(value="/ckImgSubmit")
	public void ckSubmit(@RequestParam(value="uid") String uid , @RequestParam(value="fileName") String fileName , HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//서버에 저장된 이미지 경로
		String path = "C:\\Users\\cspi\\git\\Study_Board\\MyPage\\src\\main\\webapp\\resources\\" + "ckUpload/";
		String sDirPath = path + uid + "_" + fileName;
		File imgFile = new File(sDirPath); //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
		if(imgFile.isFile()){
			byte[] buf = new byte[1024];
			int readByte = 0;
			int length = 0;
			byte[] imgBuf = null;
			FileInputStream fileInputStream = null;
			ByteArrayOutputStream outputStream = null;
			ServletOutputStream out = null;
			try{ fileInputStream = new FileInputStream(imgFile);
			outputStream = new ByteArrayOutputStream();
			out = response.getOutputStream();
			while((readByte = fileInputStream.read(buf)) != -1){
				outputStream.write(buf, 0, readByte); }
			imgBuf = outputStream.toByteArray();
			length = imgBuf.length;
			out.write(imgBuf, 0, length);
			out.flush();
			}catch(IOException e){
				 }finally {
					 } } }



	}








