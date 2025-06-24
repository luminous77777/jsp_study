<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
	<div class="container p-0">
        <main>
            <form method="post">
                <div class="small border-bottom border-3 border-primary p-0 pb-2">
                    <a href="#" class="small"> <span class="test-primary">자유게시판</span><span class="text-dark">카테고리</span></a>
                </div>
                <div class="small p-0 py-2">
                    <input placeholder="title" class="form-control" name="title" id="title">
                </div>
                <div class="p-0 py-5 ps-1 small border-bottom border-1 border-muted">
                    <textarea name="content" id="editor1" class="form-control resize-none"></textarea>
                </div>
                
                <div class="d-grid my-2 attach-area">
                	<div class="small my-1"><i class="fa-solid fa-paperclip"></i>첨부파일</div>
					<label class="btn btn-info">파일 첨부하시려면 클릭 <input type="file" multiple class="d-none" id="f1"></label>
				</div>
                
                <div class="my-2">
                    <button class="btn btn-secondary btn-sm"> <i class="fa-solid fa-list-ul"></i> 목록</button>
                    <div class="float-end">
                        <button class="btn btn-outline-primary btn-sm"><i class="fa-solid fa-pen-fancy"></i>글쓰기</button>
                    </div>
                </div>
                <input type="hidden" name="id" value="${member.id}" />
                <input type="hidden" name="cno" value="2" />
            </form>
        </main>
    </div>
    <script>
    
	$(function() {

		//return  true/false
		function validateFiles(files){
			const MAX_COUNT = 5;
			const MAX_FILE_SIZE = 10 * 1024 * 1024; //10MB
			const MAX_TOTAL_SIZE = 50 * 1024 * 1024; //50MB
			const BLOCK_EXT = ['exe', 'sh', 'jsp', 'java','class', 'html','js'];

			if(files.length > MAX_COUNT){
				alert('파일은 최대 5개만 업로드 가능합니다');
				return false;
			}
			//split 문자열 배열로 쪼개짐
			let totalSize = 0;
			const isValid = files.every(f => {
				const ext = f.name.split(".").pop().toLowerCase();
				totalSize += f.size; //확장자 추출
				return !BLOCK_EXT.includes(ext) && f.size <= MAX_FILE_SIZE; //->every때문에 단 하나라도 false 라면 false 리턴
			}) && totalSize <= MAX_TOTAL_SIZE;
			if(!isValid){
				alert('다음 파일 확장자는 업로드가 불가 합니다 [exe, sh, jsp, java,class, html,js] \n 또한 각 파일은 10MB이하 전체합계는 50MB이하여야합니다')
			}
			return isValid;
		}

		$("#f1").change(function() {			
		//$("#uploadForm").submit(function() {
			event.preventDefault();
			const formData = new FormData();
			console.log(formData);
			
			const files = this.files;
			for(let i = 0; i < files.length; i++){
				formData.append("f1",files[i]);
			}
			
			const valid = validateFiles([...files])
			if(!valid){
				return;
			}
		
			// const size = files.length;
			// if(size > 5) return false;     const files = this.f1.files;  // 유사배열임
			// const filesArr = [...files]; // 유사배열을 진짜 배열로 변환
			
			
			
			
			
			//파일의 갯수, 파일당 크기, 파일들 전체의 총량, 파일 확장자 제한
			// for(let i = 0; i < files.length ; i++){
			// 	console.log(files[i].name);
			// 	console.log(files[i].size)
			// }
			// return;
			
			$.ajax({
				url : '${cp}/upload',
				method : 'POST',
				data : formData,
				processData : false, //data를 queryString으로 쓰지 않겠다
				contentType : false, //multipart/form-data; 이후에 나오게될 브라우저 정보도 포함시킨다. 즉, 기본 브라우저 설정을 따르는 옵션
				success : function(data){
					console.log(data);
					//확인용
					for(let a in data){
						$(".container").append("<p>" + data[a].origin +"</p>");					
					}
				}
			})
		})
	})

        
        window.onload = function(){
            CKEDITOR.replace('editor1', {
                height:400
            })
        }
        
    </script>
<%@ include file="../common/footer.jsp" %>

</body>
</html>