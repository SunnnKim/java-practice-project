package dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeApi {
    public static void main(String[] args) {
        /**
         * ------------ 기계용 시간
         * Java 8버전 이후로 Date 클래스 대신 쓸 수 있는 api 들이 추가됨
         * Instant : 그리니치 기준시 (UTC, GMT)
         * ZonedDateTime : 타임존 기준의 시간
         * ------------ 휴먼 타임
         * LocalDateTime : 로컬타임기준(서버시간으로 찍히므로 클라우드 서버 사용하는 경우 주의필요)
         * LocalDate
         *
         */

        Instant instant = Instant.now(); // UTC, GMT
        System.out.println(instant);
        System.out.println(instant.atZone(ZoneId.systemDefault()));

        LocalDateTime ldt = LocalDateTime.now(); // 로컬타임
        LocalDateTime ofTime =
                LocalDateTime.of(2022,1,1,20,20,00); // of : 시간설정가능
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        System.out.println(ldt);
        System.out.println(ofTime);
        System.out.println(nowInKorea);

        /***
         * 기간비교
         * Period : 두 시간사이의 기간에 대한 기능
         *
         */
        // 두 날짜 간격 구하기 1
        LocalDate today = LocalDate.now();
        LocalDate bday = LocalDate.of(2022, 6, 10);
        Period dday = Period.between(today, bday);
        System.out.println("Birthday D-Day : " + dday.getDays());
        // 두 날짜 간격 구하기
        Period until = today.until(bday);
        System.out.println("Birthday D-Day : " + until.get(ChronoUnit.DAYS));
        /**
         * Duration : 머신용 시간비교
         */
        Instant now = Instant.now();
        Instant plus = now.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, plus);
        System.out.println(between.getSeconds());

        /**
         * DateTimeFormatter
         * 시간 출력시 특정 포맷 지정가능
         */
        LocalDateTime dateTime3 = LocalDateTime.now();
        System.out.println(dateTime3.format(DateTimeFormatter.ISO_DATE_TIME)); // 기존에 정의된 타입
        System.out.println(dateTime3.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))); // 신규패턴 작성가능

        /**
         * parse
         *
         */
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate parsingDate = LocalDate.parse("2022/07/20", yyyyMMdd);
        System.out.println(parsingDate);

    }

}
