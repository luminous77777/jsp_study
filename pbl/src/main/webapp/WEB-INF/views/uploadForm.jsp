<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
			<div class="d-grid my-2 attach-area">
				<label class="btn btn-info">파일 첨부 <input type="file" multiple class="d-none" id="f1"></label>
				<ul class="list-group my-3 attach-list">
				  <li class="list-group-item d-flex align-items-center justify-content-between"><span>Active item</span> <i class="fa-regular fa-circle-xmark float-end text-danger"></i></li>
				  <li class="list-group-item d-flex align-items-center justify-content-between"><span>Second item</span> <i class="fa-regular fa-circle-xmark float-end text-danger"></i></li>
				  <li class="list-group-item d-flex align-items-center justify-content-between"><span>Third item </span><i class="fa-regular fa-circle-xmark float-end text-danger"></i></li>
				</ul>
				<div class="row justify-content-around w-75 mx-auto attach-thumb">
					<div class="my-2 col-12 col-sm-4 col-lg-2"><div style="height: 150px" class="my-2 bg-primary"><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-12 col-sm-4 col-lg-2"><div style="height: 150px" class="my-2 bg-info"><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-12 col-sm-4 col-lg-2"><div style="height: 150px" class="my-2 bg-warning"><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-12 col-sm-4 col-lg-2"><div style="height: 150px" class="my-2 bg-secondary"><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-12 col-sm-4 col-lg-2"><div style="height: 150px" class="my-2 bg-success"><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
				</div>
			</div>
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
						let str = "";
						for(let a in data){
							// $(".container").append("<p>" + data[a].origin +"</p>");	
							str += `<li class="list-group-item d-flex align-items-center justify-content-between"
								data-uuid="\${a.uuid}"
								data-origin="\${a.origin}"
								data-image="\${a.image}"
								data-path="\${a.path}"
								data-odr="\${a.odr}"
							>
								<a href="${cp}/download">\${a.origin}</a>
								<i class="fa-regular fa-circle-xmark float-end text-danger"></i>
							</li>`				
						}
						$(".attach-list").html(str);
						
						//이미지인 경우와 아닌 경우의 구별 필요
						//첨부한 파일을 지우는 기능 필요
						//이미지인경우 썸네일을 보여주어야함
					}
				})
			})
		})
	</script>
</body>
</html>