package schedule;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import controller.attach.UploadFile;
import domain.Attach;
import lombok.extern.slf4j.Slf4j;
import mapper.AttachMapper;
import util.MybatisUtil;

@Slf4j
public class GhostFileCleanupJob implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//File 인스턴스 생성 > 어제 날짜의 업로드 폴더
//		final String UPLOAD_PATH = "D:/upload/files";
		File file = new File(UploadFile.UPLOAD_PATH, genYesterdayPath());
		log.info("{}, {}", file, file.exists());
		
		if(!file.exists() || !file.isDirectory()) {
			return;
		}
		
//		String[] strings = file.list();
//		File[] files = file.listFiles();
//		log.info("{}",Arrays.toString(files));

//		List<String> fsList = new ArrayList<String>(List.of("a.txt", "b.txt", "c.txt"));
//		List<String> dbList = new ArrayList<String>(List.of("b.txt", "c.txt"));
//		fsList.removeAll(dbList);
		
//		List<File> fsListFiles = new ArrayList<>(List.of(new File("10/a.txt"), new File("b.txt")));
//		List<File> dbListFiles = new ArrayList<>(List.of(new File("10/a.txt")));
		
		List<File> fsListFiles = new ArrayList<>(Arrays.asList(file.listFiles()));  // as 리스트로 생성한 리스트는 이뮤터블 리스트, 길이가변한다
		SqlSession session = MybatisUtil.getSqlSession();
		
		//현재의 이유
		//dbListFile에는 thumbnail파일에 대한 추가가 되지않음
		// 이미지 파일2개 일반 팡리 1개로 구성된 총 3개의 attach라면
		List<Attach> attachs = new ArrayList<>(session.getMapper(AttachMapper.class).selectYesterdayList());
		log.info("============attachs ==============");
		attachs.forEach(a ->log.info("{}, ", a));
		
		List<Attach> thumbs = new ArrayList<>(attachs).stream().filter(Attach::isImage).map(Attach::toThumb).toList();
		attachs.addAll(thumbs);
		log.info("============thumbs ==============");
		thumbs.forEach(a ->log.info("{}, ", a));
		// 이미지파일 2개 + 섬네일2개 + 일반파일1개로 구성된 총 5개의 attachlist로 변경되어야함
//		attachs.addAll(thumbs);
		// 체이닝을 통해 헌꺼번어 처리하기보단 List<Attach>상태에서 추가 작업후, 추후에 List<File>로 변경하길 추천
		log.info("============dbListFiles ==============");
		List<File> dbListFiles = attachs.stream().map(Attach::toFile).toList();
		dbListFiles.forEach(a ->log.info("{}, ", a));
//		attachMapper.selectYesterdayList();
		
//		log.info("파일:{}",attachMapper.selectYesterdayList());
//		List<Attach> yesterFile = attachMapper.selectYesterdayList();
		
//		List<File> dbListFiles =  session.getMapper(AttachMapper.class).selectYesterdayList().stream().map(Attach::toFile).toList(); // 리스트 파일 타입이출력됨, 빼기 위한리스트이므로, 길이가 변하지 않아도 괜찮다
		
		session.close();
		
//		List<File> dbListFiles = new ArrayList<>(List.of(new File("10/a.txt")));
		
		fsListFiles.removeAll(dbListFiles);
		
		log.info("{}", fsListFiles);
		fsListFiles.forEach(f -> f.delete());
	}
	
	private String genYesterdayPath() {
		return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime() - 1000 * 60 * 60 * 24);
	}
	
	public static void main(String[] args) throws Exception {
		new GhostFileCleanupJob().execute(null);
	}

}
	
