package io.github.funchcode.fcfs.web.subject.dto;

import java.time.LocalDateTime;
import java.time.YearMonth;

public record SubjectRegisterRequest(
        int limitedQuantityOf,
        DateTime openDateTime,
        DateTime deadlineDateTime
) {

    public SubjectRegisterRequest {
        if (openDateTime == null || deadlineDateTime == null) {
            throw new IllegalArgumentException("opendate, deadlinedate null 요청");
        }
    }

    public record DateTime(int year, int month, int day, int hour, int minute) {

        /**
         * @param year 오늘 기준 내년까지 입력 가능
         * @param month 1-12까지 입력 가능
         * @param day 1-현재 달에서 허용하는 day까지 가능
         * @param hour 0-23까지 입력 가능
         * @param minute 0-59까지 입력 가능
         */
        public DateTime {
            LocalDateTime today = LocalDateTime.now();

            if (year < today.getYear() || year > today.getYear() + 1) {
                throw new IllegalArgumentException("연도(year)는 현재 날짜 기준 1년 후까지 입력 가능");
            }
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("월(month)은 1부터 12까지 입력 가능");
            }
            int endDayOfMonth = YearMonth.of(year, month).atEndOfMonth().getDayOfMonth();
            if (day < 1 || day > endDayOfMonth) {
                throw new IllegalArgumentException(String.format("일(day)은 1부터 %d까지 입력 가능", endDayOfMonth));
            }
            if (hour < 0 || hour > 23) {
                throw new IllegalArgumentException("시(hour)는 0부터 23까지 입력 가능");
            }
            if (minute < 0 || minute > 59) {
                throw new IllegalArgumentException("분(minute)는 0부터 59까지 입력 가능");
            }

        }

        public LocalDateTime toLocalDateTime() {
            return LocalDateTime.of(year, month, day, hour, minute);
        }
    }

}