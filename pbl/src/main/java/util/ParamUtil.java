package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import domain.Board;
import domain.Reply;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParamUtil {
	public static <T> T get(HttpServletRequest req, Class<T> clazz) {
		
		try {
			T t = clazz.getDeclaredConstructor().newInstance();
//			Method[] methods = clazz.getDeclaredMethods();
//			for(Method m : methods) {
//				log.info(m.getName());
//				if(m.getName().equals("setBno")) {
//					m.invoke(t, 100L);
//				}
//			}
			
			Field[] fields = clazz.getDeclaredFields();
			for(Field f : fields) {
				log.info("{}, {}",f.getType(),f.getName());
				String param = req.getParameter(f.getName());
				if(param != null) {
					f.setAccessible(true);
					Object o = convert(param, f.getType());
					f.set(t, o); 
				}
				
//				if(f.getName().equals("bno")) {
//					f.setAccessible(true); //접근 제한자를 가시설 있게 변경
//					f.set(t, 10L); //필드는 접근 제한자가 private이라 접근 불가능
//				}
			}
			return t;
			
			
//			log.info("{}", t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private static Object convert(String param, Class<?> type) {
		//int
		if(type == int.class || type == Integer.class) return Integer.parseInt(param);
		//long
		if(type == long.class || type == Long.class) return Long.parseLong(param);
		//boolean
		if(type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(param);
		//enum
		if(type.isEnum()) return Enum.valueOf(type.asSubclass(Enum.class), param.toUpperCase());
		//String		
		return param;
	}
	
//	public static void main(String[] args) {
////		Reply b = get(null, Reply.class);
//		Board b = get(null, Board.class);
//		log.info("{}",b);
//	}
}
