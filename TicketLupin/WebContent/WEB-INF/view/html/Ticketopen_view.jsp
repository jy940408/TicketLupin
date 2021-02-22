<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>티켓 오픈 상세</title>
		<script src="<%=request.getContextPath() %>/js/Ticketopen_view.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/Ticketopen_view.css">
	</head>
	<body>
		<header>
			<div id="h_title">
				<div id="h_title_inner">
					<span id="h_top_menu">
						<ul id="h_top_menu_ul">
							<li><a href="#">로그인&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">회원가입&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">고객센터&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">이용안내&nbsp;&nbsp;&nbsp;&nbsp;</a></li><br>
						</ul>
						<img src="../icon/musicalads.png" id="h_ads">
					</span>
					<img src="../icon/lupinlogo.png" id="h_logo">&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" id="h_search" placeholder="뮤지컬 〈캣츠〉 40주년 내한공연 앙코르－서울（Musical CATS Encore">
					<button type="submit" id="h_search_button"><img src="../icon/search.png" id="h_search_img"></button>
				</div>
			</div>
		</header>
		<hr id="nav_bar_top">
		<div id="n_nav_div">
			<nav id="main_nav">
				<a href="#" id="n_home">홈</a>
				<a href="#">공연</a>
				<a href="#">랭킹</a>
				<a href="#">티켓</a>
				<a href="#">오픈공지</a>
				<a href="#">이벤트</a>
				<a href="#">마이 티켓</a>
			</nav>
		</div>
		<hr id="nav_bar_bottom">
		<section>
			<div class="view">
				<div class="view_top">
					<img src="../poster/montecristo.jpg">
					<div class="view_top_info">
						<span>
							뮤지컬 〈몬테크리스토〉 10주년 기념 공연 5차 티켓 오픈 안내
						</span>
						<input type="text" id="url" class="url_input" size="35" />
						<button class="url_btn" onclick="urlClipCopy()">
							<img src="../icon/url.png" class="url_btn_img">
						</button>
						<dl class="register_info">
							<dt class="dt1">
								등록일
							</dt>
							<dd class="dd1">
								2021.01.13
							</dd>
							<dt class="dt2">
								|&nbsp;&nbsp;&nbsp;조회
							</dt>
							<dd class="dd2">
								1,564
							</dd>
						</dl>
						<div class="modify">
							<button class="remove_btn">
								제거하기
							</button>
							<button class="modify_btn">
								수정하기
							</button>
						</div>
					</div>
				</div>
				<div class="view_middle">
					<h3 style="font-size:20px;">
						티켓오픈일정
					</h3>
					<div class="view_middle_info">
						<span style="font-size:18px; display:block">
							뮤지컬 〈몬테크리스토〉 10주년 기념 공연
						</span>
						<div style="font-size:15px; margin-top:10px; font-family:initial;">
							티켓오픈 : 2021년 1월 21일 (목) 14:00
						</div>
						<div class="view_middle_btn">
							<button class="dib_btn">
								찜하기
							</button>
							<button class="detail_btn">
								상세보기
							</button>
						</div>
					</div>
				</div>
				<div class="view_bottom">
					<h3 style="font-size:20px">
						기본정보
					</h3>
					<div class="data_txt">
						<p>&nbsp;</p>
						<p>
							<span style="font-size: 11pt;">
								LG아트센터, 티켓루팡, 인터파크 : 예매처별 좌석이 구분되어 오픈 됩니다. 예매페이지 상세정보를 참조바랍니다.
							</span>
						</p>
						<p>
							<br>
						</p>
						<p>
							<span style="font-size: 11pt;">
								※ 뮤지컬 &lt;몬테크리스토&gt;는 티켓 배송 서비스를 진행하지 않으며, 공연 당일 매표소에서 현장 수령만 가능합니다.
							</span>
						</p>
						<span style="font-size: 11pt;">
							※ 할인 정보와 일자별 캐스팅 및 스케줄은 별도 공지되며,&nbsp;
						</span>
						<p>
							<br>
						</p>
						<p>
							<span style="font-size: 11pt;">
								- 공연명 : 뮤지컬 &lt;몬테크리스토&gt; 10주년 기념 공연 ( Musical Monte Cristo 10th Anniversary )
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								- 공연장 : LG아트센터
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								- 공연기간 : 2020.11.17 (화) ~ 2021.3.7 (일)
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								- 공연시간 : 월화목 7시 / 수금 3시, 8시 / 토 2시, 7시 / 일 3시&nbsp;
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								( 단, 일자별 상이한 회차가 있으니 예매시 반드시 확인 )
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								- 등급 및 가격&nbsp;</span>
							</p>
						<p>
							<span style="font-size: 11pt;">
								월화수목&nbsp; &nbsp;VIP석 14만원, R석 12만원, S석 9만원, A석 6만원
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								금토일공&nbsp; &nbsp;VIP석 15만원, R석 13만원, S석 10만원, A석 7만원
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<br>
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									* 5차 티켓 오픈 : 2021년 1월 21일 (목) 오후 2시
								</b>
							</span>
						</p>
						<p>
							<b>
								<span style="font-size: 14.6667px;">
									*
								</span>
								<span style="font-size: 14.6667px;">
									&nbsp;
								</span>
								<span style="font-size: 11pt;">
									오픈 회차 : 2021년 2월 8일 (월) ~ 2021년 2월 28일 (일)&nbsp;
								</span>
							</b>
						</p>
						<div>
							<br>
						</div>
					</div>
					<h3 style="font-size:20px">
						공연소개
					<h3>
					<div class="concert_info_txt">
						<p>
							<b>
								<span style="font-size: 11pt;">
									레전드 오브 레전드 몬테크리스토의 귀환!
								</span>
							</b>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									뮤지컬 〈몬테크리스토〉
								</b>
							</span>
							<span style="font-size: 11pt;">
								<b>
									10주년 기념공연!
								</b>
							</span>
						</p>
						<p>
							<br>
						</p>
						<p>
							<span style="font-size: 11pt;">
								2010년 95%, 2016년 94% 경이로운 객석점유율 기록!&nbsp;
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								대한민국 15개 도시, 45만 관객 사로잡은 스테디셀러 뮤지컬!
							</span>
						</p>
						<p>
							<br>
						</p>
						<p>
							<span style="font-size: 11pt;">
								전세계 배급권 획득 후 첫 한국 공연이자 10주년 기념공연!
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								한국 라이선스 뮤지컬의 새로운 기준을 제시하다!
							</span>
						</p>
						<p>
							<b>
								<br>
							</b>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									10년 노하우 집결된 최고의 시즌 예고!
								</b>
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									클래시컬한 웅장함과 새로운 호흡으로
								</b>
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									놀랍도록 스타일리시하게 업그레이드된 무대!
								</b>
							</span>
						</p>
						<p>
							<br>
						</p>
						<p>
							<span style="font-size: 11pt;">
								클래시컬한 웅장함과 새로운 호흡으로 스타일리시하게 탈바꿈한 무대가 온다!
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								한국인이 가장 사랑하는 뮤지컬 작곡가 프랭크 와일드혼의 중독성 강한 드라마틱한 음악!
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								3D영상과 조명 활용한 유기적 무대로 선사하는 특별한 경험!
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								EMK의 10년 노하우가 집결된 새로운 무대로 최고의 시즌을 선사한다!
							</span>
						</p>
						<p>
							<b>
								<br>
							</b>
						</p>
						<p>
							<b>
								<span style="font-size: 11pt;">
									10주년 기념공연에 걸맞은&nbsp;
								</span>
								<span style="font-size: 11pt;">
									&nbsp;
								</span>
							</b>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									전설적 조합으로 돌아온 최강 캐스팅!
								</b>
							</span>
						</p>
						<p>
							<span style="font-size: 11pt;">
								<b>
									엄기준-카이-신성록-옥주현-린아-이지혜!
								</b>
							</span>
						</p>
						<p>
							<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						몬테크리스토 백작역에 엄기준, 카이, 신성록!
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						메르세데스 역에 옥주현, 린아, 이지혜&nbsp;
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						최민철, 김준현, 강태을, 이종문, 문성혁, 최성원, 임별, 이상준, 이한밀,
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						김영주, 전수미, 박준휘, 신재범, 윤조, 임예진, 최지혜까지!
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						관객들이 열망하는 꿈의 캐스팅 라인업!&nbsp;
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						[시놉시스]
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						<br>
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						촉망 받는 젊은 선원 에드몬드 단테스는
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						아름다운 메르세데스와 결혼을 앞두고 있지만,&nbsp;
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						그녀를 흠모하는 몬데고와 선장자리를 차지하려는 당글라스,&nbsp;
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						정치적 야심을 가진 빌포트 검사장의 모함과 음모로&nbsp;
						</b>
						</span>
						</p>
						<p>
						<b style="font-size: 11pt;">
						<span style="font-size: 11pt;">
						억울한 누명을 쓰고 악명 높은 감옥 섬 샤또 디프에 갇히게 되는데 ···
						</span>
						</b>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						억울한 누명 때문에 장밋빛 인생이 한 순간에 악몽으로 뒤바뀐 에드몬드는
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						악명 높은 감옥섬인 샤또 디프에서 14년간 갇혀 지낸다.
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						우연히 감옥에서 만난 괴짜 신부 파리아는 자신의 탈옥 계획에 에드몬드를 끌어들이고
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						그의 정신적 멘토가 되어 경제, 외국어, 정치, 검술 등을 가르치며&nbsp;
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						몬테크리스토 섬의 보물에 대해 알려준다.
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						그러나 탈옥 과정에서 심하게 부상을 입은 파리아 신부는
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						미움과 복수는 결국 에드몬드의 삶을 파멸시킬 것이라는 말을 남기고 세상을 떠난다.
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						탈출에 성공한 뒤 몬테크리스토 섬에서 끝없이 펼쳐진 보물을 발견한 에드몬드는
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						엄청난 부를 손에 쥐게 되고, 자신의 신분을 ‘몬테크리스토 백작’으로 바꾼 후
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						자신에게 누명을 씌운 자들을 찾아가 복수를 결심하는데 ···.
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						[캐릭터 소개]
						</b>
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						에드몬드 단테스 Edmond Dantes / 몬테크리스토 백작 Monte Cristo : 엄기준, 카이, 신성록
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						전도유망했던 젊은 선원. 14년의 억울한 감옥살이 후&nbsp;
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						복수심으로 가득 찬 ‘몬테크리스토 백작’이 되어 세상에 나타난다.&nbsp;
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						메르세데스 Mercedes : 옥주현, 린아, 이지혜
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						에드몬드의 사랑하는 연인. 감옥으로 끌려간 에드몬드를 기다리지만 그녀를 흠모해온 몬데고의 거짓말에 속아 그와 결혼한다.
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						몬데고 Mondego : 최민철, 김준현, 강태을
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						메르세데스를 차지하기 위해 용서받지 못할 악행을 저지르는 에드몬드의 친구
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						파리아 신부 Abbe Faria : 문성혁, 이종문
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						에드몬드가 감옥에서 만난 스승이자 은인.
						</span>
						</p>
						<p>
						<b>
						<br>
						</b>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						빌포트 Villefort : 최성원, 임별
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						자신의 정치적 야망을 위해 불의를 저지르는 검사장.
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						당글라스 Danglars : 이상준, 이한밀
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						몬데고와 공모해 에드몬드를 위험한 계략에 빠뜨리는 성공과 돈을 좇는 야심가.
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						루이자 Louisa : 김영주, 전수미
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						몬테크리스토 백작이 된 에드몬드를 도와주는 해적선의 선장.
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						알버트 Albert : 신재범, 박준휘
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						메르세데스의 아들이자 몬테크리스토 백작을 우상으로 여기는 모험심 강한 청년.
						</span>
						</p>
						<p>
						<br>
						</p>
						<p>
						<span style="font-size: 11pt;">
						<b>
						발렌타인 Valentine : 윤조, 임예진, 최지혜
						</b>
						</span>
						</p>
						<p>
						<span style="font-size: 11pt;">
						알버트의 약혼녀이자 빌포트 검사장의 매력적인 딸.
						</span>
						</p>
						<br/>
						</div>
					<h3 style="font-size:20px">
						할인정보
					</h3>
					<div class="notice_price">
						<p><span style="font-size: 11pt;">-조기예매 할인: 전석 20% (1인4매) / 3월 16일~4월 4일 공연 (~2월14일 예매시까지)</span></p><p><span style="font-size: 11pt;">-재관람 할인: 전석 30% (1인2매) / 3월 3일~4월 4일 공연</span></p><p><span style="font-size: 11pt;">-학생 할인: S, A석 30% (본인 한정) / 3월 3일~4월 4일 공연</span></p><p><span style="font-size: 11pt;">* 단체문의: 클립서비스 02-3496-8880</span></p><div><br></div></div>
					<h3 style="font-size:20px">
						기획사 정보
					</h3>
					<div class="txt" style="margin-bottom:100px">                                
						제 작 : ㈜EMK뮤지컬컴퍼니
						<br>주 최 : ㈜카카오, SBS
						<br>주 관 : ㈜EMK인터내셔널
						<br>문 의 : LG아트센터 02-2005-0114 / 티켓루팡 1899-0042
						<br>
						<br>* 단체예약 : 02-6391-6333 ( 20인이상, 동일회차/동일등급 관람시 )
						<br>* 예매 및 공연 문의 : 티켓루팡 1899-0042
						<br>- 홈페이지 : http://www.musicalmonte.com/ 
						<br>- 인스타그램 : https://www.instagram.com/emk_musical/
						<br>- 페이스북 : https://www.facebook.com/emkmusical
						<br>- 카카오 플러스친구 : http://pf.kakao.com/_WGfxmxl
						<br>- 네이버 포스트 : https://post.naver.com/emk_musical
						<br>- 유튜브 : https://www.youtube.com/emkmusical
					</div>
					<button class="list_btn">
						목록으로
					</button>
				</div>
			</div>
		</section>
		<footer>
			<div class="footer">
				<hr class="main_bar" id="main_bar_bottom">
					<div id="main_last">
						<span class="main_bottom_ment"><img src="../icon/lupinlogo.png" id="main_logo"></span>
						<span class="main_bottom_ment">
							<span class="main_bottom_tagset">예매문의(1234-1234)</span>
							<a href="#" class="main_bottom_tagset">티켓판매제휴&nbsp;&nbsp;&nbsp;&nbsp;</a>
							<a href="#" class="main_bottom_tagset">예매가이드&nbsp;&nbsp;&nbsp;&nbsp;</a>
						</span>
					</div>
				<hr class="main_bar" id="main_bar_bottom">
				<div class="f_title_inner">
					<span id="f_menu_contract_span">
						<ul id="f_menu_contract">
							<li><a href="#">회사소개</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">이용약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">전자금융거래약관</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">위치기반서비스 이용약관&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#" style="font-weight:bold;">개인정보처리방침</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">청소년보호정책</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">파트너센터</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
						</ul>
					</span>
				</div>
				<div class="f_title_inner">
					<span id="f_menu_produce">
						<ul id="f_menu_produce">
							<li><a href="#" style="font-weight:bold;">(주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">전북 전주시 덕진구 백제대로 572 4층</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">대표이사:최민우</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">사업자등록번호: 111-11-11111&nbsp;&nbsp;|&nbsp;&nbsp;</a></li>
							<li><a href="#">통신판매업 신고번호: 제1111-이젠이젠-1111호 <a href="#" style="font-weight:bold;">&nbsp;&nbsp;사업자정보확인>&nbsp;&nbsp;</a></a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
							<li><a href="#">고객센터(평일/주말 09:00~18:00): 1234-1234(유료)</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">호스팅제공자: (주)티켓루팡</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
							<li><a href="#">&copy;TicketLupin Corp. All rights reserved.</a>&nbsp;&nbsp;&nbsp;&nbsp;</li><br>
						</ul>
					</span>
				</div>
			</div>
		</footer>
	</body>
</html>