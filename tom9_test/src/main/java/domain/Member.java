package domain;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class Member {
	private final Long num;
	private final String id;
	private final String pw;
	private final String name;
	private final Date regDate;
}
